import java.net.URI;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
 
public class SpeechWebSocketClient {
	private static final String speechTranslateUriTemplate = "wss://dev.microsofttranslator.com/speech/translate?api-version=1.0&from=%1s&to=%2s&x-clientTraceId=%3s&access_token=%4s";
	
	private static String generateWsUrl(String from, String to, String traceId, String accessToken)
	{
    	return String.format(speechTranslateUriTemplate , from, to, traceId, accessToken);
	}
	
    public static void main(String[] args) {
		if(args.length != 3){
			System.out.println("Input the following Parameters:\nClientId ClientSecret AudioFile");
			return;
		}
		
    	AdmAccessToken accessToken = AdmAccessToken.getAccessToken(args[0], args[1]);
    	SslContextFactory sslContextFactory = new SslContextFactory();
        WebSocketClient client = new WebSocketClient(sslContextFactory);
        String inputAudioFile = args[2];
        SpeechTranslationSessionSocket socket = new SpeechTranslationSessionSocket(inputAudioFile);
        try 
        {
        	String from = "en-us";
        	String to = "fr-fr";
        	String traceId = UUID.randomUUID().toString();

        	ClientUpgradeRequest request = new ClientUpgradeRequest();
            //request.setHeader("Authorization", authroizationHeaderValue);

            //Authorization headers does not set the right property in the server so pass the access token in URL
         	String speechTranslateUri = generateWsUrl(from, to, traceId, URLEncoder.encode(accessToken.access_token, "UTF-8"));

            client.start();
            URI echoUri = new URI(speechTranslateUri);
            client.connect(socket, echoUri, request);
            System.out.printf("[%s] Connecting to : %s%n", new Date().toString(), echoUri);
            //wait enough so the server has ample time to respond 
            socket.awaitClose(30, TimeUnit.SECONDS);
        } 
        catch (Throwable t) 
        {
            t.printStackTrace();
        }
        finally 
        {
            try 
            {
                client.stop();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
    }
}