package org.inbio.neoportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	@RequestMapping("/simple")
	public @ResponseBody String simple() {
		return "Hola Mundo!";
	}
	
	@RequestMapping(value="/search/simple", method=RequestMethod.GET, params="searchString")
	public @ResponseBody String searchSimple(@RequestParam String searchString) {
		return "A simple search for '"+searchString+"' using Lucene will be done...[todo].";
	}

}
