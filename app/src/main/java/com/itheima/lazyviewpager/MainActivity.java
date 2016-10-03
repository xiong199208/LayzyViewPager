package com.itheima.lazyviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.itheima.lazyviewpager.base.BaseFragment;
import com.itheima.lazyviewpager.fragment.Fragment01;
import com.itheima.lazyviewpager.fragment.Fragment02;
import com.itheima.lazyviewpager.fragment.Fragment03;
import com.itheima.lazyviewpager.fragment.Fragment04;

import org.itheima.tabindicator.library.TabIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mPager;

    public String[] title = {"fragment01","fragment02","fragment03","fragment04"};
    //将已经加载了的Fragment存入集合
    List<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.viewpager);
        //ViewPager的指示器,用的是开源框架，为了方便测试- -；
        TabIndicator indicator = (TabIndicator) findViewById(R.id.indicator);


        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        //指示器绑定ViewPager
        indicator.setViewPager(mPager);

        //设置ViewPager的选择监听
        mPager.addOnPageChangeListener(this);

    }

    Boolean first = true;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (first && positionOffset == 0 && positionOffsetPixels == 0){
            onPageSelected(0);
            first = false;
        }
    }

    @Override
    public void onPageSelected(int position) {
        System.out.println("aaaaaaaaaaaaaaa");
        BaseFragment fragment = mFragments.get(position);
        fragment.loadData();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * ViewPager的适配器
     */
    class MyPagerAdapter extends FragmentPagerAdapter{

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    BaseFragment fragment01 = new Fragment01();
                    mFragments.add(fragment01);
                    return fragment01;

                case 1:
                    BaseFragment fragment02 = new Fragment02();
                    mFragments.add(fragment02);
                    return fragment02;

                case 2:
                    BaseFragment fragment03 = new Fragment03();
                    mFragments.add(fragment03);
                    return fragment03;

                case 3:
                    BaseFragment fragment04 = new Fragment04();
                    mFragments.add(fragment04);
                    return fragment04;

                default:
                    BaseFragment fragment = new Fragment01();
                    mFragments.add(fragment);
                    return fragment;

            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
