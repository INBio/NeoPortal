/*
 *  Copyright (C) 2010 INBio - Instituto Nacional de Biodiversidad, Costa Rica
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.neoportal.index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.inbio.neoportal.core.entity.Occurrence;
import org.inbio.neoportal.index.util.HibernateUtil;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.entity.TaxonDescription;



/**
 *
 * @author asanabria <asanabria@inbio.ac.cr>
 */
public class Indexer {


    public Indexer(){
        super();
    }

    /**
     * Create the Lucene index for the DarwinCore class
     * @throws InterruptedException
     */
    public void createIndex() 
            throws InterruptedException{

        System.out.println("Creating Lucene index\n");
        HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();

        FullTextSession fullTextSession = Search.getFullTextSession(session);

        System.out.println("# - Inicio de la indexación \n");

        System.out.println("# - Taxon");
        fullTextSession.createIndexer(Taxon.class).startAndWait();

        System.out.println("# - TaxonDescription");
        fullTextSession.createIndexer(TaxonDescription.class).startAndWait();

        System.out.println("# - Occurrence");
        fullTextSession.createIndexer(Occurrence.class).startAndWait();

        System.out.println("# - Fin de la indexación \n");
    }

    /**
     * Process the arguments that comes from the console.
     * @param args [action, search terms]
     * @throws ParseException
     * @throws InterruptedException
     */
    public void processArguments(String[] args)
            throws ParseException, InterruptedException{

        String searchType = null;
        String searchText = null;

        if(args.length == 1)
            if (args[0].equals("index")){
                this.createIndex();
                return;
            }

        if(args.length < 2){
            System.out.println("Error: argumentos insuficientes");
            return;
        }

        searchType = args[0];
        searchText = args[1];

        this.searchByAll(searchText);
    }

    /**
     * Search using all the indexed fields
     * @param searchText
     * @throws ParseException
     */
    public void searchByAll(String searchText) 
            throws ParseException{



        String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species"};

        String[] occurrence =
                new String[]{"scientificName", "higherTaxon", "kingdom",
                                 "phylum", "class_", "orders", "family",
                                   "genus", "specificEpithet", "country",
                                     "stateProvince", "county", "locality"};

        String[] taxonDescription =
                new String[]{ "scientificName", "kingdomTaxon", "phylumTaxon",
                             "classTaxon", "orderTaxon", "familyTaxon",
                             "genusTaxon", "synonyms", "commonNames"};

         ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));
        fieldList.addAll(Arrays.asList(occurrence));
        fieldList.addAll(Arrays.asList(taxonDescription));

        this.executeSearch(fieldList.toArray(new String[fieldList.size()]), searchText);

    }

    /**
     * Look for searchText in the index using the <code>fields</code> argument
     * @param fields
     * @param searchText
     * @throws ParseException
     */
    private void executeSearch(String[] fields, String searchText)
            throws ParseException{

        HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();

        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();

        // create native Lucene query
        MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_29, fields, new StandardAnalyzer(Version.LUCENE_29));
        org.apache.lucene.search.Query query = null;

        //FIXME Manejo de errores
        try {
            System.out.println("Buscando: " +searchText);
            query = parser.parse(searchText);
        } catch (ParseException ex) {
            Logger.getLogger(Taxon.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Wrap Lucene query in a org.hibernate.Query
        org.hibernate.search.FullTextQuery hsQuery = fullTextSession.createFullTextQuery(query, Taxon.class);

        // for paginated search
        hsQuery.setMaxResults(20);
        hsQuery.setFirstResult(0);

        int totalAmount = hsQuery.getResultSize();
        // execute search
        System.out.println("#-> Result Count "+ hsQuery.getResultSize());

        List<Taxon> result = null;

        // Show the results page by page (20 items each).
        for(int i = 0; i <=totalAmount; ){

            result = hsQuery.list();
/*
            for(Taxon res : result)
                System.out.println("#-> "+res.getSynonyms()
                                         +" => "+res.getScientificName());
             */
            
            
            for(Taxon res : result)
                System.out.println("#-> "+res.getDefaultName());


            i+=20;
            hsQuery.setFirstResult(i);
            System.out.println("#end page =================================#");
        }
        tx.commit();
        session.close();
    }

    /**
     * Entry point fot the application
     * @param args
     * @throws InterruptedException
     * @throws ParseException
     */
    public static void main(String[] args)
            throws InterruptedException, ParseException{

        //String[] localArgs = new String[2];
        //localArgs[0] = "all";
        //localArgs[1] = "country:Costa_Rica";

        Indexer index = new Indexer();
        //index.processArguments(localArgs);
        index.processArguments(args);

    }
}
