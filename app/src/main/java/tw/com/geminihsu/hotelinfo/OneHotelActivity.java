package tw.com.geminihsu.hotelinfo;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tw.com.geminihsu.hotelinfo.adapter.ListItem;
import tw.com.geminihsu.hotelinfo.adapter.SingleHotelImageAdapter;
import tw.com.geminihsu.hotelinfo.asyncTask.HotelDataProcessor;
import tw.com.geminihsu.hotelinfo.asyncTask.HotelSingleDataProcessor;
import tw.com.geminihsu.hotelinfo.asyncTask.HotelSingleDataProcessor.SingleHotelDataCallBackFunction;
import tw.com.geminihsu.hotelinfo.asyncTask.LoadImage;
import tw.com.geminihsu.hotelinfo.bean.MyHotelInfoBean;
import tw.com.geminihsu.hotelinfo.bean.SingleHotelInfoBean;
import tw.com.geminihsu.hotelinfo.common.Constants;
import tw.com.geminihsu.hotelinfo.MapsActivity;

public class OneHotelActivity extends Activity {
	 // The helper object

	private HotelSingleDataProcessor hotelSingleDataProcessor;
	private LoadImage displayImage;
	private SingleHotelInfoBean singleHotelInfoBean;
	private String hotelId;
	private MyHotelInfoBean myHotelInfoBean;

	private ImageView hotelImage;
	private TextView txt_hotelName;
	private TextView txt_hotelAddress;
	private TextView txt_hotelDescription;
	private TextView txt_hotelPhoneNumber;

	private RatingBar ratingBar_score;
	private Gallery gallery;

	private Button location_map;


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

		location_map = (Button) findViewById(R.id.btn_map);

		hotelImage = (ImageView) findViewById(R.id.image);

		gallery = (Gallery) findViewById(R.id.gallery1);
		gallery.setSpacing(1);
		//gallery.setAdapter(new SingleHotelImageAdapter(OneHotelActivity.this));

	}

	private  void setLister()
	{
		location_map.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			Intent i = new Intent();
			i.setClass(OneHotelActivity.this, MapsActivity.class);

			Bundle b = new Bundle();
			b.putString(Constants.hotel_latitude, singleHotelInfoBean.getLatitude());
			b.putString(Constants.hotel_longitude, singleHotelInfoBean.getLongitude());
			i.putExtras(b);
			startActivity(i);
		}
	});
	}

	@Override
	protected void onStart() {
		super.onStart();
        this.findViews();
		this.setLister();
		if(hotelSingleDataProcessor==null) {
			hotelSingleDataProcessor = new HotelSingleDataProcessor(OneHotelActivity.this);
			hotelSingleDataProcessor.setSingleHotelDataCallBackFunction(new SingleHotelDataCallBackFunction(){

				@Override
				public void getSingleHotelDataList(SingleHotelInfoBean hotel_data) {
					//getDataFromJsonData(hotel_data);
					singleHotelInfoBean=hotel_data.clone();
					txt_hotelName.setText(hotel_data.getHotelName());
					txt_hotelAddress.setText(hotel_data.getHotelAddress()+" "+myHotelInfoBean.getPostalCode());
					String description = Html.fromHtml(hotel_data.getLongDescription()).toString();
					//txt_hotelDescription.setText(description);
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
		if(displayImage==null) {
			displayImage = new LoadImage();
			displayImage.setSingleHotellmageDataCallBackFunction(new LoadImage.SingleHotelmageDataCallBackFunction(){


				@Override
				public void getSingleHotelImageDataList(Bitmap hotelImagedata) {
					hotelImage.setImageBitmap(hotelImagedata);

				}

				@Override
				public void catchError(String message) {

				}
			});
			displayImage.execute("http://images.travelnow.com/hotels/1000000/60000/51000/50947/50947_302_z.jpg");
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
}
