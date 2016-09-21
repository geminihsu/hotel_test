package tw.com.geminihsu.hotelinfo;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import tw.com.geminihsu.hotelinfo.adapter.ListItemAdapter;
import tw.com.geminihsu.hotelinfo.adapter.ListItem;
import tw.com.geminihsu.hotelinfo.asyncTask.HotelDataProcessor;
import tw.com.geminihsu.hotelinfo.tw.com.geminihsu.hotelinfo.common.Constants;

import android.view.Menu;
import android.widget.ListView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MainActivity extends Activity {
    private ListItemAdapter listViewAdapter;
    private ListView listView;
    private final List<ListItem> mHotelListData = new ArrayList<ListItem>();
    private HotelDataProcessor hotelDataProcessor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void findViews() {
        listView = (ListView) findViewById(R.id.listView1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.findViews();
        if(hotelDataProcessor==null) {
            hotelDataProcessor = new HotelDataProcessor(MainActivity.this, Constants.data_url);
            hotelDataProcessor.execute(Constants.data_url);
        }
        getDataFromJson();
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

    /* parse data from Json */
    private void getDataFromJson() {
        for (int i = 0; i < 10; i++) {
            // for listview data
            Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_camera_72x72);
            ListItem item = new ListItem();
            item.image = bm1;
            item.name="Hotel "+String.valueOf(i);
            item.address="CA 1233 "+String.valueOf(i);
            mHotelListData.add(item);
        }
    }
}