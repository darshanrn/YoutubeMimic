package dash.cd.youtube;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

import com.google.gdata.client.Service;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.util.ServiceException;

public class YouTubeManager {
 
    private String developerKey = null;
    private YouTubeService service;
    public static YouTubeManager INSTANCE = new YouTubeManager("nds");

    private YouTubeManager(String clientID) {
        //unique key
        this.developerKey = "AI39si5JFfM-Q4VhTGMKUvg060CG_2r7GlwavnwWqszJGjlB_gDXgrmknyoxAAyndeo9UdiW_DhPRYY6oyENX_-nmW1jm5MV1g";
        service = new YouTubeService(clientID, developerKey);
    }
 
    public Vector<YoutubeVideoFeed> getSearchVideoList(Cargo cargo)
    {
        try 
        {
            System.out.println("getSearchVideoList !!");

            String searchString = (String) cargo.getPayload();
            System.out.println("searchString : "+searchString);
            String urlString = "http://gdata.youtube.com/feeds/mobile/videos?" + searchString;
            System.out.println("urlString :: "+urlString);
            Service.GDataRequest gRequest = service.createFeedRequest(new URL(urlString));

            gRequest.execute();

            InputStream gInput = gRequest.getResponseStream();
            StringBuffer strBuffer = new StringBuffer();
            int l = 0;
            while ((l = gInput.read()) != -1) 
                strBuffer.append((char) l);
            
            Vector<YoutubeVideoFeed> videoList = XMLParser.fetchYoutubeVideos(strBuffer.toString());
            return videoList;
        } catch (IOException ex) {
            System.out.println("IOException while getting the search result");
            ex.printStackTrace();
            System.exit(1);            
        } catch (ServiceException ex) {
            System.out.println("ServiceException while getting the search result");
            ex.printStackTrace();
            System.exit(1);
        } 

        return null;
    }
}

