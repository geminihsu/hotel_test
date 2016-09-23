package tw.com.geminihsu.hotelinfo.asyncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tw.com.geminihsu.hotelinfo.bean.MyHotelInfoBean;
import tw.com.geminihsu.hotelinfo.bean.SingleHotelImageList;
import tw.com.geminihsu.hotelinfo.bean.SingleHotelInfoBean;

/**
 * Created by geminihsu on 16/9/20.
 */
public class HotelSingleDataProcessor extends AsyncTask<String, Void, SingleHotelInfoBean> {
        private static final String TAG = HttpHandler.class.getSimpleName();
        private SingleHotelInfoBean singleHotelInfoBean;
        private Activity mActivity;
        private ProgressDialog progressDialog_waitforJsonParser;
        private String url;

         //callback fucntion
        private SingleHotelDataCallBackFunction mSingleHotelDataCallBackFunction;

        public void setSingleHotelDataCallBackFunction(SingleHotelDataCallBackFunction hotelDataCallBackFunction)
        {
             mSingleHotelDataCallBackFunction = hotelDataCallBackFunction;
        }

        public interface SingleHotelDataCallBackFunction{
        public void getSingleHotelDataList(SingleHotelInfoBean hoteldata);
        public void catchError(String message);

    }
        public HotelSingleDataProcessor(Activity _activity)
        {
            mActivity=_activity;
            //mHotelDataCallBackFunction=hotelDataCallBackFunction;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            progressDialog_waitforJsonParser = new ProgressDialog(mActivity);
            progressDialog_waitforJsonParser.setMessage("Please wait...");
            progressDialog_waitforJsonParser.setCancelable(false);
            progressDialog_waitforJsonParser.show();

        }

        @Override
        protected SingleHotelInfoBean doInBackground(String... arg0) {
            url= arg0[0];
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(jsonStr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //JSONObject total = jsonObj.getJSONObject("totalHotelCount");
                    //String totalNum = jsonObj.getString("totalHotelCount");

                    // Getting JSON Array node
                    JSONArray imagelist = jsonObj.getJSONArray("photos");
                    ArrayList<SingleHotelImageList> imageInfoList=new ArrayList<SingleHotelImageList>();

                    for (int i = 0; i < imagelist.length(); i++)
                    {
                        JSONObject imageInfo = imagelist.getJSONObject(i);

                        String url=imageInfo.getString("url");
                        String thumbnailUrl = imageInfo.getString("thumbnailUrl");
                        String featured = imageInfo.optString("featured");
                        String displayText = imageInfo.getString("displayText");

                        SingleHotelImageList singleHotelImageList = new SingleHotelImageList();
                        singleHotelImageList.setUrl(url);
                        singleHotelImageList.setThumbnailUrl(thumbnailUrl);
                        singleHotelImageList.setFeatured(featured);
                        singleHotelImageList.setDisplayText(displayText);
                        imageInfoList.add(singleHotelImageList);

                    }
                        String hotelId = jsonObj.getString("hotelId");
                        String name = jsonObj.getString("hotelName");
                        String localizedHotelName = jsonObj.getString("localizedHotelName");

                        //address information
                        String nonLocalizedName = jsonObj.getString("nonLocalizedHotelName");
                        String hotelAddress = jsonObj.getString("hotelAddress");
                        String hotelCity = jsonObj.getString("hotelCity");
                        String hotelStateProvince = jsonObj.getString("hotelStateProvince");
                        String hotelCountry = jsonObj.getString("hotelCountry");
                        String latitude = jsonObj.getString("latitude");
                        String longitude = jsonObj.getString("longitude");
                        String longDescription = jsonObj.getString("longDescription");
                        String telesalesNumber = jsonObj.getString("telesalesNumber");
                        String hotelStarRating = jsonObj.getString("hotelStarRating");



                        singleHotelInfoBean=new SingleHotelInfoBean();

                        singleHotelInfoBean.setHotelId(hotelId);
                        singleHotelInfoBean.setHotelName(name);
                        singleHotelInfoBean.setHotelAddress(hotelAddress+", "+hotelCity+", "+hotelStateProvince);
                        singleHotelInfoBean.setLocalizedName(localizedHotelName);
                        singleHotelInfoBean.setNonLocalizedName(nonLocalizedName);
                        singleHotelInfoBean.setHotelCountry(hotelCountry);
                        singleHotelInfoBean.setLatitude(latitude);
                        singleHotelInfoBean.setLongitude(longitude);
                        singleHotelInfoBean.setLongDescription(longDescription);
                        singleHotelInfoBean.setTelesalesNumber(telesalesNumber);
                        singleHotelInfoBean.setRating(hotelStarRating);
                        singleHotelInfoBean.setHotelImageLists(imageInfoList);




                   // }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG)
//                                    .show();
//                        }
//                    });
                    if (progressDialog_waitforJsonParser.isShowing())
                        progressDialog_waitforJsonParser.dismiss();
                    mSingleHotelDataCallBackFunction.catchError("Json parsing error: " + e.getMessage());

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });
                if (progressDialog_waitforJsonParser.isShowing())
                    progressDialog_waitforJsonParser.dismiss();
                mSingleHotelDataCallBackFunction.catchError("Couldn't get json from server");

            }

            return null;
        }

        @Override
        protected void onPostExecute(SingleHotelInfoBean result) {
            // super.onPostExecute(result);
            // Dismiss the progress dialog
            if (progressDialog_waitforJsonParser.isShowing())
                progressDialog_waitforJsonParser.dismiss();
            mSingleHotelDataCallBackFunction.getSingleHotelDataList(singleHotelInfoBean);
            super.onPostExecute(singleHotelInfoBean);

        }

}
