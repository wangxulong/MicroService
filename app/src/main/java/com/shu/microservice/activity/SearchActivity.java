package com.shu.microservice.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shu.microservice.R;

/**
 * Created by wxl on 2016/2/24.
 */
public class SearchActivity extends AppCompatActivity {
    //icons
    private TextView iconSearch;
    private TextView iconClear;
    //搜索框
    private EditText search;
    //清除按钮所在布局
    private LinearLayout clearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
        initViews();
        initEvents();
        initIcons();
    }


    //绑定监听事件
    private void initEvents() {
        //搜索框监听事件
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果长度大于0则显示清除按钮
                int length = search.getText().length();
                if (length > 0) {
                    clearLayout.setVisibility(View.VISIBLE);
                } else {
                    clearLayout.setVisibility(View.GONE);
                }
            }
        });
        //清除按钮监听事件
        clearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
                clearLayout.setVisibility(View.GONE);
            }
        });
        //设置按键监听器
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == event.KEYCODE_ENTER) {
                    Toast.makeText(SearchActivity.this,
                            search.getText().toString().trim(),
                            Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
    }
    //初始化控件
    private void initViews() {
        iconSearch = (TextView) findViewById(R.id.icon_search);
        iconClear = (TextView) findViewById(R.id.icon_clear);
        search = (EditText) findViewById(R.id.search);
        clearLayout = (LinearLayout) findViewById(R.id.clear_layout);
    }
    //初始化Icons
    private void initIcons(){
        Typeface searchIcons = Typeface.createFromAsset(getAssets(), "iconfonts/search_icon.ttf");
        iconSearch.setTypeface(searchIcons);
        iconClear.setTypeface(searchIcons);
    }


}
