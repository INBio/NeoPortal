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
package org.inbio.neoportal.web.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.service.dto.OccurrenceLiteDTO;
import org.inbio.neoportal.service.manager.SearchManager;
import org.inbio.neoportal.web.messagebean.SpecimenLiteBean;
import org.inbio.neoportal.web.messagebean.XMLResponseWrapperBean;
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

    @RequestMapping(value="/occurrences", method=RequestMethod.GET, params={"format=xml","searchString"})
    public @ResponseBody XMLResponseWrapperBean searchOccurrencesWriteXml(@RequestParam String searchString) {

        List<OccurrenceLiteDTO> occurrenceList = null;

        XMLResponseWrapperBean slwb = new XMLResponseWrapperBean();
        try {
            occurrenceList = searchManagerImpl.fullPaginatedSearch(searchString, 0, 10);

            for(OccurrenceLiteDTO olDTO : occurrenceList)
                slwb.addElement(new SpecimenLiteBean(
                olDTO.getGlobalUniqueIdentifier(),
                olDTO.getScientificName(),
                olDTO.getCountry(),
                olDTO.getProvince(),
                olDTO.getCounty(),
                olDTO.getLocality(),
                olDTO.getLatitude(),
                olDTO.getLongitude(),
                olDTO.getCatalog(),
                olDTO.getInstitution()));

        } catch (ParseException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return slwb;
    }

    @RequestMapping(value="/species", method=RequestMethod.GET, params={"format=xml","searchString"})
    public @ResponseBody XMLResponseWrapperBean searchSpeciesWriteXml(@RequestParam String searchString) {

        List<OccurrenceLiteDTO> occurrenceList = null;

        XMLResponseWrapperBean slwb = new XMLResponseWrapperBean();
        try {
            occurrenceList = searchManagerImpl.speciesListPaginatedSearch(searchString, 0 ,15);

            for(OccurrenceLiteDTO olDTO : occurrenceList)
                slwb.addElement(new SpecimenLiteBean(
                olDTO.getGlobalUniqueIdentifier(),
                olDTO.getScientificName(),
                olDTO.getCountry(),
                olDTO.getProvince(),
                olDTO.getCounty(),
                olDTO.getLocality(),
                olDTO.getLatitude(),
                olDTO.getLongitude(),
                olDTO.getCatalog(),
                olDTO.getInstitution()));

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
