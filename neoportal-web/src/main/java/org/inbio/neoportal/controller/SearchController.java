package org.inbio.neoportal.controller;

import org.inbio.neoportal.messagebean.SpecimenLiteBean;
import org.inbio.neoportal.messagebean.XMLResponseWrapperBean;
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
	public @ResponseBody XMLResponseWrapperBean searchSimpleWriteXml(@RequestParam String searchString) {
		XMLResponseWrapperBean slwb = new XMLResponseWrapperBean();
		slwb.addElement(new SpecimenLiteBean("007", "INB","Homo sapiens sapiens","9.7425","-84.376667"));
		slwb.addElement(new SpecimenLiteBean("070", "INB","Homo sapiens sapiens","9.2545","-84.376767"));
		slwb.addElement(new SpecimenLiteBean("700", "INB","Homo sapiens sapiens","9.4725","-84.373667"));
		return slwb;
	}
		
	/*
	@RequestMapping(value="/search/simple", method=RequestMethod.GET, params="searchString")
	public @ResponseBody String searchSimple(@RequestParam String searchString) {
		return "A simple search for '"+searchString+"' using Lucene will be done...[todo].";
	}
	*/
}
