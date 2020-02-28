package com.ls.viewpager.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.ls.viewpager.R;
import com.ls.viewpager.adapter.ViewPagerAdapter;

public class HorizontalScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scrolling);
        ViewPager2 viewPager2 = findViewById(R.id.viewpager2);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter();
        viewPager2.setAdapter(viewPagerAdapter);

        //纵向滑动
//        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
    }



}
