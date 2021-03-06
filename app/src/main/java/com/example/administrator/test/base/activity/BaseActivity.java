package com.example.administrator.test.base.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.StringRes;

import com.alibaba.android.arouter.launcher.ARouter;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.administrator.test.manager.ActivityManager;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

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

        presenter = createPresenter();

        initSwipeLayout();
        initView(savedInstanceState);
        setListener();
        initData(this);

        ActivityManager.getAppManager()
                       .addActivity(this);
    }

    ZLoadingDialog dialog;

    protected void showDialog() {
        dialog = new ZLoadingDialog(this);
        dialog.setLoadingBuilder(Z_TYPE.SNAKE_CIRCLE)//设置类型
              .setLoadingColor(Color.RED)//颜色
              .setHintText("Loading...")
              .setHintTextSize(16) // 设置字体大小 dp
              .setHintTextColor(Color.GRAY)  // 设置字体颜色
              .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
              .setDialogBackgroundColor(Color.parseColor("#CC111111")) // 设置背景色，默认白色
              .show();
    }

    protected void dismissDialog() {
        if (null != dialog) {
            dialog.dismiss();
        }
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
        ButterKnife.bind(this)
                   .unbind();
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

    //===============软键盘控制 start==================================
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isTouchView(filterViewByIds(), ev)) {
                return super.dispatchTouchEvent(ev);
            }
            if (hideSoftByEditViewIds() == null || hideSoftByEditViewIds().length == 0) {
                return super.dispatchTouchEvent(ev);
            }
            View v = getCurrentFocus();
            if (isFocusEditText(v, hideSoftByEditViewIds())) {
                KeyboardUtils.hideSoftInput(this);
                clearViewFocus(v, hideSoftByEditViewIds());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 清除editText的焦点
     *
     * @param v   焦点所在View
     * @param ids 输入框
     */
    public void clearViewFocus(View v, int... ids) {
        if (null != v && null != ids && ids.length > 0) {
            for (int id : ids) {
                if (v.getId() == id) {
                    v.clearFocus();
                    break;
                }
            }
        }
    }

    /**
     * 隐藏键盘
     *
     * @param v   焦点所在View
     * @param ids 输入框
     * @return true代表焦点在edit上
     */
    public boolean isFocusEditText(View v, int... ids) {
        if (v instanceof EditText) {
            EditText et = (EditText) v;
            for (int id : ids) {
                if (et.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 传入EditText的Id
     * 没有传入的EditText不做处理
     *
     * @return id 数组
     */
    public int[] hideSoftByEditViewIds() {
        return null;
    }

    /**
     * 传入要过滤的View
     * 过滤之后点击将不会有隐藏软键盘的操作
     *
     * @return id 数组
     */
    public View[] filterViewByIds() {
        return null;
    }

    /**
     * 是否触摸在指定view上面,对某个控件过滤
     */
    public boolean isTouchView(View[] views, MotionEvent ev) {
        if (views == null || views.length == 0) {
            return false;
        }
        int[] location = new int[2];
        for (View view : views) {
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (ev.getX() > x && ev.getX() < (x + view.getWidth())
                    && ev.getY() > y && ev.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }
    //===============软键盘控制 end==================================

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