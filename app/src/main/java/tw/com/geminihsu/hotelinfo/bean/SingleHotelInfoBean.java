package tw.com.geminihsu.hotelinfo.bean;

import java.io.Serializable;

/**
 * Created by geminihsu on 16/9/22.
 */
public class SingleHotelInfoBean implements Cloneable ,Serializable {

    private static final long serialVersionUID = 7383952056046335143L;
    private Integer imageEvent;
    private String imageLink;

    private String hotelId;
    private String sortIndex;
    private String hotelName;

    private String localizedName;
    private String nonLocalizedName;
    private String hotelAddress;
    private String hotelCountry;
}
