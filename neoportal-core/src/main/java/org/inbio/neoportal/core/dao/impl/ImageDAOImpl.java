package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.inbio.neoportal.core.dao.ImageDAO;
import org.inbio.neoportal.core.entity.Image;

public class ImageDAOImpl 
				extends GenericBaseDAOImpl<Image, BigDecimal> 
				implements ImageDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> UnprocessedImages() {
		
		return (List<Image>)getSession()
				.createQuery("from Image where processed = :isProcessed")
				.setBoolean("isProcessed", false)
				.list();
		
	}

}
