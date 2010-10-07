/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.dto;

/**
 *
 * @author asanabria
 */
public class MinimalOccurrenceInfoDTO extends BaseDTO {

    private String gui;
    private String name;
    private String locality;

    public MinimalOccurrenceInfoDTO() {
    }

    public MinimalOccurrenceInfoDTO(String gui, String name, String locality) {
        this.gui = gui;
        this.name = name;
        this.locality = locality;
    }

    public String getGui() {
        return gui;
    }

    public void setGui(String gui) {
        this.gui = gui;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
