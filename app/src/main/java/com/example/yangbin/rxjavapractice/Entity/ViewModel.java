package com.example.yangbin.rxjavapractice.Entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;

/**
 * Created by yangbin on 2018/4/13.
 */
public  class ViewModel {
    private String  mTvTest;

    public ViewModel(String text){
        mTvTest = text;
    }

    @Bindable
    public String getmTvTest() {
        return mTvTest;
    }

    public void setmTvTest(String mTvTest) {
        this.mTvTest = mTvTest;
    }
}
