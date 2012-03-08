/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author avargas
 */
@Controller
@RequestMapping("/advancedSearch/*")
public class AdvancedSearchController {
    
    @RequestMapping("/")
    public String advancedSearch(){
        return "advancedSearch";
    }
    
    @RequestMapping("/#!/{searchId}")
    public String getSearch(
            @PathVariable(value = "searchId") String searchId){
        
        //preparar filtros para de consulta guardada
        
        //agregar id de consulta a lista de atributos
        
        //retornar el view
        return "advancedSearch";
    }
}
