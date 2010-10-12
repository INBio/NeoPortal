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
package org.inbio.neoportal.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.dto.OcurrenceLiteDTO;
import org.inbio.neoportal.manager.SearchManager;
import org.inbio.neoportal.messagebean.SpecimenLiteBean;
import org.inbio.neoportal.messagebean.XMLResponseWrapperBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *@autor jgutierrez
 */
@Controller
@RequestMapping("search/*")
public class SearchController {

    @Autowired
    private SearchManager searchManagerImpl;

    @RequestMapping(value="/simple", method=RequestMethod.GET, params={"format=xml","searchString"})
    public @ResponseBody XMLResponseWrapperBean searchSimpleWriteXml(@RequestParam String searchString) {

        List<OcurrenceLiteDTO> occurrenceList = null;

        XMLResponseWrapperBean slwb = new XMLResponseWrapperBean();
        try {
            occurrenceList = searchManagerImpl.fullPaginatedSearch(searchString, 0 ,20);

            for(OcurrenceLiteDTO olDTO : occurrenceList)
                slwb.addElement(new SpecimenLiteBean(
                olDTO.getCatalogNumber(),
                olDTO.getInstitutionCode(),
                olDTO.getScientificName(),
                olDTO.getLatitude(),
                olDTO.getLongitude()));

        } catch (ParseException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return slwb;
    }

    public SearchManager getSearchManagerImpl() {
        return searchManagerImpl;
    }

    public void setSearchManagerImpl(SearchManager searchManagerImpl) {
        this.searchManagerImpl = searchManagerImpl;
    }
}
