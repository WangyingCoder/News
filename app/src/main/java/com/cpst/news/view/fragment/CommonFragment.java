package com.cpst.news.view.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpst.news.R;
import com.cpst.news.adapter.GankAdapter;
import com.cpst.news.base.BaseFragment;
import com.cpst.news.databinding.FragmentCommonBinding;
import com.cpst.news.event.KeyDownEvent;
import com.cpst.news.event.ListStatusEvent;
import com.cpst.news.event.RxBus;
import com.cpst.news.model.GankItemEntity;
import com.cpst.news.view.activity.GankDetailActivity;
import com.cpst.news.vm.GankViewModel;

/**
 * HomeFragment的每个标题fragment
 * rootView 无法通过data绑定
 */
public class CommonFragment extends BaseFragment<FragmentCommonBinding> {
    private String      category;
    private GankAdapter adapter;
    private GankViewModel vm;


    /**
     * HomeFragment类调用
     * 传入category，获取对应的Fragment实例.
     * 方便拿到每个Fragment的category值，进行网络请求。
     */
    public static CommonFragment newInstance(String category){
        Bundle args = new Bundle();
        args.putString("category",category);
        CommonFragment fragment = new CommonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_common;
    }

    @Override
    protected void init() {
        category = getArguments().getString("category");
        adapter = new GankAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mBinding.list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.list.setAdapter(adapter);
        vm = new GankViewModel(this,adapter);

        //设置默认颜色
        mBinding.refresh.setColorSchemeResources(R.color.colorPrimary);
        //设置下拉进度的主题颜色
        mBinding.refresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryTrans);

        refresh();
    }

    private void refresh() {
        //下拉刷新
        mBinding.refresh.setOnRefreshListener(() -> {
            //请求数据
            vm.getDate(true,category);
        });

        //上拉加载 第三方依赖
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //请求数据
                vm.getDate(false, category);
            }
        });

        /**
         * 后期考虑删除
         * RecyclerView滑动监听，判断是否滚动到底部或顶部
         * canScrollVertically(1) 的值表示是否能向上滚动，false表示已经滚动到底部
         * canScrollVertically(-1)的值表示是否能向下滚动，false表示已经滚动到顶部
         */
        mBinding.list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //发送Event到MainActivity，根据情况
                if(mBinding.list.canScrollVertically(-1)) {
                    RxBus.getDefualt().post(new ListStatusEvent(ListStatusEvent.Scrool));
                }else {
                    RxBus.getDefualt().post(new ListStatusEvent(ListStatusEvent.Top));
                }
            }
        });

        /**
         * 订阅监听
         * 接收MainActivity发送的Event，停止页面刷新。
         */
        RxBus.getDefualt().toFlowable(KeyDownEvent.class)
                .filter(event -> event.getKeyCode() == KeyDownEvent.KEYCODE_BACK)
                .subscribe(event -> {
                    //取消刷新，回到顶部。
                    mBinding.list.smoothScrollToPosition(0);
                });

        /**
         * 查看详情页面
         */
        adapter.setOnItemClickListener((adapter1, view, position) -> GankDetailActivity.startActivity(CommonFragment.this.getActivity(), (GankItemEntity) adapter1.getItem(position)));

        vm.getDate(true,category);
    }

    @Override
    public void showLoading() {
        mBinding.layoutStatus.showLoading();
    }

    @Override
    public void showContent() {
        mBinding.layoutStatus.showContent();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        mBinding.layoutStatus.showError(msg, v -> vm.getDate(true, category));
    }

    @Override
    public void showEmpty() {

    }
}
