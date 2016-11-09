package search.quangnhat.com.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Me on 11/2/2016.
 */

public class CityAdapter extends BaseAdapter {
    Context context;
    int mLayout;
    List<City> arrList;

    public CityAdapter(Context context, int mLayout, List<City> arrList) {
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
        TextView name = (TextView)convertView.findViewById(R.id.tvCity);

        name.setText(arrList.get(position).getName());
        return convertView;
    }
}
