package org.inbio.neoportal.core.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.inbio.neoportal.core.dao.ImageDAO;
import org.inbio.neoportal.core.entity.Image;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDAOImpl 
				extends GenericBaseDAOImpl<Image, BigInteger> 
				implements ImageDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> UnprocessedImages() {
		
		return (List<Image>)getSession()
				.createQuery("from Image where processed = :isProcessed")
				.setBoolean("isProcessed", false)
				.list();
		
	}
	
	@Override
	public Image findByFlickrId(BigInteger flickrId) {
		
		return (Image)getSession()
				.createQuery("from Image where source='flickr' and externalImageId = :flickrId")
				.setBigInteger("flickrId", flickrId)
				.uniqueResult();
	}
	
	@Override
	public List<Image> findAll() {
		return super.findAll(Image.class);
	}

}
