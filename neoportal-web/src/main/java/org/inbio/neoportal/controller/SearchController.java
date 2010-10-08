package org.inbio.neoportal.controller;

import org.inbio.neoportal.messagebean.SpecimenLiteBean;
import org.inbio.neoportal.messagebean.SpecimenLiteWrapperBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("search/*")
public class SearchController {

	/*
	@RequestMapping("/simple")
	public @ResponseBody String simple() {
		return "Hola Mundo!";
	}
	*/

	@RequestMapping(value="/simple", method=RequestMethod.GET, params={"format=xml","searchString"})
	public @ResponseBody SpecimenLiteWrapperBean searchSimpleWriteXml(@RequestParam String searchString) {
		SpecimenLiteWrapperBean slwb = new SpecimenLiteWrapperBean();
		slwb.addElement(new SpecimenLiteBean());
		slwb.addElement(new SpecimenLiteBean());
		slwb.addElement(new SpecimenLiteBean());
		return slwb;
	}
		
	/*
	@RequestMapping(value="/search/simple", method=RequestMethod.GET, params="searchString")
	public @ResponseBody String searchSimple(@RequestParam String searchString) {
		return "A simple search for '"+searchString+"' using Lucene will be done...[todo].";
	}
	*/
}
