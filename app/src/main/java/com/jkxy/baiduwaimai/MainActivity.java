package com.jkxy.baiduwaimai;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yushi on 6/19/16.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView mtvToolBarTitle;
    private SearchView searchView;
    private Button btnLoginOrSignUp;
    private GridView gvHead;

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

    //TextView
    private TextView mtvHome;
    private TextView mtvDingdan;
    private TextView mtvMe;

    private List<Product> mDatas;
    private ListAdapter mProductAdapter;
    private ListView mProductListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();

        mLinearLayoutHome.callOnClick();
        searchView.setFocusable(false);
    }


    private void resetImgAndTextColor() {
        mImageHome.setImageResource(R.drawable.tab_icon_home);
        mImageDingdan.setImageResource(R.drawable.tab_icon_dingdan);
        mImageMe.setImageResource(R.drawable.tab_icon_me);
        mtvHome.setTextColor(getResources().getColor(R.color.colorText));
        mtvDingdan.setTextColor(getResources().getColor(R.color.colorText));
        mtvMe.setTextColor(getResources().getColor(R.color.colorText));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings,menu);*/

        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.onActionViewExpanded();
        searchView.setIconifiedByDefault(true);


        int searchViewTextViewID = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView tvSearch = (TextView) searchView.findViewById(searchViewTextViewID);
        tvSearch.setTextColor(getResources().getColor(R.color.colorDark));
        tvSearch.setHint(getResources().getString(R.string.search));




        //prepare views
        LayoutInflater inflater = LayoutInflater.from(this);
        View homeView = inflater.inflate(R.layout.home, null);
        View dinDanView = inflater.inflate(R.layout.dingdan, null);
        View meView = inflater.inflate(R.layout.me, null);
        mViews.add(homeView);
        mViews.add(dinDanView);
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

        //initialize TextView
        mtvHome = (TextView) findViewById(R.id.tvHome);
        mtvDingdan = (TextView) findViewById(R.id.tvDingdan);
        mtvMe = (TextView) findViewById(R.id.tvMe);
    }


    private void setSearchVisible(Boolean b) {
        if (searchView != null) {
            searchView.setVisibility(b == true ? View.VISIBLE : View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        resetImgAndTextColor();
        switch (v.getId()) {
            case R.id.id_tab_home:
                toolbar.setTitle("");
                mImageHome.setImageResource(R.drawable.tab_icon_home_selected);
                mtvHome.setTextColor(getResources().getColor(R.color.colorDarkAccent));
                mViewPager.setCurrentItem(0);
                setSearchVisible(true);
               initHome(mViews.get(0));
                break;
            case R.id.id_tab_dingdan:
                mImageDingdan.setImageResource(R.drawable.tab_icon_dingdan_selected);
                mtvDingdan.setTextColor(getResources().getColor(R.color.colorDarkAccent));
                toolbar.setTitle(getResources().getText(R.string.dindan));
                mtvToolBarTitle.setVisibility(View.VISIBLE);
                setSearchVisible(false);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.id_tab_me:
                mImageMe.setImageResource(R.drawable.tab_icon_me_selected);
                mtvMe.setTextColor(getResources().getColor(R.color.colorDarkAccent));
               toolbar.setTitle("");
                mtvToolBarTitle.setVisibility(View.GONE);
                setSearchVisible(false);
                btnLoginOrSignUp.setVisibility(View.VISIBLE);
                mViewPager.setCurrentItem(2);
                break;
        }

    }

    private void initHome(View v) {

        //setSupportActionBar(toolbar);
        mtvToolBarTitle = (TextView) findViewById(R.id.tvToolBarTitle);
        mtvToolBarTitle.setVisibility(View.INVISIBLE);

        btnLoginOrSignUp= (Button) findViewById(R.id.btnLoginOrSignUp);


        mProductListView = (ListView) v.findViewById(R.id.lvItem);
        mDatas = new ArrayList<>();
        Product p = new Product("巫山烤鱼", "90");
        mDatas.add(p);
        p = new Product("必胜客", "199");
        mDatas.add(p);
        p = new Product("麦当劳", "88");
        mDatas.add(p);
        p = new Product("大碗厨", "79");
        mDatas.add(p);
        p = new Product("莜面村", "159");
        mDatas.add(p);
        p = new Product("重庆火锅", "288");
        mDatas.add(p);

        mProductAdapter = new ProductAdapter(this, mDatas);
        mProductListView.setAdapter(mProductAdapter);
        if(mProductListView.getHeaderViewsCount()==0) {
            LayoutInflater inflater = getLayoutInflater();
            ViewGroup header = (ViewGroup) inflater.inflate(R.layout.homeheadview, mProductListView, false);

            gvHead = (GridView) header.findViewById(R.id.gvHead);
            ListAdapter homeHeadAdapter=new HomeHeadAdapter(this);
            gvHead.setAdapter(homeHeadAdapter);
            mProductListView.addHeaderView(header, null, false);
        }




    }

    private void initEvent() {

        mLinearLayoutHome.setOnClickListener(this);
        mLinearLayoutDingdan.setOnClickListener(this);
        mLinearLayoutMe.setOnClickListener(this);


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                resetImgAndTextColor();
                switch (currentItem) {
                    case 0:
                        toolbar.setTitle("");
                        mImageHome.setImageResource(R.drawable.tab_icon_home_selected);
                        mtvHome.setTextColor(getResources().getColor(R.color.colorDarkAccent));
                        mtvToolBarTitle.setVisibility(View.GONE);
                        setSearchVisible(true);
                        btnLoginOrSignUp.setVisibility(View.GONE);
                        initHome(mViews.get(0));
                        break;
                    case 1:
                        mImageDingdan.setImageResource(R.drawable.tab_icon_dingdan_selected);
                        mtvDingdan.setTextColor(getResources().getColor(R.color.colorDarkAccent));
                        toolbar.setTitle(getResources().getText(R.string.dindan));
                        mtvToolBarTitle.setVisibility(View.VISIBLE);
                        setSearchVisible(false);
                        btnLoginOrSignUp.setVisibility(View.GONE);
                        break;
                    case 2:
                        mImageMe.setImageResource(R.drawable.tab_icon_me_selected);
                        mtvMe.setTextColor(getResources().getColor(R.color.colorDarkAccent));
                        mtvToolBarTitle.setVisibility(View.GONE);
                        setSearchVisible(false);
                        toolbar.setTitle("");
                        btnLoginOrSignUp.setVisibility(View.VISIBLE);
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



}
