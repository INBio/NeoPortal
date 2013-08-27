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
 package org.inbio.neoportal.core.dto.taxon;

import org.inbio.neoportal.common.dto.BaseDTO;

/**
 *
 * @author avargas
 */
public class ImagesCDTO 
        extends BaseDTO 
            implements Comparable {
    
    private String imageId;
    private String externalImageId;
    private String taxonId;
    private String author;
    private String rights;
    private String occurrenceId;
    private String source;
//    private String processed;
    private String secret;
    private String server;
    private String farm;
    private String title;
    private String dateAdded;
    private String description;
    private String dateUpload;
    private String dateTaken;
    private String originalSecret;
    private String originalFormat;
    private String lastUpdate;
    private String latitude;
    private String longitude;
    private String accuracy;
    private String tags;

    @Override
    public int compareTo(Object t) {
        return this.externalImageId.compareTo(((ImagesCDTO)t).getExternalImageId());
    }

    public ImagesCDTO() {
    }

    public ImagesCDTO(String imageId, String externalImageId, String taxonId,
			String author, String rights, String occurrenceId, String source,
			String processed, String secret, String server, String farm,
			String title, String dateAdded, String description,
			String dateUpload, String dateTaken, String originalSecret,
			String originalFormat, String lastUpdate, String latitude,
			String longitude, String accuracy, String tags) {
		super();
		this.imageId = imageId;
		this.externalImageId = externalImageId;
		this.taxonId = taxonId;
		this.author = author;
		this.rights = rights;
		this.occurrenceId = occurrenceId;
		this.source = source;
//		this.processed = processed;
		this.secret = secret;
		this.server = server;
		this.farm = farm;
		this.title = title;
		this.dateAdded = dateAdded;
		this.description = description;
		this.dateUpload = dateUpload;
		this.dateTaken = dateTaken;
		this.originalSecret = originalSecret;
		this.originalFormat = originalFormat;
		this.lastUpdate = lastUpdate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracy = accuracy;
		this.tags = tags;
	}

	public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getExternalImageId() {
		return externalImageId;
	}

	public void setExternalImageId(String externalImageId) {
		this.externalImageId = externalImageId;
	}

	public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getTaxonId() {
        return taxonId;
    }

    public void setTaxonId(String taxonId) {
        this.taxonId = taxonId;
    }

	public String getOccurrenceId() {
		return occurrenceId;
	}

	public void setOccurrenceId(String occurrenceId) {
		this.occurrenceId = occurrenceId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getFarm() {
		return farm;
	}

	public void setFarm(String farm) {
		this.farm = farm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateUpload() {
		return dateUpload;
	}

	public void setDateUpload(String dateUpload) {
		this.dateUpload = dateUpload;
	}

	public String getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(String dateTaken) {
		this.dateTaken = dateTaken;
	}

	public String getOriginalSecret() {
		return originalSecret;
	}

	public void setOriginalSecret(String originalSecret) {
		this.originalSecret = originalSecret;
	}

	public String getOriginalFormat() {
		return originalFormat;
	}

	public void setOriginalFormat(String originalFormat) {
		this.originalFormat = originalFormat;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

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

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
    
    
}
