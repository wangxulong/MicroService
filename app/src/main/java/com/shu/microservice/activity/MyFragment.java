package com.shu.microservice.activity;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shu.microservice.MainActivity;
import com.shu.microservice.R;
import com.shu.microservice.myActivity.MyQuestionActivity;
import com.shu.microservice.myActivity.MyRequestActivity;
import com.shu.microservice.myActivity.MyReviewActivity;
import com.shu.microservice.myActivity.MyServerActivity;
import com.shu.microservice.myActivity.MySettingActivity;
import com.shu.microservice.util.LoginUtil;

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
    RelativeLayout logout_view = null;
    RelativeLayout login_view = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.my,container,false);


        return view;
    }
    //读取本地缓存的用户信息
    public void readMyInfomation(View view)
    {

    }


    @Override
    public void onStart() {
        super.onStart();
        if(LoginUtil.isLogin()){

            logout_view = (RelativeLayout) getActivity().findViewById(R.id.logout);
            logout_view.setVisibility(View.GONE);
            login_view = (RelativeLayout) getActivity().findViewById(R.id.login);
            login_view.setVisibility(View.VISIBLE);
        }
        //响应 我的需求
        layout_Request = (RelativeLayout) getActivity().findViewById(R.id.my_request);
        layout_Request.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(LoginUtil.isLogin()){
                    Intent intent = new Intent(getActivity(), MyRequestActivity.class);
                    startActivity(intent);
                }else{
                    MainActivity parentActivity = (MainActivity ) getActivity();
                    parentActivity.showDialog_Layout(getContext());
                }
            }
        });
        //响应 我的服务
        layout_Server = (RelativeLayout) getActivity().findViewById(R.id.my_server);
        layout_Server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginUtil.isLogin()) {
                    Intent intent = new Intent(getActivity(), MyServerActivity.class);
                    startActivity(intent);
                } else {
                    MainActivity parentActivity = (MainActivity) getActivity();
                    parentActivity.showDialog_Layout(getContext());
                }


            }
        });
        //响应 我的评论
        layout_Review = (RelativeLayout) getActivity().findViewById(R.id.my_review);
        layout_Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginUtil.isLogin()){
                    Intent intent = new Intent(getActivity(), MyReviewActivity.class);
                    startActivity(intent);
                }else{
                    MainActivity parentActivity = (MainActivity ) getActivity();
                    parentActivity.showDialog_Layout(getContext());
                }
            }
        });
        //响应 我的问答
        layout_Question = (RelativeLayout) getActivity().findViewById(R.id.my_question);
        layout_Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginUtil.isLogin()){
                    Intent intent = new Intent(getActivity(), MyQuestionActivity.class);
                    startActivity(intent);
                }else{
                    MainActivity parentActivity = (MainActivity ) getActivity();
                    parentActivity.showDialog_Layout(getContext());
                }
            }
        });

        //响应 个人设置
        layout_Setting = (RelativeLayout) getActivity().findViewById(R.id.my_setting);
        layout_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginUtil.isLogin()) {
                    Intent intent = new Intent(getActivity(), MySettingActivity.class);
                    startActivity(intent);
                }else{
                        MainActivity parentActivity = (MainActivity ) getActivity();
                        parentActivity.showDialog_Layout(getContext());
                }
            }
        });
    }

}
