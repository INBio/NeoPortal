/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import org.inbio.neoportal.core.dao.TaxonDescriptionDAO;
import org.inbio.neoportal.core.entity.TaxonDescription;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asanabria
 */
@Repository
public class TaxonDescriptionDAOImpl 
    extends GenericBaseDAOImpl<TaxonDescription, BigDecimal>
        implements TaxonDescriptionDAO{    
    
}
