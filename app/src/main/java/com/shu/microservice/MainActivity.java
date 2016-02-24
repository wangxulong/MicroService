package com.shu.microservice;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.shu.microservice.activity.HomeFragment;
import com.shu.microservice.activity.MyFragment;
import com.shu.microservice.activity.QuestionFragment;
import com.shu.microservice.activity.RequireFragment;
import com.shu.microservice.activity.SearchActivity;
import com.shu.microservice.activity.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView homeIcon;
    private TextView questionIcon;
    private TextView serviceIcon;
    private TextView requireIcon;
    private TextView myIcon;

    private TextView appIcon;

    private TextView homeText;
    private TextView questionText;
    private TextView serviceText;
    private TextView requireText;
    private TextView myText;

    private LinearLayout homeTab;
    private LinearLayout questionTab;
    private LinearLayout serviceTab;
    private LinearLayout requireTab;
    private LinearLayout myTab;

    private List<Fragment> fragments;
    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;

    private EditText mainSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initIcons();
        initEvents();
        resetIcons();
        setTab(0);
    }

    /**
     * 初始化点击事件
     */
    private void initEvents() {
        mainSearch.setOnClickListener(this);
        homeTab.setOnClickListener(this);
        questionTab.setOnClickListener(this);
        serviceTab.setOnClickListener(this);
        requireTab.setOnClickListener(this);
        myTab.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //mainSearch
        mainSearch = (EditText) findViewById(R.id.main_search);
        //iconTextView
        homeIcon = (TextView) findViewById(R.id.home_icon);
        questionIcon =(TextView) findViewById(R.id.questiion_icon);
        serviceIcon = (TextView) findViewById(R.id.service_icon);
        requireIcon = (TextView) findViewById(R.id.require_icon);
        myIcon = (TextView) findViewById(R.id.my_icon);
        appIcon = (TextView) findViewById(R.id.app_icon);
        //text
        homeText = (TextView) findViewById(R.id.home_text);
        questionText = (TextView) findViewById(R.id.question_text);
        serviceText = (TextView) findViewById(R.id.service_text);
        requireText = (TextView) findViewById(R.id.require_text);
        myText = (TextView) findViewById(R.id.my_text);
        //layout
        homeTab = (LinearLayout) findViewById(R.id.home);
        questionTab = (LinearLayout) findViewById(R.id.question);
        serviceTab = (LinearLayout) findViewById(R.id.service);
        requireTab = (LinearLayout) findViewById(R.id.require);
        myTab = (LinearLayout) findViewById(R.id.my);

        //ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //fragments
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new QuestionFragment());
        fragments.add(new ServiceFragment());
        fragments.add(new RequireFragment());
        fragments.add(new MyFragment());
        //适配器
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(adapter);
    }

    /**
     * 初始化图标
     */
    private void initIcons() {
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfonts/iconfont.ttf");
        Typeface appIcons = Typeface.createFromAsset(getAssets(), "iconfonts/app_icon.ttf");
        homeIcon.setTypeface(iconfont);
        questionIcon.setTypeface(iconfont);
        serviceIcon.setTypeface(iconfont);
        requireIcon.setTypeface(iconfont);
        myIcon.setTypeface(iconfont);
        appIcon.setTypeface(appIcons);
    }
    //把图标设置成黑色
    private void resetIcons(){
        int defaultColor=  Color.parseColor("#000000");
        homeIcon.setTextColor(defaultColor);
        questionIcon.setTextColor(defaultColor);
        serviceIcon.setTextColor(defaultColor);
        myIcon.setTextColor(defaultColor);
        requireIcon.setTextColor(defaultColor);

        homeText.setTextColor(defaultColor);
        questionText.setTextColor(defaultColor);
        serviceText.setTextColor(defaultColor);
        requireText.setTextColor(defaultColor);
        myText.setTextColor(defaultColor);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:
                 setTab(0);
                 break;
            case R.id.question:
                 setTab(1);
                 break;
            case R.id.service:
                setTab(2);
                 break;
            case R.id.require:
                setTab(3);
                break;
            case R.id.my:
                setTab(4);
                break;
            case R.id.main_search:
                goSearch();
                break;

        }
    }
    //跳转到search页面
    private void goSearch() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    //选中页签
    private void setTab(int i){
        resetIcons();
        int selectedColor=  Color.parseColor("#009300");
        viewPager.setCurrentItem(i);
        switch (i){
            case 0:
                homeIcon.setTextColor(selectedColor);
                homeText.setTextColor(selectedColor);
                break;
            case 1:
                questionIcon.setTextColor(selectedColor);
                questionText.setTextColor(selectedColor);
                break;
            case 2:
                serviceIcon.setTextColor(selectedColor);
                serviceText.setTextColor(selectedColor);
                break;
            case 3:
                requireIcon.setTextColor(selectedColor);
                requireText.setTextColor(selectedColor);
                break;
            case 4:
                myIcon.setTextColor(selectedColor);
                myText.setTextColor(selectedColor);
                break;
        }

    }


}
