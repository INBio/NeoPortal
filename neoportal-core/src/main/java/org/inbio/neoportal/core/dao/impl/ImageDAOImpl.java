package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.inbio.neoportal.core.dao.ImageDAO;
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.dto.transformers.ImagesTransformer;
import org.inbio.neoportal.core.entity.Image;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
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
	
	@Override
	public Image findM3sImage(BigInteger m3sId, BigDecimal taxonId) {
		
		return (Image)getSessionFactory().getCurrentSession()
				.createQuery("from Image where source='m3s' " +
						"and externalImageId = :m3sId " +
						"and taxon.taxonId = :taxonId")
				.setBigInteger("m3sId", m3sId)
				.setBigDecimal("taxonId", taxonId)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagesCDTO> searchPhrase(String field,
			String searchText, String sortField, int offset, int quantity) {
		
		Session session = getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Image.class).get();
		Query query = qb
				.phrase()
				.onField(field)
				.sentence(searchText)
				.createQuery();
		
		FullTextQuery fQuery = 
				fullTextSession.createFullTextQuery(query, Image.class);
		
		fQuery.setSort(new Sort(new SortField(sortField, SortField.STRING)));
		
		fQuery.setResultTransformer(new ImagesTransformer());
		fQuery.setFirstResult(offset);
		fQuery.setMaxResults(quantity);
		
		return fQuery.list();
	}
	
	public Long searchPhraseCount(
	        final String field,
	        final String searchText
	        ) {
		Session session = getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Image.class).get();
		Query query = qb
				.phrase()
				.onField(field)
				.sentence(searchText)
				.createQuery();
		
		FullTextQuery fQuery = 
				fullTextSession.createFullTextQuery(query, Image.class);
		
		return (long) fQuery.getResultSize();
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
