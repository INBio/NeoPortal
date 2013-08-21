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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

public class GroupPoolsInterface {
	
	private String apiKey;
	
	private int page;
	private int pages;
	
	public GroupPoolsInterface(String apiKey){
		this.apiKey = apiKey;
		this.page = 0;
		this.pages = 0;
	}

	
	public boolean hasNext(){
		if(this.pages > 0 && this.pages < this.page)
			return false;
		else
			return true;
	}
	
	
	/**
	 * Return and JSONArray with the next photos page
	 * @param groupId
	 * @return JSONArray or null when finish
	 */
	public JSONArray nextPhotosPage(String groupId){
		this.page++;
		
		if(this.pages > 0 && this.pages < this.page)
			throw new NoSuchElementException();
			
    	String flickrPoolUrl = "http://api.flickr.com/services/rest/?method=flickr.groups.pools.getPhotos";
    	flickrPoolUrl += "&api_key=" + this.apiKey;
    	flickrPoolUrl += "&group_id=" + groupId;
    	flickrPoolUrl += "&page=" + page;
    	flickrPoolUrl += "&format=json&nojsoncallback=1";
    	
    	InputStream in = null;
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule((Module)new JsonOrgModule());
    	
    	try {
			in = new URL(flickrPoolUrl).openStream();
			
			String json = IOUtils.toString(in);
			
			JSONObject jsonObject = mapper.readValue(json, JSONObject.class);
			
			this.pages = ((JSONObject)jsonObject.get("photos")).getInt("pages");
			
			JSONArray photoArray = ((JSONObject)jsonObject.get("photos")).getJSONArray("photo");
			
			return photoArray;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
		}
    	
    	return null;
    }
}
