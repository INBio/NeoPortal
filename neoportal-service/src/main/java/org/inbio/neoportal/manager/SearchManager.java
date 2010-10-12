/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.manager;

import java.util.List;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.dto.OcurrenceLiteDTO;


/**
 *
 * @author asanabria
 */
public interface SearchManager {

    public List<OcurrenceLiteDTO> fullSearch(String searchText) throws ParseException;
    public Integer fullSearchCount(String searchText) throws ParseException;

}
