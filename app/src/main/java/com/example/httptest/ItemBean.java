package com.example.httptest;

import java.util.List;

/**
 * Created by HeTingwei on 2017/9/27.
 */

public class ItemBean {

    String text;
    List<String>imgList;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
