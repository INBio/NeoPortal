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
package org.inbio.neoportal.core.entity;
// Generated 09/05/2012 05:07:36 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.inbio.neoportal.core.common.analyzer.KeywordLowerCaseAnalyzer;

/**
 * Taxon generated by hbm2java
 * @author asanabria
 */
@Indexed
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Taxon  
    implements java.io.Serializable {

	/**
	 * Enum to get taxonomical range field names
	 * @author avargas
	 *
	 */
	public static enum TaxonomicalRange{
		ROOT(new Long(0), ""),
	    KINGDOM(new Long(1), "kingdom"),
	    PHYLUM(new Long(2), "phylum"),
	    SUBFHYLUM(new Long(3), "subphylumSubdivisionTaxonId"),
	    CLASS(new Long(4),"class_"),
	    SUBCLASS(new Long(5),"subclassTaxonId"),
	    ORDER(new Long(6),"order"),
	    SUBORDEN(new Long(7),"suborderTaxonId"),
	    SUPERFAMILY(new Long(8),"superfamilyTaxonId"),
	    FAMILY(new Long(9),"family"),
	    SUBFAMILY(new Long(10),"subfamilyTaxonId"),
	    TRIBE(new Long(11),"tribeTaxonId"),
	    SUBTRIBE(new Long(12),"subtribeTaxonId"),
	    GENUS(new Long(13),"genus"),
	    SUBGENUS(new Long(14),"subgenusTaxonId"),
	    SECTION(new Long(15),"sectionTaxonId"),
	    SUBSECTION(new Long(16),"subsectionTaxonId"),
	    STIRPS(new Long(18),"stirpsTaxonId"),
	    SPECIES(new Long(19),"species"),
	    SUBSPECIES(new Long(20),"subspeciesTaxonId"),
	    VARIETY(new Long(21),"varietyTaxonId"),
	    FORM(new Long(22),""),
	    DOMAIN(new Long(23),"dominiumTaxonId");
		
		private Long id;
	    private String taxonomicalRangeName;

	    private TaxonomicalRange(Long id, String column){
	        this.id = id;
	        this.taxonomicalRangeName = column;
	    }

	    public static TaxonomicalRange getById(Long id) {

	        TaxonomicalRange[] all = TaxonomicalRange.values();
	        for (TaxonomicalRange tre : all) {
	            //System.out.println(tre.getId()+" = "+id);
	            if (tre.getId().equals(id)) {
	                //System.out.println(tre.getTaxonomicalRangeName());
	                return tre;
	            }
	        }
	        return null;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTaxonomicalRangeName() {
			return taxonomicalRangeName;
		}

		public void setTaxonomicalRangeName(String taxonomicalRangeName) {
			this.taxonomicalRangeName = taxonomicalRangeName;
		}

	}
	
	public static final Map<String, String> columnToProperty;
    static {
        Map<String, String> map = new HashMap<String, String>();
        map.put("taxon_id","taxonId");
        map.put("ancestor_taxon_id", "ancestorTaxonId");
        map.put("sinonym_taxon_id", "sinonymTaxonId");
        map.put("taxonomical_range_id", "taxonomicalRangeId");
        map.put("default_name", "defaultName");
        map.put("kingdom", "kingdom");
        map.put("phylum", "phylum");
        map.put("class", "class_");
        map.put("order_", "order");
        map.put("family", "family");
        map.put("genus", "genus");
        map.put("species", "species");
        map.put("dominium_id", "dominiumId");
        map.put("kingdom_id", "kingdomId");
        map.put("phylum_id", "phylumId");
        map.put("subdivision_id", "subdivisionId");
        map.put("class_id", "classId");
        map.put("subclass_id", "subclassId");
        map.put("order_id", "orderId");
        map.put("sub_order_id", "subOrderId");
        map.put("super_family_id", "superFamilyId");
        map.put("family_id", "familyId");
        map.put("sub_family_id", "subFamilyId");
        map.put("tribe_id", "tribeId");
        map.put("sub_tribe_id", "subTribeId");
        map.put("genus_id", "genusId");
        map.put("sub_genus_id", "subGenusId");
        map.put("section_id", "sectionId");
        map.put("sub_section_id", "subSectionId");
        map.put("race_id", "raceId");
        map.put("species_id", "speciesId");
        map.put("sub_species_id", "subSpeciesId");
        map.put("variety_id", "varietyId");
        map.put("form_id", "formId");
        map.put("domain", "domain");
        map.put("image_url", "imageUrl");
        map.put("subdivision", "subDivision");
        map.put("subclass", "subClass");
        map.put("sub_order", "subOrder");
        map.put("super_family", "superFamily");
        map.put("sub_family", "subFamily");
        map.put("tribe", "tribe");
        map.put("sub_tribe", "subTribe");
        map.put("sub_genus", "subGenus");
        map.put("sub_section", "subSection");
        map.put("race", "race");
        map.put("sub_species", "subSpecies");
        map.put("variety", "variety");
        map.put("form", "form");
        map.put("section", "section");
        columnToProperty = Collections.unmodifiableMap(map);
    }
    
    public static final Map<String, String> customNameToProperty;
    static {
        Map<String, String> map = new HashMap<String, String>();
        map.put("taxon_id","taxonId");
        map.put("ancestor_id", "ancestorTaxonId");
        map.put("sinonym_taxon_id", "sinonymTaxonId");
        map.put("taxonomical_range_id", "taxonomicalRangeId");
        map.put("default_name", "defaultName");
        map.put("kingdom_taxon_name", "kingdom");
        map.put("phylum_division_taxon_name", "phylum");
        map.put("class_taxon_name", "class_");
        map.put("order_taxon_name", "order");
        map.put("family_taxon_name", "family");
        map.put("genus_taxon_name", "genus");
        map.put("species_taxon_name", "species");
        map.put("dominium_id", "dominiumId");
        map.put("kingdom_taxon_id", "kingdomId");
        map.put("phylum_division_taxon_id", "phylumId");
        map.put("subphylum_subdiv_taxon_id", "subdivisionId");
        map.put("class_taxon_id", "classId");
        map.put("subclass_taxon_id", "subclassId");
        map.put("order_taxon_id", "orderId");
        map.put("suborder_taxon_id", "subOrderId");
        map.put("superfamily_taxon_id", "superFamilyId");
        map.put("family_taxon_id", "familyId");
        map.put("subfamily_taxon_id", "subFamilyId");
        map.put("tribe_taxon_id", "tribeId");
        map.put("subtribe_taxon_id", "subTribeId");
        map.put("genus_taxon_id", "genusId");
        map.put("subgenus_taxon_id", "subGenusId");
        map.put("section_taxon_id", "sectionId");
        map.put("subsection_taxon_id", "subSectionId");
        map.put("race_id", "raceId");
        map.put("species_taxon_id", "speciesId");
        map.put("subspecies_taxon_id", "subSpeciesId");
        map.put("variety_taxon_id", "varietyId");
        map.put("form_taxon_id", "formId");
        map.put("domain", "domain");
        map.put("image_url", "imageUrl");
        map.put("subphylum_subdiv_taxon_name", "subDivision");
        map.put("subclass_taxon_name", "subClass");
        map.put("suborder_taxon_name", "subOrder");
        map.put("superfamily_taxon_name", "superFamily");
        map.put("subfamily_taxon_name", "subFamily");
        map.put("tribe_taxon_name", "tribe");
        map.put("subtribe_taxon_name", "subTribe");
        map.put("subgenus_taxon_name", "subGenus");
        map.put("subsection_taxon_name", "subSection");
        map.put("race", "race");
        map.put("subspecies_taxon_name", "subSpecies");
        map.put("variety_taxon_name", "variety");
        map.put("form_taxon_name", "form");
        map.put("section_taxon_name", "section");
        customNameToProperty = Collections.unmodifiableMap(map);
    }

	/**
	 * 
	 */
	public static final String TaxonFields = "kingdom|phylum|class_|order|family|genus|species|defaultName|commonNames";
	
	public static final String TaxonFieldsForAutocomplete = "kingdom|phylum|class_|order|family|genus|species|defaultName_keyword|commonNames";
	
    // Fields    

    @DocumentId
     private BigDecimal taxonId;
     private Taxon taxonBySinonymTaxonId;
     private Taxon ancestorTaxonId;

     @Field
     private BigDecimal taxonomicalRangeId;

     @Fields({
    	 @Field,
    	 @Field(
    			 name     = "defaultName_keyword",
    			 analyzer = @Analyzer(impl=KeywordLowerCaseAnalyzer.class),
    			 store    = Store.YES)
     })
     private String defaultName;

     @Field(store=Store.YES)
     private String kingdom;

     @Field(store=Store.YES)
     private String phylum;

     @Field(store=Store.YES)
     private String class_;

     @Field(store=Store.YES)
     private String order;

     @Field(store=Store.YES)
     private String family;

     @Field(store=Store.YES)
     private String genus;

     @Field(store=Store.YES)
     private String species;
     
     private String subDivision;
     private String subClass;
     private String subOrder;
     private String superFamily;
     private String subFamily;
     private String tribe;
     private String subTribe;
     private String subGenus;
     private String section;
     private String subSection;
     private String race;
     private String subSpecies;
     private String variety;
     private String form;
     
     private BigDecimal dominiumId;
     private BigDecimal kingdomId;
     private BigDecimal phylumId;
     private BigDecimal subdivisionId;
     private BigDecimal classId;
     private BigDecimal subclassId;
     private BigDecimal orderId;
     private BigDecimal subOrderId;
     private BigDecimal superFamilyId;
     private BigDecimal familyId;
     private BigDecimal subFamilyId;
     private BigDecimal tribeId;
     private BigDecimal subTribeId;
     private BigDecimal genusId;
     private BigDecimal subGenusId;
     private BigDecimal sectionId;
     private BigDecimal subSectionId;
     private BigDecimal raceId;
     private BigDecimal speciesId;
     private BigDecimal subSpeciesId;
     private BigDecimal varietyId;
     private BigDecimal formId;
     private String domain;
     private String imageUrl;
     private Set taxonsForAncestorTaxonId = new HashSet(0);
     private Set taxonsForSinonymTaxonId = new HashSet(0);

     @Field(store=Store.YES)
     private String commonNames;
     
     // main relationships
     
     @OneToMany(mappedBy="taxon")
     private Set<TaxonDescription> taxonDescriptions = new HashSet<TaxonDescription>(0);

     @ContainedIn
     @XmlTransient
     private Set<OccurrenceDwc> occurrences = new HashSet<OccurrenceDwc>(0);


     private Set<TaxonHasReferenceElement> taxonHasReferenceElements 
         = new HashSet<TaxonHasReferenceElement>(0);
          
     private Set<TaxonHasAssociatedAttribute> taxonHasAssociatedAttributes 
         = new HashSet<TaxonHasAssociatedAttribute>(0);
     
     @ContainedIn
     @XmlTransient
     private Set<Image> images = new HashSet<Image>(0);
     
    // Constructors

    /** default constructor */
    public Taxon() {
    }

	
    public Taxon(BigDecimal taxonId) {
        this.taxonId = taxonId;
    }
    
	public Taxon(BigDecimal taxonId, Taxon taxonBySinonymTaxonId,
			Taxon ancestorTaxonId, BigDecimal taxonomicalRangeId,
			String defaultName, String kingdom, String phylum, String class_,
			String order, String family, String genus, String species,
			String subDivision, String subClass, String subOrder,
			String superFamily, String subFamily, String tribe,
			String subTribe, String subGenus, String section,
			String subSection, String race, String subSpecies, String variety,
			String form, BigDecimal dominiumId, BigDecimal kingdomId,
			BigDecimal phylumId, BigDecimal subdivisionId, BigDecimal classId,
			BigDecimal subclassId, BigDecimal orderId, BigDecimal subOrderId,
			BigDecimal superFamilyId, BigDecimal familyId,
			BigDecimal subFamilyId, BigDecimal tribeId, BigDecimal subTribeId,
			BigDecimal genusId, BigDecimal subGenusId, BigDecimal sectionId,
			BigDecimal subSectionId, BigDecimal raceId, BigDecimal speciesId,
			BigDecimal subSpeciesId, BigDecimal varietyId, BigDecimal formId,
			String domain, String imageUrl, Set taxonsForAncestorTaxonId,
			Set taxonsForSinonymTaxonId, String commonNames,
			Set<TaxonDescription> taxonDescriptions,
			Set<OccurrenceDwc> occurrences,
			Set<TaxonHasReferenceElement> taxonHasReferenceElements,
			Set<TaxonHasAssociatedAttribute> taxonHasAssociatedAttributes,
			Set<Image> images) {
		this.taxonId = taxonId;
		this.taxonBySinonymTaxonId = taxonBySinonymTaxonId;
		this.ancestorTaxonId = ancestorTaxonId;
		this.taxonomicalRangeId = taxonomicalRangeId;
		this.defaultName = defaultName;
		this.kingdom = kingdom;
		this.phylum = phylum;
		this.class_ = class_;
		this.order = order;
		this.family = family;
		this.genus = genus;
		this.species = species;
		this.subDivision = subDivision;
		this.subClass = subClass;
		this.subOrder = subOrder;
		this.superFamily = superFamily;
		this.subFamily = subFamily;
		this.tribe = tribe;
		this.subTribe = subTribe;
		this.subGenus = subGenus;
		this.section = section;
		this.subSection = subSection;
		this.race = race;
		this.subSpecies = subSpecies;
		this.variety = variety;
		this.form = form;
		this.dominiumId = dominiumId;
		this.kingdomId = kingdomId;
		this.phylumId = phylumId;
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
		this.taxonsForAncestorTaxonId = taxonsForAncestorTaxonId;
		this.taxonsForSinonymTaxonId = taxonsForSinonymTaxonId;
		this.commonNames = commonNames;
		this.taxonDescriptions = taxonDescriptions;
		this.occurrences = occurrences;
		this.taxonHasReferenceElements = taxonHasReferenceElements;
		this.taxonHasAssociatedAttributes = taxonHasAssociatedAttributes;
		this.images = images;
	}


	public BigDecimal getTaxonId() {
        return this.taxonId;
    }
    
    public void setTaxonId(BigDecimal taxonId) {
        this.taxonId = taxonId;
    }
    public Taxon getTaxonBySinonymTaxonId() {
        return this.taxonBySinonymTaxonId;
    }
    
    public void setTaxonBySinonymTaxonId(Taxon taxonBySinonymTaxonId) {
        this.taxonBySinonymTaxonId = taxonBySinonymTaxonId;
    }
    public Taxon getAncestorTaxonId() {
        return this.ancestorTaxonId;
    }
    
    public void setAncestorTaxonId(Taxon taxonByAncestorTaxonId) {
        this.ancestorTaxonId = taxonByAncestorTaxonId;
    }
    public BigDecimal getTaxonomicalRangeId() {
        return this.taxonomicalRangeId;
    }
    
    public void setTaxonomicalRangeId(BigDecimal taxonomicalRangeId) {
        this.taxonomicalRangeId = taxonomicalRangeId;
    }
    public String getDefaultName() {
        return this.defaultName;
    }
    
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
    public String getKingdom() {
        return this.kingdom;
    }
    
    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }
    public String getPhylum() {
        return this.phylum;
    }
    
    public void setPhylum(String division) {
        this.phylum = division;
    }
    public String getClass_() {
        return this.class_;
    }
    
    public void setClass_(String class_) {
        this.class_ = class_;
    }
    public String getOrder() {
        return this.order;
    }
    
    public void setOrder(String order) {
        this.order = order;
    }
    public String getFamily() {
        return this.family;
    }
    
    public void setFamily(String family) {
        this.family = family;
    }
    public String getGenus() {
        return this.genus;
    }
    
    public void setGenus(String genus) {
        this.genus = genus;
    }
    public String getSpecies() {
        return this.species;
    }
    
    public void setSpecies(String species) {
        this.species = species;
    }
    
    
    
    public String getSubDivision() {
		return subDivision;
	}


	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}


	public String getSubClass() {
		return subClass;
	}


	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}


	public String getSubOrder() {
		return subOrder;
	}


	public void setSubOrder(String subOrder) {
		this.subOrder = subOrder;
	}


	public String getSuperFamily() {
		return superFamily;
	}


	public void setSuperFamily(String superFamily) {
		this.superFamily = superFamily;
	}


	public String getSubFamily() {
		return subFamily;
	}


	public void setSubFamily(String subFamily) {
		this.subFamily = subFamily;
	}


	public String getTribe() {
		return tribe;
	}


	public void setTribe(String tribe) {
		this.tribe = tribe;
	}


	public String getSubTribe() {
		return subTribe;
	}


	public void setSubTribe(String subTribe) {
		this.subTribe = subTribe;
	}


	public String getSubGenus() {
		return subGenus;
	}


	public void setSubGenus(String subGenus) {
		this.subGenus = subGenus;
	}

	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}


	public String getSubSection() {
		return subSection;
	}


	public void setSubSection(String subSection) {
		this.subSection = subSection;
	}


	public String getRace() {
		return race;
	}


	public void setRace(String race) {
		this.race = race;
	}


	public String getSubSpecies() {
		return subSpecies;
	}


	public void setSubSpecies(String subSpecies) {
		this.subSpecies = subSpecies;
	}


	public String getVariety() {
		return variety;
	}


	public void setVariety(String variety) {
		this.variety = variety;
	}


	public String getForm() {
		return form;
	}


	public void setForm(String form) {
		this.form = form;
	}


	public BigDecimal getDominiumId() {
        return this.dominiumId;
    }
    
    public void setDominiumId(BigDecimal dominiumId) {
        this.dominiumId = dominiumId;
    }
    public BigDecimal getKingdomId() {
        return this.kingdomId;
    }
    
    public void setKingdomId(BigDecimal kingdomId) {
        this.kingdomId = kingdomId;
    }
    
    public BigDecimal getPhylumId() {
        return this.phylumId;
    }
    
    public void setPhylumId(BigDecimal divisionId) {
        this.phylumId = divisionId;
    }
    public BigDecimal getSubdivisionId() {
        return this.subdivisionId;
    }
    
    public void setSubdivisionId(BigDecimal subdivisionId) {
        this.subdivisionId = subdivisionId;
    }
    public BigDecimal getClassId() {
        return this.classId;
    }
    
    public void setClassId(BigDecimal classId) {
        this.classId = classId;
    }
    public BigDecimal getSubclassId() {
        return this.subclassId;
    }
    
    public void setSubclassId(BigDecimal subclassId) {
        this.subclassId = subclassId;
    }
    public BigDecimal getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(BigDecimal orderId) {
        this.orderId = orderId;
    }
    public BigDecimal getSubOrderId() {
        return this.subOrderId;
    }
    
    public void setSubOrderId(BigDecimal subOrderId) {
        this.subOrderId = subOrderId;
    }
    public BigDecimal getSuperFamilyId() {
        return this.superFamilyId;
    }
    
    public void setSuperFamilyId(BigDecimal superFamilyId) {
        this.superFamilyId = superFamilyId;
    }
    public BigDecimal getFamilyId() {
        return this.familyId;
    }
    
    public void setFamilyId(BigDecimal familyId) {
        this.familyId = familyId;
    }
    public BigDecimal getSubFamilyId() {
        return this.subFamilyId;
    }
    
    public void setSubFamilyId(BigDecimal subFamilyId) {
        this.subFamilyId = subFamilyId;
    }
    public BigDecimal getTribeId() {
        return this.tribeId;
    }
    
    public void setTribeId(BigDecimal tribeId) {
        this.tribeId = tribeId;
    }
    public BigDecimal getSubTribeId() {
        return this.subTribeId;
    }
    
    public void setSubTribeId(BigDecimal subTribeId) {
        this.subTribeId = subTribeId;
    }
    public BigDecimal getGenusId() {
        return this.genusId;
    }
    
    public void setGenusId(BigDecimal genusId) {
        this.genusId = genusId;
    }
    public BigDecimal getSubGenusId() {
        return this.subGenusId;
    }
    
    public void setSubGenusId(BigDecimal subGenusId) {
        this.subGenusId = subGenusId;
    }
    public BigDecimal getSectionId() {
        return this.sectionId;
    }
    
    public void setSectionId(BigDecimal sectionId) {
        this.sectionId = sectionId;
    }
    public BigDecimal getSubSectionId() {
        return this.subSectionId;
    }
    
    public void setSubSectionId(BigDecimal subSectionId) {
        this.subSectionId = subSectionId;
    }
    public BigDecimal getRaceId() {
        return this.raceId;
    }
    
    public void setRaceId(BigDecimal raceId) {
        this.raceId = raceId;
    }
    public BigDecimal getSpeciesId() {
        return this.speciesId;
    }
    
    public void setSpeciesId(BigDecimal speciesId) {
        this.speciesId = speciesId;
    }
    public BigDecimal getSubSpeciesId() {
        return this.subSpeciesId;
    }
    
    public void setSubSpeciesId(BigDecimal subSpeciesId) {
        this.subSpeciesId = subSpeciesId;
    }
    public BigDecimal getVarietyId() {
        return this.varietyId;
    }
    
    public void setVarietyId(BigDecimal varietyId) {
        this.varietyId = varietyId;
    }
    public BigDecimal getFormId() {
        return this.formId;
    }
    
    public void setFormId(BigDecimal formId) {
        this.formId = formId;
    }

    public Set<TaxonHasReferenceElement> getTaxonHasReferenceElements() {
        return this.taxonHasReferenceElements;
    }
    
    public void setTaxonHasReferenceElements(
        Set<TaxonHasReferenceElement> taxonHasReferenceElements) {
        
        this.taxonHasReferenceElements = taxonHasReferenceElements;
    }
    public Set getTaxonsForAncestorTaxonId() {
        return this.taxonsForAncestorTaxonId;
    }
    
    public void setTaxonsForAncestorTaxonId(Set taxonsForAncestorTaxonId) {
        this.taxonsForAncestorTaxonId = taxonsForAncestorTaxonId;
    }
    public Set getTaxonsForSinonymTaxonId() {
        return this.taxonsForSinonymTaxonId;
    }
    
    public void setTaxonsForSinonymTaxonId(Set taxonsForSinonymTaxonId) {
        this.taxonsForSinonymTaxonId = taxonsForSinonymTaxonId;
    }
    @XmlTransient
    public Set getOccurrences() {
        return this.occurrences;
    }
    
    public Set<TaxonDescription> getTaxonDescriptions() {
        return this.taxonDescriptions;
}

    public void setTaxonDescriptions(Set<TaxonDescription> taxonDescriptions) {
        this.taxonDescriptions = taxonDescriptions;
    }

    public Set<TaxonHasAssociatedAttribute> getTaxonHasAssociatedAttributes() {
        return this.taxonHasAssociatedAttributes;
    }
    
    public void setTaxonHasAssociatedAttributes(
        Set<TaxonHasAssociatedAttribute> taxonHasAssociatedAttributes) {
        
        this.taxonHasAssociatedAttributes = taxonHasAssociatedAttributes;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setOccurrences(Set<OccurrenceDwc> occurrences) {
        this.occurrences = occurrences;
    }

    public String getCommonNames() {
        return commonNames;
    }

    public void setCommonNames(String commonNames) {
        this.commonNames = commonNames;
    }

    @XmlTransient
    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
}
