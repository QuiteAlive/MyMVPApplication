package com.mengb.mymvpapplication.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mengb.mymvpapplication.app.AppApplication;
import com.mengb.mymvpapplication.bean.Datas;
import com.mengb.mymvpapplication.bean.JsonDataBean;
import com.mengb.mymvpapplication.model.inter.IMainAModel;
import com.mengb.mymvpapplication.presenter.callback.CallBack;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainAModelImpl implements IMainAModel {

    JsonDataBean jsonDataBean;
    Datas datas;

    @Override
    public void getData(String token, final CallBack callBack) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.wanandroid.com/article/list/1/json")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("login", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String responseData = response.body().string();
                jsonDataBean = gson.fromJson(JSON.parseObject(responseData).getString("data"), JsonDataBean.class);
                if (response.isSuccessful()){

                }
                if (jsonDataBean != null) {

                    callBack.onSuccess(jsonDataBean);
                } else {
                    callBack.onError(responseData);
                }
            }
        });
        /*进行网络请求，获取数据*/
        // 方式二：使用静态方式创建并显示，这种进度条只能是圆条,设置title和Message提示内容
           /* RequestQueue mQueue = Volley.newRequestQueue(AppApplication.getmContext());
            final StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, "http://www.wanandroid.com/article/list/1/json", new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Log.e("login", "-------获取到的idjson--------" + s.toString());
                    Log.e("login", "-------JSON.parseObject(json).data--------" + JSON.parseObject(s.toString()).getString("data"));
                    // jsondatabean = JSON.parseObject(JSON.parseObject(s.toString()).getString("data"), JsonDataBean.class);
                    Gson gson=new Gson();
                    jsonDataBean = gson.fromJson(JSON.parseObject(s.toString()).getString("data"), JsonDataBean.class);

                    //成功之后，传递出jsondatabean
                    if (jsonDataBean != null) {//获取到了数据
                        callBack.onSuccess(jsonDataBean);
                    } else {
                        callBack.onError(s);//获取失败信息
                    }
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    return map;
                }
            };
            /*设置请求一次*/
            /*stringRequest.setRetryPolicy(
                    new DefaultRetryPolicy(
                            500000,//默认超时时间，应设置一个稍微大点儿的，例如本处的500000
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,//默认最大尝试次数
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
            );
            mQueue.add(stringRequest);/*请求数据*/
    }
}
