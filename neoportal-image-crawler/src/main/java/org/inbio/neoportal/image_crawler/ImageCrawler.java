package org.inbio.neoportal.image_crawler;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


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
        					.hasArg()
        					.withDescription("Import images from csv file")
        					.create("m3s"));
        
        try {
			CommandLineParser parser = new org.apache.commons.cli.PosixParser();
			CommandLine cmd = parser.parse(options, args);
			
			ApplicationContext context = 
	                new ClassPathXmlApplicationContext("applicationContext.xml");
			
			if(cmd.hasOption("flickr")) {
				Indexer indexer = context.getBean(Indexer.class);
				indexer.indexFlickr(THREADS);
			}
			else if(cmd.hasOption("m3s")) {
				Indexer indexer = context.getBean(Indexer.class);
				indexer.indexM3s(THREADS, cmd.getOptionValue("m3s"));
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
