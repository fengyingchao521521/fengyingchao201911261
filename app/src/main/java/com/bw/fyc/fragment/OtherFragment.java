package com.bw.fyc.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.fyc.R;
import com.bw.fyc.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends BaseFragment {


    private TextView text2;

    @Override
    protected void initView(View inflate) {
        text2 = inflate.findViewById(R.id.text2);


    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initData() {
        String key = getArguments().getString("key");
        text2.setText(key);
    }

    public static OtherFragment getInstance(String values) {
        OtherFragment otherFragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", values);
        otherFragment.setArguments(bundle);
        return otherFragment;
    }
}
