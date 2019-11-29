package com.bw.fyc.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
 *@auther: 封英超
 *@Date: 2019/11/26
 *@Time:14:09
 *@Description:${DESCRIPTION}
 *
 */public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        initView();
        initData();

    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int layoutId();
}
