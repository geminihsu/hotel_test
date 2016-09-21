package tw.com.geminihsu.hotelinfo.bean;

import java.io.Serializable;

/**
 * Created by geminihsu on 16/9/21.
 */
public class MyHotelInfoBean implements Cloneable ,Serializable {


    private static final long serialVersionUID = 6975343483449291796L;

    //以下為針對收到通知顯示的欄位變數
    private Integer imageEvent;
    private String hostServerName;
    private String hostServerIP;
    private String hostServerCommandPort;

    private String hostServerWebPort;//GV-monior會走web port

    private String hostServerMac;
    private String hostServerCameraIndex;

    public MyHotelInfoBean clone()  {
        try {
            return (MyHotelInfoBean) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

}
