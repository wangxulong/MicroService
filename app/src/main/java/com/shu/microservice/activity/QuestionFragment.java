package com.shu.microservice.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shu.microservice.R;
import com.shu.microservice.activity.question.QuestionDetail;
import com.shu.microservice.adapter.QuestionAdapter;
import com.shu.microservice.model.QuestionItem;
import com.shu.microservice.util.AppContext;
import com.shu.microservice.util.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wxl on 2016/2/22.
 */
public class QuestionFragment extends Fragment {
    private ListView hotQuestion;
    private List<QuestionItem> hotQuestionItems;
    private QuestionAdapter adapter;

    private ListView laterUpdateQuestion;
    private List<QuestionItem> laterUpdateItems;
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
        adapter = new QuestionAdapter(hotQuestionItems);
        hotQuestion.setAdapter(adapter);

        adapter = new QuestionAdapter(laterUpdateItems);
        laterUpdateQuestion.setAdapter(adapter);

    }

    private void initData() {
        hotQuestionItems = new ArrayList<>();
        for(int i=0;i<3;i++){
            QuestionItem item = new QuestionItem();
            item.setId((long)i);
            item.setAuthor("wxl" + i);
            item.setTitle("自行车" + i);
            item.setCreateTime(new Date());
            hotQuestionItems.add(item);
        }
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

}
