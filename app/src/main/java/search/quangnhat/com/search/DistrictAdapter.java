package search.quangnhat.com.search;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Me on 11/3/2016.
 */

public class DistrictAdapter extends BaseAdapter{
    Context context;
    int mLayout;
    List<District> arrList;

    public DistrictAdapter(Context context, int mLayout, List<District> arrList) {
        this.context = context;
        this.mLayout = mLayout;
        this.arrList = arrList;
    }

    @Override
    public int getCount() {
        return arrList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(mLayout,null);
        TextView name = (TextView)convertView.findViewById(R.id.tvdistrict);
        name.setText(arrList.get(position).getDistrict());
        return convertView;
    }
}
