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

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.inbio.neoportal.core.dao.ImportDwcDAO;
import org.inbio.neoportal.core.entity.ImportDwc;
import org.postgresql.PGConnection;
import org.postgresql.copy.CopyManager;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.impl.NewProxyConnection;

/**
 *
 * @author avargas
 */
@Repository
public class ImportDwcDAOImpl
        extends GenericDAOImpl<ImportDwc, BigDecimal>
        implements ImportDwcDAO{

  /* (non-Javadoc)
   * @see org.inbio.neoportal.core.dao.ImportDwcDAO#deleteAll()
   */
  @Override
  public void deleteAll() {
    Session session = getSessionFactory().getCurrentSession();
    Query query = session.createQuery("delete from ImportDwc");
    query.executeUpdate();
  }

  /* (non-Javadoc)
   * @see org.inbio.neoportal.core.dao.ImportDwcDAO#copy(java.lang.String)
   */
  @Override
  public long copy(final Reader fileReader) {
    final long[] copyRows = {0};
    Session session = getSessionFactory().getCurrentSession();
    session.doWork(new Work() {
      
      @Override
      public void execute(Connection connection) throws SQLException {
        try {
          Method m = PGConnection.class.getMethod("getCopyAPI", new Class[0]);
          Object[] arg = new Object[0];
          CopyManager copyManager = (CopyManager) ((NewProxyConnection)connection)
              .rawConnectionOperation(m, NewProxyConnection.RAW_CONNECTION, arg);
          
          String sql = "COPY import_dwc (\"type\",\"modified\",\"language\",\"rights\",\"rightsHolder\",\"accessRights\",\"bibliographicCitation\",\"references\",\"institutionID\",\"collectionID\",\"datasetID\",\"institutionCode\",\"collectionCode\",\"datasetName\",\"ownerInstitutionCode\",\"basisOfRecord\",\"informationWithheld\",\"dataGeneralizations\",\"dynamicProperties\",\"occurrenceID\",\"catalogNumber\",\"occurrenceRemarks\",\"recordNumber\",\"recordedBy\",\"individualID\",\"individualCount\",\"sex\",\"lifeStage\",\"reproductiveCondition\",\"behavior\",\"establishmentMeans\",\"occurrenceStatus\",\"preparations\",\"disposition\",\"otherCatalogNumbers\",\"previousIdentifications\",\"associatedMedia\",\"associatedReferences\",\"associatedOccurrences\",\"associatedSequences\",\"associatedTaxa\",\"eventID\",\"samplingProtocol\",\"samplingEffort\",\"eventDate\",\"eventTime\",\"startDayOfYear\",\"endDayOfYear\",\"year\",\"month\",\"day\",\"verbatimEventDate\",\"habitat\",\"fieldNumber\",\"fieldNotes\",\"eventRemarks\",\"locationID\",\"higherGeographyID\",\"higherGeography\",\"continent\",\"waterBody\",\"islandGroup\",\"island\",\"country\",\"countryCode\",\"stateProvince\",\"county\",\"municipality\",\"locality\",\"verbatimLocality\",\"verbatimElevation\",\"minimumElevationInMeters\",\"maximumElevationInMeters\",\"verbatimDepth\",\"minimumDepthInMeters\",\"maximumDepthInMeters\",\"minimumDistanceAboveSurfaceInMeters\",\"maximumDistanceAboveSurfaceInMeters\",\"locationAccordingTo\",\"locationRemarks\",\"verbatimCoordinates\",\"verbatimLatitude\",\"verbatimLongitude\",\"verbatimCoordinateSystem\",\"verbatimSRS\",\"decimalLongitude\",\"decimalLatitude\",\"geodeticDatum\",\"coordinateUncertaintyInMeters\",\"coordinatePrecision\",\"pointRadiusSpatialFit\",\"footprintWKT\",\"footprintSRS\",\"footprintSpatialFit\",\"georeferencedBy\",\"georeferenceProtocol\",\"georeferenceSources\",\"georeferenceVerificationStatus\",\"georeferenceRemarks\",\"identificationID\",\"identifiedBy\", \"dateIdentified\",\"identificationReferences\",\"identificationRemarks\",\"identificationQualifier\",\"typeStatus\",\"taxonID\",\"scientificNameID\",\"acceptedNameUsageID\",\"parentNameUsageID\",\"originalNameUsageID\",\"nameAccordingToID\",\"namePublishedInID\",\"taxonConceptID\",\"scientificName\",\"acceptedNameUsage\",\"parentNameUsage\",\"originalNameUsage\",\"nameAccordingTo\",\"namePublishedIn\",\"higherClassification\",\"kingdom\",\"phylum\",\"class\",\"order\",\"family\",\"genus\",\"subgenus\",\"specificEpithet\",\"infraspecificEpithet\",\"taxonRank\",\"verbatimTaxonRank\",\"scientificNameAuthorship\",\"vernacularName\",\"nomenclaturalCode\",\"taxonomicStatus\",\"nomenclaturalStatus\",\"taxonRemarks\",\"taxonCategoryID\",\"oldTaxonID\") " +
          		"FROM STDIN " +
          		"(FORMAT CSV, HEADER, ESCAPE '\\')";
          
          copyRows[0] = copyManager.copyIn(sql, fileReader);
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (SecurityException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (NoSuchMethodException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IllegalArgumentException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
    
    return copyRows[0];
  }

    
    
}
