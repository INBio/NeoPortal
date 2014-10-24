/*
 * NeoPortal - New implementation of the INBio Species and Occurrences portal.
 * 
 * Copyright (C) 2010 INBio - Instituto Nacional de Biodiversidad, Costa Rica
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.neoportal.index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.inbio.neoportal.core.entity.CommonName;
import org.inbio.neoportal.core.entity.GeoFeature;
import org.inbio.neoportal.core.entity.Image;
import org.inbio.neoportal.core.entity.Location;
import org.inbio.neoportal.core.entity.OccurrenceDwc;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.entity.TaxonDescription;
import org.inbio.neoportal.index.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


/**
 * 
 * @author asanabria <asanabria@inbio.ac.cr>
 */
@Component
public class Indexer {

  @Autowired
  Importer importer;

  @Autowired
  private SessionFactory sessionFactory;

  public Indexer() {
    super();
  }

  /**
   * Create the Lucene index for the indicated class. This method purge the existing documents
   * before indexing.
   * 
   * @param classToIndex The name of the class to reindex all - for all indexable classes (Taxon,
   *        CommonName, TaxonDescription, GeoFeature, Location, Ocurrences)
   * @throws InterruptedException
   */
  public void createIndex(String classToIndex) throws InterruptedException {

    // list of indexable classes
    HashMap<String, Object> classList = new HashMap<String, Object>();
    classList.put("Taxon", Taxon.class);
    classList.put("CommonName", CommonName.class);
    classList.put("TaxonDescription", TaxonDescription.class);
    classList.put("GeoFeature", GeoFeature.class);
    classList.put("Location", Location.class);
    classList.put("Occurrences", OccurrenceDwc.class);
    classList.put("Image", Image.class);

    System.out.println("Creating Lucene index\n");
    Session session = sessionFactory.openSession();

    FullTextSession fullTextSession = Search.getFullTextSession(session);

    System.out.println("# - Inicio de la indexación \n");

    if (classToIndex.equals("all")) {
      for (String indexKey : classList.keySet()) {
        System.out.println("# - " + indexKey);
        fullTextSession.purgeAll((Class) classList.get(indexKey));
        fullTextSession.createIndexer((Class) classList.get(indexKey)).startAndWait();
      }
    } else {
      System.out.println("# - " + classToIndex);
      fullTextSession.purgeAll((Class) classList.get(classToIndex));
      fullTextSession.createIndexer((Class) classList.get(classToIndex)).startAndWait();
    }

    System.out.println("# - Fin de la indexación de " + classToIndex + "\n");
  }

  /**
   * Process the arguments that comes from the console.
   * 
   * @param args [action, search terms]
   * @throws InterruptedException
   */
  @SuppressWarnings("static-access")
  public void processArguments(String[] args) throws InterruptedException {

    // configure the command line options
    Options options = new Options();
    options
        .addOption(OptionBuilder
            .withArgName("class to index")
            .hasArgs(1)
            .withDescription(
                "Index an entity (all|Taxon|CommonName|TaxonDescription|GeoFeature|Location|Occurrences)")
            .create("index"));
    options
        .addOption(OptionBuilder
            .withArgName("csvFile")
            .hasArgs(1)
            .withDescription("Import and index taxonomy")
            .create("t"));
    options
        .addOption(OptionBuilder
            .withArgName("csvFile")
            .hasArgs(1)
            .withDescription("Import and index occurrences")
            .create("o"));
    options
        .addOption(OptionBuilder
            .isRequired(false)
            .withDescription("Index occurrences from import_dwc table")
            .withLongOpt("dwc-index")
            .create());
    options
        .addOption(OptionBuilder
            .isRequired(false)
            .withDescription("Just insert records, do not run indexer")
            .withLongOpt("insert-only")
            .create());

    try {
      CommandLineParser parser = new org.apache.commons.cli.GnuParser();
      CommandLine cmd = parser.parse(options, args);

      // execute the appropriate functions
      if (cmd.hasOption("index")) {
        String entityName = cmd.getOptionValue("index");
        this.createIndex(entityName);
        // System.out.println("option index");
      } else if (cmd.hasOption("t")) {
        String csvFile = cmd.getOptionValue("t");
        importer.importTaxonomy(csvFile);
        if(!cmd.hasOption("insert-only"))
            createIndex("Taxon");
        // System.out.println("option t");
      } else if (cmd.hasOption("o")) {
        String csvFile = cmd.getOptionValue("o");
//        importer.importIndexOccurrences(csvFile);
        importer.importDwcOccurrences(csvFile);
        importer.indexOccurrences();
      } else if (cmd.hasOption("dwc-index")) {
        importer.indexOccurrences();
      } else {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("indexer", options);
      }


      // Importer importer = new Importer();
      // importer.importOccurrences();
      // importer.importAll(
      // "/home/arturo/Proyectos/atta2-portal/AttaExport/GeoCapas/AttaGeoLayersFromSnaps_v03.csv",
      // "/home/arturo/Proyectos/atta2-portal/AttaExport/GeoCapas/AttaProvincias.csv",
      // "/home/arturo/Proyectos/atta2-portal/AttaExport/GeoCapas/AttaGeoSitesFromSnaps_v03.csv",
      // "/home/arturo/Proyectos/atta2-portal/AttaExport/ubis_20120518_2.csv");

    } catch (org.apache.commons.cli.ParseException e) {
      System.out.print("Parse error: ");
      System.out.println(e.getMessage());

      // automatically generate the help statement
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("indexer", options);
    }

  }

  /**
   * Search using all the indexed fields
   * 
   * @param searchText
   * @throws ParseException
   */
  @Deprecated
  public void searchByAll(String searchText) throws ParseException {



    String[] taxon =
        new String[] {"defaultName", "kingdom", "division", "class_", "order", "family", "genus",
            "species"};

    String[] occurrence =
        new String[] {"scientificName", "higherTaxon", "kingdom", "phylum", "class_", "orders",
            "family", "genus", "specificEpithet", "country", "stateProvince", "county", "locality"};

    String[] taxonDescription =
        new String[] {"scientificName", "kingdomTaxon", "phylumTaxon", "classTaxon", "orderTaxon",
            "familyTaxon", "genusTaxon", "synonyms", "commonNames"};

    ArrayList<String> fieldList = new ArrayList<String>();

    fieldList.addAll(Arrays.asList(taxon));
    fieldList.addAll(Arrays.asList(occurrence));
    fieldList.addAll(Arrays.asList(taxonDescription));

    this.executeSearch(fieldList.toArray(new String[fieldList.size()]), searchText);

  }

  /**
   * Look for searchText in the index using the <code>fields</code> argument
   * 
   * @param fields
   * @param searchText
   * @throws ParseException
   */
  @Deprecated
  private void executeSearch(String[] fields, String searchText) throws ParseException {

    HibernateUtil.getSessionFactory();
    Session session = HibernateUtil.getSessionFactory().openSession();

    FullTextSession fullTextSession = Search.getFullTextSession(session);
    Transaction tx = fullTextSession.beginTransaction();

    // create native Lucene query
    MultiFieldQueryParser parser =
        new MultiFieldQueryParser(Version.LUCENE_33, fields,
            new StandardAnalyzer(Version.LUCENE_33));

    org.apache.lucene.search.Query query = null;

    // FIXME Manejo de errores
    try {
      System.out.println("Buscando: " + searchText);
      query = parser.parse(searchText);
    } catch (ParseException ex) {
      Logger.getLogger(Taxon.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Wrap Lucene query in a org.hibernate.Query
    FullTextQuery hsQuery = fullTextSession.createFullTextQuery(query, Taxon.class);

    // for paginated search
    hsQuery.setMaxResults(20);
    hsQuery.setFirstResult(0);

    int totalAmount = hsQuery.getResultSize();
    // execute search
    System.out.println("#-> Result Count " + hsQuery.getResultSize());

    List<Taxon> result = null;

    // Show the results page by page (20 items each).
    for (int i = 0; i <= totalAmount;) {

      result = ((org.hibernate.Query) hsQuery).list();
      /*
       * for(Taxon res : result) System.out.println("#-> "+res.getSynonyms()
       * +" => "+res.getScientificName());
       */


      for (Taxon res : result)
        System.out.println("#-> " + res.getDefaultName());


      i += 20;
      hsQuery.setFirstResult(i);
      System.out.println("#end page =================================#");
    }
    tx.commit();
    session.close();
  }

  /**
   * Entry point fot the application
   * 
   * @param args
   * @throws InterruptedException
   * @throws ParseException
   * @throws org.apache.commons.cli.ParseException
   */
  public static void main(String[] args) throws InterruptedException, ParseException,
      org.apache.commons.cli.ParseException {

    ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    Indexer i = appContext.getBean(Indexer.class);
    i.processArguments(args);
    
    System.exit(0);

  }
}
