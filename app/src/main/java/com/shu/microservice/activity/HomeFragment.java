package com.shu.microservice.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.shu.microservice.R;
import com.shu.microservice.adapter.ServiceAdapter;
import com.shu.microservice.model.ServiceItem;
import com.shu.microservice.views.Banner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wxl on 2016/2/22.
 */
public class HomeFragment extends Fragment {
    private static String  defaultUrl="http://img4.duitang.com/uploads/item/201409/11/20140911193650_nYcxX.thumb.224_0.jpeg";
    //滚动条东西
    private ArrayList<View> views;
    private Banner pager;
    private Context context;

    //热点服务
    private ListView hotServiceListView;
    private List<ServiceItem> serviceItems;
    private ServiceAdapter serviceAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View home = inflater.inflate(R.layout.home,container,false);
        context =  this.getActivity().getBaseContext();
        initViews(home);
        initBanner();
        return home;
    }
    //初始化控件
    private void initViews(View home) {
        pager = (Banner)home.findViewById(R.id.my_view_pager);
        hotServiceListView = (ListView) home.findViewById(R.id.home_hot_service);
        initHotServiceData();
        serviceAdapter = new ServiceAdapter(context,serviceItems);
        hotServiceListView.setAdapter(serviceAdapter);
    }
    //加载热点服务数据
    private void initHotServiceData(){
        serviceItems = new ArrayList<>();
        for(int i=0;i<4;i++){
            //String picUrl, String title, String author, Date createTime
            ServiceItem item = new ServiceItem(defaultUrl,"测试"+i,"王旭龙"+i,new Date());
            serviceItems.add(item);
        }
    }
    //图片轮播
    private void initBanner(){
        views = new ArrayList<View>();

        ImageView image = new ImageView(context);
        image.setImageResource(R.drawable.image1);
        views.add(image);
        image = new ImageView(context);
        image.setImageResource(R.drawable.image2);
        views.add(image);
        image = new ImageView(context);
        image.setImageResource(R.drawable.image3);
        views.add(image);
        image = new ImageView(context);
        image.setImageResource(R.drawable.image4);
        views.add(image);

        pager.setViewPagerViews(views);
        pager.setOnSingleTouchListener(new Banner.OnSingleTouchListener() {
            @Override
            public void onSingleTouch(int position) {
                Toast.makeText(getActivity().getBaseContext(), "当前点击" + position, Toast.LENGTH_LONG).show();
            }
        });

    }



}
