package com.cpst.news.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cpst.news.R;
import com.cpst.news.base.BaseFragment;
import com.cpst.news.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页Fragment
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private MyFragmentPagerAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[]{"Android","iOS","休息视频","前端","拓展资源","福利"};

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_home;
    }

    /**
     * 敲黑板(重点)：
     * 方法1.每次初始化创建7个Fragment实例
     *   好处：可以获取到每个Fragment中的标题
     *   坏处：重复操作会创建N倍个Fragment,导致fragments与titles中position角标越界，需要fragments.clear()。
     *
     * 方法2.创建一个Fragment实例
     *   好处：简化Fragment个数，节约资源
     *   坏处：不能获取到每个Fragment中的标题
     */

    protected void init() {
        fragments.clear();
        for(int i = 0; i < titles.length; i++) {
            fragments.add(CommonFragment.newInstance(titles[i]));
        }
        //关键的一个知识点getChidFragmentManager
        adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragments);
        mBinding.viewPager.setAdapter(adapter);

        //TabLayout
        mBinding.tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mBinding.tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mBinding.tablayout.setupWithViewPager(mBinding.viewPager);
        //显示当前那个标签页
        //viewPager.setCurrentItem(0);

    }

    /**
     * 头部Tab菜单
     * 经验：只要发生角标越界异常：1.务必检查角标值，list个数；2.是否没有清理list.clear()。
     */
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment>  mList;

        public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            this.mList = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return this.mList == null ? 0 : titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showEmpty() {

    }
}
