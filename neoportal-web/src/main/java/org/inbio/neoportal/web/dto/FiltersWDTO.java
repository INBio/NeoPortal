/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.web.dto;

import org.springframework.roo.addon.json.RooJson;
import java.util.List;
import org.inbio.neoportal.service.dto.advancedSearch.ColumnDefaultSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.SearchColumnSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FilterListSDTO;

/**
 *
 * @author avargas
 */
@RooJson
public class FiltersWDTO {
    
    private List<SearchColumnSDTO> columnList;
    private List<ColumnDefaultSDTO> columnDefault;
    private List<FilterListSDTO> filterList;
    
    public FiltersWDTO(){
        
    }
    
    public void setColumnList(List<SearchColumnSDTO> columnList){
        this.columnList = columnList;
    }
    
    public List<SearchColumnSDTO> getColumnList(){
        return this.columnList;
    }
    
    public void setColumnDefault(List<ColumnDefaultSDTO> columnDefault){
        this.columnDefault = columnDefault;
    }
    
    public List<ColumnDefaultSDTO> getColumnDefault(){
        return this.columnDefault;
    }

    public List<FilterListSDTO> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<FilterListSDTO> filterList) {
        this.filterList = filterList;
    }
    
}
