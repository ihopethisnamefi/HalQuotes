package com.example.halquotes;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

public class App extends MainActivity {

    private static Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
