package com.shu.microservice;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.shu.microservice.activity.HomeFragment;
import com.shu.microservice.activity.MyFragment;
import com.shu.microservice.activity.QuestionFragment;
import com.shu.microservice.activity.RequireFragment;
import com.shu.microservice.activity.SearchActivity;
import com.shu.microservice.activity.ServiceFragment;
import com.shu.microservice.adapter.QuestionCommentAdapter;
import com.shu.microservice.model.CommentItem;
import com.shu.microservice.myActivity.MySettingActivity;
import com.shu.microservice.util.AppContext;
import com.shu.microservice.util.NetUtil;
import com.shu.microservice.util.NormalPostRequest;
import com.shu.microservice.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView homeIcon;
    private TextView questionIcon;
    private TextView serviceIcon;
    private TextView requireIcon;
    private TextView myIcon;

    private TextView appIcon;

    private TextView homeText;
    private TextView questionText;
    private TextView serviceText;
    private TextView requireText;
    private TextView myText;

    private LinearLayout homeTab;
    private LinearLayout questionTab;
    private LinearLayout serviceTab;
    private LinearLayout requireTab;
    private LinearLayout myTab;

    private List<Fragment> fragments;
    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;

    private EditText mainSearch;
    //登录
    private RequestQueue mRequestQueue;
    private SharedPreferences mySharedPreferences;
    private String name;
    private String password;
    private long id;
    private static Boolean loginStatus = false;
    //用户登录API
    public  static final String hotQuestionURL = "http://115.159.104.74:8080/micro_admin/ajax/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initIcons();
        initEvents();
        resetIcons();
        setTab(0);
    }

    /**
     * 初始化点击事件
     */
    private void initEvents() {
        mainSearch.setOnClickListener(this);
        homeTab.setOnClickListener(this);
        questionTab.setOnClickListener(this);
        serviceTab.setOnClickListener(this);
        requireTab.setOnClickListener(this);
        myTab.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //mainSearch
        mainSearch = (EditText) findViewById(R.id.main_search);
        //iconTextView
        homeIcon = (TextView) findViewById(R.id.home_icon);
        questionIcon =(TextView) findViewById(R.id.questiion_icon);
        serviceIcon = (TextView) findViewById(R.id.service_icon);
        requireIcon = (TextView) findViewById(R.id.require_icon);
        myIcon = (TextView) findViewById(R.id.my_icon);
        appIcon = (TextView) findViewById(R.id.app_icon);
        //text
        homeText = (TextView) findViewById(R.id.home_text);
        questionText = (TextView) findViewById(R.id.question_text);
        serviceText = (TextView) findViewById(R.id.service_text);
        requireText = (TextView) findViewById(R.id.require_text);
        myText = (TextView) findViewById(R.id.my_text);
        //layout
        homeTab = (LinearLayout) findViewById(R.id.home);
        questionTab = (LinearLayout) findViewById(R.id.question);
        serviceTab = (LinearLayout) findViewById(R.id.service);
        requireTab = (LinearLayout) findViewById(R.id.require);
        myTab = (LinearLayout) findViewById(R.id.my);

        //ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //fragments
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new QuestionFragment());
        fragments.add(new ServiceFragment());
        fragments.add(new RequireFragment());
        fragments.add(new MyFragment());
        //适配器
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(adapter);
    }

    /**
     * 初始化图标
     */
    private void initIcons() {
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfonts/iconfont.ttf");
        Typeface appIcons = Typeface.createFromAsset(getAssets(), "iconfonts/app_icon.ttf");
        Typeface ques_iconfont = Typeface.createFromAsset(getAssets(), "iconfonts/ques_iconfont.ttf");
        homeIcon.setTypeface(iconfont);
        questionIcon.setTypeface(ques_iconfont);
        serviceIcon.setTypeface(iconfont);
        requireIcon.setTypeface(iconfont);
        myIcon.setTypeface(iconfont);
        appIcon.setTypeface(appIcons);
    }
    //把图标设置成黑色
    private void resetIcons(){
        int defaultColor=  Color.parseColor("#000000");
        homeIcon.setTextColor(defaultColor);
        questionIcon.setTextColor(defaultColor);
        serviceIcon.setTextColor(defaultColor);
        myIcon.setTextColor(defaultColor);
        requireIcon.setTextColor(defaultColor);

        homeText.setTextColor(defaultColor);
        questionText.setTextColor(defaultColor);
        serviceText.setTextColor(defaultColor);
        requireText.setTextColor(defaultColor);
        myText.setTextColor(defaultColor);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:
                 setTab(0);
                 break;
            case R.id.question:
                 setTab(1);
                 break;
            case R.id.service:
                setTab(2);
                 break;
            case R.id.require:
                setTab(3);
                break;
            case R.id.my:
                setTab(4);
                break;
            case R.id.main_search:
                goSearch();
                break;

        }
    }
    //跳转到search页面
    private void goSearch() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    //选中页签
    private void setTab(int i){
        resetIcons();
        int selectedColor=  Color.parseColor("#009300");
        viewPager.setCurrentItem(i);
        switch (i){
            case 0:
                homeIcon.setTextColor(selectedColor);
                homeText.setTextColor(selectedColor);
                break;
            case 1:
                questionIcon.setTextColor(selectedColor);
                questionText.setTextColor(selectedColor);
                break;
            case 2:
                serviceIcon.setTextColor(selectedColor);
                serviceText.setTextColor(selectedColor);
                break;
            case 3:
                requireIcon.setTextColor(selectedColor);
                requireText.setTextColor(selectedColor);
                break;
            case 4:
                myIcon.setTextColor(selectedColor);
                myText.setTextColor(selectedColor);
                break;
        }

    }

    public void Login_Click(View view){

        showDialog_Layout(this);

    }
    public void loginImageClick(View view){
        showDialog_Layout(this);
    }

    public void showMessage(){
        ToastUtil.showLong("1111");
    }
    public  void showDialog_Layout(Context context) {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View textEntryView = inflater.inflate(R.layout.login, null);
        final EditText editUsername=(EditText)textEntryView.findViewById(R.id.edit_username);
        final EditText editPassword=(EditText)textEntryView.findViewById(R.id.edit_password);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("登录界面");
        builder.setView(textEntryView);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        setTitle(editUsername.getText().toString());
                        setTitle(editPassword.getText().toString());
                        name = editUsername.getText().toString();
                        password = editPassword.getText().toString();
                        Log.i("tag", name + "/" + password);
                        loginConnet();
//                        if(loginStatus){
//
//                        }else{
//
//                        }


                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        setTitle("");
                    }
                });
        builder.show();
    }



    public void loginConnet(){
        if(!NetUtil.isConnected(AppContext.getAppContext())){
            ToastUtil.showLong("网络连接失败");
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("name", name);
        map.put("password", password);
        // 1 创建RequestQueue对象
        mRequestQueue = AppContext.getAppQueue();
        // 2 创建JsonObjectRequest对象
        Request<JSONObject> request = new NormalPostRequest(hotQuestionURL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //
                        try {
                            if(response.getString("status").equals("success")){
                                loginStatus = true;
                                JSONObject results = response.getJSONObject("data");
                                if(results!=null){
                                    id = results.getLong("id");
                                }
                                //保存用户个人信息
                                saveInfomation();

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
        },map);
        // 3 将JsonObjectRequest添加到RequestQueue
        mRequestQueue.add(request);
    }

    //保存个人基本信息
    public void saveInfomation(){
        mySharedPreferences= getSharedPreferences("MyInfo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putLong("id", id);
        editor.putString("name", name);
        editor.putString("password", password);
        editor.commit();
        //使用toast信息提示框提示成功写入数据
        Toast.makeText(this, "数据成功写入SharedPreferences！", Toast.LENGTH_LONG).show();

        //返回STRING_KEY的值
        Log.d("SP", mySharedPreferences.getString("STRING_KEY", "none"));
        //如果NOT_EXIST不存在，则返回值为"none"
        Log.d("SP", mySharedPreferences.getString("NOT_EXIST", "none"));

    }
}
