package org.inbio.neoportal.image_crawler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import org.json.JSONArray;
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
			
			ApplicationContext appContext = 
	                new ClassPathXmlApplicationContext("applicationContext.xml");
			
			if(cmd.hasOption("crawler")) {
				ImageCrawler imageCrawler = appContext.getBean(ImageCrawler.class);
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
		
		// get image list from flickr
		getFlickrImages();
		
		// loop the list and start threads
		
		
		// This will make the executor accept no new threads
	    // and finish all existing threads in the queue
	    executor.shutdown();
	    // Wait until all threads are finish
	    try {
	    	  executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
	    	} catch (InterruptedException e) {
	    	  
	    	}
	    System.out.println("Finished all threads");
	}
    
    private List getFlickrImages(){
    	String flickrPoolUrl = "http://api.flickr.com/services/rest/?method=flickr.groups.pools.getPhotos&api_key=5b084e281a611c38f0c045b969c7829d&group_id=2235531%40N20&format=json&nojsoncallback=1";
    	InputStream in = null;
    	com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    	mapper.registerModule((Module)new JsonOrgModule());
    	
    	try {
			in = new URL(flickrPoolUrl).openStream();
			
			String json = IOUtils.toString(in);
			
			JSONArray jsonArray = mapper.readValue(json, JSONArray.class);
			
			
			List<String> result = new ArrayList<String>();
			
			return result;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
		}
    	
    	return null;
    }
}
