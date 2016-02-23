/**********************************************************************
 * BitmapLoader
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

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Darshanr
 * Used to load the Bitmap images from the server.
 */
public class BitmapLoader
{
	public static Bitmap getRemoteImage(final URL aURL) 
	{	  	 
	    try 
	    {
            final URLConnection conn = aURL.openConnection();
            conn.connect();

            final BufferedInputStream
            bis = new BufferedInputStream(conn.getInputStream());

            final Bitmap bm = BitmapFactory.decodeStream(bis);
            return bm;

	    } 
	    catch(Exception e){}

	    return null;

	}

}
