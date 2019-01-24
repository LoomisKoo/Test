package com.example.administrator.test.http;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ProjectName: ewg_android
 * @Package: com.ewg.vip.eshop.utils
 * @ClassName: JsonLogFormat
 * @Description: java类作用描述  格式化Json数据的log打印
 * @Author: koo
 * @CreateDate: 2018/12/11 10:27 AM
 * @UpdateUser:
 * @UpdateDate: 2018/12/11 10:27 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class JsonLogFormat {
    private static boolean isLogger = true;

    /**
     * 打印标签
     */
    private static String TAG = "JsonFormat";

    /**
     * 点击跳转到类的关键字  eclipse是 at android studio是☞.(任意文字加.)
     */
    private static       String jumpKeyWord    = "  ☞. ";
    /**
     * logcat在实现上对于message的内存分配大概,2k左右, 所以超过的内容都直接被丢弃,设置文本长度超过LOG_MAX_LENGTH分多条打印
     */
    private final static int    LOG_MAX_LENGTH = 2048;

    /**
     * 记录上次的logLocation
     */
    private static String lastLogMethod = "";


    /**
     * 打印文本
     *
     * @param text
     */
    public static void i(String text) {
        if (isLogger) {
            Log.i(TAG, logContent(text) + logLocation(0));
        }
    }

    public static void i(String text, int index) {
        if (isLogger) {
            Log.i(TAG, logContent(text) + logLocation(index));
        }
    }


    /**
     * 打印异常文本
     *
     * @param text
     */
    public static void e(String text) {
        if (isLogger) {
            Log.e(TAG, logContent(text) + logLocation(0));
        }
    }

    /**
     * 打印异常
     *
     * @param msg
     * @param e
     */
    public static void e(String msg, Exception e) {
        if (isLogger) {
            Log.e(TAG, msg + logLocation(0), e);
        }
    }

    /**
     * 打印json格式文本
     *
     * @param json
     */
    public static void json(String json) {
        if (isLogger) {
            json("", json);
        }
    }

    /**
     * 打印json格式文本
     *
     * @param prefix 前缀文本
     * @param json
     */
    public static void json(String prefix, String json) {
        if (isLogger) {
            String text = prefix + formatJson(json);
            Log.i(TAG, logContent(text) + logLocation(0));
        }
    }

    /**
     * 打印内容
     *
     * @param text
     * @return
     */
    private static String logContent(String text) {
        // 内容长度不超过50，前面加空格加到50
        if (text.length() < 50) {
            int minLength = 50 - text.length();
            if (minLength > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < minLength; i++) {
                    stringBuilder.append(" ");
                }
                text = text + stringBuilder.toString();
            }
        }
        // 内容超过logcat单条打印上限，分批打印
        else if (text.length() > LOG_MAX_LENGTH) {
            int logTime = text.length() / LOG_MAX_LENGTH;
            for (int i = 0; i < logTime; i++) {
                String length = text.substring(i * LOG_MAX_LENGTH, (i + 1)
                        * LOG_MAX_LENGTH);
                // 提示
                if (i == 0) {
                    Log.i(TAG, "打印分" + logTime + "条显示 :" + length);
                }
                else {
                    Log.i(TAG, "接上条↑" + length);
                }

            }
            text = "接上条↑"
                    + text.substring(logTime * LOG_MAX_LENGTH, text.length());
        }
        return text;
    }

    /**
     * 定位打印的方法
     *
     * @return
     */
    private static StringBuilder logLocation(int index) {
        StackTraceElement logStackTrace = getLogStackTrace(index);
        StringBuilder     stringBuilder = new StringBuilder();
        stringBuilder.append(jumpKeyWord)
                     .append(" (")
                     .append(logStackTrace.getFileName())
                     .append(":")
                     .append(logStackTrace.getLineNumber() + ")");
        if (stringBuilder.toString()
                         .equals(lastLogMethod)) {
            stringBuilder = new StringBuilder("");
        }
        else {
            lastLogMethod = stringBuilder.toString();
        }

        return stringBuilder;
    }

    /**
     * json格式化
     *
     * @param jsonStr
     * @return
     */
    private static String formatJson(String jsonStr) {
        try {
            jsonStr = jsonStr.trim();
            if (jsonStr.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(jsonStr);
                return jsonObject.toString(2)
                                 .replace("\\/", "/");
            }
            if (jsonStr.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(jsonStr);
                return jsonArray.toString(2)
                                .replace("\\/", "/");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /**
     * 获取调用打印方法的栈 index 调用打印i/e/json的index为0
     *
     * @return
     */
    private static StackTraceElement getLogStackTrace(int index) {
        StackTraceElement logTackTraces = null;
        StackTraceElement[] stackTraces = Thread.currentThread()
                                                .getStackTrace();
        for (int i = 0; i < stackTraces.length; i++) {
            StackTraceElement stackTrace = stackTraces[i];
            if (stackTrace.getClassName()
                          .equals(JsonLogFormat.class.getName())) {
                // getLogStackTrace--logLocation--i/e/json--方法栈,所以调用打印方法栈的位置是当前方法栈后的第3个
                logTackTraces = stackTraces[i + 3 + index];
                i = stackTraces.length;
            }
        }
        return logTackTraces;
    }

    public static void setTAG(String TAG) {
        JsonLogFormat.TAG = TAG;
    }

    public static void setIsLog(boolean isLogger) {
        JsonLogFormat.isLogger = isLogger;
    }
}
