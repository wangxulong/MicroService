package com.shu.microservice.activity.question;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shu.microservice.R;
import com.shu.microservice.util.ToastUtil;

/**
 * Created by wxl on 2016/2/25.
 */
public class QuestionDetail extends AppCompatActivity {
    private TextView commentIcon;
    private TextView backIcon;

    private Long currentQuestionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);
        Long currentQuestionId=  getIntent().getLongExtra("id",-1);
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
    }



}
