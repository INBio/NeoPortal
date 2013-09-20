package org.inbio.neoportal.image_crawler;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.inbio.neoportal.core.dao.ImageDAO;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.entity.Image;
import org.inbio.neoportal.core.entity.OccurrenceDwc;
import org.inbio.neoportal.core.entity.Taxon;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("prototype")
@Transactional
public class FlickrIndexer implements Runnable {

	@Autowired
	private ImageDAO imageDAO;
	
	@Autowired
	private OccurrenceDAO occurrenceDAO;
	
	@Autowired
	private TaxonDAO taxonDAO;
	
	private final static Logger LOGGER = Logger.getLogger(FlickrIndexer.class);
	
	private JSONObject jsonImage;
	
	public FlickrIndexer() {
		
	}
	
	/**
	 * 
	 */
	public FlickrIndexer(JSONObject jsonImage) {
		this.jsonImage = jsonImage;
	}
	
	@Override
	public void run() {
		String title;
		try {
			title = this.jsonImage.getString("title");
			
			String flickrId = this.jsonImage.getString("id");
			Image image = imageDAO.findByFlickrId(new BigInteger(flickrId));
			
			if (image == null)
				image = new Image();
			
			image.setExternalImageId(new BigInteger(flickrId));
			image.setAuthor(jsonImage.getString("ownername"));
			image.setRights(this.jsonImage.getString("license"));
			image.setSource("flickr");
			image.setSecret(this.jsonImage.getString("secret"));
			image.setServer(this.jsonImage.getInt("server"));
			image.setFarm(this.jsonImage.getInt("farm"));
			image.setTitle(title);
			image.setDateAdded( new Date(this.jsonImage.getLong("dateadded") * 1000));
			image.setDescription(this.jsonImage.getJSONObject("description")
					.getString("_content"));
			image.setDateUpload(new Date(this.jsonImage.getLong("dateupload") * 1000));
			image.setDateTaken(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(this.jsonImage.getString("datetaken")));
			image.setOriginalSecret(this.jsonImage.getString("originalsecret"));
			image.setOriginalFormat(this.jsonImage.getString("originalformat"));
			image.setLastUpdate(new Date(this.jsonImage.getLong("lastupdate") * 1000));
			image.setLatitude(this.jsonImage.getString("latitude"));
			image.setLongitude(this.jsonImage.getString("longitude"));
			image.setAccuracy(this.jsonImage.getString("accuracy"));
			image.setTags(this.jsonImage.getString("tags"));
			image.setProcessed(true);
			
			// find if the title is a bar code or a taxon name
			String catalogNumber = title.replaceAll("[^0-9]", "").replaceAll("^0+", "");
			
			OccurrenceDwc occurrenceDwc = null;
			Taxon taxon;
			
			// is a bar code
			if (catalogNumber.length() > 0) {
				occurrenceDwc = occurrenceDAO.findByCatalogNumber(catalogNumber);
			}
			
			if (occurrenceDwc != null) {
				taxon = occurrenceDwc.getTaxon();
				
				LOGGER.debug("Image " + title + " related with occurrence " + occurrenceDwc.getOccurrenceId());
				LOGGER.debug("Image " + title + " related with taxon " + taxon.getTaxonId().toString());
			}
			else { 
				// try to find a taxon name...
				taxon = taxonDAO.findByDefaultName(title);
				if (taxon == null) {
					image.setProcessed(false); // identified image that are not related with other entity
					LOGGER.warn("Image " + title + " not releated with any entity");
				}
			}
			
			image.setOccurrence(occurrenceDwc);
			image.setTaxon(taxon);
			
			imageDAO.create(image);
			LOGGER.info("Inserting image " + title);
			
		} catch (JSONException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
