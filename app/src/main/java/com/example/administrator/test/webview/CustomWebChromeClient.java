package com.example.administrator.test.webview;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.blankj.utilcode.util.AppUtils;
import com.example.administrator.test.activity.WebActivity;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.util
 * @ClassName: CustomWebChromeClient
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/8 11:33 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/8 11:33 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomWebChromeClient extends WebChromeClient {

    private IWebPageView mIWebPageView;
    private WebActivity  mActivity;

    public CustomWebChromeClient(IWebPageView mIWebPageView) {
        this.mIWebPageView = mIWebPageView;
        mActivity = (WebActivity) mIWebPageView;
    }

    public CustomWebChromeClient(IWebPageView mIWebPageView, CallBack callback) {
        this.callBack = callback;
        this.mIWebPageView = mIWebPageView;
        mActivity = (WebActivity) mIWebPageView;
    }


//    @SuppressWarnings("deprecation")
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
////        DebugUtil.error("----url:"+url);
//        if (TextUtils.isEmpty(url)) {
//            return false;
//        }
//        if (url.startsWith("http:") || url.startsWith("https:")) {
//            // 可能有提示下载Apk文件
//            if (url.contains(".apk")) {
//                handleOtherwise(url);
//                return true;
//            }
//            return false;
//        }
//
//        handleOtherwise(url);
//        return true;
//    }

//    @Override
//    public void onPageFinished(WebView view, String url) {
//        if (!NetworkUtils.isConnected()) {
//            mIWebPageView.hindProgressBar();
//        }
//        // html加载完成之后，添加监听图片的点击js函数
//        mIWebPageView.addImageClickListener();
//        super.onPageFinished(view, url);
//    }

    // 视频全屏播放按返回页面被放大的问题
//    @Override
//    public void onScaleChanged(WebView view, float oldScale, float newScale) {
//        super.onScaleChanged(view, oldScale, newScale);
//        if (newScale - oldScale > 7) {
//            //异常放大，缩回去。
//            view.setInitialScale((int) (oldScale / newScale * 100));
//        }
//    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (null != callBack) {
            callBack.onProgressChanged(newProgress);
        }
    }

    /**
     * 网页里可能唤起其他的app
     */
    private void handleOtherwise(String url) {
        String appPackageName = "";
        // 支付宝支付
        if (url.contains("alipays")) {
            appPackageName = "com.eg.android.AlipayGphone";

            // 微信支付
        }
        else if (url.contains("weixin://wap/pay")) {
            appPackageName = "com.tencent.mm";

            // 京东产品详情
        }
        else if (url.contains("openapp.jdmobile")) {
            appPackageName = "com.jingdong.app.mall";
        }
        else {
            startActivity(url);
        }
        if (AppUtils.isAppInstalled(appPackageName)) {
            startActivity(url);
        }
    }

    private void startActivity(String url) {
        try {
            Intent intent1 = new Intent();
            intent1.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse(url);
            intent1.setData(uri);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mActivity.startActivity(intent1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CallBack callBack;

    public interface CallBack {
        void onProgressChanged(int newProgress);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
