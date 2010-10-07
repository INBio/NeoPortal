package org.inbio.neoportal.dto;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.transform.ResultTransformer;
import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.neoportal.entity.DarwinCore;


/**
 * @author jgutierrez
 *
 */
public class MinimalOccurrenceInfoDTOFactory extends BaseDTOFactory<DarwinCore, MinimalOccurrenceInfoDTO> implements ResultTransformer {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public MinimalOccurrenceInfoDTO createDTO(DarwinCore p) {
		if(p == null)
				return null;
		return new MinimalOccurrenceInfoDTO(p.getGlobaluniqueidentifier(), p.getScientificname(), p.getLocality());
	}

    @Override
    public List transformList(List list) {
        List<DarwinCore> dwcList = (List<DarwinCore>) list;
        List<MinimalOccurrenceInfoDTO> newList = new ArrayList<MinimalOccurrenceInfoDTO>();

        for(DarwinCore dwc: dwcList)
            newList.add( new MinimalOccurrenceInfoDTO( dwc.getGlobaluniqueidentifier(), dwc.getScientificname(), dwc.getLocality()));

        return newList;
    }

    @Override
    public Object transformTuple(Object[] os, String[] strings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
