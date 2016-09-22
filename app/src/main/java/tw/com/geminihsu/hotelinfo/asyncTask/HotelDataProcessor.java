package tw.com.geminihsu.hotelinfo.asyncTask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

import tw.com.geminihsu.hotelinfo.bean.MyHotelInfoBean;
import tw.com.geminihsu.hotelinfo.common.Constants;
//import tw.com.geminihsu.hotelinfo.dataManager.HotelDataManger.HotelDataCallBackFunction;

/**
 * Created by geminihsu on 16/9/20.
 */
public class HotelDataProcessor extends AsyncTask<String, Void, List<MyHotelInfoBean>> {
        private static final String TAG = HttpHandler.class.getSimpleName();
        private List<MyHotelInfoBean> hotelDataList=new ArrayList <MyHotelInfoBean>();
        private Activity mActivity;
        private ProgressDialog progressDialog_waitforJsonParser;
        private String url;

         //callback fucntion
        private HotelDataCallBackFunction mHotelDataCallBackFunction;

        public void setHotelDataCallBackFunction(HotelDataCallBackFunction hotelDataCallBackFunction)
        {
             mHotelDataCallBackFunction = hotelDataCallBackFunction;
        }

        public interface HotelDataCallBackFunction{
        public void getHotelDataList(List<MyHotelInfoBean> dataList);
        public void catchError(String message);

    }
        public HotelDataProcessor(Activity _activity)
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
        protected List<MyHotelInfoBean> doInBackground(String... arg0) {
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
                    String totalNum = jsonObj.getString("totalHotelCount");

                    // Getting JSON Array node
                    JSONArray datalist = jsonObj.getJSONArray("hotelList");

                    // looping through All Contacts
                    for (int i = 0; i < datalist.length(); i++) {
                        JSONObject c = datalist.getJSONObject(i);

                        String sortIndex = c.getString("sortIndex");
                        String hotelId = c.getString("hotelId");
                        String name = c.getString("name");

                        //address information
                        String localizedName = c.getString("localizedName");
                        String nonLocalizedName = c.getString("nonLocalizedName");
                        String address = c.getString("address");
                        String city = c.getString("city");
                        String stateProvinceCode = c.getString("stateProvinceCode");
                        String countryCode = c.getString("countryCode");
                        String postalCode = c.getString("postalCode");
                        String airportCode = c.getString("airportCode");
                        String imageURL = c.getString("thumbnailUrl");

                        //shortDescription
                        String shortDescription = c.getString("shortDescription");


                        // tmp hash map for single contact
                        MyHotelInfoBean myHotelInfoBean=new MyHotelInfoBean();

                        myHotelInfoBean.setHotelId(hotelId);
                        myHotelInfoBean.setSortIndex(sortIndex);
                        myHotelInfoBean.setHotelName(name);
                        myHotelInfoBean.setHotelAddress(address+", "+city+", "+stateProvinceCode+" "+postalCode);
                        myHotelInfoBean.setImageLink(imageURL);
                        //myHotelInfoBean.setHotelId(hotelId);



                        hotelDataList.add(myHotelInfoBean);
                    }
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
                    mHotelDataCallBackFunction.catchError("Json parsing error: " + e.getMessage());

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
                mHotelDataCallBackFunction.catchError("Couldn't get json from server");

            }

            return null;
        }

        @Override
        protected void onPostExecute(List<MyHotelInfoBean> result) {
            // super.onPostExecute(result);
            // Dismiss the progress dialog
            if (progressDialog_waitforJsonParser.isShowing())
                progressDialog_waitforJsonParser.dismiss();
            mHotelDataCallBackFunction.getHotelDataList(hotelDataList);
            super.onPostExecute(hotelDataList);

        }

}
