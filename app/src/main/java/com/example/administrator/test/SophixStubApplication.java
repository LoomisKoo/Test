//package com.example.administrator.test;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.taobao.sophix.PatchStatus;
//import com.taobao.sophix.SophixApplication;
//import com.taobao.sophix.SophixEntry;
//import com.taobao.sophix.SophixManager;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import androidx.annotation.Keep;
//
//public class SophixStubApplication extends SophixApplication {
//    private final       String TAG       = "SophixStubApplication";
//    public static final String appId     = "25935803-1";
//    public static final String appSecret = "b1b14cee618073157392137272330846";
//    public static final String rsaSecret = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCOYe+FJTrhRj5HKAtGdsiCg0uCPsRJ8u0bXsw/A5CwIJIuoC6rpLuY3/buOCuQcVcuycJpYRz+giAw71BKe/HCukwcOMSA+RuhuyHOUqClbjTxTrCBfrRrUL86T9dHv3/AeZaubuv6PgxIiZTfMuPbo25ImZhWDvOet/cZIxq7eJgyF3OCj43QxZrwVbcGhtaaWOFZJkB9kjOHDhPRl3OPUP0o8fN5X84hA0AXbDAG/18R1fT7eEJFeaD/BgB92EP71TSYSKuoLxCJPk3c6MjZY4wx0Q7mZNepxSXxJ21gPDXIYhlqLgT8v4Kc2Q8bqE6Q1QPyndl6Adhqlm5jvPp1AgMBAAECggEAMQTwtJNLwTEw0ciskRbmE2DmhzHa3GbBURIDGZK9ppWgHmNRjhCJ8ajx17QI85LB5D7cKvGfTNQ6Bv7tUqd3ZYZEG9dDd+/LnpPRoLn7yti1a3qe9AZOUUAchhPk1zPL5NU3llhj7+Sa0AJl+a4Imp6aom4W71H4d+V4MfRPCMmJR0xbi2tYql0tJ2wgvfI2JG0MwNjqCHyxTUXPLxtrz9478wZQkGqFGtH4C0g5DlGoQEx2fQGJqxUAdZxMeiWM4ZPuG+lN9hL6xXsyEtpJhhszjhsz7Q78rfCoTvAbtHu7Xe+FEa0m6KY8jcOc0i6W7U5NB7VVw9qjbTxeONsEoQKBgQDLwfwxNO3dGfjbyDOvo0aU3jjlSIdipjgAMez33dytz0fxYLra3ANInvaMA/Z4nxvFyOz1F25Vz3ttySdD+qqPpTGjeVeKSD0CkNYCtyQZBkZhlqcs/ji1r8q+Fl0Z03DeqdNsVWhHeTZq3MbTolWBSTxw2bfgMPXHqgP+zyEA7QKBgQCy43oRanOiyNGtJNP6UPY2MzjI88tMhFkaM5baVsvK2kFkcr4kGZOZuQUr1I31gfoOsZ1HWp2YflA21Y55RVqT3V0T4qrPKy0IarrrlF13yEJIdx69hG48z4R+wWeTIzzHhy4fGzbmE/rbXedWd+lxDJUzdi5bHq0ywfVj+3sWqQKBgQC9ScoSoHw1QhKJgvRZP5Kra8JpZra3M3mk3LyrDAYdxgmsuNL9zRST23FpY6kOJ6myVVWk3w6WUzj0JcH/ihf3eFyj5G8pvLBI+OqH/UFgqg6qTD2kM8vVJalwa/AztNUPVa9e3f0RIQ8nuVBQfWd3wuSOZgRHFtTNlyk9B4k+hQKBgEhkJ6zOkFcKv1c69RFyHADY/5zKk7W2RTyGLgDTaQsBAb99c5a2RvXJWt9e18fHPoMdu1D5yZbBXwKhr1NCDqKRzS3T512I0mN6lYPp+V0UzEX49pao9ddj/vEwoRJ5CwqPmMl7wN6grT6CpLBoPC7EcchuhdnX8yz8MqqwRSJpAoGBAMLYu4xUPzACLRzHDS5vJTjRqDbCVc1DEmGIzciwMVPKU2atb5ETlL28YYJ1O0Kmn8FHioVnoDgybk5nW6jBIJfyGf28rVJUkf5CuCI+JwzLPTrUfDtyozWCjqMDO2Tu5EGTdQCYHOOKePogU1c4nM8/jBcKCvtPkDR6vnjqQO5z";
//
//    @Keep
//    @SophixEntry(TestApplication.class)//只有这里改成自己的Application类，下面static不要改
//    static class RealApplicationStub {
//    }
//
//
//    //这里不能调用非系统API的类
//
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//
//        //如用到MultiDex，必须在此初始化
////        MultiDex.install(this);
//
//        //这里不能调用非系统API的类
//        initSophix();
//
//        //这里可以调用非系统API的类
//    }
//
//    private void initSophix() {
//        String appVersion = "0.0.0";
//        try {
//            appVersion = getBaseContext().getPackageManager()
//                                         .getPackageInfo(this.getPackageName(), 0)
//                    .versionName;
//        }
//        catch (Exception e) {
//            appVersion = "1.0.0";
//        }
//
//        List<String> tags = new ArrayList<>();
//        tags.add("测试123");
//        tags.add("测试Abc123");
//        final SophixManager instance = SophixManager.getInstance();
//        instance.setContext(this)
//                .setAppVersion(appVersion)
////                .setAppVersion("tags")//与控制台创建的版本号对应，否则拉取不到补丁
////                .setHost("http://pre-hotfix-api.aliyun.com", false)
////                .setTags(tags)
//                .setAesKey("9999999999999999")
//                .setEnableDebug(true)
//                .setEnableFullLog()
//                .setSecretMetaData(appId, appSecret, rsaSecret)
//                .setPatchLoadStatusStub((mode, code, info, handlePatchVersion) -> {
//                    String msg = new StringBuilder("").append("Mode:")
//                                                      .append(mode)
//                                                      .append(" Code:")
//                                                      .append(code)
//                                                      .append(" Info:")
//                                                      .append(info)
//                                                      .append(" HandlePatchVersion:")
//                                                      .append(handlePatchVersion)
//                                                      .toString();
////                    if (msgDisplayListener != null) {
////                        msgDisplayListener.handle(msg);
////                    }
////                    else {
////                        cacheMsg.append("\n")
////                                .append(msg);
////                    }
//                    if (code == PatchStatus.CODE_LOAD_SUCCESS) {
//                        Log.i(TAG, "sophix load patch success!");
//                    }
//                    else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
//                        // 如果需要在后台重启，建议此处用SharePreference保存状态。
//                        Log.i(TAG, "sophix preload patch success. restart app to make effect.");
//                    }
//                })
//                .initialize();
//    }
//
//    public interface MsgDisplayListener {
//        void handle(String msg);
//    }
//
//    public static MsgDisplayListener msgDisplayListener = null;
//    public static StringBuilder      cacheMsg           = new StringBuilder();
//}
