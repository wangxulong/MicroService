package com.shu.microservice.myActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import com.shu.microservice.MainActivity;
import com.shu.microservice.R;

/**
 * Created by Administrator on 2016/2/24 0024.
 */
public class MySettingActivity extends AppCompatActivity {
    ImageView imageView_back = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_setting);
        //返回到上一级页面
        imageView_back = (ImageView) findViewById(R.id.back_button);
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
