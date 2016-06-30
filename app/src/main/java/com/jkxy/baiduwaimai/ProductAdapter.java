package com.jkxy.baiduwaimai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yushi on 6/26/16.
 */
public class ProductAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Product> mData;

    public ProductAdapter(Context context, List<Product> data) {
        mInflater = LayoutInflater.from(context);
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Product getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.itemlayout, parent, false);
            holder = new ViewHolder();
            holder.mProductName = (TextView) convertView.findViewById(R.id.tvProductName);
            holder.mPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mProductName.setText(mData.get(position).getProductName());
        holder.mPrice.setText(mInflater.getContext().getResources().getString(R.string.qisong) + mData.get(position).getPrice());
        return convertView;
    }

    private class ViewHolder {
        private TextView mProductName;
        private TextView mPrice;
    }

}
