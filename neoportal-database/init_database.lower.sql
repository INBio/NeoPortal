--
-- pOSTGREsql DATABASE DUMP
--

-- sTARTED ON 2011-02-15 08:49:49 cst

set CLIENT_ENCODING = 'utf8';
set STANDARD_CONFORMING_STRINGS = OFF;
set CHECK_FUNCTION_BODIES = FALSE;
set CLIENT_MIN_MESSAGES = WARNING;
set ESCAPE_STRING_WARNING = OFF;

set SEARCH_PATH = PUBLIC, PG_CATALOG;

set DEFAULT_TABLESPACE = '';

set DEFAULT_WITH_OIDS = FALSE;

--
-- toc ENTRY 1505 (CLASS 1259 oid 874097)
-- dEPENDENCIES: 6
-- nAME: associated_attribute; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "associated_attribute" (
    "associated_attribute_id" NUMERIC not null,
    "name" TEXT,
    "description" TEXT
);


--
-- toc ENTRY 1506 (CLASS 1259 oid 874103)
-- dEPENDENCIES: 6
-- nAME: common_name; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "common_name" (
    "common_name_id" NUMERIC not null,
    "taxon_id" NUMERIC,
    "name" TEXT,
    "language" TEXT,
    "temporality" TEXT,
    "locality" TEXT,
    "sex" TEXT,
    "life_stage" TEXT,
    "used_by" TEXT
);


--
-- toc ENTRY 1507 (CLASS 1259 oid 874109)
-- dEPENDENCIES: 6
-- nAME: data_provider; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "data_provider" (
    "data_provider_id" NUMERIC not null,
    "name" TEXT,
    "display_name" TEXT,
    "website_url" TEXT,
    "contact_email" TEXT
);


--
-- toc ENTRY 1508 (CLASS 1259 oid 874115)
-- dEPENDENCIES: 6
-- nAME: occurrence; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "occurrence" (
    "occurrence_id" NUMERIC not null,
    "data_provider_id" NUMERIC not null,
    "taxon_id" NUMERIC not null,
    "global_unique_identifier" TEXT,
    "date_last_modified" TEXT,
    "institution_code" TEXT,
    "collection_code" TEXT,
    "catalog_number" TEXT,
    "scientific_name" TEXT,
    "basis_of_record" TEXT,
    "information_withheld" TEXT,
    "higher_taxon" TEXT,
    "kingdom" TEXT,
    "phylum" TEXT,
    "class" TEXT,
    "orders" TEXT,
    "family" TEXT,
    "genus" TEXT,
    "specific_epithet" TEXT,
    "infraspecific_epithet" TEXT,
    "infraspecific_rank" TEXT,
    "author_year_of_scientific_name" TEXT,
    "nomenclatural_code" TEXT,
    "identification_qualifier" TEXT,
    "collecting_method" TEXT,
    "valid_distribution_flag" TEXT,
    "collector" TEXT,
    "earliest_date_collected" TEXT,
    "latest_date_collected" TEXT,
    "day_of_year" NUMERIC,
    "higher_geography" TEXT,
    "continent" TEXT,
    "water_body" TEXT,
    "island_group" TEXT,
    "island" TEXT,
    "country" TEXT,
    "state_province" TEXT,
    "county" TEXT,
    "locality" TEXT,
    "minimum_elevation_in_meters" TEXT,
    "maximum_elevation_in_meters" TEXT,
    "minimum_depth_inmeters" TEXT,
    "maximum_depth_inmeters" TEXT,
    "sex" TEXT,
    "life_stage" TEXT,
    "remarks" TEXT,
    "attributes" TEXT,
    "image_url" TEXT,
    "related_information" TEXT
);


--
-- toc ENTRY 1509 (CLASS 1259 oid 874121)
-- dEPENDENCIES: 6
-- nAME: occurrence_curatorial_extension; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "occurrence_curatorial_extension" (
    "occurrence_curatorial_extension_id" NUMERIC not null,
    "occurrence_id" NUMERIC not null,
    "catalog_number_numeric" TEXT,
    "identified_by" TEXT,
    "date_identified" TEXT,
    "collector_number" TEXT,
    "field_number" TEXT,
    "field_notes" TEXT,
    "verbatim_collecting_date" TEXT,
    "verbatim_elevation" TEXT,
    "verbatim_depth" TEXT,
    "preparations" TEXT,
    "type_status" TEXT,
    "gen_bank_number" TEXT,
    "other_catalog_numbers" TEXT,
    "related_catalog_items" TEXT,
    "disposition" TEXT,
    "individual_count" TEXT
);


--
-- toc ENTRY 1510 (CLASS 1259 oid 874127)
-- dEPENDENCIES: 6
-- nAME: occurrence_geospatial_extension; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "occurrence_geospatial_extension" (
    "occurrence_geospatial_extension_id" NUMERIC not null,
    "occurrence_id" NUMERIC not null,
    "decimal_latitude" TEXT,
    "decimal_longitude" TEXT,
    "geodetic_datum" TEXT,
    "coordinate_uncertainty_in_meters" TEXT,
    "point_radius_spatial_fit" TEXT,
    "footprint_wkt" TEXT,
    "foot_print_spatial_fit" TEXT,
    "verbatim_coordinates" TEXT,
    "verbatim_latitude" TEXT,
    "verbatim_longitude" TEXT,
    "verbatim_coordinate_system" TEXT,
    "georeference_protocol" TEXT,
    "georeference_sources" TEXT,
    "georeference_verification_status" TEXT,
    "georeference_remarks" TEXT
);


--
-- toc ENTRY 1511 (CLASS 1259 oid 874133)
-- dEPENDENCIES: 6
-- nAME: occurrence_has_reference_element; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "occurrence_has_reference_element" (
    "occurrence_id" NUMERIC not null,
    "reference_element_id" NUMERIC not null,
    "value" TEXT
);


--
-- toc ENTRY 1512 (CLASS 1259 oid 874139)
-- dEPENDENCIES: 6
-- nAME: reference_element; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "reference_element" (
    "reference_element_id" NUMERIC not null,
    "name" TEXT,
    "label" TEXT,
    "description" TEXT,
    "guide_lines" TEXT,
    "examples" TEXT
);


--
-- toc ENTRY 1513 (CLASS 1259 oid 874145)
-- dEPENDENCIES: 6
-- nAME: service; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "service" (
    "service_id" NUMERIC not null,
    "name" TEXT,
    "description" TEXT,
    "url" TEXT,
    "url_connection_format" TEXT
);


--
-- toc ENTRY 1514 (CLASS 1259 oid 874151)
-- dEPENDENCIES: 6
-- nAME: taxon; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "taxon" (
    "taxon_id" NUMERIC not null,
    "ancestor_taxon_id" NUMERIC,
    "sinonym_taxon_id" NUMERIC,
    "taxonomical_range_id" NUMERIC,
    "default_name" TEXT,
    "kingdom" TEXT,
    "division" TEXT,
    "class" TEXT,
    "order" TEXT,
    "family" TEXT,
    "genus" TEXT,
    "species" TEXT,
    "dominium_id" NUMERIC,
    "kingdom_id" NUMERIC,
    "division_id" NUMERIC,
    "subdivision_id" NUMERIC,
    "class_id" NUMERIC,
    "subclass_id" NUMERIC,
    "order_id" NUMERIC,
    "sub_order_id" NUMERIC,
    "super_family_id" NUMERIC,
    "family_id" NUMERIC,
    "sub_family_id" NUMERIC,
    "tribe_id" NUMERIC,
    "sub_tribe_id" NUMERIC,
    "genus_id" NUMERIC,
    "sub_genus_id" NUMERIC,
    "section_id" NUMERIC,
    "sub_section_id" NUMERIC,
    "race_id" NUMERIC,
    "species_id" NUMERIC,
    "sub_species_id" NUMERIC,
    "variety_id" NUMERIC,
    "form_id" NUMERIC
);


--
-- toc ENTRY 1515 (CLASS 1259 oid 874157)
-- dEPENDENCIES: 6
-- nAME: taxon_associated_attribute_has_reference_element; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "taxon_associated_attribute_has_reference_element" (
    "associated_attribute_id" NUMERIC not null,
    "reference_element_id" NUMERIC not null,
    "value" TEXT,
    "taxon_id" NUMERIC not null
);


--
-- toc ENTRY 1516 (CLASS 1259 oid 874163)
-- dEPENDENCIES: 6
-- nAME: taxon_description; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "taxon_description" (
    "taxon_description_id" NUMERIC not null,
    "provider_id" NUMERIC not null,
    "taxon_id" NUMERIC not null,
    "global_unique_identifier" TEXT,
    "scientific_name" TEXT,
    "institution_code" TEXT,
    "date_last_modified" DATE,
    "taxon_record_id" TEXT,
    "language" TEXT,
    "creators" TEXT,
    "distribution" TEXT,
    "abstract" TEXT,
    "kingdom_taxon" TEXT,
    "phylum_taxon" TEXT,
    "class_taxon" TEXT,
    "order_taxon" TEXT,
    "family_taxon" TEXT,
    "genus_taxon" TEXT,
    "synonyms" TEXT,
    "author_year_of_scientific_name" TEXT,
    "species_publication_reference" TEXT,
    "common_names" TEXT,
    "typification" TEXT,
    "contributors" TEXT,
    "date_created" DATE,
    "habit" TEXT,
    "life_cycle" TEXT,
    "reproduction" TEXT,
    "annual_cycle" TEXT,
    "scientific_description" TEXT,
    "brief_description" TEXT,
    "feeding" TEXT,
    "behavior" TEXT,
    "interactions" TEXT,
    "chromosomic_number_n" TEXT,
    "molecular_data" TEXT,
    "population_biology" TEXT,
    "threat_status" TEXT,
    "legislation" TEXT,
    "habitat" TEXT,
    "territory" TEXT,
    "endemicity" TEXT,
    "the_uses" TEXT,
    "the_management" TEXT,
    "folklore" TEXT,
    "the_references" TEXT,
    "unstructured_documentation" TEXT,
    "other_information_sources" TEXT,
    "papers" TEXT,
    "identification_keys" TEXT,
    "migratory_data" TEXT,
    "ecological_significance" TEXT,
    "unstructured_natural_history" TEXT,
    "invasiveness_data" TEXT,
    "target_audiences" TEXT,
    "version" TEXT
);


--
-- toc ENTRY 1517 (CLASS 1259 oid 874169)
-- dEPENDENCIES: 6
-- nAME: taxon_description_has_reference_element; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "taxon_description_has_reference_element" (
    "taxon_description_id" NUMERIC not null,
    "reference_element_id" NUMERIC not null,
    "value" TEXT
);


--
-- toc ENTRY 1518 (CLASS 1259 oid 874175)
-- dEPENDENCIES: 6
-- nAME: taxon_has_associated_attribute; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "taxon_has_associated_attribute" (
    "taxon_id" NUMERIC not null,
    "associated_attribute_id" NUMERIC not null,
    "certainty_level" TEXT,
    "evaluation_criteria" TEXT,
    "regionality" TEXT
);


--
-- toc ENTRY 1519 (CLASS 1259 oid 874181)
-- dEPENDENCIES: 6
-- nAME: taxon_has_reference_element; tYPE: table; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

create table "taxon_has_reference_element" (
    "taxon_id" NUMERIC not null,
    "reference_element_id" NUMERIC not null,
    "value" TEXT
);


--
-- toc ENTRY 1787 (CLASS 2606 oid 874188)
-- dEPENDENCIES: 1505 1505
-- nAME: associated_attribute_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "associated_attribute"
    add constraint "associated_attribute_pkey" primary key ("associated_attribute_id");


--
-- toc ENTRY 1789 (CLASS 2606 oid 874190)
-- dEPENDENCIES: 1506 1506
-- nAME: common_name_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "common_name"
    add constraint "common_name_pkey" primary key ("common_name_id");


--
-- toc ENTRY 1791 (CLASS 2606 oid 874192)
-- dEPENDENCIES: 1507 1507
-- nAME: data_provider_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "data_provider"
    add constraint "data_provider_pkey" primary key ("data_provider_id");


--
-- toc ENTRY 1795 (CLASS 2606 oid 874194)
-- dEPENDENCIES: 1509 1509
-- nAME: occurrence_curatorial_extension_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "occurrence_curatorial_extension"
    add constraint "occurrence_curatorial_extension_pkey" primary key ("occurrence_curatorial_extension_id");


--
-- toc ENTRY 1797 (CLASS 2606 oid 874196)
-- dEPENDENCIES: 1510 1510
-- nAME: occurrence_geospatial_extension_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "occurrence_geospatial_extension"
    add constraint "occurrence_geospatial_extension_pkey" primary key ("occurrence_geospatial_extension_id");


--
-- toc ENTRY 1799 (CLASS 2606 oid 874198)
-- dEPENDENCIES: 1511 1511 1511
-- nAME: occurrence_has_reference_element_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "occurrence_has_reference_element"
    add constraint "occurrence_has_reference_element_pkey" primary key ("occurrence_id", "reference_element_id");


--
-- toc ENTRY 1793 (CLASS 2606 oid 874200)
-- dEPENDENCIES: 1508 1508
-- nAME: occurrence_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "occurrence"
    add constraint "occurrence_pkey" primary key ("occurrence_id");


--
-- toc ENTRY 1801 (CLASS 2606 oid 874202)
-- dEPENDENCIES: 1512 1512
-- nAME: reference_element_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "reference_element"
    add constraint "reference_element_pkey" primary key ("reference_element_id");


--
-- toc ENTRY 1803 (CLASS 2606 oid 874204)
-- dEPENDENCIES: 1513 1513
-- nAME: service_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "service"
    add constraint "service_pkey" primary key ("service_id");


--
-- toc ENTRY 1807 (CLASS 2606 oid 874216)
-- dEPENDENCIES: 1515 1515 1515 1515
-- nAME: taxon_associated_attribute_has_reference_element_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "taxon_associated_attribute_has_reference_element"
    add constraint "taxon_associated_attribute_has_reference_element_pkey" primary key ("associated_attribute_id", "reference_element_id", "taxon_id");


--
-- toc ENTRY 1811 (CLASS 2606 oid 874206)
-- dEPENDENCIES: 1517 1517 1517
-- nAME: taxon_description_has_reference_element_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "taxon_description_has_reference_element"
    add constraint "taxon_description_has_reference_element_pkey" primary key ("taxon_description_id", "reference_element_id");


--
-- toc ENTRY 1809 (CLASS 2606 oid 874208)
-- dEPENDENCIES: 1516 1516
-- nAME: taxon_description_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "taxon_description"
    add constraint "taxon_description_pkey" primary key ("taxon_description_id");


--
-- toc ENTRY 1813 (CLASS 2606 oid 874210)
-- dEPENDENCIES: 1518 1518 1518
-- nAME: taxon_has_associated_attribute_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "taxon_has_associated_attribute"
    add constraint "taxon_has_associated_attribute_pkey" primary key ("taxon_id", "associated_attribute_id");


--
-- toc ENTRY 1815 (CLASS 2606 oid 874212)
-- dEPENDENCIES: 1519 1519 1519
-- nAME: taxon_has_reference_element_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "taxon_has_reference_element"
    add constraint "taxon_has_reference_element_pkey" primary key ("taxon_id", "reference_element_id");


--
-- toc ENTRY 1805 (CLASS 2606 oid 874214)
-- dEPENDENCIES: 1514 1514
-- nAME: taxon_pkey; tYPE: constraint; sCHEMA: PUBLIC; oWNER: -; tABLESPACE: 
--

alter table only "taxon"
    add constraint "taxon_pkey" primary key ("taxon_id");


--
-- toc ENTRY 1830 (CLASS 2606 oid 874252)
-- dEPENDENCIES: 1518 1505 1786
-- nAME: from_this_to_associated_attribute_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "taxon_has_associated_attribute"
    add constraint "from_this_to_associated_attribute_fkey" foreign key ("associated_attribute_id") references "associated_attribute"("associated_attribute_id");


--
-- toc ENTRY 1818 (CLASS 2606 oid 874237)
-- dEPENDENCIES: 1508 1507 1790
-- nAME: from_this_to_data_provider_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "occurrence"
    add constraint "from_this_to_data_provider_fkey" foreign key ("data_provider_id") references "data_provider"("data_provider_id");


--
-- toc ENTRY 1826 (CLASS 2606 oid 874297)
-- dEPENDENCIES: 1507 1516 1790
-- nAME: from_this_to_data_provider_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "taxon_description"
    add constraint "from_this_to_data_provider_fkey" foreign key ("provider_id") references "data_provider"("data_provider_id");


--
-- toc ENTRY 1821 (CLASS 2606 oid 874227)
-- dEPENDENCIES: 1508 1511 1792
-- nAME: from_this_to_occurrence_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "occurrence_has_reference_element"
    add constraint "from_this_to_occurrence_fkey" foreign key ("occurrence_id") references "occurrence"("occurrence_id");


--
-- toc ENTRY 1819 (CLASS 2606 oid 874242)
-- dEPENDENCIES: 1792 1509 1508
-- nAME: from_this_to_occurrence_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "occurrence_curatorial_extension"
    add constraint "from_this_to_occurrence_fkey" foreign key ("occurrence_id") references "occurrence"("occurrence_id");


--
-- toc ENTRY 1820 (CLASS 2606 oid 874247)
-- dEPENDENCIES: 1508 1510 1792
-- nAME: from_this_to_occurrence_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "occurrence_geospatial_extension"
    add constraint "from_this_to_occurrence_fkey" foreign key ("occurrence_id") references "occurrence"("occurrence_id");


--
-- toc ENTRY 1832 (CLASS 2606 oid 874262)
-- dEPENDENCIES: 1519 1800 1512
-- nAME: from_this_to_reference_element; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "taxon_has_reference_element"
    add constraint "from_this_to_reference_element" foreign key ("reference_element_id") references "reference_element"("reference_element_id");


--
-- toc ENTRY 1822 (CLASS 2606 oid 874232)
-- dEPENDENCIES: 1511 1512 1800
-- nAME: from_this_to_reference_element_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "occurrence_has_reference_element"
    add constraint "from_this_to_reference_element_fkey" foreign key ("reference_element_id") references "reference_element"("reference_element_id");


--
-- toc ENTRY 1829 (CLASS 2606 oid 874292)
-- dEPENDENCIES: 1517 1512 1800
-- nAME: from_this_to_reference_element_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "taxon_description_has_reference_element"
    add constraint "from_this_to_reference_element_fkey" foreign key ("reference_element_id") references "reference_element"("reference_element_id");


--
-- toc ENTRY 1833 (CLASS 2606 oid 874267)
-- dEPENDENCIES: 1519 1514 1804
-- nAME: from_this_to_taxon; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "taxon_has_reference_element"
    add constraint "from_this_to_taxon" foreign key ("taxon_id") references "taxon"("taxon_id");


--
-- toc ENTRY 1828 (CLASS 2606 oid 874287)
-- dEPENDENCIES: 1808 1516 1517
-- nAME: from_this_to_taxon_description_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "taxon_description_has_reference_element"
    add constraint "from_this_to_taxon_description_fkey" foreign key ("taxon_description_id") references "taxon_description"("taxon_description_id");


--
-- toc ENTRY 1816 (CLASS 2606 oid 874217)
-- dEPENDENCIES: 1804 1514 1506
-- nAME: from_this_to_taxon_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "common_name"
    add constraint "from_this_to_taxon_fkey" foreign key ("taxon_id") references "taxon"("taxon_id");


--
-- toc ENTRY 1817 (CLASS 2606 oid 874222)
-- dEPENDENCIES: 1508 1804 1514
-- nAME: from_this_to_taxon_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "occurrence"
    add constraint "from_this_to_taxon_fkey" foreign key ("taxon_id") references "taxon"("taxon_id");


--
-- toc ENTRY 1831 (CLASS 2606 oid 874257)
-- dEPENDENCIES: 1514 1804 1518
-- nAME: from_this_to_taxon_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "taxon_has_associated_attribute"
    add constraint "from_this_to_taxon_fkey" foreign key ("taxon_id") references "taxon"("taxon_id");


--
-- toc ENTRY 1827 (CLASS 2606 oid 874302)
-- dEPENDENCIES: 1514 1516 1804
-- nAME: from_this_to_taxon_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "taxon_description"
    add constraint "from_this_to_taxon_fkey" foreign key ("taxon_id") references "taxon"("taxon_id");


--
-- toc ENTRY 1823 (CLASS 2606 oid 874272)
-- dEPENDENCIES: 1804 1514 1514
-- nAME: from_this_to_taxon_fkey_1; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "taxon"
    add constraint "from_this_to_taxon_fkey_1" foreign key ("ancestor_taxon_id") references "taxon"("taxon_id");


--
-- toc ENTRY 1824 (CLASS 2606 oid 874277)
-- dEPENDENCIES: 1514 1514 1804
-- nAME: from_this_to_taxon_fkey_2; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "taxon"
    add constraint "from_this_to_taxon_fkey_2" foreign key ("sinonym_taxon_id") references "taxon"("taxon_id");


--
-- toc ENTRY 1825 (CLASS 2606 oid 874282)
-- dEPENDENCIES: 1518 1515 1515 1518 1812
-- nAME: from_this_to_taxon_has_associated_attribute_fkey; tYPE: fk constraint; sCHEMA: PUBLIC; oWNER: -
--

alter table only "taxon_associated_attribute_has_reference_element"
    add constraint "from_this_to_taxon_has_associated_attribute_fkey" foreign key ("taxon_id", "associated_attribute_id") references "taxon_has_associated_attribute"("taxon_id", "associated_attribute_id");


--
-- toc ENTRY 1838 (CLASS 0 oid 0)
-- dEPENDENCIES: 6
-- nAME: PUBLIC; tYPE: acl; sCHEMA: -; oWNER: -
--

revoke all on schema PUBLIC from public;
revoke all on schema PUBLIC from POSTGRES;
grant all on schema PUBLIC to POSTGRES;
grant all on schema PUBLIC to public;


-- cOMPLETED ON 2011-02-15 08:49:49 cst

--
-- pOSTGREsql DATABASE DUMP COMPLETE
--

