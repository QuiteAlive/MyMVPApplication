package com.mengb.mymvpapplication.view.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mengb.mymvpapplication.R;
import com.mengb.mymvpapplication.adapter.ListAdapter;
import com.mengb.mymvpapplication.bean.Datas;
import com.mengb.mymvpapplication.bean.JsonDataBean;
import com.mengb.mymvpapplication.presenter.impl.MainAPresenterImpl;
import com.mengb.mymvpapplication.presenter.inter.IMainAPresenter;
import com.mengb.mymvpapplication.view.inter.IMainAView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainAView {

    private IMainAPresenter mIMainAPresenter;
    private Button button;
    List<Datas> datasList;
    JsonDataBean jsonDataBeans;
    private ListView data_list;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIMainAPresenter = new MainAPresenterImpl(this);
        setContentView(R.layout.activity_main);
        initViewBind();
    }

    private void initViewBind() {
        button = (Button) findViewById(R.id.button);
        data_list = (ListView) findViewById(R.id.lv_data_list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIMainAPresenter.getData();
            }
        });
    }

    @Override
    public <T> T request(int requestFlag) {
        return null;
    }

    @Override
    public <T> void response(T response, int responseFlag) {
        if (responseFlag == IMainAView.RESPONSE_ONE) {
            jsonDataBeans = (JsonDataBean) response;
            // datasList = jsonDataBeans.getDatas();
            datasList = jsonDataBeans.getDatas();
            listAdapter = new ListAdapter(MainActivity.this, datasList);
            data_list.setAdapter(listAdapter);
//            new UpData().execute();
        }
    }

    @Override
    public String getToken() {
        return null;
    }
    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
//    class UpData extends AsyncTask<String ,Void,Boolean>{
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Boolean doInBackground(String... strings) {
//            data_list.setAdapter(listAdapter);
//            return true;
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//    }
}
