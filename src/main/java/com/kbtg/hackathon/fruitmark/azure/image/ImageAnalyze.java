package com.kbtg.hackathon.fruitmark.azure.image;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

public class ImageAnalyze {
	
    // **********************************************
    // *** Update or verify the following values. ***
    // **********************************************

    // Replace <Subscription Key> with your valid subscription key.
	@Value("${azure.subscription.key}")
    private String subscriptionKey;

    // You must use the same Azure region in your REST API method as you used to
    // get your subscription keys. For example, if you got your subscription keys
    // from the West US region, replace "westcentralus" in the URL
    // below with "westus".
    //
    // Free trial subscription keys are generated in the "westus" region.
    // If you use a free trial subscription key, you shouldn't need to change
    // this region.
	@Value("${azure.cognitive.uri}")
    private String uriBase;

    private String imageToAnalyze;
    
    public String analzye(String uriImage) {
    	
    	this.imageToAnalyze = uriImage;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            URIBuilder builder = new URIBuilder(uriBase);

            // Request parameters. All of them are optional.
            builder.setParameter("visualFeatures", "Categories,Description,Color");
            builder.setParameter("language", "en");

            // Prepare the URI for the REST API method.
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Request body.
            
            StringEntity requestEntity =
                    new StringEntity("{\"url\":\"" + imageToAnalyze + "\"}");
            request.setEntity(requestEntity);
            System.out.println("url : " + requestEntity);
            // Call the REST API method and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
            	System.out.println(EntityUtils.toString(entity));
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                JSONObject text = (JSONObject) json.getJSONObject("description").getJSONArray("captions").get(0);
                System.out.println(text.getString("test"));
                
                return text.getString("test");
            }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }
        
        return null;

    }

}
