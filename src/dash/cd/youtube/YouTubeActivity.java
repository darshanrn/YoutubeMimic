package dash.cd.youtube;

import java.util.Vector;

import nds.cd.youtube.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class YouTubeActivity extends Activity {

	int GET_YOUTUBE_VIDEO_LIST = 1003;
	public static Vector<YoutubeVideoFeed> videos = null;
	private Cargo feedCargo = null;
	private ProgressDialog pd = null;
	private Intent intent = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target);
       
        new progressDialogThread().start(); //progress bar
        
        String searchString = "kolaveridi";
        
        feedCargo = new Cargo();
        feedCargo.setServiceType(GET_YOUTUBE_VIDEO_LIST);
        feedCargo.setPayload(new String("q=" + searchString +"&start-index=1&max-results=20&v=2"));
        System.out.println("send the request !!");
        new YouTubeSearchThread().start(); // search related videos via thread  
        
        intent = new Intent(this, PlayerActivity.class);
        
	}
    
    @SuppressWarnings("unchecked")
	public class YouTubeSearchThread extends Thread 
	{    	
		public void run() 
    	{
			feedCargo.setPayload(YouTubeManager.INSTANCE.getSearchVideoList(feedCargo));    			
			videos = (Vector<YoutubeVideoFeed>)feedCargo.getPayload();
            
	        for (dash.cd.youtube.YoutubeVideoFeed youtubeVideo : videos) 
	        {
	        	Log.i("Message", "************************************");
	            Log.i("Title", youtubeVideo.getTitle());
	            Log.i("Thumbnails",  youtubeVideo.getThumbNailURL());
	            Log.i("PlayerURL", youtubeVideo.getPlayerURL());
	            Log.i("VideoLinkURL", youtubeVideo.getVideoLinkURL());
	            Log.i("threeGPURL", youtubeVideo.getthreegpurl());
	            Log.i("Description", youtubeVideo.getDescription());
	            Log.i("Avg Rating", Float.toString(youtubeVideo.getAvgRating()));
	            Log.i("Views",Integer.toString( youtubeVideo.getViews()));
	            Log.i("Message", "************************************");
	        }
	        
	        //Update List
	        listHandler.sendEmptyMessage(0);	        
    	}
		
		Handler listHandler = new Handler() 
	    {
	        @Override
	        public void handleMessage(Message msg) 
	        {
	        	updateList();	        	
			}
	    };
		    
	    private void updateList()
	    {
	    	try
	    	{
		    	ListView listView=(ListView)findViewById(R.id.list_results);
		        ResultAdapter adapter = new ResultAdapter(YouTubeActivity.this);
				listView.setAdapter(adapter);
				pd.dismiss();
				
				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		        {		
        			public void onItemClick(AdapterView<?> adapterView, View view,int position, long arg3)
        		    {	
        				YoutubeVideoFeed video =(YoutubeVideoFeed)videos.get(position);
        				
        				/*1*/        			
//        				String videoId = video.getPlayerURL().split("=")[1].split("&")[0];
        				intent.putExtra("SearchString", video.getthreegpurl());
//        				intent.putExtra("SearchString", "vnd.youtube://" + videoId);       				
        				startActivity(intent);
        				
        				/*2*/
        				
//        				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + videoId)));
        				
        				/*3*/
//        				String videoId = video.getPlayerURL().split("=")[1].split("&")[0];
//        				intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + videoId));
//                        if(isAppInstalled("com.google.android.youtube", getApplicationContext())) 
//                        {
//                            intent.setClassName("com.google.android.youtube", "com.google.android.youtube.WatchActivity");
//                        }
//                        startActivity(intent);
        		    }	        
	    		});
				
	    	}
	    	catch(Exception Ex)
	    	{
	    		Ex.printStackTrace();
	    	}
	    }	
	    
	    private boolean isAppInstalled(String uri, Context context) {
	        PackageManager pm = context.getPackageManager();
	        boolean installed = false;
	        try {
	            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
	            installed = true;
	        } catch (PackageManager.NameNotFoundException e) {
	            installed = false;
	        }
	        return installed;
	    }
	}
    
    
    	
    public class progressDialogThread extends Thread 
   	{
       	Handler mHandler = new Handler() 
       	{
   	        @Override
   	        public void handleMessage(Message msg) 
   	        {
   	        	displayProgressDialog("Loading...");	        	
   			}
   	    };
       	public void run() 
       	{
       		mHandler.sendEmptyMessage(0);
       		try {
   				Thread.sleep(10000);
   			} catch (InterruptedException e) {}
       		if(pd != null)
       			pd.dismiss();
       	}
       	
       	private void displayProgressDialog(String message)
    	{
    		pd = ProgressDialog.show(YouTubeActivity.this,"", message);        	
    	} 
   	}
}