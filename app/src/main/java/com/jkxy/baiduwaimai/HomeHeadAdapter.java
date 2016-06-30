package com.jkxy.baiduwaimai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yushi on 6/26/16.
 */
public class HomeHeadAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Catagory> mData=new ArrayList<>();


    public HomeHeadAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        Catagory catagory = new Catagory(R.drawable.home_category_eat,R.string.eat);
        mData.add(catagory);
        catagory = new Catagory(R.drawable.home_category_buy,R.string.shopping);
        mData.add(catagory);
        catagory = new Catagory(R.drawable.home_category_fruit,R.string.fruit);
        mData.add(catagory);
        catagory = new Catagory(R.drawable.home_category_tea,R.string.tea);
        mData.add(catagory);
        catagory = new Catagory(R.drawable.home_category_tuhao,R.string.tuhao);
        mData.add(catagory);
        catagory = new Catagory(R.drawable.home_category_newdian,R.string.newdian);
        mData.add(catagory);
        catagory = new Catagory(R.drawable.home_category_deliver,R.string.baidupeisong);
        mData.add(catagory);
        catagory = new Catagory(R.drawable.home_category_medicine,R.string.medicine);
        mData.add(catagory);



    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Catagory getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.homeheadviewitem, parent, false);
            holder = new ViewHolder();
            holder.mImage = (ImageView) convertView.findViewById(R.id.ivImage);
            holder.mCategoryName = (TextView) convertView.findViewById(R.id.tvCategoryName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mImage.setImageResource(mData.get(position).getImgId());
        holder.mCategoryName.setText(mData.get(position).getCategoryNameID());
        return convertView;
    }

    private class ViewHolder {
        private ImageView mImage;
        private TextView mCategoryName;
    }

    private class Catagory {
        private int imgId;
        private int categoryNameID;

        public Catagory() {
        }


        public Catagory(int imgId, int categoryNameID) {
            this.imgId = imgId;
            this.categoryNameID = categoryNameID;
        }

        public int getImgId() {
            return imgId;
        }

        public void setImgId(int imgId) {
            this.imgId = imgId;
        }

        public int getCategoryNameID() {
            return categoryNameID;
        }

        public void setCategoryNameID(int categoryNameID) {
            this.categoryNameID = categoryNameID;
        }
    }
}
