package search.quangnhat.com.search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Me on 11/4/2016.
 */

public class CustomListAdapterDialog extends BaseAdapter {
    private ArrayList<ItemDistrict> listDistrict;
    private LayoutInflater layoutInflater;

    public CustomListAdapterDialog(Context context, ArrayList<ItemDistrict> listDistrict) {
        this.listDistrict = listDistrict;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listDistrict.size();
    }

    @Override
    public Object getItem(int position) {
        return listDistrict.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_district, null);
            holder = new ViewHolder();
            holder.nameDistrict = (TextView)convertView.findViewById(R.id.tvnameDistrict);
            holder.name = (CheckBox) convertView.findViewById(R.id.cbname);
            convertView.setTag(holder);
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    ItemDistrict itemDistrict = (ItemDistrict) cb.getTag();
                    if (cb.isChecked())
                        Toast.makeText(v.getContext(), "Bạn đã chọn " + listDistrict.get(position).getNameDistrict(),Toast.LENGTH_LONG).show();
                    else {
                        Toast.makeText(v.getContext(), "Bạn đã hủy chọn " + listDistrict.get(position).getNameDistrict(), Toast.LENGTH_SHORT).show();
                    }
                    itemDistrict.setSelected(cb.isChecked());
                }
            });
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameDistrict.setText(listDistrict.get(position).getNameDistrict().toString());
        holder.name.setChecked(listDistrict.get(position).isSelected());
        holder.name.setTag(listDistrict.get(position));
        return convertView;
    }

    static class ViewHolder {
        TextView nameDistrict;
        CheckBox name;
    }
}
