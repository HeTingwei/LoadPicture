package com.example.httptest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeTingwei on 2017/9/27.
 */

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.MyImgViewHolder> {

    List<String> imgList;
    Context context;
    int width = 0;
    DisplayMetrics dm;
    List<ImageView>imgViewList;
    Handler imgHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what>=0&&msg.what<9){
                ImageView imgtemp=imgViewList.get(msg.what);
                byte[] picture= (byte[]) msg.obj;
                Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
                //通过imageview，设置图片
                imgtemp.setImageBitmap(bitmap);
                picture=null;
            }
        }
    };

    public ImgAdapter(List<String> imgList, Context context) {
        this.imgList = imgList;
        this.context = context;
        dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        imgViewList=new ArrayList<>();

        for (int i = 0; i <imgList.size() ; i++) {
            new GetHttpThread(imgList.get(i),imgHandler,i).start();
        }
    }

    @Override
    public MyImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        width = (dm.widthPixels - dip2px(20)) / 3;
        MyImgViewHolder holder = new MyImgViewHolder(LayoutInflater.from(context).inflate(R.layout.img_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyImgViewHolder holder, int position) {


        holder.img.setLayoutParams(new LinearLayout.LayoutParams(width, width));
        imgViewList.add(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    class MyImgViewHolder extends RecyclerView.ViewHolder {

    ImageView img;

        public MyImgViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }

    //转换大小单位
    int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
