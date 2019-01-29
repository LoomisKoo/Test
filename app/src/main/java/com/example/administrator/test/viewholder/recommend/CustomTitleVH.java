package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.recommend
 * @ClassName: CustomTitleVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/29 2:21 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/29 2:21 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomTitleVH extends BaseViewHolder {
    public TextView tvType, tvChooseType;

    public CustomTitleVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        tvType = getView(R.id.tv_type);
        tvChooseType = getView(R.id.tv_choose_type);
    }

    public void setData() {
    }
}
