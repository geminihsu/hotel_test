package tw.com.geminihsu.hotelinfo;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.Toast;

import java.util.List;

import tw.com.geminihsu.hotelinfo.asyncTask.HotelDataProcessor;
import tw.com.geminihsu.hotelinfo.bean.MyHotelInfoBean;
import tw.com.geminihsu.hotelinfo.common.Constants;

public class OneHotelActivity extends Activity {
	 // The helper object

	private HotelDataProcessor hotelDataProcessor;
	private String hotelId;

	private final static String TAG=OneHotelActivity.class.getSimpleName();
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		



	}
	@Override
	protected void onStart() {
		super.onStart();

		if(hotelDataProcessor==null) {
			hotelDataProcessor = new HotelDataProcessor(OneHotelActivity.this);
			hotelDataProcessor.setHotelDataCallBackFunction(new HotelDataProcessor.HotelDataCallBackFunction() {
				@Override
				public void getHotelDataList(List<MyHotelInfoBean> dataList) {

				}

				@Override
				public void catchError(String message) {
					showError(message);
				}
			});
			hotelDataProcessor.execute(Constants.hotel_domain_name+"hotelId="+hotelId+"&apikey="+Constants.hotel_query_apikey);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private Handler myHandler = new Handler() {
		// @Override
		public void handleMessage(Message msg) {
			/* 當取得識別為 持續在執行緒當中時所取得的訊息 */
			if (!Thread.currentThread().isInterrupted()) {

				switch (msg.what) {
				case 1: // get live view image
				{
					//下面註解的部分是讓使用者可以存取AP 300次寫入和讀取檔案
					/*File limitCountfolder=new File(Environment.getExternalStorageDirectory() + Constants.SDACRD_DIR_APP_ROOT);
					if(!limitCountfolder.exists())
						limitCountfolder.mkdirs();
					//讀取記錄檔得知使用者使用AP幾次
					File limitCount=new File(Environment.getExternalStorageDirectory() + Constants.SDACRD_DIR_APP_ROOT + Constants.SDACRD_DIR_APP_ROOT_LIMITCOUNT_FILE);
					if(!limitCount.exists()){
						//若沒有此紀錄檔建立新的file
						try {
							
							limitCount.createNewFile();
							LimitOverUtil.writeLimitCount(limitCount,String.valueOf(Constants.LIMIT_OVER_COUNT));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}else{
						//若有此紀錄檔則更新數值
						String old_count=LimitOverUtil.readLimitCount(limitCount);
						int count = Integer.valueOf(old_count);
						if (count > 0)//若不等於零則次數減一
							count--;
						else
							count = 0;	
						LimitOverUtil.writeLimitCount(limitCount,String.valueOf(count));
					}*/
					Intent intent = new Intent();
					intent.setClass(OneHotelActivity.this, MainActivity.class);
					startActivity(intent);
					OneHotelActivity.this.finish();

					break;
				}
				case 2: // login Page
				{
					
									break;
				}
				}
			}
			super.handleMessage(msg);
		}
	};

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
