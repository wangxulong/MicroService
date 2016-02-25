package com.shu.microservice.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shu.microservice.MainActivity;
import com.shu.microservice.R;
import com.shu.microservice.myActivity.MyQuestionActivity;
import com.shu.microservice.myActivity.MyRequestActivity;
import com.shu.microservice.myActivity.MyReviewActivity;
import com.shu.microservice.myActivity.MyServerActivity;
import com.shu.microservice.myActivity.MySettingActivity;

/**
 * ‘我的’模块的Fragment
 * Created by wxl on 2016/2/22.
 */
public class MyFragment extends Fragment {
    RelativeLayout layout_Request = null;
    RelativeLayout layout_Server = null;
    RelativeLayout layout_Review = null;
    RelativeLayout layout_Question = null;
    RelativeLayout layout_Setting = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.my,container,false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //响应 我的需求
        layout_Request = (RelativeLayout) getActivity().findViewById(R.id.my_request);
        layout_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyRequestActivity.class);
                startActivity(intent);
            }
        });
        //响应 我的服务
        layout_Server = (RelativeLayout) getActivity().findViewById(R.id.my_server);
        layout_Server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyServerActivity.class);
                startActivity(intent);
            }
        });
        //响应 我的评论
        layout_Review = (RelativeLayout) getActivity().findViewById(R.id.my_review);
        layout_Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyReviewActivity.class);
                startActivity(intent);
            }
        });
        //响应 我的问答
        layout_Question = (RelativeLayout) getActivity().findViewById(R.id.my_question);
        layout_Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyQuestionActivity.class);
                startActivity(intent);
            }
        });

        //响应 个人设置
        layout_Setting = (RelativeLayout) getActivity().findViewById(R.id.my_setting);
        layout_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
