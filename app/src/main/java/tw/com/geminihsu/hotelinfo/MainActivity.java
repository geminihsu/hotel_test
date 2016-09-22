package tw.com.geminihsu.hotelinfo;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import tw.com.geminihsu.hotelinfo.adapter.ListItemAdapter;
import tw.com.geminihsu.hotelinfo.adapter.ListItem;
import tw.com.geminihsu.hotelinfo.asyncTask.HotelDataProcessor;
import tw.com.geminihsu.hotelinfo.bean.MyHotelInfoBean;
import tw.com.geminihsu.hotelinfo.common.Constants;
import tw.com.geminihsu.hotelinfo.asyncTask.HotelDataProcessor.HotelDataCallBackFunction;



import android.view.Menu;
import android.widget.ListView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;


public class MainActivity extends Activity {
    private ListItemAdapter listViewAdapter;
    private ListView listView;
    private final List<ListItem> mHotelListData = new ArrayList<ListItem>();
    private HotelDataProcessor hotelDataProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }

    private void findViews() {
        listView = (ListView) findViewById(R.id.listView1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.findViews();
        if(hotelDataProcessor==null) {
            hotelDataProcessor = new HotelDataProcessor(MainActivity.this);
            hotelDataProcessor.setHotelDataCallBackFunction(new HotelDataCallBackFunction() {
                @Override
                public void getHotelDataList(List<MyHotelInfoBean> dataList) {
                    //listViewAdapter = new ListItemAdapter(MainActivity.this, 0, (ArrayList)dataList);
                    //listView.setAdapter(listViewAdapter);
                    mHotelListData.clear();
                    getDataFromJsonData((ArrayList)dataList);
                    listViewAdapter.notifyDataSetChanged();
                }

                @Override
                public void catchError(String message) {
                    showError(message);
                }
            });
            hotelDataProcessor.execute(Constants.hotel_data_url);
        }
        //getDataFromJson();
        // 建立ListItemAdapter
        listViewAdapter = new ListItemAdapter(this, 0, mHotelListData);
        listView.setAdapter(listViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private void getDataFromJsonData(ArrayList<MyHotelInfoBean> datalist) {
        for (int i = 0; i < datalist.size(); i++) {
            // for listview data
            Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_camera_72x72);
            MyHotelInfoBean data=datalist.get(i);
            ListItem item = new ListItem();
            item.image = bm1;
            item.name=data.getHotelName();
            item.address=data.getHotelAddress();
            item.image_url=Constants.image_data_url+data.getImageLink();
            mHotelListData.add(item);
        }
    }

    public void showError(String _message)
    {
        final String message = _message;
        runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                        MainActivity.this.finish();
                    }
                });

    }
}