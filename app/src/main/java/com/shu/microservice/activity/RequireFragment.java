package com.shu.microservice.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shu.microservice.R;
import com.shu.microservice.adapter.ServiceAdapter;
import com.shu.microservice.model.ServiceItem;
import com.shu.microservice.util.TimeFormatUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wxl on 2016/2/22.
 */
public class RequireFragment extends Fragment {
    private static String  defaultUrl="http://www.csdn.net/css/logo.png";

    ListView listview = null;
    ServiceAdapter serviceAdapter = null;
    List<ServiceItem> items = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.require,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        listview = (ListView) view.findViewById(R.id.service_list);
        initData();
        serviceAdapter = new ServiceAdapter(items);

        listview.setAdapter(serviceAdapter);
    }
    private void initData(){
        items = new ArrayList<>();
        //String picUrl, String title, String author, Date createTime
        ServiceItem item = new ServiceItem(defaultUrl,"我想买个二手自行车","王旭龙", TimeFormatUtil.getFormatStr(null, new Date()));
        items.add(item);
    }
}
