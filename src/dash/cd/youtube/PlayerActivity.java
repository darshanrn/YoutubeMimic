package dash.cd.youtube;

import nds.cd.youtube.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.VideoView;

public class PlayerActivity extends Activity
{

	private Bundle intentExtras = null;
	private GestureDetector gestureScanner;	
	ProgressDialog pd = null;
	
	@Override
	public void onCreate(Bundle instance)
	{
		super.onCreate(instance);
		setContentView(R.layout.playerview);
		
		intentExtras = getIntent().getExtras();
        String searchString = (String) intentExtras.get("SearchString");
        
        pd = ProgressDialog.show(this, null, "Buffering in progress..");
        VideoView mVideoView = (VideoView)findViewById(R.id.videoView);
        mVideoView.setOnPreparedListener(new OnPreparedListener() {
			
			public void onPrepared(MediaPlayer arg0) {
				pd.dismiss();
				
			}
		});
        mVideoView.setVideoURI(Uri.parse(searchString));
        mVideoView.start();
        
        
        
        gestureScanner = new GestureDetector(this,simpleOnGestureListener);
        
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureScanner.onTouchEvent(event);
    }
	
	GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

        public boolean onDown(MotionEvent event) {
           return true;
        }

        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) 
        {
            synchronized (event2) 
            {
            	try {event2.wait(16);} catch (InterruptedException e) {e.printStackTrace();}
            	
            	if(event2.getAction() == MotionEvent.ACTION_UP)
                {
                	Log.i("Message", "Flicked");
                }
			}
            
            
            return false;
            
        }

    };
        
}
