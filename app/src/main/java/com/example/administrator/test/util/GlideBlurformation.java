package com.example.administrator.test.util;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

import androidx.annotation.NonNull;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.util
 * @ClassName: GlideBlurformation
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/7 1:54 AM
 * @UpdateUser:
 * @UpdateDate: 2019/2/7 1:54 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GlideBlurformation extends BitmapTransformation {
    private Context context;

    public GlideBlurformation(Context context) {
        this.context = context;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return BlurBitmapUtil.instance()
                             .blurBitmap(context, toTransform, 25, 100, 100);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }
}