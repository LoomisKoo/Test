package com.example.administrator.test.base.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StringRes;

import com.alibaba.android.arouter.launcher.ARouter;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.test.mvp.base.IBasePresenter;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * @author koo
 */
public abstract class BaseActivity<P extends IBasePresenter> extends SwipeBackActivity implements View.OnClickListener {
    protected P               presenter;
    private   SwipeBackLayout backLayout;

    /**
     * 当前Activity渲染的视图View
     **/
    private         View   mContextView = null;
    /**
     * 日志输出标志
     **/
    protected final String TAG          = this.getClass()
                                              .getSimpleName();

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ARouter.getInstance()
               .inject(this);

        ButterKnife.bind(this);
        presenter = createPresenter();

        initSwipeLayout();
        initView(savedInstanceState);
        setListener();
        initData(this);
    }

    @Override
    public void finish() {
        super.finish();
        //防止转场动画闪屏
        overridePendingTransition(0, 0);
    }

    /**
     * 初始化滑动布局
     */
    private void initSwipeLayout() {
        //滑动退出布局
        backLayout = getSwipeBackLayout();
        backLayout.setEnabled(true);
        backLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    /**
     * [初始化控件]
     *
     * @param savedInstanceState
     */
    public abstract void initView(Bundle savedInstanceState);

    protected abstract P createPresenter();

    /**
     * [绑定控件]
     *
     * @param resId
     * @return
     */
    protected <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }

    /**
     * [设置监听]
     */
    public abstract void setListener();

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void initData(Context mContext);


    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT)
             .show();
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG)
             .show();
    }

    /**
     * 显示Toast
     *
     * @param resId
     */
    public void showToast(@StringRes int resId) {
        Toast.makeText(this, getResources().getString(resId), Toast.LENGTH_SHORT)
             .show();
    }

    /**
     * 显示长时间Toast
     *
     * @param resId
     */
    public void showLongToast(@StringRes int resId) {
        Toast.makeText(this, getResources().getString(resId), Toast.LENGTH_LONG)
             .show();
    }


    /**
     * 设置侧边滑动模式
     *
     * @param mode SwipeBackLayout.EDGE_ALL     设置为向所有方向都可以滑动
     *             SwipeBackLayout.EDGE_LEFT    设置为向左滑动
     *             SwipeBackLayout.EDGE_RIGHT   设置为向右滑动
     *             SwipeBackLayout.EDGE_BOTTOM  设置为向下滑动
     */
    public void setEdgeTrackingMode(int mode) {
        ////设置为向所有方向都可以滑动
        backLayout.setEdgeTrackingEnabled(mode);
    }

    /**
     * 设置是否支持侧边滑动退出
     *
     * @param enable
     */
    public void setEnableGesture(boolean enable) {
        //设置为不能滑动
        backLayout.setEnableGesture(enable);
    }
}