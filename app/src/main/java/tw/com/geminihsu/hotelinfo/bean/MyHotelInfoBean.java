package tw.com.geminihsu.hotelinfo.bean;

import java.io.Serializable;

/**
 * Created by geminihsu on 16/9/21.
 */
public class MyHotelInfoBean implements Cloneable ,Serializable {


    private static final long serialVersionUID = 6975343483449291796L;


    private Integer imageEvent;
    private String imageLink;

    private String hotelId;
    private String sortIndex;
    private String hotelName;

    private String localizedName;
    private String nonLocalizedName;
    private String hotelAddress;

    private String city;
    private String stateProvinceCode;
    private String countryCode;
    private String postalCode;
    private String shortDescription;
    public MyHotelInfoBean clone()  {
        try {
            return (MyHotelInfoBean) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public Integer getImageEvent() {
        return imageEvent;
    }

    public void setImageEvent(Integer imageEvent) {
        this.imageEvent = imageEvent;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvinceCode() {
        return stateProvinceCode;
    }

    public void setStateProvinceCode(String stateProvinceCode) {
        this.stateProvinceCode = stateProvinceCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }



}
