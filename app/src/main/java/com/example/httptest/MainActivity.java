package com.example.httptest;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    SwipeRefreshLayout srl;
    ItemAdapter itemAdapter;
    public static  final int COUNT_ITEM=5;//列表每次下拉加载的子项数
    public static  final int COUNT_IMG=9;//每一个列表子项有的图片数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,}, 1);

        rv = (RecyclerView) findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));

        itemAdapter = new ItemAdapter(this, new ArrayList<ItemBean>());

        rv.setAdapter(itemAdapter);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                srl.setRefreshing(false);
                //rv.smoothScrollToPosition(0);
            }
        });
    }

    //在下拉时开始加载了表，并开始子项图片，数据是随便加的所以用了for循环
    private void loadData() {

        for (int i = 0; i < COUNT_ITEM; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.setText("第" + i + "项");
            List<String> list = new ArrayList<>();
            for (int j = 1; j <=COUNT_IMG; j++) {
                list.add("https://hetingwei.github.io/Web/image/hzw" + j + ".jpg");
            }
            itemBean.setImgList(list);
            itemAdapter.addItem(itemBean);

        }


    }

}
