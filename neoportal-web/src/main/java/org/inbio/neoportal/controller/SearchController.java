package org.inbio.neoportal.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Qualifier;
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
            occurrenceList = searchManagerImpl.fullSearch(searchString);

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
