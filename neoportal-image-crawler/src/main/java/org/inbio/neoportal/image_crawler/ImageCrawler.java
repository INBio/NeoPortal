package org.inbio.neoportal.image_crawler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.io.IOUtils;
import org.inbio.neoportal.image_crawler.flickr.Flickr;
import org.inbio.neoportal.image_crawler.flickr.GroupPoolsInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;


/**
 * Crawle and index images from m3s and flickr groups
 * @author avargas
 *
 */
public class ImageCrawler 
{
	
	/**
	 * 
	 */
	private static final int THREADS = 10;
	
	
    @SuppressWarnings("static-access")
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Options options = new Options();
        options.addOption(OptionBuilder
        					.withDescription("Collect new images from flickr")
        					.create("flickr"));
        options.addOption(OptionBuilder
        					.withArgName("csv")
        					.withDescription("Import images from csv file")
        					.create("m3s"));
        
        try {
			CommandLineParser parser = new GnuParser();
			CommandLine cmd = parser.parse(options, args);
			
			ApplicationContext context = 
	                new ClassPathXmlApplicationContext("applicationContext.xml");
			
			if(cmd.hasOption("flickr")) {
				Indexer indexer = context.getBean(Indexer.class);
				indexer.indexFlickr(THREADS);
			}
			else if(cmd.hasOption("m3s")) {
				Indexer indexer = context.getBean(Indexer.class);
				indexer.indexM3s(THREADS, cmd.getOptionValue("csv"));
			}
			else {
				// automatically generate the help statement
				 HelpFormatter formatter = new HelpFormatter();
				 formatter.printHelp( "image-crawler", options );
			}
			
		} catch (org.apache.commons.cli.ParseException exp) {
			// automatically generate the help statement
			 HelpFormatter formatter = new HelpFormatter();
			 formatter.printHelp( "image-crawler", options );
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }
}
