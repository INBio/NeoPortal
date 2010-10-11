/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.manager;

import java.util.List;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.dto.MinimalOccurrenceInfoDTO;


/**
 *
 * @author asanabria
 */
public interface SearchManager {


    public void searchByTaxon(String searchText) throws ParseException;
    public void searchByLocality(String searchText) throws ParseException;
    public void searchByArea(String searchText) throws ParseException;
    public List<MinimalOccurrenceInfoDTO> searchByAll(String searchText) throws ParseException;
}
