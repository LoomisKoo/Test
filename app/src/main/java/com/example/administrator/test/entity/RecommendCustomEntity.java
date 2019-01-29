package com.example.administrator.test.entity;

import java.util.List;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.entity
 * @ClassName: RecommendCustomEntity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/29 1:51 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/29 1:51 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendCustomEntity {

    /**
     * error : false
     * results : [{"_id":"5c4dbd2d9d21224326203318","createdAt":"2019-01-27T14:16:13.365Z","desc":"前端组件设计原则","publishedAt":"2019-01-27T14:18:06.726Z","source":"web","type":"前端","url":"https://juejin.im/post/5c49cff56fb9a049bd42a90f","used":true,"who":"bym"},{"_id":"5c4bfdc19d212243205cc7f3","createdAt":"2019-01-26T06:27:13.191Z","desc":"这篇文章聊明白管理状态这一堆看起来挺复杂的东西。","publishedAt":"2019-01-27T14:18:02.608Z","source":"web","type":"前端","url":"https://zhuanlan.zhihu.com/p/53599723","used":true,"who":"bym"},{"_id":"5c31d8a79d21222bd38ce73e","createdAt":"2019-01-06T10:29:59.746Z","desc":"flutter开发的干货集中营客户端","publishedAt":"2019-01-25T13:02:10.57Z","source":"web","type":"App","url":"https://github.com/fujianlian/GankFlutter","used":true,"who":"lijinshanmx"},{"_id":"5a5570d8421aa9115b930657","createdAt":"2018-01-10T09:48:08.708Z","desc":"用Python爬取各Android市场应用下载量（3分钟学会）","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247489179&idx=1&sn=4b99a633b1f4fc1804782c6e85faa5bf","used":true,"who":"陈宇明"},{"_id":"5a614fc6421aa9115b930678","createdAt":"2019-01-21T07:51:14.120Z","desc":"12款堪称神器的 Chrome 插件","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247489341&idx=2&sn=f4c9ed88bbd724018e39e42fa1dc0fca","used":true,"who":"陈宇明"},{"_id":"5c2db16e9d212204a1d33f4f","createdAt":"2019-01-21T07:14:52.757Z","desc":"又撸一年的代码！尽管我秃头还白发，我还是坚持了","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://mp.weixin.qq.com/s/5Q7LJ_-AtWsw8p6gUgbGFg","used":true,"who":"codeGoogler"},{"_id":"5c2dcd159d212233034e2482","createdAt":"2019-01-03T08:51:33.346Z","desc":"状态管理探索篇\u2014\u2014Scoped Model","publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"拓展资源","url":"https://www.colabug.com/4587631.html","used":true,"who":"lijinshanmx"},{"_id":"5c2df1479d2122759a04b597","createdAt":"2019-01-03T11:25:59.115Z","desc":"Android 一键加入侧滑返回 (类似\u201c小米MIX\u201d和新版\u201c即刻\u201d滑动返回)","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze96rdfhmg308w0ft7wh","https://ww1.sinaimg.cn/large/0073sXn7ly1fze96s6tdag308w0ftjvw"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"Android","url":"https://github.com/qinci/AndroidSlideBack","used":true,"who":"qinci"},{"_id":"5c2eab309d21227597139cf0","createdAt":"2019-01-21T07:15:09.80Z","desc":"加密混淆，应用就安全了嘛？","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://mp.weixin.qq.com/s/NVDeWbjIQsEB7iWbR9ZV4A","used":true,"who":"JavaBoyHW"},{"_id":"5c2edcc59d21227592da3243","createdAt":"2019-01-21T07:12:59.715Z","desc":"漫画：30张图带你彻底理解红黑树","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://mp.weixin.qq.com/s/P6lNWTwkaxJmBIy1kkcm5g","used":true,"who":"codeGoogler"}]
     */

    private boolean                error;
    private List<CustomInfoEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<CustomInfoEntity> getResults() {
        return results;
    }

    public void setResults(List<CustomInfoEntity> results) {
        this.results = results;
    }

    public static class CustomInfoEntity {
        /**
         * _id : 5c4dbd2d9d21224326203318
         * createdAt : 2019-01-27T14:16:13.365Z
         * desc : 前端组件设计原则
         * publishedAt : 2019-01-27T14:18:06.726Z
         * source : web
         * type : 前端
         * url : https://juejin.im/post/5c49cff56fb9a049bd42a90f
         * used : true
         * who : bym
         * images : ["https://ww1.sinaimg.cn/large/0073sXn7ly1fze96rdfhmg308w0ft7wh","https://ww1.sinaimg.cn/large/0073sXn7ly1fze96s6tdag308w0ftjvw"]
         */

        private String       _id;
        private String       createdAt;
        private String       desc;
        private String       publishedAt;
        private String       source;
        private String       type;
        private String       url;
        private boolean      used;
        private String       who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
