package tw.com.geminihsu.hotelinfo;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tw.com.geminihsu.hotelinfo.adapter.ListItem;
import tw.com.geminihsu.hotelinfo.asyncTask.HotelDataProcessor;
import tw.com.geminihsu.hotelinfo.asyncTask.HotelSingleDataProcessor;
import tw.com.geminihsu.hotelinfo.asyncTask.HotelSingleDataProcessor.SingleHotelDataCallBackFunction;
import tw.com.geminihsu.hotelinfo.bean.MyHotelInfoBean;
import tw.com.geminihsu.hotelinfo.bean.SingleHotelInfoBean;
import tw.com.geminihsu.hotelinfo.common.Constants;

public class OneHotelActivity extends Activity {
	 // The helper object

	private HotelSingleDataProcessor hotelSingleDataProcessor;
	private String hotelId;
	private MyHotelInfoBean myHotelInfoBean;

	private TextView txt_hotelName;
	private TextView txt_hotelAddress;
	private TextView txt_hotelDescription;
	private TextView txt_hotelPhoneNumber;

	private RatingBar ratingBar_score;

	private final static String TAG=OneHotelActivity.class.getSimpleName();
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_hote_info_activity);
		getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));


		Bundle bundle = this.getIntent().getExtras();
		myHotelInfoBean = (MyHotelInfoBean) bundle.getSerializable(Constants.hotelId);
		Log.e(TAG,"hotelId:"+myHotelInfoBean.getHotelId());
        hotelId=myHotelInfoBean.getHotelId();
		getActionBar().setTitle(myHotelInfoBean.getHotelName()+" Hotel Information");

	}

	private void findViews() {
		txt_hotelName = (TextView) findViewById(R.id.txt_title);
		txt_hotelAddress= (TextView) findViewById(R.id.infocomment);
		txt_hotelDescription = (TextView) findViewById(R.id.comment);
		txt_hotelPhoneNumber = (TextView) findViewById(R.id.phonenumber);
		ratingBar_score = (RatingBar) findViewById(R.id.rating);
	}

	@Override
	protected void onStart() {
		super.onStart();
        this.findViews();
		if(hotelSingleDataProcessor==null) {
			hotelSingleDataProcessor = new HotelSingleDataProcessor(OneHotelActivity.this);
			hotelSingleDataProcessor.setSingleHotelDataCallBackFunction(new SingleHotelDataCallBackFunction(){

				@Override
				public void getSingleHotelDataList(SingleHotelInfoBean hotel_data) {
					//getDataFromJsonData(hotel_data);
					txt_hotelName.setText(hotel_data.getHotelName());
					txt_hotelAddress.setText(hotel_data.getHotelAddress()+" "+myHotelInfoBean.getPostalCode());
					String description = Html.fromHtml(hotel_data.getLongDescription()).toString();
					txt_hotelDescription.setText(description);
					txt_hotelPhoneNumber.setText(hotel_data.getTelesalesNumber());
					ratingBar_score.setRating(Float.parseFloat(hotel_data.getRating()));
				    //Log.e(TAG, description);
				}

				@Override
				public void catchError(String message) {

				}
			});
			hotelSingleDataProcessor.execute(Constants.hotel_domain_name+"hotelId="+hotelId+"&apikey="+Constants.hotel_query_apikey);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	public void showError(String _message)
	{
		final String message = _message;
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(OneHotelActivity.this,message,Toast.LENGTH_LONG).show();
				OneHotelActivity.this.finish();
			}
		});

	}

	private void getDataFromJsonData(SingleHotelInfoBean data) {

			// for listview data
			Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_camera_72x72);

			ListItem item = new ListItem();
			item.image = bm1;
			item.name=data.getHotelName();
			item.address=data.getHotelAddress();
			item.image_url=Constants.image_data_url+data.getImageLink();
	}
}
