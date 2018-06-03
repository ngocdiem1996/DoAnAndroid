package com.ngocdiem.map.Map.HienThiDanhSachTheoCumDiaDiemDaChon;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ngocdiem.map.Map.HienThiCacDIaDiemXUngQuanhTrenBAnDo.MapActivity;
import com.ngocdiem.map.Map.LocationItem;
import com.ngocdiem.map.Map.layTenCumDiaDiem.PlayerListener;
import com.ngocdiem.map.R;

import java.util.ArrayList;

public class DataListLocation extends ArrayAdapter<LocationItem> {
    private Intent mIntent;
    private PlayerListener mPlayerListener;
    private Context mContext;

    public DataListLocation(Context context,int resource,ArrayList<LocationItem> list,PlayerListener playerListener) {
        super(context,resource, list);
        this.mContext = context;
        this.mPlayerListener = playerListener;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.location_item,null);
            viewHolder = new ViewHolder();
            viewHolder.mName = (TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.mAddress = (TextView) convertView.findViewById(R.id.tv_address);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final LocationItem item = getItem(position);
        viewHolder.mName.setText(item.getmName());
        viewHolder.mAddress.setText(item.getmAddress());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(mContext,MapActivity.class);
                mIntent.putExtra("LATITUDE",item.getmLocation().latitude+"");
                mIntent.putExtra("LONGITUDE",item.getmLocation().longitude+"");
                mIntent.putExtra("NAME",item.getmName());
                mPlayerListener.onClickPlayer(position, mIntent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        private TextView mName;
        private TextView mAddress;
    }
}
