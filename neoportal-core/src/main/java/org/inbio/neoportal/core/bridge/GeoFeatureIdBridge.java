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
package org.inbio.neoportal.core.bridge;

import org.hibernate.search.bridge.TwoWayStringBridge;
import org.inbio.neoportal.core.entity.GeoFeatureId;

/**
 *  Hibernate Search Bridge implementation for 
 * composite Id
 * @author avargas
 */
public class GeoFeatureIdBridge
        implements TwoWayStringBridge{

    @Override
    public Object stringToObject(String string) {
        String [] result = string.split("_");
        
        return new GeoFeatureId(
                Long.parseLong(result[0]),
                Long.parseLong(result[1]));
    }

    @Override
    public String objectToString(Object o) {
        GeoFeatureId featureId = (GeoFeatureId)o;
        return 
                String.valueOf(featureId.getGeoLayerId())
                + "_"
                + String.valueOf(featureId.getGeoFeatureId());
    }
    
}
