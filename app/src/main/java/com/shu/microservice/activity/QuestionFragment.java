package com.shu.microservice.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shu.microservice.MainActivity;
import com.shu.microservice.R;
import com.shu.microservice.activity.question.QuestionDetail;
import com.shu.microservice.adapter.QuestionAdapter;
import com.shu.microservice.model.QuestionItem;
import com.shu.microservice.util.AppContext;
import com.shu.microservice.util.NetUtil;
import com.shu.microservice.util.ToastUtil;
import android.app.ProgressDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wxl on 2016/2/22.
 */
public class QuestionFragment extends Fragment {
    public  static final String hotQuestionURL = "http://115.159.104.74:8080/micro_admin/ajax/hotQuestion";
    private ListView hotQuestion;
    private List<QuestionItem> hotQuestionItems;
    private QuestionAdapter adapter;

    private ListView laterUpdateQuestion;
    private List<QuestionItem> laterUpdateItems;

    private  RequestQueue mRequestQueue;
    private JsonObjectRequest mJsonObjectRequest;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question,container,false);
        initViews(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        hotQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuestionItem item = hotQuestionItems.get(position);
                Long itemId = item.getId();
                showDetail(itemId);
                ToastUtil.showShort(position);
            }
        });

        laterUpdateQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuestionItem item = laterUpdateItems.get(position);
                Long itemId = item.getId();
                showDetail(itemId);
                ToastUtil.showShort("最近更新" + itemId);
            }
        });
    }

    private void showDetail(long id){
        Intent intent = new Intent(AppContext.getAppContext(), QuestionDetail.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    private void initViews(View view) {
        hotQuestion = (ListView) view.findViewById(R.id.question_hot);
        laterUpdateQuestion = (ListView) view.findViewById(R.id.question_later_update);
        initData();


        adapter = new QuestionAdapter(laterUpdateItems);
        laterUpdateQuestion.setAdapter(adapter);

    }
    private void getHotQuestion() {
        if(!NetUtil.isConnected(AppContext.getAppContext())){
            ToastUtil.showLong("网络连接失败");
            return;
        }
        // 1 创建RequestQueue对象
        mRequestQueue = AppContext.getAppQueue();
        // 2 创建JsonObjectRequest对象
        mJsonObjectRequest = new JsonObjectRequest(hotQuestionURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //
                        try {
                            if(response.getString("status").equals("success")){
                                hotQuestionItems = new ArrayList<>();
                                JSONArray results = response.getJSONArray("data");
                                JSONObject result = null;
                                for(int i=0;i<results.length();i++){
                                    result = results.getJSONObject(i);
                                    QuestionItem item = new QuestionItem();
                                    item.setId(result.getLong("id"));
                                    item.setAuthor(result.getString("userName"));
                                    item.setTitle(result.getString("title"));
                                    item.setCreateTime(new Date(result.getLong("createTime")));
                                    hotQuestionItems.add(item);
                                }
                                adapter = new QuestionAdapter(hotQuestionItems);
                                hotQuestion.setAdapter(adapter);
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
        mJsonObjectRequest.setTag("hotQuestion");
        // 3 将JsonObjectRequest添加到RequestQueue
        mRequestQueue.add(mJsonObjectRequest);
    }
    private void initData() {
        getHotQuestion();
        laterUpdateItems = new ArrayList<>();
        for(int i=0;i<10;i++){
            QuestionItem item = new QuestionItem();
            item.setId((long)i+100);
            item.setAuthor("wxl" + i);
            item.setTitle("最近更新" + i);
            item.setCreateTime(new Date());
            laterUpdateItems.add(item);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        AppContext.getAppQueue().cancelAll("hotQuestion");
    }
}
