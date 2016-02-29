package com.shu.microservice.activity.question;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.shu.microservice.R;
import com.shu.microservice.adapter.QuestionAdapter;
import com.shu.microservice.adapter.QuestionCommentAdapter;
import com.shu.microservice.model.CommentItem;
import com.shu.microservice.model.QuestionItem;
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
public class QuestionDetail extends AppCompatActivity {
    public static final String url="http://115.159.104.74:8080/micro_admin/ajax/question";
    public static final String commentUrl="http://115.159.104.74:8080/micro_admin/ajax/question/comment";
    private TextView commentIcon;
    private TextView backIcon;
    private RequestQueue mRequestQueue;
    private JsonObjectRequest mJsonObjectRequest;
    private Long currentQuestionId;

    private  TextView questionTitle;
    private  TextView questionAuthor;
    private  TextView questionContent;
    private  TextView questionCreateDate;

    private ListView questionComment;
    private QuestionCommentAdapter commentAdapter;
    private List<CommentItem> commentItems;

    private TextView noComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);
        currentQuestionId=  getIntent().getLongExtra("id", -1);
        initViews();
        initIcons();
        initEvents();

    }

    private void initIcons() {
        Typeface question = Typeface.createFromAsset(getAssets(), "iconfonts/question_icon.ttf");
        Typeface interactIcon = Typeface.createFromAsset(getAssets(), "iconfonts/interact_icon.ttf");
        backIcon.setTypeface(question);
        commentIcon.setTypeface(interactIcon);
    }
    private void initEvents(){
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initViews() {
        backIcon = (TextView) findViewById(R.id.question_detail_back);
        commentIcon = (TextView) findViewById(R.id.icon_comment);
        questionTitle = (TextView) findViewById(R.id.question_detail_title);
        questionAuthor = (TextView) findViewById(R.id.question_detail_author);
        questionContent = (TextView) findViewById(R.id.question_detail_content);
        questionCreateDate = (TextView) findViewById(R.id.question_detail_date);
        noComment = (TextView) findViewById(R.id.question_no_comment);

        questionComment = (ListView) findViewById(R.id.question_comment);
        getQuestion();
        initQuestionCommentData();
    }

    private void getQuestion(){
        if(!NetUtil.isConnected(AppContext.getAppContext())){
                ToastUtil.showLong("网络连接失败");
                return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("id",currentQuestionId+"");
        JSONObject params = new JSONObject(map);
        // 1 创建RequestQueue对象
            mRequestQueue = AppContext.getAppQueue();
            // 2 创建JsonObjectRequest对象
        Request<JSONObject> request = new NormalPostRequest(url,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //
                            try {
                                if(response.getString("status").equals("success")){
                                    JSONObject result = response.getJSONObject("data");
                                    if(result.length()>0) {
                                        questionTitle.setText(result.getString("title"));
                                        questionAuthor.setText(result.getString("userName"));
                                        questionContent.setText(result.getString("description"));
                                        questionCreateDate.setText(result.getString("createTime"));
                                    }
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
            request.setTag("question-" + currentQuestionId);
            // 3 将JsonObjectRequest添加到RequestQueue
            mRequestQueue.add(request);
    }
    //加载评论数据
    private void initQuestionCommentData(){
        if(!NetUtil.isConnected(AppContext.getAppContext())){
            ToastUtil.showLong("网络连接失败");
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("id",currentQuestionId+"");
        JSONObject params = new JSONObject(map);
        // 1 创建RequestQueue对象
        mRequestQueue = AppContext.getAppQueue();
        // 2 创建JsonObjectRequest对象
        Request<JSONObject> request = new NormalPostRequest(commentUrl,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //
                        try {
                            if(response.getString("status").equals("success")){
                                commentItems = new ArrayList<>();
                                JSONArray results = response.getJSONArray("data");
                                if(results.length()>0){
                                    JSONObject result = null;
                                    for(int i=0;i<results.length();i++){
                                        result = results.getJSONObject(i);
                                        CommentItem item = new CommentItem();
                                        item.setId(result.getLong("id"));
                                        item.setUserName(result.getString("userName"));
                                        item.setContent(result.getString("content"));
                                        item.setCreateTime(result.getString("createTime"));
                                        commentItems.add(item);
                                    }
                                    commentAdapter = new QuestionCommentAdapter(commentItems);
                                    questionComment.setAdapter(commentAdapter);
                                }else{
                                    questionComment.setVisibility(View.GONE);
                                    noComment.setVisibility(View.VISIBLE);
                                }

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
        request.setTag("question-comment-" + currentQuestionId);
        // 3 将JsonObjectRequest添加到RequestQueue
        mRequestQueue.add(request);
    }
}
