/**********************************************************************
 * XMLParser
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

import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author Darshanr
 * @created Jun21, 2012
 */
public class XMLParser {

    public static Vector<YoutubeVideoFeed> fetchYoutubeVideos(String xmlString) {

        Vector<YoutubeVideoFeed> videos = new Vector<YoutubeVideoFeed>();
        Document dom = getDocumentBuilder(xmlString);
	    Element docEle = dom.getDocumentElement();

        //get a nodelist of elements
        NodeList entryList = docEle.getElementsByTagName("entry");
        if (entryList != null && entryList.getLength() > 0) 
        {
            for (int i = 0; i < entryList.getLength(); i++)
            {
                YoutubeVideoFeed tempVideo = new YoutubeVideoFeed();

                //get the entry element
                Element entry = (Element) entryList.item(i);

                //get the media:list element inside it
                Element mediaElement = (Element) ((NodeList) entry.getElementsByTagName("media:group")).item(0);

                String category = null;
                String videoURL = null;
                String description = null;
                String keywords = null;
                String playerURL = null;
                String thumbnailURL = null;
                String title = null;
                String duration = null;
                String threegpurl = null;
                float avgRating = 0;
                int noOfViews = 0;
                int likes = 0;
                int dislikes = 0;
                
                try 
                {
                    category = mediaElement.getElementsByTagName("media:category").item(0).getAttributes().getNamedItem("label").getNodeValue();
                    videoURL = mediaElement.getElementsByTagName("media:content").item(0).getAttributes().getNamedItem("url").getNodeValue();
                } catch (NullPointerException npe) {
                    System.out.println("NullPointerException while getting the video content..");
                    continue;
                }
                try {
                    description = mediaElement.getElementsByTagName("media:description").item(0).getTextContent();
                    keywords = mediaElement.getElementsByTagName("media:keywords").item(0).getTextContent();
                    playerURL = mediaElement.getElementsByTagName("media:player").item(0).getAttributes().getNamedItem("url").getNodeValue();
                    threegpurl = mediaElement.getElementsByTagName("media:content").item(0).getAttributes().getNamedItem("url").getNodeValue();
                    thumbnailURL = mediaElement.getElementsByTagName("media:thumbnail").item(0).getAttributes().getNamedItem("url").getNodeValue();
                    title = mediaElement.getElementsByTagName("media:title").item(0).getTextContent();
                    duration = mediaElement.getElementsByTagName("yt:duration").item(0).getAttributes().getNamedItem("seconds").getNodeValue();
                    
                    avgRating = Float.parseFloat(((NodeList) entry.getElementsByTagName("gd:rating")).item(0).getAttributes().getNamedItem("average").getTextContent());
                    noOfViews = Integer.parseInt(((NodeList) entry.getElementsByTagName("yt:statistics")).item(0).getAttributes().getNamedItem("viewCount").getNodeValue());
                    likes =  Integer.parseInt(((NodeList) entry.getElementsByTagName("yt:rating")).item(0).getAttributes().getNamedItem("numLikes").getNodeValue());
                    dislikes = Integer.parseInt(((NodeList) entry.getElementsByTagName("yt:rating")).item(0).getAttributes().getNamedItem("numDislikes").getNodeValue());
                } catch (NullPointerException npe) {
                    System.out.println("NullPointerException while getting the additional details..");
                    duration = "0";
                    npe.printStackTrace();
                }
                
                tempVideo.setCategory(category);
                tempVideo.setTitle(title);
                tempVideo.setDescription(description);
                tempVideo.setVideoLinkURL(videoURL);
                tempVideo.setThumbNailURL(thumbnailURL);
                tempVideo.setKeyWords(keywords);
                tempVideo.setPlayerURL(playerURL);
                tempVideo.setDuration(Integer.parseInt(duration));
                tempVideo.setAvgRating(avgRating);
                tempVideo.setViews(noOfViews);
                tempVideo.setLikes(likes);
                tempVideo.setDislikes(dislikes);
                tempVideo.setthreegpurl(threegpurl);
                
                videos.addElement(tempVideo);
            }
        }

        return videos;
    }

    private static Document getDocumentBuilder(String xmlString)
    {
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try 
        {
            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource inputSrc = new InputSource();
            inputSrc.setCharacterStream(new StringReader(xmlString));

            //parse using builder to get DOM representation of the XML file
            Document dom = db.parse(inputSrc);

            return dom;
        } catch (ParserConfigurationException pce) {
            System.out.println("ParserConfigurationException while creating the Document ");
            pce.printStackTrace();
            return null;
        } catch (SAXException se) {
            System.out.println("SAXException while creating the Document ");
            se.printStackTrace();
            return null;
        } catch (IOException ioe) {
            System.out.println("IOException while creating the Document ");
            ioe.printStackTrace();
            return null;
        }
    }    
}
