/**********************************************************************
 * VideoFeed
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

/**
 * @author Darshanr
 * @created Jun 21, 2012
 */
public class YoutubeVideoFeed {

    private String title;
    private String category;
    private String description;
    private String thumbNailURL;
    private String videoLinkURL;
    private String playerURL;
    private String keyWords;
    private int views;
    private float avgRating;
    private int likes;
    private int dislikes;
    private int duration; //seconds
    private String threegpurl;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the thumbNailURL
     */
    public String getThumbNailURL() {
        return thumbNailURL;
    }

    /**
     * @param thumbNailURL the thumbNailURL to set
     */
    public void setThumbNailURL(String thumbNailURL) {
        this.thumbNailURL = thumbNailURL;
    }

    /**
     * @return the videoLinkURL
     */
    public String getVideoLinkURL() {
        return videoLinkURL;
    }

    /**
     * @param videoLinkURL the videoLinkURL to set
     */
    public void setVideoLinkURL(String videoLinkURL) {
        this.videoLinkURL = videoLinkURL;
    }

    /**
     * @return the keyWords
     */
    public String getKeyWords() {
        return keyWords;
    }

    /**
     * @param url to set
     */
    public void setthreegpurl(String url) {
        this.threegpurl = url;
    }
    
    /**
     * @param url to get
     */
    public String getthreegpurl() {
        return threegpurl;
    }
    
    /**
     * @param keyWords the keyWords to set
     */
    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    /**
     * @return the views
     */
    public int getViews() {
        return views;
    }

    /**
     * @param views the views to set
     */
    public void setViews(int views) {
        this.views = views;
    }

    /**
     * @return the avgRating
     */
    public float getAvgRating() {
        return avgRating;
    }

    /**
     * @param avgRating the avgRating to set
     */
    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    /**
     * @return the likes
     */
    public int getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * @return the dislikes
     */
    public int getDislikes() {
        return dislikes;
    }

    /**
     * @param dislikes the dislikes to set
     */
    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    /**
     * @return the playerURL
     */
    public String getPlayerURL() {
        return playerURL;
    }

    /**
     * @param playerURL the playerURL to set
     */
    public void setPlayerURL(String playerURL) {
        this.playerURL = playerURL;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String toString() {
        return "{"
                + "title : " + title + " , "
                + "category : " + category + ", "
                + "description : " + description + " , "
                + "thumbNailURL : " + thumbNailURL + " , "
                + "videoLinkURL : " + videoLinkURL + " , "
                + "playerURL : " + playerURL + " , "
                + "keyWords : " + keyWords + " , "
                + "duration : " + duration
                + "}";
    }
}
