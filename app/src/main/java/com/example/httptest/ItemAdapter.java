package com.example.httptest;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HeTingwei on 2017/9/27.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    Context context;
    List<ItemBean> list;
    ImgAdapter imgAdapter;

    public ItemAdapter(Context context, List<ItemBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getText());
        holder.imgRv.setLayoutManager(new GridLayoutManager(context,3));
        imgAdapter=new ImgAdapter(list.get(position).getImgList(),context);

        holder.imgRv.setAdapter(imgAdapter);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        RecyclerView imgRv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv);
            imgRv = (RecyclerView) itemView.findViewById(R.id.img_rv);
        }
    }

    public void addItem(ItemBean itemBean){
        list.add(0,itemBean);
        notifyItemInserted(0);
    }
}
