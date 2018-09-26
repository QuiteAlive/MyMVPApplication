package com.mengb.mymvpapplication.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mengb.mymvpapplication.Main2Activity;
import com.mengb.mymvpapplication.R;
import com.mengb.mymvpapplication.adapter.ListAdapter;
import com.mengb.mymvpapplication.bean.BannerBean;
import com.mengb.mymvpapplication.bean.Datas;
import com.mengb.mymvpapplication.bean.JsonDataBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.mengb.mymvpapplication.ActivityUtils.exitBy2Click;

public class OneFragment extends Fragment implements AbsListView.OnScrollListener {
    private int i=1;
    JsonDataBean jsonDataBean;
    BannerBean bannerBean;
    private Button button;
    List<Datas> datasList;
    private ListView data_list;
    private List<Datas> olddatasList = new ArrayList<>();
    ListAdapter listAdapter;
    View rootView;
    private Banner banner;
    private List<BannerBean.Data> UrlList;
    private  List<String> pathurl;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      rootView = inflater.inflate(R.layout.activity_main,container,false);
        initViewBind(rootView);

        return rootView;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
            // 滚动到最后一行了
            okURL("http://www.wanandroid.com/article/list/"+i+"/json",1);
            i++;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    private void initViewBind(View view) {
        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableScrollContentWhenLoaded(false);//是否在加载完成时滚动列表显示新的内容
        refreshLayout.setEnableScrollContentWhenRefreshed(false);//是否在刷新完成时滚动列表显示新的内容 1.0.5
        refreshLayout.setEnableAutoLoadMore(false);//是否启用列表惯性滑动到底部时自动加载更多
        okURL("http://www.wanandroid.com/article/list/0/json",1);
        button = (Button) view.findViewById(R.id.button);
        banner = (Banner)view.findViewById(R.id.banner);
        data_list = (ListView) view.findViewById(R.id.lv_data_list);
        data_list.setOnScrollListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        banner();
    }
    private void banner(){
        okURL("http://www.wanandroid.com/banner/json",2);


    }
    private void okURL(String url,final  int j){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Toast.makeText("ces");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String responsedata = response.body().string();
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = parser.parse(responsedata).getAsJsonObject();
                if (j==1) {
                    jsonDataBean = gson.fromJson(jsonObject.get("data").toString(), JsonDataBean.class);
                }else {
                    bannerBean = gson.fromJson(jsonObject,BannerBean.class);
                }
                // jsonDataBean = gson.fromJson(JSON.parseObject(responsedata).getString("data"), JsonDataBean.class);
                if (response.isSuccessful()){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (j==1) {
                                datasList = jsonDataBean.getDatas();
                                olddatasList.addAll(datasList);
                                listAdapter = new ListAdapter(getContext(), olddatasList);
                                data_list.setAdapter(listAdapter);
                            }else {
                                UrlList = bannerBean.getData();
                                pathurl = new ArrayList<>();
                                for (BannerBean.Data data:UrlList){
                                    pathurl.add(data.getImagePath());
                                }
                                //设置图片加载器
                                banner.setImageLoader(new GlideImageLoader());
                                //设置图片集合
                                banner.setImages(pathurl);
                                //banner设置方法全部调用完毕时最后调用
                                banner.start();
                            }
                        }
                    });
                }
            }
        });
    }
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
            //  Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建

    }
}
