package tw.com.geminihsu.hotelinfo.asyncTask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by geminihsu on 16/9/20.
 */
public class HotelDataProcessor extends AsyncTask<String, Void, Void> {
        private static final String TAG = HttpHandler.class.getSimpleName();
        private List<HashMap<String, String>> hotelDataList=new ArrayList <HashMap<String, String>>();
        private Activity mActivity;
        private ProgressDialog progressDialog_waitforJsonParser;
        private String url;

        public HotelDataProcessor(Activity _activity, String _url)
        {
            mActivity=_activity;
            //url=_url;
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
        protected Void doInBackground(String... arg0) {
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

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("hotelList");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

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

                        //shortDescription
                        String shortDescription = c.getString("shortDescription");


                        // tmp hash map for single contact
                        HashMap<String, String> hotelData = new HashMap<>();

                        // adding each child node to HashMap key => value
                        hotelData.put("id", id);
                        contact.put("name", name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);

                        // adding contact to contact list
                        hotelDataList.add(contact);
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

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (progressDialog_waitforJsonParser.isShowing())
                progressDialog_waitforJsonParser.dismiss();

        }



}
