package com.shu.microservice.myActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.shu.microservice.R;

/**
 * Created by Administrator on 2016/2/24 0024.
 */
public class MyQuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_question);
        TextView tv1= (TextView) findViewById(R.id.question);
        tv1.setText("this is test!,my question");
    }

}
