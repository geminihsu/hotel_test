package tw.com.geminihsu.hotelinfo.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by geminihsu on 16/9/22.
 */
public class SingleHotelInfoBean implements Cloneable ,Serializable {

    private static final long serialVersionUID = 7383952056046335143L;

    private String imageLink;

    private String hotelId;
    private String hotelName;

    private String localizedName;
    private String nonLocalizedName;
    private String hotelAddress;
    private String hotelCountry;
    private String latitude;
    private String longitude;
    private String longDescription;
    private String telesalesNumber;
    private String rating;
    private ArrayList<SingleHotelImageList> hotelImageLists;

    public SingleHotelInfoBean clone()  {
        try {
            return (SingleHotelInfoBean) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getNonLocalizedName() {
        return nonLocalizedName;
    }

    public void setNonLocalizedName(String nonLocalizedName) {
        this.nonLocalizedName = nonLocalizedName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelCountry() {
        return hotelCountry;
    }

    public void setHotelCountry(String hotelCountry) {
        this.hotelCountry = hotelCountry;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public ArrayList<SingleHotelImageList> getHotelImageLists() {
        return hotelImageLists;
    }

    public void setHotelImageLists(ArrayList<SingleHotelImageList> hotelImageLists) {
        this.hotelImageLists = hotelImageLists;
    }

    public String getTelesalesNumber() {
        return telesalesNumber;
    }

    public void setTelesalesNumber(String telesalesNumber) {
        this.telesalesNumber = telesalesNumber;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
