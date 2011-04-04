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
package org.inbio.neoportal.core.dto;

import java.io.Serializable;

/**
 * All DTO's should extend this base!
 * 
 * @author jgutierrez
 *
 */
public class BaseDTO 
    implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//log value
	private String username;
	private String logCreationDate;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the logCreationDate
	 */
	public String getLogCreationDate() {
		return logCreationDate;
	}

	/**
	 * @param logCreationDate the logCreationDate to set
	 */
	public void setLogCreationDate(String logCreationDate) {
		this.logCreationDate = logCreationDate;
	}

}
