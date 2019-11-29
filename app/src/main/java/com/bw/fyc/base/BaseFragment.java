package com.bw.fyc.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
 *@auther: 封英超
 *@Date: 2019/11/26
 *@Time:14:10
 *@Description:${DESCRIPTION}
 *
 */public abstract class BaseFragment  extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), layoutId(), null);
        initView(inflate);
        return inflate;

    }

    protected abstract void initView(View inflate);

    protected abstract int layoutId();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();
}
