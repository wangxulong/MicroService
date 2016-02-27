package com.shu.microservice.myActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import com.shu.microservice.R;

/**
 * Created by Administrator on 2016/2/24 0024.
 */
public class MyServerActivity extends AppCompatActivity {

    ImageView imageView_back = null;
    ListView listview = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_server);


        listview=(ListView)findViewById(R.id.server_list);
        MyServerList adapter=new MyServerList(this);
        listview.setAdapter(adapter);
        listview.setItemsCanFocus(false);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                MyServerList.ViewHolder vHollder = (MyServerList.ViewHolder) view.getTag();
                //在每次获取点击的item时将对于的checkbox状态改变，同时修改map的值。
                vHollder.cBox.toggle();
                MyServerList.isSelected.put(position, vHollder.cBox.isChecked());
            }
        });

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
