package com.shu.microservice.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.shu.microservice.R;
import com.shu.microservice.adapter.QuestionAdapter;
import com.shu.microservice.adapter.ServiceAdapter;
import com.shu.microservice.model.QuestionItem;
import com.shu.microservice.model.ServiceItem;
import com.shu.microservice.util.AppContext;
import com.shu.microservice.util.ListViewUtil;
import com.shu.microservice.util.NetUtil;
import com.shu.microservice.util.TimeFormatUtil;
import com.shu.microservice.util.ToastUtil;
import com.shu.microservice.views.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wxl on 2016/2/22.
 */
public class HomeFragment extends Fragment {
    private static String  defaultUrl="http://www.csdn.net/css/logo.png";
    private static final String hotRequireUrl="http://115.159.104.74:8080/micro_admin/ajax/require/hot";
    private static final String picHostUrl="http://115.159.104.74:8080/micro_admin/upload/require/";
    //滚动条东西
    private ArrayList<View> views;
    private Banner pager;
    private Context context;

    //热点服务
    private ListView hotServiceListView;
    private List<ServiceItem> serviceItems;
    private ServiceAdapter serviceAdapter;

    //热点需求
    private ListView hotRequireListView;
    private List<ServiceItem> requireItems;
    private RequestQueue mRequestQueue;
    private JsonObjectRequest mJsonObjectRequest;
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
        hotRequireListView = (ListView) home.findViewById(R.id.home_hot_require);
        initHotServiceData();
        serviceAdapter = new ServiceAdapter(serviceItems);
        hotServiceListView.setAdapter(serviceAdapter);
        //用需求的测试
     //   hotRequireListView.setAdapter(serviceAdapter);
        //初始化点击事件
        initEvents();
    }
    //初始化点击事件
    private void initEvents() {
        hotServiceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showShort(id + "");
                ToastUtil.showShort(serviceItems.get(position).getAuthor());

            }
        });
    }

    //加载热点服务数据
    private void initHotServiceData(){
        serviceItems = new ArrayList<>();
        for(int i=0;i<4;i++){
            //String picUrl, String title, String author, Date createTime
            ServiceItem item = new ServiceItem(defaultUrl,"测试"+i,"王旭龙"+i, TimeFormatUtil.getFormatStr(null,new Date()));
            serviceItems.add(item);
        }
        getHotRequire();
    }

    private void getHotRequire(){
        if(!NetUtil.isConnected(AppContext.getAppContext())){
            ToastUtil.showLong("网络连接失败");
            return;
        }
        // 1 创建RequestQueue对象
        mRequestQueue = AppContext.getAppQueue();
        // 2 创建JsonObjectRequest对象
        mJsonObjectRequest = new JsonObjectRequest(hotRequireUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //
                        try {
                            if(response.getString("status").equals("success")){
                                requireItems = new ArrayList<>();
                                JSONArray results = response.getJSONArray("data");
                                JSONObject result = null;
                                if(null!=results&&results.length()>0){
                                    for(int i=0;i<results.length();i++){
                                        result = results.getJSONObject(i);
                                        ServiceItem item = new ServiceItem();
                                        item.setId(result.getLong("id"));
                                        item.setAuthor(result.getString("userName"));
                                        item.setTitle(result.getString("title"));
                                        item.setCreateTime(result.getString("createTime"));
                                        item.setPicUrl(picHostUrl+result.getString("picUrl"));
                                        requireItems.add(item);
                                    }
                                }


                                serviceAdapter = new ServiceAdapter(requireItems);
                                hotRequireListView.setAdapter(serviceAdapter);
                              //  ListViewUtil.setListViewHeightBasedOnChildren(hotRequireListView);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("请求结果hotRequrie:" + response.toString());
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("请求错误:" + error.toString());
            }
        });
        mJsonObjectRequest.setTag("hotRequire");
        // 3 将JsonObjectRequest添加到RequestQueue
        mRequestQueue.add(mJsonObjectRequest);
    }

    @Override
    public void onStop() {
        super.onStop();
        AppContext.getAppQueue().cancelAll("hotRequire");
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
                ToastUtil.showLong("当前点击" + position);
            }
        });

    }



}
