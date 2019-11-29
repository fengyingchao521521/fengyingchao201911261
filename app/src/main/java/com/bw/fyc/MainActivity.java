package com.bw.fyc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bw.fyc.base.BaseActivity;
import com.bw.fyc.fragment.HomerFragment;
import com.bw.fyc.fragment.OtherFragment;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private ImageView image;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> list = new ArrayList<>();
    private ArrayList<String> name = new ArrayList<>();
    private Bitmap bitmap;


    @Override
    protected void initData() {
        HomerFragment homerFragment = new HomerFragment();
        list.add(homerFragment);

        //传值
        OtherFragment TujianFragment = OtherFragment.getInstance("推荐");
        list.add(TujianFragment);
        OtherFragment PhotoFragment = OtherFragment.getInstance("视频");
        list.add(PhotoFragment);

        name.add("要闻");
        name.add("推荐");
        name.add("视频");
        name.add("封英超");

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return name.get(position);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initView() {
        image = findViewById(R.id.image);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.vp);
        //点击切换头像
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == -1) {
            Uri data1 = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data1);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
