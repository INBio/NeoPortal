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

package org.inbio.neoportal.image_crawler.flickr;

public class Flickr {
	
	private String apiKey = "api_key";
	
	private GroupPoolsInterface groupPoolsInterface;
	
	public Flickr (String apiKey){
		this.apiKey = apiKey;
	}
	
	public GroupPoolsInterface getGroupPoolsInterface(){
		if(this.groupPoolsInterface == null)
			this.groupPoolsInterface = new GroupPoolsInterface(this.apiKey);
		return this.groupPoolsInterface;
	}

}
