/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
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
package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.dto.transformers.TaxonTransformer;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Implementantion of TaxonDAO that grant access to the Taxon information.
 * @author asanabria
 */
@Repository
public class TaxonDAOImpl 
    extends GenericBaseDAOImpl<Taxon, BigDecimal> 
        implements TaxonDAO{
    
    
    @Override
    public List<TaxonLiteCDTO> search
        (final String searchText, final int offset, final int quantity) {
        
       String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species",
                                "taxonomicalRangeId", "commonNames.name"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));

        return super.search(Taxon.class,
                            new TaxonTransformer(), 
                            fieldList.toArray(new String[fieldList.size()]), 
                            searchText, 
                            offset, 
                            quantity);
       
    }

    @Override
    public Long searchCount(final String searchText) {
        
        String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species",
                                "taxonomicalRangeId", "commonNames"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));
        
        return super.searchCount(Taxon.class, 
                                new TaxonTransformer(), 
                                fieldList.toArray(new String[fieldList.size()]), 
                                searchText);
    }

    @Override
    public List<TaxonLiteCDTO> searchBoost(
            final String searchText, 
            final int offset, 
            final int quantity) {
        

        HibernateTemplate template = getHibernateTemplate();

        return (List) template.execute(new HibernateCallback() {
                
            @Override
            public Object doInHibernate(Session session) {

                String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species",
                                "taxonomicalRangeId", "commonNames"};

                ArrayList<String> fieldList = new ArrayList<String>();

                fieldList.addAll(Arrays.asList(taxon));
                
                Query query = null;
                FullTextSession fullTextSession = 
                    Search.getFullTextSession(session);
                
                HashMap<String,Float> boosts = new HashMap<String,Float>();
                    boosts.put("defaultName", 5f);
                
                // create native Lucene query
                MultiFieldQueryParser parser = 
                        new MultiFieldQueryParser(Version.LUCENE_33, 
                            fieldList.toArray(new String[fieldList.size()]),
                            new StandardAnalyzer(Version.LUCENE_33),
                            boosts);


                //FIXME Manejo de errores
                try {
                    
                    query = parser.parse(searchText);
                    
                } catch (ParseException ex) {
                    
                    Logger.getLogger(Taxon.class.getName())
                        .log(Level.SEVERE, null, ex);
                    
                    return null;
                }
                
                // Wrap Lucene query in a org.hibernate.Query
                org.hibernate.Query hsQuery =
                        (org.hibernate.Query) fullTextSession.createFullTextQuery(
                            query, Taxon.class);

                // Configure the result list
                hsQuery.setResultTransformer(new TaxonTransformer());
                hsQuery.setFirstResult(offset);
                hsQuery.setMaxResults(quantity);
                
                
                return hsQuery.list();
            }
        });
    }
}
