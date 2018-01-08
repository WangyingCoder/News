package com.cpst.news;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.cpst.news.databinding.ActivityMainBinding;
import com.cpst.news.event.KeyDownEvent;
import com.cpst.news.event.ListStatusEvent;
import com.cpst.news.event.RxBus;
import com.cpst.news.utils.CircleImageView;
import com.cpst.news.view.fragment.HomeFragment;
import com.cpst.news.view.fragment.ImageFragment;
import com.cpst.news.view.fragment.JokesFragment;
import com.cpst.news.view.fragment.VideoFragment;

/**
 * 主页 菜单导航栏
 */
public class MainActivity extends AppCompatActivity  implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener{
    private ActivityMainBinding mBinding;
    private HomeFragment mHomeFragment = new HomeFragment();
    private JokesFragment mJokesFragment = new JokesFragment();
    private ImageFragment mImageFragment = new ImageFragment();
    private VideoFragment mVideoFragment = new VideoFragment();
    private ActionBarDrawerToggle drawerToggle;  //抽屉菜单切换
    private NavigationView navigationView;       //抽屉菜单
    private boolean  isLogin = false;
    private View headerlayout;
    private CircleImageView imageView;
    private int mListStatus;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.toolbar.setTitle("今日头条 - 新闻");
        setSupportActionBar(mBinding.toolbar);
        initDrawerLayout();
        intBoomTab();
        getInfo();
        initEvent();
    }

    /**
     * 验证登录信息
     * 暂时假数据
     */
    private void getInfo() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        if(!isLogin) {
            navigationView.addHeaderView(layoutInflater.inflate(R.layout.navigation_header1,navigationView,false));
            headerlayout = navigationView.getHeaderView(0);
            imageView = (CircleImageView) headerlayout.findViewById(R.id.image_before);
            imageView.setOnClickListener(this);
        }else {
            navigationView.addHeaderView( layoutInflater.inflate(R.layout.navigation_header2,navigationView,false));
            headerlayout = navigationView.getHeaderView(0);
            TextView tv_header = (TextView) headerlayout.findViewById(R.id.tv_header);
            TextView followers = (TextView) headerlayout.findViewById(R.id.followers);
            TextView following = (TextView) headerlayout.findViewById(R.id.following);
        }
    }

    /**
     * 抽屉菜单
     */
    private void initDrawerLayout() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //设置侧滑菜单Item图标显示原始颜色
        navigationView.setItemIconTintList(null);

        drawerToggle = new ActionBarDrawerToggle(this,mBinding.drawerLayout,mBinding.toolbar,R.string.open,R.string.close);
        //自动和actionBar关联，将开关的图片显示在了action上，如果不设置，则用默认的图标
        drawerToggle.syncState();
        mBinding.drawerLayout.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(false);
            item.setCheckable(false);
            switch (item.getItemId()){
                case R.id.item_set:
                    Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item_theme:
                    Toast.makeText(MainActivity.this, "主题", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item_love:
                    Toast.makeText(MainActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item_share:
                    Toast.makeText(MainActivity.this, "分享", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item_login:
                    Toast.makeText(MainActivity.this, "退出", Toast.LENGTH_SHORT).show();
                    break;
            }
            mBinding.drawerLayout.closeDrawers();
            return true;
        });
    }

    /**
     * 监听RecycleView滑动的监听
     * 拿到Fragment的状态，用于返回键的监听。
     */
    private void initEvent() {
        RxBus.getDefualt().toFlowable(ListStatusEvent.class)
                .subscribe(event -> mListStatus = event.getStatue());
    }

    /**
     * 返回键监听
     * 如果处于刷新状态，则取消刷新状态
     * 如果处于正常状态，则提示退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyDownEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if(mListStatus == ListStatusEvent.Top) {
                //System.currentTimeMillis()是一个13位的数字
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    exitTime = System.currentTimeMillis();
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();;
                } else {
                    finish();
                }
            }else {
                //点击按钮，停止刷新
                RxBus.getDefualt().post(new KeyDownEvent(KeyDownEvent.KEYCODE_BACK));
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 底部导航菜单
     */
    private void intBoomTab() {
        mBinding.bottom.setMode(BottomNavigationBar.MODE_FIXED);
        mBinding.bottom
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        mBinding.bottom.setBarBackgroundColor("#FCFCFC");
        mBinding.bottom.addItem(new BottomNavigationItem(R.mipmap.topline, "主页").setInActiveColor(R.color.colorbttonfont).setActiveColorResource(R.color.colorblue))
                .addItem(new BottomNavigationItem(R.mipmap.entertainment, "段子").setInActiveColor(R.color.colorbttonfont).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.news, "图片").setInActiveColor(R.color.colorbttonfont).setActiveColorResource(R.color.colororange))
                .addItem(new BottomNavigationItem(R.mipmap.sports, "视频").setInActiveColor(R.color.colorbttonfont).setActiveColorResource(R.color.colorpurple2))
                .setFirstSelectedPosition(0)
                .initialise();

        //设置启动页
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mHomeFragment).commit();
        //底部导航监听事件
        mBinding.bottom.setTabSelectedListener(this);
    }
    /**
     * 底部按钮监听
     */
    @Override
    public void onTabSelected(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                mBinding.toolbar.setTitle("今日头条 - 新闻");
                ft.replace(R.id.fragment, mHomeFragment).commit();
                break;
            case 1:
                ft.replace(R.id.fragment, mJokesFragment).commit();
                mBinding.toolbar.setTitle("今日头条 - 段子");
                break;
            case 2:
                ft.replace(R.id.fragment, mImageFragment).commit();
                mBinding.toolbar.setTitle("今日头条 - 图片");
                break;
            case 3:
                ft.replace(R.id.fragment, mVideoFragment).commit();
                mBinding.toolbar.setTitle("今日头条 - 视频");
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) { }

    @Override
    public void onTabReselected(int position) { }

    @Override
    public void onClick(View view) { }

}
