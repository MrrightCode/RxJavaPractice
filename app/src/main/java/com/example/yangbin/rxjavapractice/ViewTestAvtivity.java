package com.example.yangbin.rxjavapractice;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yangbin.rxjavapractice.databinding.ActivityViewtestBinding;


/**
 * Created by yangbin on 2018/4/12.
 *
 * DataBinding 的学习类
 */
public class ViewTestAvtivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityViewtestBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_viewtest);
        

    }
}
