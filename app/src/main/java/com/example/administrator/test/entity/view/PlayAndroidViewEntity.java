package com.example.administrator.test.entity.view;

import com.example.administrator.test.entity.BannerEntity;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.entity
 * @ClassName: PlayAndroidViewEntity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2018/11/27 5:05 PM
 * @UpdateUser:
 * @UpdateDate: 2018/11/27 5:05 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PlayAndroidViewEntity {
    private int viewType = -1;

    public PlayAndroidViewEntity(Object data, int viewType) {
        this.data = data;
        this.viewType = viewType;
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
