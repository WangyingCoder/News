package com.cpst.news.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpst.news.R;
import com.cpst.news.base.BaseFragment;
import com.cpst.news.databinding.FragmentImageBinding;
import com.cpst.news.event.KeyDownEvent;
import com.cpst.news.event.ListStatusEvent;
import com.cpst.news.event.RxBus;
import com.cpst.news.view.adapter.ImageViewAdapter;
import com.cpst.news.vm.ImageViewModel;

/**
 * 图片
 */
public class ImageFragment extends BaseFragment<FragmentImageBinding> implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{

    private ImageViewAdapter adapter;
    private ImageViewModel   vm;

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_image;
    }

    @Override
    protected void init() {
        adapter = new ImageViewAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mBinding.list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.list.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, mBinding.list);
        vm = new ImageViewModel(this,adapter);
        //设置默认颜色
        mBinding.refresh.setColorSchemeResources(R.color.colorPrimary);
        //设置下拉进度的主题颜色
        mBinding.refresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryTrans);
        mBinding.refresh.setOnRefreshListener(this);
        refresh();
    }

    /**
     * RecyclerView滑动监听，判断是否滚动到底部或顶部
     * canScrollVertically(1) 的值表示是否能向上滚动，false表示已经滚动到底部
     * canScrollVertically(-1)的值表示是否能下拉，false表示已经滚动到顶部，不能下拉
     */
    private void refresh() {
        mBinding.list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                /**
                 * 若滑动到底部可自动加载更多，替换掉加载更多的监听。
                 */
//                if(!mBinding.list.canScrollVertically(-1)) {
//                    //TODO:滑动到顶部,不能下拉
//                    RxBus.getDefualt().post(new ListStatusEvent(ListStatusEvent.Top));
//                }else if(!mBinding.list.canScrollVertically(1)) {
//                    //TODO:滑动到底部，加载更多
//                    vm.getImageView(false);
//                }else {
//                    //TODO:可以滑动
//                    RxBus.getDefualt().post(new ListStatusEvent(ListStatusEvent.Scrool));
//                }

                if(mBinding.list.canScrollVertically(-1)) {
                    RxBus.getDefualt().post(new ListStatusEvent(ListStatusEvent.Scrool));
                }else {
                    RxBus.getDefualt().post(new ListStatusEvent(ListStatusEvent.Top));
                }
            }
        });

        /**
         * 订阅监听：接收MainActivity发送的Event，停止页面刷新。
         */
        RxBus.getDefualt().toFlowable(KeyDownEvent.class)
                .filter(event -> event.getKeyCode() == KeyDownEvent.KEYCODE_BACK)
                .subscribe(event -> {
                    //取消刷新，回到顶部。
                    mBinding.list.smoothScrollToPosition(0);
                });
        vm.getImageView(true);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        vm.getImageView(true);
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        vm.getImageView(false);
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
        mBinding.layoutStatus.showError(msg, v -> vm.getImageView(true));
    }

    @Override
    public void showEmpty() {

    }
}