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
package org.inbio.neoportal.core.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;

/**
 *
 * @author avargas
 */
@Indexed
public class Image
        implements java.io.Serializable{
    
	@DocumentId
    private BigInteger imageId;
    private BigInteger externalImageId;
    @IndexedEmbedded
    private Taxon taxon;
    private OccurrenceDwc occurrence;
    private String author;
    private String rights;
    private String source;
    private boolean processed;
    private String secret;
    private int server;
    private int farm;
    private String title;
    private Date dateAdded;
    private String description;
    private Date dateUpload;
    private Date dateTaken;
    private String originalSecret;
    private String originalFormat;
    private Date lastUpdate;
    private String latitude;
    private String longitude;
    private String accuracy;
    private String tags;

    /**
     * Create the appropriate url based on the external image source 
     * @param imagesCDTO
     * @return
     */
    public static String getSmallUrl(ImagesCDTO imagesCDTO) {
		String url = "";
		if (imagesCDTO.getSource().equalsIgnoreCase("flickr")){
			url += "http://farm" + imagesCDTO.getFarm();
			url += ".staticflickr.com/" + imagesCDTO.getServer();
			url += "/" + imagesCDTO.getExternalImageId() + "_" + imagesCDTO.getSecret();
			url += "_m.jpg";
		}
		else
			url = "http://multimedia.inbio.ac.cr/m3sINBio/getImage?size=thumb&id=" + imagesCDTO.getExternalImageId();
		
		return url;
    }
    
    /**
     * Create the appropriate url based on the external image source 
     * @param imagesCDTO
     * @return
     */
    public static String getBigUrl(ImagesCDTO imagesCDTO) {
		String url = "";
		if (imagesCDTO.getSource().equalsIgnoreCase("flickr")){
			url += "http://farm" + imagesCDTO.getFarm();
			url += ".staticflickr.com/" + imagesCDTO.getServer();
			url += "/" + imagesCDTO.getExternalImageId() + "_" + imagesCDTO.getSecret();
			url += "_b.jpg";
		}
		else
			url = "http://multimedia.inbio.ac.cr/m3sINBio/getImage?size=big&id=" + imagesCDTO.getExternalImageId();
		
		return url;
    }
    
    
    
    public Image() {

    }

    public BigInteger getImageId() {
        return imageId;
    }

    public void setImageId(BigInteger ImageId) {
        this.imageId = ImageId;
    }

    public BigInteger getExternalImageId() {
        return externalImageId;
    }

    public void setExternalImageId(BigInteger externalImageId) {
        this.externalImageId = externalImageId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public Taxon getTaxon() {
        return taxon;
    }

    public void setTaxon(Taxon taxon) {
        this.taxon = taxon;
    }

	public OccurrenceDwc getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(OccurrenceDwc occurrence) {
		this.occurrence = occurrence;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public int getServer() {
		return server;
	}

	public void setServer(int server) {
		this.server = server;
	}

	public int getFarm() {
		return farm;
	}

	public void setFarm(int farm) {
		this.farm = farm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateUpload() {
		return dateUpload;
	}

	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}

	public Date getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(Date dateTaken) {
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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
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
