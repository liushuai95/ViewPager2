package com.ls.viewpager.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.ls.viewpager.R;
import com.ls.viewpager.fragment.PageFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentStateAdapterActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager2;
    private List<Integer> colors = new ArrayList<>();
    private ViewPagerFragmentStateAdapter mAdapter;


    {
        colors.add(android.R.color.black);
        colors.add(android.R.color.holo_purple);
        colors.add(android.R.color.holo_blue_dark);
        colors.add(android.R.color.holo_green_light);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_state_adapter);

        mTabLayout = findViewById(R.id.tablayout);
        mViewPager2 = findViewById(R.id.viewpager2);

        mAdapter = new ViewPagerFragmentStateAdapter(this);
        mViewPager2.setAdapter(mAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText("Tab0"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab3"));

        // 添加页签选中监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // 注册页面变化的回调接口
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTabLayout.setScrollPosition(position, 0, false);
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fragment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                if (TextUtils.equals(item.getTitle(), getString(R.string.action_add))) {
                    colors.add(android.R.color.holo_red_light);
                    mTabLayout.addTab(mTabLayout.newTab().setText("Tab4"));
                    //添加通知
                    mAdapter.notifyItemInserted(colors.size() - 1);
                    item.setIcon(R.drawable.ic_action_remove);
                    item.setTitle(R.string.action_remove);
                } else {
                    item.setIcon(R.drawable.ic_action_add);
                    item.setTitle(R.string.action_add);
                    int last = colors.size() - 1;
                    colors.remove(last);
                    mTabLayout.removeTabAt(last);
                    //移除通知
                    mAdapter.notifyItemRemoved(last);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class ViewPagerFragmentStateAdapter extends FragmentStateAdapter {

//        public ViewPagerFragmentStateAdapter(@NonNull FragmentManager fragmentManager) {
//            super(fragmentManager);
//        }


        public ViewPagerFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return PageFragment.newInstance(colors, position);
        }

        @Override
        public int getItemCount() {
            return colors.size();
        }


    }

}
