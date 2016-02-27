package com.shu.microservice.activity.question;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.shu.microservice.R;
import com.shu.microservice.adapter.QuestionAdapter;
import com.shu.microservice.model.QuestionItem;
import com.shu.microservice.util.AppContext;
import com.shu.microservice.util.NetUtil;
import com.shu.microservice.util.TimeFormatUtil;
import com.shu.microservice.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wxl on 2016/2/25.
 */
public class QuestionDetail extends AppCompatActivity {
    public static final String url="http://115.159.104.74:8080/micro_admin/ajax/question";
    private TextView commentIcon;
    private TextView backIcon;
    private RequestQueue mRequestQueue;
    private JsonObjectRequest mJsonObjectRequest;
    private Long currentQuestionId;

    private  TextView questionTitle;
    private  TextView questionAuthor;
    private  TextView questionContent;
    private  TextView questionCreateDate;


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
        getQuestion();
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
            mJsonObjectRequest = new JsonObjectRequest(url+"?id="+currentQuestionId,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //
                            try {
                                if(response.getString("status").equals("success")){
                                    JSONObject result = response.getJSONObject("data");
                                    questionTitle.setText(result.getString("title"));
                                    questionAuthor.setText(result.getString("userName"));
                                    questionContent.setText(result.getString("description"));
                                    questionCreateDate.setText(TimeFormatUtil.getFormatStr(null, new Date(result.getLong("createTime"))));
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
            });
            mJsonObjectRequest.setTag("question-" + currentQuestionId);
            // 3 将JsonObjectRequest添加到RequestQueue
            mRequestQueue.add(mJsonObjectRequest);
    }

}
