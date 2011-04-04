/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.entity.Occurrence;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asanabria
 */
@Repository
public class OccurrenceDAOImpl 
    extends GenericBaseDAOImpl<Occurrence, BigDecimal>
        implements OccurrenceDAO{ 
}
