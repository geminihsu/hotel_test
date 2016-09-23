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
import tw.com.geminihsu.hotelinfo.utils.ImageUtils;


public class SingleHotelImageAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context context;
	private ImageUtils imageUtils;
	private ArrayList<String> image_url;
	private int mPaddingInPixels;


    public SingleHotelImageAdapter(Context _context, ArrayList<String> url) {


        mInflater = (LayoutInflater)_context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context=_context;
		imageUtils=new ImageUtils(_context);
		image_url=url;
    }

	@Override
	public int getCount() {
		return image_url.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}


	public View getView(int position,
						View convertView, ViewGroup parent) {


		ImageView imageView = new ImageView(context);
		// 設定圖片來源
		//imageView.setImageBitmap(bitmapList.get(index));
		imageUtils.DisplayImage(image_url.get(position), imageView);
		// 設定圖片與圖片之間的間距
		final float scale = context.getResources().getDisplayMetrics().density;
		mPaddingInPixels = (int) (10 * scale + 0.5f);
		mPaddingInPixels = mPaddingInPixels + 5;
		imageView.setPadding(mPaddingInPixels, mPaddingInPixels, mPaddingInPixels, mPaddingInPixels);

		return imageView;


    }


    

	

}
