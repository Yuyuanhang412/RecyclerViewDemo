package com.bwie.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bwie.recyclerviewdemo.adapter.MAdapter;
import com.bwie.recyclerviewdemo.bean.CommunityBean;
import com.bwie.recyclerviewdemo.url.Url;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //url="http://open.qyer.com/qyer/bbs/entry?client_id=qyer_android&client_secret=9fcaae8aefc4f9ac4915"
    RequestQueue queue;
    RecyclerView mRV;
    //@BindView(R.id.tv_text)
   // TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ButterKnife.bind(this);

        //初始化View
        initView();
        //初始化请求队列
        queue = Volley.newRequestQueue(this);
        // RecyclerView
        //从网上初始化数据
        initDataFromNet();

    }


    /**
     * 初始化视图
     */
    private void initView() {
        mRV = (RecyclerView) findViewById(R.id.id_recyclerView);
        mRV.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * 从网上获取数据
     */
    private void initDataFromNet() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.HOME_ONE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                processData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //将请求加入到队列中
        queue.add(stringRequest);
    }

    /**
     * gson 解析数据
     *
     * @param response
     */
    private void processData(String response) {
        System.out.println("response==" + response);
        CommunityBean communityBean = new Gson().fromJson(response, CommunityBean.class);
        //设置适配器
        mRV.setAdapter(new MAdapter(this, communityBean));
    }

   /* @OnClick(R.id.tv_text)
    public void onClick() {
    }*/
}
