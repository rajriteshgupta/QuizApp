package com.example.myquiz;

import android.widget.ImageView;
import android.widget.TextView;

public class HomeViewModel {
    private int iv;
    private String tv;

    public HomeViewModel()
    {

    }
    public HomeViewModel(int iv ,String tv)
    {
        this.iv=iv;
        this.tv=tv;
    }

    public int getIv() {
        return iv;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public void setIv(int iv) {
        this.iv = iv;
    }
}
