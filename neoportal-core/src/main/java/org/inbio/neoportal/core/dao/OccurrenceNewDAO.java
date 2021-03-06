/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2012 INBio - Instituto Nacional de Biodiversidad, Costa Rica
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
package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;

import org.inbio.neoportal.core.entity.OccurrenceDwc;

/**
 * @author avargas
 *
 */
public interface OccurrenceNewDAO 
	extends GenericDAO<OccurrenceDwc, BigDecimal> {

  public OccurrenceDwc findByCatalogNumber(final String catalogNumber);
  
  public OccurrenceDwc findByCatalogNumberHql(final String catalogNumber);
}
