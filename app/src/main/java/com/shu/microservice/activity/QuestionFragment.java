package com.shu.microservice.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shu.microservice.R;

/**
 * Created by wxl on 2016/2/22.
 */
public class QuestionFragment extends Fragment {
    private ListView allQuestion;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        allQuestion = (ListView) view.findViewById(R.id.all_question);

    }
}
