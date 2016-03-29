import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

//Sample class to get languages from api/languages api
public class GetLanguages {
	
	public static void main(String[] args) {
		final String getLanguageUrl = "https://dev.microsofttranslator.com/languages?api-version=1.0";
		final String charset = StandardCharsets.UTF_8.name();
		
		try
		{
			String scope = "speech";
			String query = String.format("scope=%s", URLEncoder.encode(scope, charset));
			URL url = new URL(getLanguageUrl + "&" + query);
	        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			
	        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
	        {
	            // OK
	            try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())))
	            {
		            StringBuffer responseString = new StringBuffer(); 
		            String line;
		            while ((line = reader.readLine()) != null)
		            {
		            	responseString.append(line);
		            }
		            System.out.println(responseString.toString());
		          }
	        }     
	 	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}


