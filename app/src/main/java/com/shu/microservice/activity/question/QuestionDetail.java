package com.shu.microservice.activity.question;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shu.microservice.R;
import com.shu.microservice.util.ToastUtil;

/**
 * Created by wxl on 2016/2/25.
 */
public class QuestionDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);
        Long id=  getIntent().getLongExtra("id",-1);
        ToastUtil.showShort(id+"");
    }
}
