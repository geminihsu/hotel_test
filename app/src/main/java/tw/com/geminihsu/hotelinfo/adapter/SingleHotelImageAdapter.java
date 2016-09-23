package tw.com.geminihsu.hotelinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tw.com.geminihsu.hotelinfo.R;
import tw.com.geminihsu.hotelinfo.bean.SingleHotelImageList;
import tw.com.geminihsu.hotelinfo.common.Constants;
import tw.com.geminihsu.hotelinfo.utils.ImageUtils;


public class SingleHotelImageAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context context;
	private ImageUtils imageUtils;
	private String[] image_url;
	private int mPaddingInPixels;


    public SingleHotelImageAdapter(Context _context, String[] url) {


        context=_context;
		imageUtils=new ImageUtils(_context);
		image_url=url;
    }

	public int getCount() {
		return image_url.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imgView = new ImageView(context);

		imgView.setLayoutParams(new Gallery.LayoutParams(500, 500));
		imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageUtils.DisplayImage(image_url[position], imgView);
		return imgView;
	}


	

}
