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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.DuplicateFilter;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.dao.impl.DwCDAOImpl;
import org.inbio.neoportal.dto.OcurrenceLiteDTO;
import org.inbio.neoportal.entity.DarwinCore;
import org.inbio.neoportal.util.HibernateUtil;



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
        fullTextSession.createIndexer(DarwinCore.class).startAndWait();
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

        if(searchType.equals("taxon"))
            this.searchByTaxon(searchText);
        else if (searchType.equals("locality"))
            this.searchByLocality(searchText);
        else if (searchType.equals("area"))
            this.searchByArea(searchText);
        else if (searchType.equals("all"))
            this.searchByAll(searchText);
        else
            System.out.println("Error: unrecognized option");
}

    /**
     * Search using only the Scientific Name field
     * @param searchText
     * @throws ParseException
     */
    public void searchByTaxon(String searchText)
            throws ParseException{
        String[] fields =
                new String[]{ "scientificname" };
        this.executeSearch(fields, searchText);
    }

    /**
     * Search using only the Locality field
     * @param searchText
     * @throws ParseException
     */
    public void searchByLocality(String searchText)
            throws ParseException{
         String[] fields =
                new String[]{ "locality" };
        this.executeSearch(fields, searchText);

    }

    /**
     * Search using only the country, stateprovince and county fields
     * @param searchText
     * @throws ParseException
     */
    public void searchByArea(String searchText) 
            throws ParseException{

         String[] fields =
                new String[]{   "country",
                                "stateprovince",
                                "county" };
        this.executeSearch(fields, searchText);
    }

    /**
     * Search using all the indexed fields
     * @param searchText
     * @throws ParseException
     */
    public void searchByAll(String searchText) 
            throws ParseException{

        String[] fields =
                new String[]{ "scientificname",
                                "locality",
                                "country",
                                "stateprovince",
                                "county"
                    };
        this.executeSearch(fields, searchText);

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
                    query = parser.parse(searchText);
                } catch (ParseException ex) {
                    Logger.getLogger(DwCDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Wrap Lucene query in a org.hibernate.Query
                org.hibernate.search.FullTextQuery hsQuery = fullTextSession.createFullTextQuery(query, DarwinCore.class);

                // Configure the result list
                hsQuery.setFilter(new DuplicateFilter("scientificname"));
                hsQuery.setResultTransformer(new OccurrenceResultTransformer());


        // for paginated search
        hsQuery.setMaxResults(20);

        int totalAmount = hsQuery.getResultSize();
        // execute search
        System.out.println("#-> Result Count "+ hsQuery.getResultSize());

        List<OcurrenceLiteDTO> result = null;

        // Show the results page by page (20 items each).
        for(int i = 0; i <=totalAmount; ){

            result = hsQuery.list();

            for(OcurrenceLiteDTO res : result)
                System.out.println("#-> "+res.getGlobalUniqueIdentifier()
                                         +" => "+res.getInstitutionCode()
                                         +" : "+res.getScientificName()
                                         +" { "+res.getLocality()+ " }");

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

        String[] localArgs = new String[2];
        localArgs[0] = "all";
        localArgs[1] = "country:Costa_Rica";

        Indexer index = new Indexer();
        //index.processArguments(localArgs);
        index.processArguments(args);

    }

    private class OccurrenceResultTransformer implements ResultTransformer {

        @Override
        public List transformList(List list) {
            List<DarwinCore> dwcList = (List<DarwinCore>) list;
            List<OcurrenceLiteDTO> newList = new ArrayList<OcurrenceLiteDTO>();

            for(DarwinCore dwc: dwcList)
                newList.add(
                    new OcurrenceLiteDTO(dwc.getGlobaluniqueidentifier(),
                                         dwc.getCatalognumber(),
                                         dwc.getInstitutioncode(),
                                         dwc.getScientificname(),
                                         dwc.getDecimallatitude(),
                                         dwc.getDecimallongitude(),
                                         dwc.getLocality()));
            return newList;
        }

        @Override
        public Object transformTuple(Object[] os, String[] strings) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
