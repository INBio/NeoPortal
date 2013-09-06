package org.inbio.neoportal.core.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dao.ImageDAO;
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.dto.transformers.ImagesTransformer;
import org.inbio.neoportal.core.entity.Image;
import org.inbio.neoportal.core.entity.Taxon;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDAOImpl 
				extends GenericDAOImpl<Image, BigInteger> 
				implements ImageDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> UnprocessedImages() {
		
		return (List<Image>)getSessionFactory().getCurrentSession()
				.createQuery("from Image where processed = :isProcessed")
				.setBoolean("isProcessed", false)
				.list();
		
	}
	
	@Override
	public Image findByFlickrId(BigInteger flickrId) {
		
		return (Image)getSessionFactory().getCurrentSession()
				.createQuery("from Image where source='flickr' and externalImageId = :flickrId")
				.setBigInteger("flickrId", flickrId)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagesCDTO> search(String[] fields,
			String searchText, int offset, int quantity) {
		return (List<ImagesCDTO>)super.search(new ImagesTransformer(), fields, searchText, offset, quantity);
	}
	
	public Long searchCount(
	        final String[] fields,
	        final String searchText
	        ) {
		return super.searchCount(fields, searchText);
	}
	
	public List<ImagesCDTO> search(
			String[] fields,
			String searchText) {
		
		Session session = getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		// create Lucene query using the query DSL
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Image.class).get();
		Query query = qb
				.keyword()
				.onFields(fields)	// use keyword field to get a exact match
				.matching(searchText)
				.createQuery();
		
		// wrap Lucene query in a org.hibernate.Query
		org.hibernate.Query hQuery = 
				fullTextSession.createFullTextQuery(query, Image.class);
		
		hQuery.setResultTransformer(new ImagesTransformer());
		
		return hQuery.list();		
	}
	
}
