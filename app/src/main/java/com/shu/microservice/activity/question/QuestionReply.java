package com.shu.microservice.activity.question;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.shu.microservice.R;
import com.shu.microservice.adapter.QuestionCommentAdapter;
import com.shu.microservice.model.CommentItem;
import com.shu.microservice.util.AppContext;
import com.shu.microservice.util.NetUtil;
import com.shu.microservice.util.NormalPostRequest;
import com.shu.microservice.util.TimeFormatUtil;
import com.shu.microservice.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wxl on 2016/2/25.
 */
public class QuestionReply extends AppCompatActivity {
    private static final String replyURL="";
    private EditText replyContent;
    private Button replyButton;
    private TextView replyBack;

    private RequestQueue mRequestQueue;
    private Request<JSONObject> mJsonObjectRequest;


    //问答ID
    private Long questionId;
    //回复的内容
    private String replyContentStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_reply);
        questionId=  getIntent().getLongExtra("questionId", -1);
        initViews();
        intiEvents();
    }

    private void intiEvents() {
        replyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = replyContent.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.showLong("请输入回复的内容");
                } else {
                    //添加回复
                   // addReply();
                    ToastUtil.showLong("回复内容"+content);
                    replyContentStr = content;
                    //返回到问题详情页面
                    Intent intent = new Intent(AppContext.getAppContext(), QuestionDetail.class);
                    intent.putExtra("id", questionId);
                    startActivity(intent);

                }
            }
        });
    }

    private void initViews() {
        replyContent = (EditText) findViewById(R.id.question_reply_content);
        replyButton = (Button) findViewById(R.id.question_reply_commit);
        replyBack = (TextView) findViewById(R.id.question_reply_back);
    }
    //添加回复
    private void addReply(){
        //1.获取当前用户的ID 2.发送请求接口(用户id 问答id 评论内容)
        if(!NetUtil.isConnected(AppContext.getAppContext())){
            ToastUtil.showLong("网络连接失败");
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("id","7");
        map.put("contentId",questionId+"");
        map.put("content",replyContentStr);
        JSONObject params = new JSONObject(map);
        // 1 创建RequestQueue对象
        mRequestQueue = AppContext.getAppQueue();
        // 2 创建JsonObjectRequest对象
        mJsonObjectRequest = new NormalPostRequest(replyURL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //
                        try {
                            if(response.getString("status").equals("success")){
                                 ToastUtil.showLong("添加成功");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("请求结果:" + response.toString());
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("请求错误:" + error.toString());
            }
        },map);
        mJsonObjectRequest.setTag("question-reply" + questionId);
        // 3 将JsonObjectRequest添加到RequestQueue
        mRequestQueue.add(mJsonObjectRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRequestQueue.cancelAll("question-reply" + questionId);
    }
}
