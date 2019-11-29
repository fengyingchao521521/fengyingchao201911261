package com.bw.fyc;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
 *@auther: 封英超
 *@Date: 2019/11/26
 *@Time:14:39
 *@Description:${DESCRIPTION}
 *
 */public class MyAdapter extends BaseAdapter {

    List<Bean.ListdataBean> list;

    public MyAdapter(List<Bean.ListdataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = list.get(position).getItemType();
        if (itemType == 1) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        int itemViewType = getItemViewType(position);
        if (convertView == null) {
            if (itemViewType == 0) {
                convertView = View.inflate(parent.getContext(), R.layout.item1, null);
            } else {
                convertView = View.inflate(parent.getContext(), R.layout.item2, null);
            }
            viewHodler = new ViewHodler();
            viewHodler.tv_imageView = convertView.findViewById(R.id.tv_imageView);
            viewHodler.tv_textView = convertView.findViewById(R.id.tv_textView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        //获取
        Bean.ListdataBean listdataBean = list.get(position);
        //绑定
        viewHodler.tv_textView.setText(listdataBean.getContent());
        //照片
        NetUtiler.getInstance().getphoto(listdataBean.getImageurl(), viewHodler.tv_imageView);

        return convertView;
    }

    class ViewHodler {
        ImageView tv_imageView;
        TextView tv_textView;
    }

}
