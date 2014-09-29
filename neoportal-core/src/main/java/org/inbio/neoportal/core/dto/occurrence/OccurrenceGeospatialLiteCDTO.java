/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
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
package org.inbio.neoportal.core.dto.occurrence;

import org.inbio.neoportal.core.common.dto.BaseDTO;

/**
 * A subset of the data hold by the taxon entity
 * @author asanabria
 */
public class OccurrenceGeospatialLiteCDTO 
    extends BaseDTO {
    
    private String latitude;
    private String longitude;
    
    public OccurrenceGeospatialLiteCDTO() {
    }

    public OccurrenceGeospatialLiteCDTO(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /* Getters & setters */
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}