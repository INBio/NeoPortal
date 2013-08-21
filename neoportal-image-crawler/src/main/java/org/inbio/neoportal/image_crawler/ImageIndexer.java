package org.inbio.neoportal.image_crawler;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ImageIndexer implements Runnable {

	private JSONObject image;
	
	/**
	 * 
	 */
	public ImageIndexer(JSONObject image) {
		this.image = image;
	}
	
	@Override
	public void run() {

		try {
			
			System.out.println("image: " + this.image.getString("title"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
