package com.example.administrator.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseActivity;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.ArouteHelper;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.activity
 * @ClassName: BigImgActivity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/25 6:08 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/25 6:08 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Route(path = ArouteHelper.ROUTE_ACTIVITY_BIG_IMAGE)
public class BigImgActivity extends BaseActivity {
    PhotoView photoView;

    @Autowired
    public String url;
    @Autowired
    public String activityTitle;

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
        return R.layout.activity_big_image;
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
        setTooBarTitle(activityTitle);
        photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setEnabled(true);
        photoView.setOnClickListener(view -> finish());
        photoView.setOnLongClickListener(view -> {
            ArrayList<String> tips = new ArrayList<>();
            tips.add("保存图片");
            //TODO 待完善
//            DialogUtils.showListDialog(BigImgActivity.this, "", tips, position -> {
//                Bitmap saveBitmap = null;
//                saveBitmap = ((BitmapDrawable) (photoView).getDrawable()).getBitmap();
//                if (null != saveBitmap) {
//                    File file = FileUtil.savePictureToAlbum(BigImgActivity.this, saveBitmap);
//                    if (null != file) {
//                        ToastUtils.showShort("保存成功");
//                    }
//                    else {
//                        ToastUtils.showShort("保存失败");
//                    }
//                }
//                else {
//                    ToastUtils.showShort("保存失败");
//                }
//            });
            return false;
        });
        Glide.with(this)
             .load(url)
             .into(photoView);
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
}
