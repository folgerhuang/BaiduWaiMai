package com.jkxy.baiduwaimai;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yushi on 6/19/16.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private Button btnClick;

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private List<View> mViews = new ArrayList<View>();

    //tab
    private LinearLayout mLinearLayoutHome;
    private LinearLayout mLinearLayoutDingdan;
    private LinearLayout mLinearLayoutMe;

    //image
    private ImageView mImageHome;
    private ImageView mImageDingdan;
    private ImageView mImageMe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        initView();

        initEvent();

//        findViewById(R.id.btnClick).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActionBar bar = getSupportActionBar();
//                if (bar != null) {
//                    if (bar.isShowing()) {
//                        bar.hide();
//                    } else {
//                        bar.show();
//                    }
//                }
//            }
//        });


    }

    private void initEvent() {

        mImageHome.setOnClickListener(this);
        mImageDingdan.setOnClickListener(this);
        mImageMe.setOnClickListener(this);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                resetImg();
                switch (currentItem) {
                    case 0:
                        mImageHome.setImageResource(R.drawable.tab_icon_home_selected);
                        break;
                    case 1:
                        mImageDingdan.setImageResource(R.drawable.tab_icon_dingdan_selected);
                        break;
                    case 2:
                        mImageMe.setImageResource(R.drawable.tab_icon_me_selected);
                        break;

                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private void resetImg() {
        mImageHome.setImageResource(R.drawable.tab_icon_home);
        mImageDingdan.setImageResource(R.drawable.tab_icon_dingdan);
        mImageMe.setImageResource(R.drawable.tab_icon_me);
    }

    private void initView() {


        //prepare views
        LayoutInflater inflater = LayoutInflater.from(this);
        View homeView = inflater.inflate(R.layout.home, null);
        View dingdanView = inflater.inflate(R.layout.dingdan, null);
        View meView = inflater.inflate(R.layout.me, null);
        mViews.add(homeView);
        mViews.add(dingdanView);
        mViews.add(meView);

        //initialize pageadapter
        mAdapter = new PagerAdapter() {
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        };

        //initialize viewpager
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mAdapter);

        //initialize layout
        mLinearLayoutHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mLinearLayoutDingdan = (LinearLayout) findViewById(R.id.id_tab_dingdan);
        mLinearLayoutMe = (LinearLayout) findViewById(R.id.id_tab_me);

        //initialize imageView
        mImageHome = (ImageView) findViewById(R.id.id_tab_home_img);
        mImageDingdan = (ImageView) findViewById(R.id.id_tab_dingdan_img);
        mImageMe = (ImageView) findViewById(R.id.id_tab_me_img);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.tool_bar_layout, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.btnFavorite:
//                Toast.makeText(getApplicationContext(), getResources().getText(R.string.btnFavorite), Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.setting:
//                Toast.makeText(getApplicationContext(), getResources().getText(R.string.settings), Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.search:
//                Toast.makeText(getApplicationContext(), getResources().getText(R.string.search), Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()) {
            case R.id.id_tab_home_img:
                mImageHome.setImageResource(R.drawable.tab_icon_home_selected);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.id_tab_dingdan_img:
                mImageDingdan.setImageResource(R.drawable.tab_icon_dingdan_selected);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.id_tab_me_img:
                mImageMe.setImageResource(R.drawable.tab_icon_me_selected);
                mViewPager.setCurrentItem(2);
                break;
        }

    }
}
