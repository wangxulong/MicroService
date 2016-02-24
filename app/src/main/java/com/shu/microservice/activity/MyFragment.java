package com.shu.microservice.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shu.microservice.MainActivity;
import com.shu.microservice.R;
import com.shu.microservice.myActivity.MySettingActivity;

/**
 * ‘我的’模块的Fragment
 * Created by wxl on 2016/2/22.
 */
public class MyFragment extends Fragment {
    TextView textView = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.my,container,false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        textView = (TextView) getActivity().findViewById(R.id.my_setting);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
