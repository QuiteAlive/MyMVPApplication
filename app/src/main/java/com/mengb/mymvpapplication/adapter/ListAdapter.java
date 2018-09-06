package com.mengb.mymvpapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mengb.mymvpapplication.ActivityUtils;
import com.mengb.mymvpapplication.R;
import com.mengb.mymvpapplication.bean.Datas;
import com.mengb.mymvpapplication.bean.JsonDataBean;

import java.util.HashMap;
import java.util.List;

public class ListAdapter extends BaseAdapter{
    private Context context;
    private List<Datas> listdatas;

    @Override
    public int getCount() {
        if (listdatas==null){
            return 0;
        }
        return listdatas.size();
    }

    @Override
    public Object getItem(int position) {
        if (listdatas==null){
            return null;
        }
        return listdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
            holder.ll_item_list_all = convertView.findViewById(R.id.ll_item_list_all);
            holder.iv_item_list_image = convertView.findViewById(R.id.im_item_list);
            holder.tv_item_list_data = convertView.findViewById(R.id.tv_liem_list_data);
            holder.tv_item_list_title = convertView.findViewById(R.id.tv_item_list_title);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
      //加载图片现无

        holder.tv_item_list_title.setText(listdatas.get(position).getTitle());
        holder.tv_item_list_data.setText(listdatas.get(position).getNiceDate());


        holder.ll_item_list_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(listdatas.get(position).getLink());
                context.startActivity(intent);*/
              //  context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(listdatas.get(position).getLink())));
                ActivityUtils.StartURLIntent(context,listdatas.get(position).getLink());

                //Toast.makeText(context,"作者"+listdatas.get(position).getAuthor(),Toast.LENGTH_SHORT).show();

            }
        });
        return convertView;
    }
    public ListAdapter(Context context, List<Datas> datasList){
        this.context = context;
        this.listdatas= datasList;
    }
    public class ViewHolder{
        private LinearLayout ll_item_list_all;
        private TextView tv_item_list_title,
        tv_item_list_data;
        private ImageView iv_item_list_image;

    }
}
