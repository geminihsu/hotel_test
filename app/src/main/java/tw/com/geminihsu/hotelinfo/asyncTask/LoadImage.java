package tw.com.geminihsu.hotelinfo.asyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

import tw.com.geminihsu.hotelinfo.bean.SingleHotelInfoBean;

/**
 * Created by geminihsu on 16/9/22.
 */
public class LoadImage extends AsyncTask<String, String, Bitmap> {
    private Bitmap bitmap;
    //callback fucntion
    private SingleHotelmageDataCallBackFunction mSingleHotelmageDataCallBackFunction;

    public void setSingleHotellmageDataCallBackFunction(SingleHotelmageDataCallBackFunction hotelImageDataCallBackFunction)
    {
        mSingleHotelmageDataCallBackFunction = hotelImageDataCallBackFunction;
    }

    public interface SingleHotelmageDataCallBackFunction{
        public void getSingleHotelImageDataList(Bitmap hotelImagedata);
        public void catchError(String message);

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }
    protected Bitmap doInBackground(String... args) {
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap image) {

        mSingleHotelmageDataCallBackFunction.getSingleHotelImageDataList(image);
        super.onPostExecute(image);
    }
}

