package com.mengb.mymvpapplication.fragment;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mengb.mymvpapplication.R;

import java.util.ArrayList;
import java.util.List;

import static com.mengb.mymvpapplication.ActivityUtils.exitBy2Click;

public class MainFragment extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

        private DrawerLayout mDrawerLayout;
        private ViewPager main_viewpager;
        private NavigationView navigation_view;
        private ImageView ImageView1;
        private ImageView ImageView2;
        private ImageView ImageView3;
        private List<Fragment> fragments = new ArrayList<>();
        private MainAdapter mAdapter;
        private ImageView NavigationImageView;


        private OneFragment oneFragment;
        private TwoFragment twoFragment;
        private ThreeFragment threeFragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_fragment);
            initView();
            initData();

        }

        private void initData() {
            NavigationImageView.setOnClickListener(this);
            /**
             * 利用menu设置监听事件
             */
           /* navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    int item_id = item.getItemId();
                    switch (item_id) {
                        case R.id.item_green:
                            Toast.makeText(MainActivity.this, "我的信息", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.item_blue:
                            Toast.makeText(MainActivity.this, "积分商城", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.item_pink:
                            Toast.makeText(MainActivity.this, "付费音乐包", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.subitem_01:
                            Toast.makeText(MainActivity.this, "听歌识曲", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.subitem_02:
                            Toast.makeText(MainActivity.this, "主题换肤", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "。。。其他。。。。", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    return true;
                }
            });*/
        }

        private void initView() {
            mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);
            main_viewpager = (ViewPager) findViewById(R.id.main_viewpager);
          //  navigation_view = (NavigationView) findViewById(R.id.navigation_view);
            ImageView1 = (ImageView) findViewById(R.id.ImageView1);
            ImageView2 = (ImageView) findViewById(R.id.ImageView2);
            ImageView3 = (ImageView) findViewById(R.id.ImageView3);
            NavigationImageView = (ImageView) findViewById(R.id.NavigationImageView);

            oneFragment = new OneFragment();
            twoFragment = new TwoFragment();
            threeFragment = new ThreeFragment();

            fragments.add(oneFragment);
            fragments.add(twoFragment);
            fragments.add(threeFragment);

            ImageView1.setSelected(true);

            mAdapter = new MainAdapter(getSupportFragmentManager());
            main_viewpager.setAdapter(mAdapter);

            main_viewpager.addOnPageChangeListener(this);
        }

        /**
         * NavigationImageView的监听事件（Imageview）
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            mDrawerLayout.openDrawer(navigation_view);

        }

        /**
         * 滑动
         *
         * @param position
         * @param positionOffset
         * @param positionOffsetPixels
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 滑动选中
         *
         * @param position
         */
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    ImageView1.setSelected(true);
                    ImageView2.setSelected(false);
                    ImageView3.setSelected(false);
                    break;
                case 1:
                    ImageView1.setSelected(false);
                    ImageView2.setSelected(true);
                    ImageView3.setSelected(false);
                    break;
                case 2:
                    ImageView1.setSelected(false);
                    ImageView2.setSelected(false);
                    ImageView3.setSelected(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        class MainAdapter extends FragmentPagerAdapter {

            public MainAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        }

        public void one_iv(View view) {

            main_viewpager.setCurrentItem(0);


            ImageView1.setSelected(true);
            ImageView2.setSelected(false);
            ImageView3.setSelected(false);
        }

        public void two_iv(View view) {
            main_viewpager.setCurrentItem(1);


            ImageView1.setSelected(false);
            ImageView2.setSelected(true);
            ImageView3.setSelected(false);
        }

        public void three_iv(View view) {
            main_viewpager.setCurrentItem(2);


            ImageView1.setSelected(false);
            ImageView2.setSelected(false);
            ImageView3.setSelected(true);
        }
        @Override
    public void onBackPressed() {
        if (exitBy2Click()) {
            super.onBackPressed();
        }else {
            Toast.makeText(MainFragment.this,"请再按一下",0).show();
        }
    }
    }
