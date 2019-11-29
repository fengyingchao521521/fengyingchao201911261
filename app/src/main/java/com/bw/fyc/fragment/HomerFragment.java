package com.bw.fyc.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bw.fyc.Bean;
import com.bw.fyc.MyAdapter;
import com.bw.fyc.NetUtiler;
import com.bw.fyc.R;
import com.bw.fyc.SerActivity;
import com.bw.fyc.base.BaseFragment;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomerFragment extends BaseFragment {

    private PullToRefreshListView pullToRefreshListView;
    private RelativeLayout relativeLayout;
    List<Bean.ListdataBean> list = new ArrayList<>();
    private int page = 1;

    @Override
    protected void initView(final View inflate) {
        pullToRefreshListView = inflate.findViewById(R.id.pull);
        relativeLayout = inflate.findViewById(R.id.rl);

        //=============================================
        //webView
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bean.ListdataBean listdataBean = list.get(position);
                String imageurl = listdataBean.getImageurl();
                Intent intent = new Intent(getActivity(), SerActivity.class);
                intent.putExtra("key",intent);
                startActivity(intent);
            }
        });
        //=============================================

        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                list.clear();
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                getData();
            }
        });
    }

    private void getData() {
        if (NetUtiler.getInstance().hasNet(getActivity())) {
            pullToRefreshListView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);

            String httpurl = "";
            if (page == 1) {
                httpurl = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai1.json";
            } else if (page == 2) {
                httpurl = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai2.json";
            } else {
                httpurl = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai3.json";
            }

            NetUtiler.getInstance().getjson(httpurl, new NetUtiler.MyCallback() {
                @Override
                public void onGetjson(String json) {
                    Log.i("xxx","请求成功"+json);
                    Bean bean = new Gson().fromJson(json, Bean.class);
                    List<Bean.ListdataBean> newlistdata = bean.getListdata();
                    list.addAll(newlistdata);
                    pullToRefreshListView.setAdapter(new MyAdapter(list));
                    pullToRefreshListView.onRefreshComplete();
                }
            });
        } else {
            pullToRefreshListView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_homer;
    }

    @Override
    protected void initData() {
        getData();
    }


}
