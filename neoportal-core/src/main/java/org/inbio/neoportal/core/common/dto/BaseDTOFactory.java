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
package org.inbio.neoportal.core.common.dto;


import java.util.ArrayList;
import java.util.List;
import org.inbio.neoportal.core.entity.LogGenericEntity;


/**
 *
 * @author jgutierrez
 *
 * Based on dmartin code, in the GBIF customizable portal.
 */
public abstract class 
    BaseDTOFactory<E extends LogGenericEntity, D extends BaseDTO> 
        implements DTOFactory<E,D> {


    /**
     * 
     * @param entitiesList
     * @return
     */
    @Override
	public List<D> createDTOList(List<E> entitiesList) {
		if(entitiesList==null)
			return null;
		List<D> dtoList = new ArrayList<D>();
		for (E entity: entitiesList)
			dtoList.add(createDTO(entity));
		return dtoList;
	}

}