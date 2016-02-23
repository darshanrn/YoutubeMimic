
/**********************************************************************
 * ResultAdapter
 *
 * Copyright (c) 2012: NDS Limited
 *
 * P R O P R I E T A R Y & C O N F I D E N T I A L
 *
 * The copyright of this code and related documentation together with any
 * other associated intellectual property rights are vested in NDS Limited
 * and may not be used except in accordance with the terms of the license
 * that you have entered into with NDS Limited. Use of this material
 * without an express license from NDS Limited shall be an infringement of
 * copyright and any other intellectual property rights that may be
 * incorporated with this material.
 **********************************************************************/
package dash.cd.youtube;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import nds.cd.youtube.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * @author DarshanR
 *Used to add or update the results to list view.
 */
public class ResultAdapter extends BaseAdapter {
	

        private LayoutInflater layout;
        Typeface font;  
        private YoutubeVideoFeed vf;
        private FeedViewHolder feedViewHolder=null;
        private Vector<Bitmap> vecThumbImages=null;
       
        public ResultAdapter(Activity a) 
        {
        	layout=(LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	vecThumbImages=new Vector<Bitmap>();        	
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
         * 
         * this method is executed for each search result to update it into the listview. 
         * this also executed for each item when a user is scrolling in the results presented in listview.
         */
        public View getView(int position, View convertView, ViewGroup parent) 
        {
        	if(convertView==null)
        	{
	        	 convertView = layout.inflate(R.layout.search_item, parent, false);   
	        	 feedViewHolder = new FeedViewHolder();  
	        	 feedViewHolder.imagThumbNail=(ImageView)convertView.findViewById(R.id.result_thumbnail);
	        	 feedViewHolder.videoTitle=(TextView)convertView.findViewById(R.id.result_title);
	        	 feedViewHolder.videoDesc=(TextView)convertView.findViewById(R.id.result_desc);
	        	 feedViewHolder.videoViews=(TextView)convertView.findViewById(R.id.result_views);
	        	 feedViewHolder.videoRating=(RatingBar)convertView.findViewById(R.id.video_rating);
	        	 convertView.setTag(feedViewHolder);   
        	}
        	else
        	{
        		 feedViewHolder = (FeedViewHolder) convertView.getTag();  
        	}

               
            try
            {
				vf=YouTubeActivity.videos.get(position);
			} catch (Exception e1) {
				e1.printStackTrace();
			}


			try {
				if(vecThumbImages.size()<=position)
					vecThumbImages.add(position, BitmapLoader.getRemoteImage(new URL(vf.getThumbNailURL())));
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            try {
            	
            	feedViewHolder.imagThumbNail.setImageBitmap(vecThumbImages.elementAt(position));
            	feedViewHolder.videoTitle.setText(vf.getTitle());
            	String desc=vf.getDescription();
				desc=desc.replaceAll("\\<.*?>","");
            	feedViewHolder.videoDesc.setText(desc);
            	feedViewHolder.videoViews.setText(vf.getViews() +" views");
            	feedViewHolder.videoRating.setRating(vf.getAvgRating());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
            return convertView;
        }


		/* (non-Javadoc)
		 * @see android.widget.Adapter#getCount()
		 * setting the result listview item count
		 */
		public int getCount() 
		{			
			return YouTubeActivity.videos.size();
			
		}


		public Object getItem(int arg0) {
			return null;
		}


		public long getItemId(int position) {
			return position;
		}
		
		/**
		 * @author Darshanr
		 *this used to hold the data of a single search result.
		 */
		class FeedViewHolder   
		{   
			ImageView imagThumbNail=null;
	        TextView videoTitle=null;
	        TextView videoDesc=null;
	        TextView videoViews=null;
	        RatingBar videoRating=null; 
		} 
		
	}



