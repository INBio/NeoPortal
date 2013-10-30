package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.util.List;

import org.inbio.neoportal.core.entity.Location;

public interface LocationDAO extends GenericDAO<Location, BigDecimal> {

  public List<Location> searchLocationsByDistance(double radius, double latitude, double longitude);
}
