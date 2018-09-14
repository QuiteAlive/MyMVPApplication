package com.mengb.mymvpapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mengb.mymvpapplication.adapter.ListAdapter;
import com.mengb.mymvpapplication.bean.Datas;
import com.mengb.mymvpapplication.bean.JsonDataBean;
import com.mengb.mymvpapplication.presenter.inter.IMainAPresenter;
import com.mengb.mymvpapplication.view.activity.MainActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.mengb.mymvpapplication.ActivityUtils.exitBy2Click;


public class Main2Activity extends AppCompatActivity{
    JsonDataBean jsonDataBean;
    private Button button;
    List<Datas> datasList;
    private ListView data_list;
    ListAdapter listAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewBind();
        okURL();
    }
    private void initViewBind() {
        button = (Button) findViewById(R.id.button);
        data_list = (ListView) findViewById(R.id.lv_data_list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void okURL(){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
        .url("http://www.wanandroid.com/article/list/0/json")
        .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText("ces");


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String responsedata = response.body().string();
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = parser.parse(responsedata).getAsJsonObject();
                jsonDataBean = gson.fromJson(jsonObject.get("data").toString(),JsonDataBean.class);
               // jsonDataBean = gson.fromJson(JSON.parseObject(responsedata).getString("data"), JsonDataBean.class);
                if (response.isSuccessful()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            datasList = jsonDataBean.getDatas();
                            listAdapter = new ListAdapter(Main2Activity.this, datasList);
                            data_list.setAdapter(listAdapter);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (exitBy2Click()) {
            super.onBackPressed();
        }else {
            Toast.makeText(Main2Activity.this,"请再按一下",0).show();
        }
    }
}
