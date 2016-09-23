package tw.com.geminihsu.hotelinfo.adapter;

import java.util.List;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;

import tw.com.geminihsu.hotelinfo.R;
import tw.com.geminihsu.hotelinfo.utils.ImageUtils;


public class ListItemAdapter extends ArrayAdapter<ListItem> {

    private LayoutInflater mInflater;
    private Context context;
	private ImageUtils imageUtils;
    public ListItemAdapter(Context _context,
                           int rid, List<ListItem> list) {
        super(_context, rid, list);

        mInflater = (LayoutInflater)_context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context=_context;
		imageUtils=new ImageUtils(_context);
       
    }
   
    public View getView(int position,
                        View convertView, ViewGroup parent) {


		// obtain data
		final ListItem item = (ListItem) getItem(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			//generated layout view
			convertView = mInflater.inflate(R.layout.hotelinfo_list_item, null);
			// set picture
			holder.image = (ImageView)convertView.findViewById(R.id.image);
			//holder.image.setImageBitmap(item.image);
			imageUtils.DisplayImage(item.image_url, holder.image);

			// set hotel name
			holder.name = (TextView)convertView.findViewById(R.id.name);
			holder.name.setText(item.name);

			// set hotel address
			holder.address = (TextView)convertView.findViewById(R.id.address);
			holder.address.setText(item.address);


			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			holder = (ViewHolder)convertView.getTag();
			holder.image.setImageBitmap(item.image);
			holder.name.setText(item.name);
			holder.address.setText(item.address);
			imageUtils.DisplayImage(item.image_url, holder.image);


		}



    	
        

        	

        	
        return convertView;
    }

    private class ViewHolder {
		ImageView image;
		ImageView imagestatus;
		TextView name;
		TextView address;
		TextView positionInGrid;
		Button notifycount;
	}
    

	

}
