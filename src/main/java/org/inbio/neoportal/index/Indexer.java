/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.index;

import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.entity.DarwinCore;
import org.inbio.neoportal.util.HibernateUtil;
import org.inbio.neoportal.dto.MinimalOccurrenceInfoDTO;



/**
 *
 * @author asanabria
 */
public class Indexer {


    public Indexer(){
        super();
    }

    public void createIndex() throws InterruptedException{
        System.out.println("Creando el <Indice>\n");
        HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();
/*
session.beginTransaction();
        Query q = session.createQuery("From DarwinCore");
        q.setMaxResults(10);
        List resultList = q.list();
        System.out.println("#-> count = "+resultList.size());
session.getTransaction().commit();
*/
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer(DarwinCore.class).startAndWait();

    }

    public void processArguments(String[] args) throws ParseException, InterruptedException{

        String type = null;
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

        type = args[0];
        searchText = args[1];

        if(type.equals("taxon"))
            this.searchByTaxon(searchText);
        else if (type.equals("locality"))
            this.searchByLocality(searchText);
        else if (type.equals("area"))
            this.searchByArea(searchText);
        else if (type.equals("all"))
            this.searchByAll(searchText);
        else
            System.out.println("Error: unrecognized option");
}

    public void searchByTaxon(String searchText) throws ParseException{
        String[] fields =
                new String[]{ "scientificname" };
        this.executeSearch(fields, searchText);
    }

    public void searchByLocality(String searchText) throws ParseException{
         String[] fields =
                new String[]{ "locality" };
        this.executeSearch(fields, searchText);

    }
    public void searchByArea(String searchText) throws ParseException{
         String[] fields =
                new String[]{   "country",
                                "stateprovince",
                                "county" };
        this.executeSearch(fields, searchText);
    }
    public void searchByAll(String searchText) throws ParseException{


        String[] fields =
                new String[]{ "scientificname",
                                "locality",
                                "country",
                                "stateprovince",
                                "county"
                    };
        this.executeSearch(fields, searchText);

    }

    private void executeSearch(String[] fields, String searchText) throws ParseException{

        HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();

        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();

        // create native Lucene query
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
        org.apache.lucene.search.Query query = parser.parse(searchText);

        // wrap Lucene query in a org.hibernate.Query
        // org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, DarwinCore.class);
        org.hibernate.search.FullTextQuery hsQuery = fullTextSession.createFullTextQuery(query, DarwinCore.class);
        hsQuery.setMaxResults(20);
        hsQuery.setResultTransformer(new DwCEntityMapper());

        int totalAmount = hsQuery.getResultSize();
        // execute search
        System.out.println("#-> Result Count "+ hsQuery.getResultSize());

        List<MinimalOccurrenceInfoDTO> result = null;

        for(int i = 0; i <=totalAmount; ){

            result = hsQuery.list();

            for(MinimalOccurrenceInfoDTO res : result)
                System.out.println("#-> "+res.getGui()+ " => "+res.getName()+" : "+res.getLocality());

            i+=20;
            hsQuery.setFirstResult(i);
            System.out.println("#=================================#");
        }
        tx.commit();
        session.close();
    }

    public static void main(String[] args) throws InterruptedException, ParseException{

        System.out.println("args "  +args.length);

        String[] localArgs = new String[2];
        localArgs[0] = "all";
        localArgs[1] = "country:Costa_Rica";

        Indexer index = new Indexer();
        //index.processArguments(localArgs);
        index.processArguments(args);

    }

    private class DwCEntityMapper implements ResultTransformer{

        @Override
        public List transformList(List list) {
            List<DarwinCore> dwcList = (List<DarwinCore>) list;
            List<MinimalOccurrenceInfoDTO> newList = new ArrayList<MinimalOccurrenceInfoDTO>();

            MinimalOccurrenceInfoDTO ro = null;

            for(DarwinCore dwc: dwcList){

                ro = new MinimalOccurrenceInfoDTO();
                ro.setGui(dwc.getGlobaluniqueidentifier());
                ro.setName(dwc.getScientificname());
                ro.setLocality(dwc.getLocality());
                newList.add(ro);
            }

            return newList;
        }

        @Override
        public Object transformTuple(Object[] os, String[] strings) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
