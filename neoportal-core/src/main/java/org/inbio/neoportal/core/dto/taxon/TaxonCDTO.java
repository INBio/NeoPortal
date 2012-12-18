/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2012 INBio - Instituto Nacional de Biodiversidad, Costa Rica
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
package org.inbio.neoportal.core.dto.taxon;

import java.util.ArrayList;

import org.inbio.neoportal.common.dto.BaseDTO;
import org.inbio.neoportal.core.entity.Taxon;

/**
 * @author avargas
 *
 */
public class TaxonCDTO extends BaseDTO
		implements Comparable<TaxonCDTO>{

	private String taxonId;
    private Taxon taxonBySinonymTaxonId;
    private Taxon taxonByAncestorTaxonId;

    private String taxonomicalRangeId;

    private String defaultName;

    private String kingdom;

    private String division;

    private String class_;

    private String order;

    private String family;

    private String genus;

    private String species;
    private String dominiumId;
    private String kingdomId;
    private String divisionId;
    private String subdivisionId;
    private String classId;
    private String subclassId;
    private String orderId;
    private String subOrderId;
    private String superFamilyId;
    private String familyId;
    private String subFamilyId;
    private String tribeId;
    private String subTribeId;
    private String genusId;
    private String subGenusId;
    private String sectionId;
    private String subSectionId;
    private String raceId;
    private String speciesId;
    private String subSpeciesId;
    private String varietyId;
    private String formId;
    private String domain;
    private String imageUrl;
    
    private ArrayList<ImagesCDTO> imageList;

	
	@Override
	public int compareTo(TaxonCDTO arg0) {
		return Integer.parseInt(this.taxonId) - Integer.parseInt(arg0.taxonId);
	}


	public TaxonCDTO() {
		super();
	}


	public TaxonCDTO(String taxonId, Taxon taxonBySinonymTaxonId,
			Taxon taxonByAncestorTaxonId, String taxonomicalRangeId,
			String defaultName, String kingdom, String division, String class_,
			String order, String family, String genus, String species,
			String dominiumId, String kingdomId, String divisionId,
			String subdivisionId, String classId, String subclassId,
			String orderId, String subOrderId, String superFamilyId,
			String familyId, String subFamilyId, String tribeId,
			String subTribeId, String genusId, String subGenusId,
			String sectionId, String subSectionId, String raceId,
			String speciesId, String subSpeciesId, String varietyId,
			String formId, String domain, String imageUrl) {
		super();
		this.taxonId = taxonId;
		this.taxonBySinonymTaxonId = taxonBySinonymTaxonId;
		this.taxonByAncestorTaxonId = taxonByAncestorTaxonId;
		this.taxonomicalRangeId = taxonomicalRangeId;
		this.defaultName = defaultName;
		this.kingdom = kingdom;
		this.division = division;
		this.class_ = class_;
		this.order = order;
		this.family = family;
		this.genus = genus;
		this.species = species;
		this.dominiumId = dominiumId;
		this.kingdomId = kingdomId;
		this.divisionId = divisionId;
		this.subdivisionId = subdivisionId;
		this.classId = classId;
		this.subclassId = subclassId;
		this.orderId = orderId;
		this.subOrderId = subOrderId;
		this.superFamilyId = superFamilyId;
		this.familyId = familyId;
		this.subFamilyId = subFamilyId;
		this.tribeId = tribeId;
		this.subTribeId = subTribeId;
		this.genusId = genusId;
		this.subGenusId = subGenusId;
		this.sectionId = sectionId;
		this.subSectionId = subSectionId;
		this.raceId = raceId;
		this.speciesId = speciesId;
		this.subSpeciesId = subSpeciesId;
		this.varietyId = varietyId;
		this.formId = formId;
		this.domain = domain;
		this.imageUrl = imageUrl;
	}


	public String getTaxonId() {
		return taxonId;
	}


	public void setTaxonId(String taxonId) {
		this.taxonId = taxonId;
	}


	public Taxon getTaxonBySinonymTaxonId() {
		return taxonBySinonymTaxonId;
	}


	public void setTaxonBySinonymTaxonId(Taxon taxonBySinonymTaxonId) {
		this.taxonBySinonymTaxonId = taxonBySinonymTaxonId;
	}


	public Taxon getTaxonByAncestorTaxonId() {
		return taxonByAncestorTaxonId;
	}


	public void setTaxonByAncestorTaxonId(Taxon taxonByAncestorTaxonId) {
		this.taxonByAncestorTaxonId = taxonByAncestorTaxonId;
	}


	public String getTaxonomicalRangeId() {
		return taxonomicalRangeId;
	}


	public void setTaxonomicalRangeId(String taxonomicalRangeId) {
		this.taxonomicalRangeId = taxonomicalRangeId;
	}


	public String getDefaultName() {
		return defaultName;
	}


	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}


	public String getKingdom() {
		return kingdom;
	}


	public void setKingdom(String kingdom) {
		this.kingdom = kingdom;
	}


	public String getDivision() {
		return division;
	}


	public void setDivision(String division) {
		this.division = division;
	}


	public String getClass_() {
		return class_;
	}


	public void setClass_(String class_) {
		this.class_ = class_;
	}


	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}


	public String getFamily() {
		return family;
	}


	public void setFamily(String family) {
		this.family = family;
	}


	public String getGenus() {
		return genus;
	}


	public void setGenus(String genus) {
		this.genus = genus;
	}


	public String getSpecies() {
		return species;
	}


	public void setSpecies(String species) {
		this.species = species;
	}


	public String getDominiumId() {
		return dominiumId;
	}


	public void setDominiumId(String dominiumId) {
		this.dominiumId = dominiumId;
	}


	public String getKingdomId() {
		return kingdomId;
	}


	public void setKingdomId(String kingdomId) {
		this.kingdomId = kingdomId;
	}


	public String getDivisionId() {
		return divisionId;
	}


	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}


	public String getSubdivisionId() {
		return subdivisionId;
	}


	public void setSubdivisionId(String subdivisionId) {
		this.subdivisionId = subdivisionId;
	}


	public String getClassId() {
		return classId;
	}


	public void setClassId(String classId) {
		this.classId = classId;
	}


	public String getSubclassId() {
		return subclassId;
	}


	public void setSubclassId(String subclassId) {
		this.subclassId = subclassId;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getSubOrderId() {
		return subOrderId;
	}


	public void setSubOrderId(String subOrderId) {
		this.subOrderId = subOrderId;
	}


	public String getSuperFamilyId() {
		return superFamilyId;
	}


	public void setSuperFamilyId(String superFamilyId) {
		this.superFamilyId = superFamilyId;
	}


	public String getFamilyId() {
		return familyId;
	}


	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}


	public String getSubFamilyId() {
		return subFamilyId;
	}


	public void setSubFamilyId(String subFamilyId) {
		this.subFamilyId = subFamilyId;
	}


	public String getTribeId() {
		return tribeId;
	}


	public void setTribeId(String tribeId) {
		this.tribeId = tribeId;
	}


	public String getSubTribeId() {
		return subTribeId;
	}


	public void setSubTribeId(String subTribeId) {
		this.subTribeId = subTribeId;
	}


	public String getGenusId() {
		return genusId;
	}


	public void setGenusId(String genusId) {
		this.genusId = genusId;
	}


	public String getSubGenusId() {
		return subGenusId;
	}


	public void setSubGenusId(String subGenusId) {
		this.subGenusId = subGenusId;
	}


	public String getSectionId() {
		return sectionId;
	}


	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}


	public String getSubSectionId() {
		return subSectionId;
	}


	public void setSubSectionId(String subSectionId) {
		this.subSectionId = subSectionId;
	}


	public String getRaceId() {
		return raceId;
	}


	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}


	public String getSpeciesId() {
		return speciesId;
	}


	public void setSpeciesId(String speciesId) {
		this.speciesId = speciesId;
	}


	public String getSubSpeciesId() {
		return subSpeciesId;
	}


	public void setSubSpeciesId(String subSpeciesId) {
		this.subSpeciesId = subSpeciesId;
	}


	public String getVarietyId() {
		return varietyId;
	}


	public void setVarietyId(String varietyId) {
		this.varietyId = varietyId;
	}


	public String getFormId() {
		return formId;
	}


	public void setFormId(String formId) {
		this.formId = formId;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public ArrayList<ImagesCDTO> getImageList() {
		return imageList;
	}


	public void setImageList(ArrayList<ImagesCDTO> imageList) {
		this.imageList = imageList;
	}

	
}
