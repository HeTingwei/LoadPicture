package com.example.httptest;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HeTingwei on 2017/9/27.
 */

public class GetHttpThread extends Thread {

    private static final String TAG = "GetHttpThread";
    String url;
    Handler handler;
    int what;
    OkHttpClient client;
    byte[] b;

    public GetHttpThread(String url, Handler handler,int what) {
        this.url = url;
        this.handler = handler;
        client = new OkHttpClient();
        this.what=what;
    }

    @Override
    public void run() {
        super.run();
        try {
            b=getHttp(url);
            Message msg=new Message();
            msg.what=what;
            msg.obj=b;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "run: 123321");
    }


    byte[] getHttp(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().bytes();
    }
}
