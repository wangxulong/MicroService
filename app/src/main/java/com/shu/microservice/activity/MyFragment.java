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
    TextView textView_Request = null;
    TextView textView_Server = null;
    TextView textView_Review = null;
    TextView textView_Question = null;
    TextView textView_Setting = null;
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
        textView_Request = (TextView) getActivity().findViewById(R.id.my_request);
        textView_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyRequestActivity.class);
                startActivity(intent);
            }
        });
        //响应 我的服务
        textView_Server = (TextView) getActivity().findViewById(R.id.my_server);
        textView_Server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyServerActivity.class);
                startActivity(intent);
            }
        });
        //响应 我的评论
        textView_Review = (TextView) getActivity().findViewById(R.id.my_review);
        textView_Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyReviewActivity.class);
                startActivity(intent);
            }
        });
        //响应 我的问答
        textView_Question = (TextView) getActivity().findViewById(R.id.my_question);
        textView_Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyQuestionActivity.class);
                startActivity(intent);
            }
        });

        //响应 个人设置
        textView_Setting = (TextView) getActivity().findViewById(R.id.my_setting);
        textView_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
