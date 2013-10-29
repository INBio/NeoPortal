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
 package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.neoportal.core.dao.GeoLayerDAO;
import org.inbio.neoportal.core.dto.advancedsearch.GeoLayerCDTO;
import org.inbio.neoportal.core.dto.transformers.GeoLayerTransformer;
import org.inbio.neoportal.core.entity.GeoLayer;
import org.springframework.stereotype.Repository;

/**
 *
 * @author avargas
 */
@Repository
public class GeoLayerDAOImpl
    extends GenericDAOImpl<GeoLayer, BigDecimal>
    implements GeoLayerDAO{

    @Override
    public List<GeoLayerCDTO> getGeoLayerByName(final String name) {
      Session session = getSessionFactory().getCurrentSession();
      Query query = session.createQuery(
        "from GeoLayer as gl"
            + " where gl.name = :name");
      query.setParameter("name", name);
      //query.setResultTransformer(new GeoLayerTransformer());
      query.setResultTransformer(new GeoLayerTransformer());
      return query.list();
    }

    @Override
    public void create(GeoLayer entity) {
        super.create(entity);
    }

    @Override
    public void delete(GeoLayer entity) {
        super.delete(entity);
    }

    @Override
    public void update(GeoLayer entity) {
        super.update(entity);
    }

}
