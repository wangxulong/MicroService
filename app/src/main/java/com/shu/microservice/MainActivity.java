package com.shu.microservice;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView homeIcon;
    private TextView serviceIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initIcons();
    }
    /**
     * 初始化控件
     */
    private void initView(){
        homeIcon = (TextView) findViewById(R.id.home_icon);
        serviceIcon = (TextView) findViewById(R.id.service_icon);
    }

    /**
     * 初始化图标
     */
    private void initIcons(){
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfonts/iconfont.ttf");
        homeIcon.setTypeface(iconfont);
        serviceIcon.setTypeface(iconfont);
    }
}
