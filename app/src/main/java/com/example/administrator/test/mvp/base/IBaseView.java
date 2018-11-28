package com.example.administrator.test.mvp.base;

/**
 * @author koo
 */
public interface IBaseView {
    /**
     * 显示dialog
     */
    void showLoading();

    /**
     * 隐藏 dialog
     */
    void hideLoading();

    /**
     * 显示错误信息
     *
     * @param msg
     */
    void showError(String msg);

    /**
     * 错误码
     */
    void onErrorCode(IBaseModel model);
}