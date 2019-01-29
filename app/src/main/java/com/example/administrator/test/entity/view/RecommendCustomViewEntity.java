package com.example.administrator.test.entity.view;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.entity
 * @ClassName: RecommendCustomViewEntity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2018/11/27 5:05 PM
 * @UpdateUser:
 * @UpdateDate: 2018/11/27 5:05 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendCustomViewEntity {
    /**
     * title
     */
    public static final int VIEW_TYPE_TITLE        = 0;
    /**
     * 文章列表
     */
    public static final int VIEW_TYPE_ARTICLE_LIST = 1;

    private int viewType = -1;

    public RecommendCustomViewEntity(Object data, int viewType) {
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
