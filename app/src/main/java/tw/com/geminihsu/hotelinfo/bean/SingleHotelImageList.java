package tw.com.geminihsu.hotelinfo.bean;

import java.io.Serializable;

/**
 * Created by geminihsu on 16/9/22.
 * This is define each hotel image snapshot information
 */
public class SingleHotelImageList implements Cloneable ,Serializable {

    private static final long serialVersionUID = 1586553951765040502L;
    private String url;
    private String thumbnailUrl;
    private String featured;
    private String displayText;

    public SingleHotelImageList clone()  {
        try {
            return (SingleHotelImageList) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
}
