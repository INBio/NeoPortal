package org.inbio.neoportal.core.entity;

import java.io.Serializable;

/**
 *
 * @author avargas
 */
public class ImportDwc implements Serializable {
    
    private String type;
    private String modified;
    private String language;
    private String rights;
    private String rightsholder;
    private String accessrights;
    private String bibliographiccitation;
    private String institutionid;
    private String collectionid;
    private String datasetid;
    private String institutioncode;
    private String collectioncode;
    private String datasetname;
    private String ownerinstitutioncode;
    private String basisofrecord;
    private String informationwithheld;
    private String datageneralizations;
    private String dynamicproperties;
    private String occurrenceid;
    private String catalognumber;
    private String occurrenceremarks;
    private String recordnumber;
    private String individualid;
    private String individualcount;
    private String sex;
    private String lifestage;
    private String reproductivecondition;
    private String behavior;
    private String establishmentmeans;
    private String occurrencestatus;
    private String preparations;
    private String disposition;
    private String othercatalognumbers;
    private String previousidentifications;
    private String associatedmedia;
    private String associatedreferences;
    private String associatedoccurrences;
    private String associatedsequences;
    private String associatedtaxa;
    private String eventid;
    private String samplingprotocol;
    private String samplingeffort;
    private String eventdate;
    private String eventtime;
    private String startdayofyear;
    private String enddayofyear;
    private String year;
    private String month;
    private String day;
    private String verbatimeventdate;
    private String habitat;
    private String fieldnumber;
    private String fieldnotes;
    private String eventremarks;
    private String locationid;
    private String highergeographyid;
    private String highergeography;
    private String continent;
    private String waterbody;
    private String islandgroup;
    private String island;
    private String country;
    private String countrycode;
    private String stateprovince;
    private String county;
    private String municipality;
    private String locality;
    private String verbatimlocality;
    private String verbatimelevation;
    private String minimumelevationinmeters;
    private String maximumelevationinmeters;
    private String verbatimdepth;
    private String minimumdepthinmeters;
    private String maximumdepthinmeters;
    private String mindistabovesurfaceinmeters;
    private String maxdistabovesurfaceinmeters;
    private String locationaccordingto;
    private String locationremarks;
    private String verbatimcoordinates;
    private String verbatimlatitude;
    private String verbatimlongitude;
    private String verbatimcoordinatesystem;
    private String verbatimsrs;
    private String decimallatitude;
    private String decimallongitude;
    private String geodeticdatum;
    private String coordinateuncertaintyinmeters;
    private String coordinateprecision;
    private String pointradiusspatialfit;
    private String footprintwkt;
    private String footprintsrs;
    private String footprintspatialfit;
    private String georeferencedby;
    private String georeferenceprotocol;
    private String georeferencesources;
    private String georeferenceverificationstatus;
    private String georeferenceremarks;
    private String geologicalcontextid;
    private String earliesteonorlowesteonothem;
    private String latesteonorhighesteonothem;
    private String earliesteraorlowesterathem;
    private String latesteraorhighesterathem;
    private String earliestperiodorlowestsystem;
    private String latestperiodorhighestsystem;
    private String earliestepochorlowestseries;
    private String latestepochorhighestseries;
    private String earliestageorloweststage;
    private String latestageorhigheststage;
    private String lowestbiostratigraphiczone;
    private String highestbiostratigraphiczone;
    private String lithostratigraphicterms;
    private String lithostratigraphicGroup;
    private String formation;
    private String member;
    private String bed;
    private String identificationid;
    private String identifiedby;
    private String dateidentified;
    private String identificationreferences;
    private String identificationremarks;
    private String identificationqualifier;
    private String typestatus;
    private String taxonid;
    private String scientificnameid;
    private String acceptednameusageid;
    private String parentnameusageid;
    private String originalnameusageid;
    private String nameaccordingtoid;
    private String namepublishedinid;
    private String taxonconceptid;
    private String scientificname;
    private String acceptednameusage;
    private String parentnameusage;
    private String originalnameusage;
    private String nameaccordingto;
    private String namepublishedin;
    private String higherclassification;
    private String kingdom;
    private String phylum;
    private String class_;
    private String taxonOrder;
    private String family;
    private String genus;
    private String subgenus;
    private String specificepithet;
    private String infraspecificepithet;
    private String taxonrank;
    private String verbatimtaxonrank;
    private String scientificnameauthorship;
    private String vernacularname;
    private String nomenclaturalcode;
    private String taxonomicstatus;
    private String nomenclaturalstatus;
    private String taxonremarks;

    private Long id;

    public ImportDwc() {
    }

    public ImportDwc(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getRightsholder() {
        return rightsholder;
    }

    public void setRightsholder(String rightsholder) {
        this.rightsholder = rightsholder;
    }

    public String getAccessrights() {
        return accessrights;
    }

    public void setAccessrights(String accessrights) {
        this.accessrights = accessrights;
    }

    public String getBibliographiccitation() {
        return bibliographiccitation;
    }

    public void setBibliographiccitation(String bibliographiccitation) {
        this.bibliographiccitation = bibliographiccitation;
    }

    public String getInstitutionid() {
        return institutionid;
    }

    public void setInstitutionid(String institutionid) {
        this.institutionid = institutionid;
    }

    public String getCollectionid() {
        return collectionid;
    }

    public void setCollectionid(String collectionid) {
        this.collectionid = collectionid;
    }

    public String getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    public String getInstitutioncode() {
        return institutioncode;
    }

    public void setInstitutioncode(String institutioncode) {
        this.institutioncode = institutioncode;
    }

    public String getCollectioncode() {
        return collectioncode;
    }

    public void setCollectioncode(String collectioncode) {
        this.collectioncode = collectioncode;
    }

    public String getDatasetname() {
        return datasetname;
    }

    public void setDatasetname(String datasetname) {
        this.datasetname = datasetname;
    }

    public String getOwnerinstitutioncode() {
        return ownerinstitutioncode;
    }

    public void setOwnerinstitutioncode(String ownerinstitutioncode) {
        this.ownerinstitutioncode = ownerinstitutioncode;
    }

    public String getBasisofrecord() {
        return basisofrecord;
    }

    public void setBasisofrecord(String basisofrecord) {
        this.basisofrecord = basisofrecord;
    }

    public String getInformationwithheld() {
        return informationwithheld;
    }

    public void setInformationwithheld(String informationwithheld) {
        this.informationwithheld = informationwithheld;
    }

    public String getDatageneralizations() {
        return datageneralizations;
    }

    public void setDatageneralizations(String datageneralizations) {
        this.datageneralizations = datageneralizations;
    }

    public String getDynamicproperties() {
        return dynamicproperties;
    }

    public void setDynamicproperties(String dynamicproperties) {
        this.dynamicproperties = dynamicproperties;
    }

    public String getOccurrenceid() {
        return occurrenceid;
    }

    public void setOccurrenceid(String occurrenceid) {
        this.occurrenceid = occurrenceid;
    }

    public String getCatalognumber() {
        return catalognumber;
    }

    public void setCatalognumber(String catalognumber) {
        this.catalognumber = catalognumber;
    }

    public String getOccurrenceremarks() {
        return occurrenceremarks;
    }

    public void setOccurrenceremarks(String occurrenceremarks) {
        this.occurrenceremarks = occurrenceremarks;
    }

    public String getRecordnumber() {
        return recordnumber;
    }

    public void setRecordnumber(String recordnumber) {
        this.recordnumber = recordnumber;
    }

    public String getIndividualid() {
        return individualid;
    }

    public void setIndividualid(String individualid) {
        this.individualid = individualid;
    }

    public String getIndividualcount() {
        return individualcount;
    }

    public void setIndividualcount(String individualcount) {
        this.individualcount = individualcount;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLifestage() {
        return lifestage;
    }

    public void setLifestage(String lifestage) {
        this.lifestage = lifestage;
    }

    public String getReproductivecondition() {
        return reproductivecondition;
    }

    public void setReproductivecondition(String reproductivecondition) {
        this.reproductivecondition = reproductivecondition;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getEstablishmentmeans() {
        return establishmentmeans;
    }

    public void setEstablishmentmeans(String establishmentmeans) {
        this.establishmentmeans = establishmentmeans;
    }

    public String getOccurrencestatus() {
        return occurrencestatus;
    }

    public void setOccurrencestatus(String occurrencestatus) {
        this.occurrencestatus = occurrencestatus;
    }

    public String getPreparations() {
        return preparations;
    }

    public void setPreparations(String preparations) {
        this.preparations = preparations;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getOthercatalognumbers() {
        return othercatalognumbers;
    }

    public void setOthercatalognumbers(String othercatalognumbers) {
        this.othercatalognumbers = othercatalognumbers;
    }

    public String getPreviousidentifications() {
        return previousidentifications;
    }

    public void setPreviousidentifications(String previousidentifications) {
        this.previousidentifications = previousidentifications;
    }

    public String getAssociatedmedia() {
        return associatedmedia;
    }

    public void setAssociatedmedia(String associatedmedia) {
        this.associatedmedia = associatedmedia;
    }

    public String getAssociatedreferences() {
        return associatedreferences;
    }

    public void setAssociatedreferences(String associatedreferences) {
        this.associatedreferences = associatedreferences;
    }

    public String getAssociatedoccurrences() {
        return associatedoccurrences;
    }

    public void setAssociatedoccurrences(String associatedoccurrences) {
        this.associatedoccurrences = associatedoccurrences;
    }

    public String getAssociatedsequences() {
        return associatedsequences;
    }

    public void setAssociatedsequences(String associatedsequences) {
        this.associatedsequences = associatedsequences;
    }

    public String getAssociatedtaxa() {
        return associatedtaxa;
    }

    public void setAssociatedtaxa(String associatedtaxa) {
        this.associatedtaxa = associatedtaxa;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getSamplingprotocol() {
        return samplingprotocol;
    }

    public void setSamplingprotocol(String samplingprotocol) {
        this.samplingprotocol = samplingprotocol;
    }

    public String getSamplingeffort() {
        return samplingeffort;
    }

    public void setSamplingeffort(String samplingeffort) {
        this.samplingeffort = samplingeffort;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }

    public String getStartdayofyear() {
        return startdayofyear;
    }

    public void setStartdayofyear(String startdayofyear) {
        this.startdayofyear = startdayofyear;
    }

    public String getEnddayofyear() {
        return enddayofyear;
    }

    public void setEnddayofyear(String enddayofyear) {
        this.enddayofyear = enddayofyear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getVerbatimeventdate() {
        return verbatimeventdate;
    }

    public void setVerbatimeventdate(String verbatimeventdate) {
        this.verbatimeventdate = verbatimeventdate;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getFieldnumber() {
        return fieldnumber;
    }

    public void setFieldnumber(String fieldnumber) {
        this.fieldnumber = fieldnumber;
    }

    public String getFieldnotes() {
        return fieldnotes;
    }

    public void setFieldnotes(String fieldnotes) {
        this.fieldnotes = fieldnotes;
    }

    public String getEventremarks() {
        return eventremarks;
    }

    public void setEventremarks(String eventremarks) {
        this.eventremarks = eventremarks;
    }

    public String getLocationid() {
        return locationid;
    }

    public void setLocationid(String locationid) {
        this.locationid = locationid;
    }

    public String getHighergeographyid() {
        return highergeographyid;
    }

    public void setHighergeographyid(String highergeographyid) {
        this.highergeographyid = highergeographyid;
    }

    public String getHighergeography() {
        return highergeography;
    }

    public void setHighergeography(String highergeography) {
        this.highergeography = highergeography;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getWaterbody() {
        return waterbody;
    }

    public void setWaterbody(String waterbody) {
        this.waterbody = waterbody;
    }

    public String getIslandgroup() {
        return islandgroup;
    }

    public void setIslandgroup(String islandgroup) {
        this.islandgroup = islandgroup;
    }

    public String getIsland() {
        return island;
    }

    public void setIsland(String island) {
        this.island = island;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getStateprovince() {
        return stateprovince;
    }

    public void setStateprovince(String stateprovince) {
        this.stateprovince = stateprovince;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getVerbatimlocality() {
        return verbatimlocality;
    }

    public void setVerbatimlocality(String verbatimlocality) {
        this.verbatimlocality = verbatimlocality;
    }

    public String getVerbatimelevation() {
        return verbatimelevation;
    }

    public void setVerbatimelevation(String verbatimelevation) {
        this.verbatimelevation = verbatimelevation;
    }

    public String getMinimumelevationinmeters() {
        return minimumelevationinmeters;
    }

    public void setMinimumelevationinmeters(String minimumelevationinmeters) {
        this.minimumelevationinmeters = minimumelevationinmeters;
    }

    public String getMaximumelevationinmeters() {
        return maximumelevationinmeters;
    }

    public void setMaximumelevationinmeters(String maximumelevationinmeters) {
        this.maximumelevationinmeters = maximumelevationinmeters;
    }

    public String getVerbatimdepth() {
        return verbatimdepth;
    }

    public void setVerbatimdepth(String verbatimdepth) {
        this.verbatimdepth = verbatimdepth;
    }

    public String getMinimumdepthinmeters() {
        return minimumdepthinmeters;
    }

    public void setMinimumdepthinmeters(String minimumdepthinmeters) {
        this.minimumdepthinmeters = minimumdepthinmeters;
    }

    public String getMaximumdepthinmeters() {
        return maximumdepthinmeters;
    }

    public void setMaximumdepthinmeters(String maximumdepthinmeters) {
        this.maximumdepthinmeters = maximumdepthinmeters;
    }

    public String getMindistabovesurfaceinmeters() {
        return mindistabovesurfaceinmeters;
    }

    public void setMindistabovesurfaceinmeters(String mindistabovesurfaceinmeters) {
        this.mindistabovesurfaceinmeters = mindistabovesurfaceinmeters;
    }

    public String getMaxdistabovesurfaceinmeters() {
        return maxdistabovesurfaceinmeters;
    }

    public void setMaxdistabovesurfaceinmeters(String maxdistabovesurfaceinmeters) {
        this.maxdistabovesurfaceinmeters = maxdistabovesurfaceinmeters;
    }

    public String getLocationaccordingto() {
        return locationaccordingto;
    }

    public void setLocationaccordingto(String locationaccordingto) {
        this.locationaccordingto = locationaccordingto;
    }

    public String getLocationremarks() {
        return locationremarks;
    }

    public void setLocationremarks(String locationremarks) {
        this.locationremarks = locationremarks;
    }

    public String getVerbatimcoordinates() {
        return verbatimcoordinates;
    }

    public void setVerbatimcoordinates(String verbatimcoordinates) {
        this.verbatimcoordinates = verbatimcoordinates;
    }

    public String getVerbatimlatitude() {
        return verbatimlatitude;
    }

    public void setVerbatimlatitude(String verbatimlatitude) {
        this.verbatimlatitude = verbatimlatitude;
    }

    public String getVerbatimlongitude() {
        return verbatimlongitude;
    }

    public void setVerbatimlongitude(String verbatimlongitude) {
        this.verbatimlongitude = verbatimlongitude;
    }

    public String getVerbatimcoordinatesystem() {
        return verbatimcoordinatesystem;
    }

    public void setVerbatimcoordinatesystem(String verbatimcoordinatesystem) {
        this.verbatimcoordinatesystem = verbatimcoordinatesystem;
    }

    public String getVerbatimsrs() {
        return verbatimsrs;
    }

    public void setVerbatimsrs(String verbatimsrs) {
        this.verbatimsrs = verbatimsrs;
    }

    public String getDecimallatitude() {
        return decimallatitude;
    }

    public void setDecimallatitude(String decimallatitude) {
        this.decimallatitude = decimallatitude;
    }

    public String getDecimallongitude() {
        return decimallongitude;
    }

    public void setDecimallongitude(String decimallongitude) {
        this.decimallongitude = decimallongitude;
    }

    public String getGeodeticdatum() {
        return geodeticdatum;
    }

    public void setGeodeticdatum(String geodeticdatum) {
        this.geodeticdatum = geodeticdatum;
    }

    public String getCoordinateuncertaintyinmeters() {
        return coordinateuncertaintyinmeters;
    }

    public void setCoordinateuncertaintyinmeters(String coordinateuncertaintyinmeters) {
        this.coordinateuncertaintyinmeters = coordinateuncertaintyinmeters;
    }

    public String getCoordinateprecision() {
        return coordinateprecision;
    }

    public void setCoordinateprecision(String coordinateprecision) {
        this.coordinateprecision = coordinateprecision;
    }

    public String getPointradiusspatialfit() {
        return pointradiusspatialfit;
    }

    public void setPointradiusspatialfit(String pointradiusspatialfit) {
        this.pointradiusspatialfit = pointradiusspatialfit;
    }

    public String getFootprintwkt() {
        return footprintwkt;
    }

    public void setFootprintwkt(String footprintwkt) {
        this.footprintwkt = footprintwkt;
    }

    public String getFootprintsrs() {
        return footprintsrs;
    }

    public void setFootprintsrs(String footprintsrs) {
        this.footprintsrs = footprintsrs;
    }

    public String getFootprintspatialfit() {
        return footprintspatialfit;
    }

    public void setFootprintspatialfit(String footprintspatialfit) {
        this.footprintspatialfit = footprintspatialfit;
    }

    public String getGeoreferencedby() {
        return georeferencedby;
    }

    public void setGeoreferencedby(String georeferencedby) {
        this.georeferencedby = georeferencedby;
    }

    public String getGeoreferenceprotocol() {
        return georeferenceprotocol;
    }

    public void setGeoreferenceprotocol(String georeferenceprotocol) {
        this.georeferenceprotocol = georeferenceprotocol;
    }

    public String getGeoreferencesources() {
        return georeferencesources;
    }

    public void setGeoreferencesources(String georeferencesources) {
        this.georeferencesources = georeferencesources;
    }

    public String getGeoreferenceverificationstatus() {
        return georeferenceverificationstatus;
    }

    public void setGeoreferenceverificationstatus(String georeferenceverificationstatus) {
        this.georeferenceverificationstatus = georeferenceverificationstatus;
    }

    public String getGeoreferenceremarks() {
        return georeferenceremarks;
    }

    public void setGeoreferenceremarks(String georeferenceremarks) {
        this.georeferenceremarks = georeferenceremarks;
    }

    public String getGeologicalcontextid() {
        return geologicalcontextid;
    }

    public void setGeologicalcontextid(String geologicalcontextid) {
        this.geologicalcontextid = geologicalcontextid;
    }

    public String getEarliesteonorlowesteonothem() {
        return earliesteonorlowesteonothem;
    }

    public void setEarliesteonorlowesteonothem(String earliesteonorlowesteonothem) {
        this.earliesteonorlowesteonothem = earliesteonorlowesteonothem;
    }

    public String getLatesteonorhighesteonothem() {
        return latesteonorhighesteonothem;
    }

    public void setLatesteonorhighesteonothem(String latesteonorhighesteonothem) {
        this.latesteonorhighesteonothem = latesteonorhighesteonothem;
    }

    public String getEarliesteraorlowesterathem() {
        return earliesteraorlowesterathem;
    }

    public void setEarliesteraorlowesterathem(String earliesteraorlowesterathem) {
        this.earliesteraorlowesterathem = earliesteraorlowesterathem;
    }

    public String getLatesteraorhighesterathem() {
        return latesteraorhighesterathem;
    }

    public void setLatesteraorhighesterathem(String latesteraorhighesterathem) {
        this.latesteraorhighesterathem = latesteraorhighesterathem;
    }

    public String getEarliestperiodorlowestsystem() {
        return earliestperiodorlowestsystem;
    }

    public void setEarliestperiodorlowestsystem(String earliestperiodorlowestsystem) {
        this.earliestperiodorlowestsystem = earliestperiodorlowestsystem;
    }

    public String getLatestperiodorhighestsystem() {
        return latestperiodorhighestsystem;
    }

    public void setLatestperiodorhighestsystem(String latestperiodorhighestsystem) {
        this.latestperiodorhighestsystem = latestperiodorhighestsystem;
    }

    public String getEarliestepochorlowestseries() {
        return earliestepochorlowestseries;
    }

    public void setEarliestepochorlowestseries(String earliestepochorlowestseries) {
        this.earliestepochorlowestseries = earliestepochorlowestseries;
    }

    public String getLatestepochorhighestseries() {
        return latestepochorhighestseries;
    }

    public void setLatestepochorhighestseries(String latestepochorhighestseries) {
        this.latestepochorhighestseries = latestepochorhighestseries;
    }

    public String getEarliestageorloweststage() {
        return earliestageorloweststage;
    }

    public void setEarliestageorloweststage(String earliestageorloweststage) {
        this.earliestageorloweststage = earliestageorloweststage;
    }

    public String getLatestageorhigheststage() {
        return latestageorhigheststage;
    }

    public void setLatestageorhigheststage(String latestageorhigheststage) {
        this.latestageorhigheststage = latestageorhigheststage;
    }

    public String getLowestbiostratigraphiczone() {
        return lowestbiostratigraphiczone;
    }

    public void setLowestbiostratigraphiczone(String lowestbiostratigraphiczone) {
        this.lowestbiostratigraphiczone = lowestbiostratigraphiczone;
    }

    public String getHighestbiostratigraphiczone() {
        return highestbiostratigraphiczone;
    }

    public void setHighestbiostratigraphiczone(String highestbiostratigraphiczone) {
        this.highestbiostratigraphiczone = highestbiostratigraphiczone;
    }

    public String getLithostratigraphicterms() {
        return lithostratigraphicterms;
    }

    public void setLithostratigraphicterms(String lithostratigraphicterms) {
        this.lithostratigraphicterms = lithostratigraphicterms;
    }

    public String getLithostratigraphicGroup() {
        return lithostratigraphicGroup;
    }

    public void setLithostratigraphicGroup(String lithostratigraphicGroup) {
        this.lithostratigraphicGroup = lithostratigraphicGroup;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getIdentificationid() {
        return identificationid;
    }

    public void setIdentificationid(String identificationid) {
        this.identificationid = identificationid;
    }

    public String getIdentifiedby() {
        return identifiedby;
    }

    public void setIdentifiedby(String identifiedby) {
        this.identifiedby = identifiedby;
    }

    public String getDateidentified() {
        return dateidentified;
    }

    public void setDateidentified(String dateidentified) {
        this.dateidentified = dateidentified;
    }

    public String getIdentificationreferences() {
        return identificationreferences;
    }

    public void setIdentificationreferences(String identificationreferences) {
        this.identificationreferences = identificationreferences;
    }

    public String getIdentificationremarks() {
        return identificationremarks;
    }

    public void setIdentificationremarks(String identificationremarks) {
        this.identificationremarks = identificationremarks;
    }

    public String getIdentificationqualifier() {
        return identificationqualifier;
    }

    public void setIdentificationqualifier(String identificationqualifier) {
        this.identificationqualifier = identificationqualifier;
    }

    public String getTypestatus() {
        return typestatus;
    }

    public void setTypestatus(String typestatus) {
        this.typestatus = typestatus;
    }

    public String getTaxonid() {
        return taxonid;
    }

    public void setTaxonid(String taxonid) {
        this.taxonid = taxonid;
    }

    public String getScientificnameid() {
        return scientificnameid;
    }

    public void setScientificnameid(String scientificnameid) {
        this.scientificnameid = scientificnameid;
    }

    public String getAcceptednameusageid() {
        return acceptednameusageid;
    }

    public void setAcceptednameusageid(String acceptednameusageid) {
        this.acceptednameusageid = acceptednameusageid;
    }

    public String getParentnameusageid() {
        return parentnameusageid;
    }

    public void setParentnameusageid(String parentnameusageid) {
        this.parentnameusageid = parentnameusageid;
    }

    public String getOriginalnameusageid() {
        return originalnameusageid;
    }

    public void setOriginalnameusageid(String originalnameusageid) {
        this.originalnameusageid = originalnameusageid;
    }

    public String getNameaccordingtoid() {
        return nameaccordingtoid;
    }

    public void setNameaccordingtoid(String nameaccordingtoid) {
        this.nameaccordingtoid = nameaccordingtoid;
    }

    public String getNamepublishedinid() {
        return namepublishedinid;
    }

    public void setNamepublishedinid(String namepublishedinid) {
        this.namepublishedinid = namepublishedinid;
    }

    public String getTaxonconceptid() {
        return taxonconceptid;
    }

    public void setTaxonconceptid(String taxonconceptid) {
        this.taxonconceptid = taxonconceptid;
    }

    public String getScientificname() {
        return scientificname;
    }

    public void setScientificname(String scientificname) {
        this.scientificname = scientificname;
    }

    public String getAcceptednameusage() {
        return acceptednameusage;
    }

    public void setAcceptednameusage(String acceptednameusage) {
        this.acceptednameusage = acceptednameusage;
    }

    public String getParentnameusage() {
        return parentnameusage;
    }

    public void setParentnameusage(String parentnameusage) {
        this.parentnameusage = parentnameusage;
    }

    public String getOriginalnameusage() {
        return originalnameusage;
    }

    public void setOriginalnameusage(String originalnameusage) {
        this.originalnameusage = originalnameusage;
    }

    public String getNameaccordingto() {
        return nameaccordingto;
    }

    public void setNameaccordingto(String nameaccordingto) {
        this.nameaccordingto = nameaccordingto;
    }

    public String getNamepublishedin() {
        return namepublishedin;
    }

    public void setNamepublishedin(String namepublishedin) {
        this.namepublishedin = namepublishedin;
    }

    public String getHigherclassification() {
        return higherclassification;
    }

    public void setHigherclassification(String higherclassification) {
        this.higherclassification = higherclassification;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    public String getPhylum() {
        return phylum;
    }

    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class1) {
        this.class_ = class1;
    }

    public String getTaxonOrder() {
        return taxonOrder;
    }

    public void setTaxonOrder(String taxonOrder) {
        this.taxonOrder = taxonOrder;
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

    public String getSubgenus() {
        return subgenus;
    }

    public void setSubgenus(String subgenus) {
        this.subgenus = subgenus;
    }

    public String getSpecificepithet() {
        return specificepithet;
    }

    public void setSpecificepithet(String specificepithet) {
        this.specificepithet = specificepithet;
    }

    public String getInfraspecificepithet() {
        return infraspecificepithet;
    }

    public void setInfraspecificepithet(String infraspecificepithet) {
        this.infraspecificepithet = infraspecificepithet;
    }

    public String getTaxonrank() {
        return taxonrank;
    }

    public void setTaxonrank(String taxonrank) {
        this.taxonrank = taxonrank;
    }

    public String getVerbatimtaxonrank() {
        return verbatimtaxonrank;
    }

    public void setVerbatimtaxonrank(String verbatimtaxonrank) {
        this.verbatimtaxonrank = verbatimtaxonrank;
    }

    public String getScientificnameauthorship() {
        return scientificnameauthorship;
    }

    public void setScientificnameauthorship(String scientificnameauthorship) {
        this.scientificnameauthorship = scientificnameauthorship;
    }

    public String getVernacularname() {
        return vernacularname;
    }

    public void setVernacularname(String vernacularname) {
        this.vernacularname = vernacularname;
    }

    public String getNomenclaturalcode() {
        return nomenclaturalcode;
    }

    public void setNomenclaturalcode(String nomenclaturalcode) {
        this.nomenclaturalcode = nomenclaturalcode;
    }

    public String getTaxonomicstatus() {
        return taxonomicstatus;
    }

    public void setTaxonomicstatus(String taxonomicstatus) {
        this.taxonomicstatus = taxonomicstatus;
    }

    public String getNomenclaturalstatus() {
        return nomenclaturalstatus;
    }

    public void setNomenclaturalstatus(String nomenclaturalstatus) {
        this.nomenclaturalstatus = nomenclaturalstatus;
    }

    public String getTaxonremarks() {
        return taxonremarks;
    }

    public void setTaxonremarks(String taxonremarks) {
        this.taxonremarks = taxonremarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImportDwc)) {
            return false;
        }
        ImportDwc other = (ImportDwc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.inbio.neoportal.core.entity.ImportDwc[ id=" + id + " ]";
    }
    
}
