package com.example.administrator.test.viewholder.recommend;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;

import java.util.ArrayList;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.recommend
 * @ClassName: WelfareVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/26 1:46 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/26 1:46 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WelfareVH extends BaseViewHolder {

    public ImageView imageView;

    public WelfareVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        imageView = getView(R.id.image);
    }

    @SuppressLint("CheckResult")
    public void setData(ArrayList<String> urlList, int curImgPosition) {

        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.ic_launcher)
               .placeholder(R.mipmap.ic_launcher);
        Glide.with(context)
             .setDefaultRequestOptions(options)
             .load(urlList.get(curImgPosition))
             .into(imageView);
    }
}
