/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.manager;

import org.apache.lucene.queryParser.ParseException;


/**
 *
 * @author asanabria
 */
public interface SearchManager {


    public void searchByTaxon(String searchText) throws ParseException;
    public void searchByLocality(String searchText) throws ParseException;
    public void searchByArea(String searchText) throws ParseException;
    public void searchByAll(String searchText) throws ParseException;
}
