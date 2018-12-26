package com.example.administrator.test.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.ArouterHelper;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.activity
 * @ClassName: WebActivity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2018/12/26 5:17 PM
 * @UpdateUser:
 * @UpdateDate: 2018/12/26 5:17 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Route(path = ArouterHelper.ROUTE_ACTIVITY_WEB)
public class WebActivity extends BaseActivity {
    // 进度条
    private ProgressBar mProgressBar;
    private WebView     webView;
    // 全屏时视频加载view
    private FrameLayout videoFullView;
    private Toolbar     mTitleToolBar;
    //TODO 加载视频相关
//    private MyWebChromeClient mWebChromeClient;
    // title
    @Autowired
    String title;
    // 网页链接
    @Autowired
    String url;
    // 可滚动的title 使用简单 没有渐变效果，文字两旁有阴影
    private TextView tvGunTitle;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParameter(Bundle parameter) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindContentLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    public int bindTopLayout() {
        return 0;
    }

    @Override
    public int bindBottomLayout() {
        return 0;
    }

    @Override
    public int bindMenu() {
        return 0;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_web_view);
        initTitle();
        initWebView();
        webView.loadUrl(url);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onMenuClickListener(int menuId) {

    }

    @Override
    public void initData(Context mContext) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initTitle() {
//        StatusBarUtil.setColor(this, CommonUtils.getColor(R.color.colorTheme), 0);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_progress);
        webView = (WebView) findViewById(R.id.webview_detail);
        videoFullView = (FrameLayout) findViewById(R.id.video_fullView);
        mTitleToolBar = (Toolbar) findViewById(R.id.title_tool_bar);
        tvGunTitle = (TextView) findViewById(R.id.tv_gun_title);

        initToolBar();
    }

    private void initToolBar() {
        setSupportActionBar(mTitleToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mTitleToolBar.setOverflowIcon(ContextCompat.getDrawable(this, R.mipmap.actionbar_more));
        tvGunTitle.postDelayed(() -> tvGunTitle.setSelected(true), 1900);
        tvGunTitle.setText(title);
        setTitle(title);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        mProgressBar.setVisibility(View.VISIBLE);
        WebSettings ws = webView.getSettings();
        // 网页内容的宽度是否可大于WebView控件的宽度
        ws.setLoadWithOverviewMode(false);
        // 保存表单数据
        ws.setSaveFormData(true);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        ws.setDisplayZoomControls(false);
        // 启动应用缓存
        ws.setAppCacheEnabled(true);
        // 设置缓存模式
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        // setDefaultZoom  api19被弃用
        // 设置此属性，可任意比例缩放。
        ws.setUseWideViewPort(true);
        // 不缩放
        webView.setInitialScale(100);
        // 告诉WebView启用JavaScript执行。默认的是false。
        ws.setJavaScriptEnabled(true);
        //  页面加载好以后，再放开图片
        ws.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        ws.setDomStorageEnabled(true);
        // 排版适应屏幕
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // WebView是否新窗口打开(加了后可能打不开网页)
//        ws.setSupportMultipleWindows(true);

        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        /** 设置字体默认缩放大小(改变网页字体大小,setTextSize  api14被弃用)*/
        ws.setTextZoom(100);
        //TODO
//        mWebChromeClient = new MyWebChromeClient(this);
//        webView.setWebChromeClient(mWebChromeClient);
//        // 与js交互
//        webView.addJavascriptInterface(new ImageClickInterface(this), "injectedObject");
//        webView.setWebViewClient(new MyWebViewClient(this));
    }
}
