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
@Component
public class ImageCrawler 
{
	
	@Autowired
	private static ApplicationContext context;
	
    @SuppressWarnings("static-access")
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Options options = new Options();
        options.addOption(OptionBuilder
        					.withDescription("Collect new images from flickr")
        					.create("crawler"));
        
        try {
			CommandLineParser parser = new GnuParser();
			CommandLine cmd = parser.parse(options, args);
			
//			ApplicationContext appContext = 
//	                new ClassPathXmlApplicationContext("applicationContext.xml");
			
			if(cmd.hasOption("crawler")) {
				ImageCrawler imageCrawler = context.getBean(ImageCrawler.class);
				imageCrawler.index(1);
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
    
    /*
     * Get images from flickr and start threads for 
     * associate taxon and occurrence and then index 
     */
    private void index(int threads) {
		ExecutorService executor = Executors.newFixedThreadPool(threads);
		
		Properties properties = new Properties();
		String flickrApiKey = null;
		String groupId = null;
		
		try {
			properties.load(getClass().getResourceAsStream("/config.properties"));
			flickrApiKey = properties.getProperty("flickr_api_key");
			groupId = properties.getProperty("group_id");
			
			// get image list from flickr
			Flickr flickr = new Flickr(flickrApiKey);
			GroupPoolsInterface groupPoolsInterface = flickr.getGroupPoolsInterface();
			JSONArray photos;
			
			while( groupPoolsInterface.hasNext()){
				photos = groupPoolsInterface.nextPhotosPage(groupId);
				// loop the list and start threads
				for (int i = 0; i < photos.length(); i++) {
					ImageIndexer imageIndexer = (ImageIndexer)context.getBean("ImageIndexer", photos.getJSONObject(i));
					//imageIndexer = new ImageIndexer(photos.getJSONObject(i));
					System.out.println("schedule: " + photos.getJSONObject(i).getString("title"));
					executor.execute(imageIndexer);
				}
			}
			// This will make the executor accept no new threaImagesds
		    // and finish all existing threads in the queue
		    executor.shutdown();
		    // Wait until all threads are finish
	    	executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    	} catch (InterruptedException e) {
    	  
    	} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Finished all threads");
	}
    
    
}
