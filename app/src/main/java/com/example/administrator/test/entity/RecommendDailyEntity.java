package com.example.administrator.test.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.entity
 * @ClassName: RecommendDailyEntity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/23 11:33 AM
 * @UpdateUser:
 * @UpdateDate: 2019/1/23 11:33 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendDailyEntity implements Serializable {

    /**
     * category : ["拓展资源","瞎推荐","ddd","App","IOS","休息视频","福利"]
     * error : false
     * results : {"ddd":[{"_id":"5c2df1479d2122759a04b597","createdAt":"2019-01-03T11:25:59.115Z","desc":"ddd 一键加入侧滑返回 (类似\u201c小米MIX\u201d和新版\u201c即刻\u201d滑动返回)","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze96rdfhmg308w0ft7wh","https://ww1.sinaimg.cn/large/0073sXn7ly1fze96s6tdag308w0ftjvw"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"ddd","url":"https://github.com/qinci/AndroidSlideBack","used":true,"who":"qinci"},{"_id":"5c30271a9d2122759a04b59d","createdAt":"2019-01-05T03:40:10.216Z","desc":"一个漂亮的卡片式切换菜单","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze96t2usdg30m80gogrr"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"ddd","url":"https://github.com/notice501/coolMenu","used":true,"who":"foocoder"},{"_id":"5c3089459d2122759d3e5ff4","createdAt":"2019-01-05T10:39:01.88Z","desc":"仿QQ图片发送挺炫效果的加载View,效果赞","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze96wakcng30dc0ngb29"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"ddd","url":"https://github.com/hewking/HaloImageProgressView","used":true,"who":"hewking"},{"_id":"5c36dc329d212264ce006f29","createdAt":"2019-01-10T05:46:26.150Z","desc":"结合 Zxing Zbar，并处理优化的极速扫码","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"ddd","url":"https://github.com/nanchen2251/AiYaScanner","used":true,"who":"LiuShilin"},{"_id":"5c384b029d212264ce006f2d","createdAt":"2019-01-11T07:51:30.67Z","desc":"一起来复现网易云音乐引导页效果","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze972ev1og309z0gok3u","https://ww1.sinaimg.cn/large/0073sXn7ly1fze973gq93g309c0godph"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"ddd","url":"https://github.com/wobiancao/Music163GuideDemo","used":true,"who":"兔子吃过窝边草"},{"_id":"5c3853279d212264d4501d23","createdAt":"2019-01-11T08:26:15.564Z","desc":"升级UETool，可查看修改任意安装App的布局参数","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze97bnyefg30dc0o0kjn"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"ddd","url":"https://github.com/zhangke3016/VirtualUETool","used":true,"who":"张珂"},{"_id":"5c3ecde79d212264d4501d2c","createdAt":"2019-01-16T06:23:35.316Z","desc":"💍一个简洁而优雅的Android原生UI框架，解放你的双手！","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze97f16k0j305e09mq3e","https://ww1.sinaimg.cn/large/0073sXn7ly1fze97fg7orj305e09maag","https://ww1.sinaimg.cn/large/0073sXn7ly1fze97fxcgxj305e09mq5n","https://ww1.sinaimg.cn/large/0073sXn7ly1fze97g6ey1j305e09mq3g","https://ww1.sinaimg.cn/large/0073sXn7ly1fze97ge8pij305e09mjru"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"ddd","url":"https://github.com/xuexiangjys/XUI","used":true,"who":"xuexiangjys"},{"_id":"5c4572419d212264cbcc5bd7","createdAt":"2019-01-21T07:18:25.158Z","desc":"此库应用视频过滤器生成Mp4和ExoPlayer视频以及使用Camera2进行视频录制。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze97qh5sxg308w050tkd","https://ww1.sinaimg.cn/large/0073sXn7ly1fze97s5aung308w050qks","https://ww1.sinaimg.cn/large/0073sXn7ly1fze97u1e6mg308w050nh3"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"ddd","url":"https://github.com/MasayukiSuda/GPUVideo-android","used":true,"who":"lijinshanmx"},{"_id":"5c4572d69d212264cbcc5bd8","createdAt":"2019-01-21T07:20:54.364Z","desc":"一个易于使用的表格验证器为Kotlin和Android。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze97ui6cpj30uk0kiq5m"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"ddd","url":"https://github.com/afollestad/vvalidator","used":true,"who":"lijinshanmx"},{"_id":"5c4573b89d212264d18bb26d","createdAt":"2019-01-21T07:24:40.432Z","desc":"初学者入门学习Bloc模式，RxDart，sqflite，Fluro和Dio来构建一个flutter的项目。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze97znin9g308w0i57wi"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"ddd","url":"https://github.com/KingWu/flutter_starter_kit","used":true,"who":"lijinshanmx"},{"_id":"5c4573d99d212264d4501d3a","createdAt":"2019-01-21T07:25:13.956Z","desc":"美丽而简单的色带视图，闪烁效果。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze988tynvg309d0gv1l0","https://ww1.sinaimg.cn/large/0073sXn7ly1fze98deowug309d0gvnpd"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"ddd","url":"https://github.com/skydoves/AndroidRibbon","used":true,"who":"lijinshanmx"},{"_id":"5c45748b9d212264ce006f46","createdAt":"2019-01-21T07:28:11.298Z","desc":"一个PhotoView库的指示器","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze98hd5hjj30qn0czq9c"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"ddd","url":"https://github.com/iammert/PhotoViewIndicator","used":true,"who":"lijinshanmx"}],"App":[{"_id":"5c370ae29d212264d18bb25e","createdAt":"2019-01-16T05:11:23.740Z","desc":"基于组件化 + MVP + Retrofit + RxKotlin + Dagger2实现的一款用Kotlin语言编写的影视类应用。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze9706gdzj30ae0kqmyw","https://ww1.sinaimg.cn/large/0073sXn7ly1fze970ocgxj30ae0ks0tc","https://ww1.sinaimg.cn/large/0073sXn7ly1fze97124aej30af0kq404"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"App","url":"https://github.com/guofudong/KotlinAndroid","used":true,"who":"guofudong"},{"_id":"5c39cecb9d212264ce006f2e","createdAt":"2019-01-12T11:26:03.526Z","desc":"使用flutter开发的俄罗斯方块游戏","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze97da5y7g30c20j6ap9"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"App","url":"https://github.com/boyan01/flutter-tetris","used":true,"who":"YangBin"},{"_id":"5c3b55829d212264cbcc5bc9","createdAt":"2019-01-13T15:13:06.8Z","desc":"高仿书旗小说 Flutter版，支持iOS、ddd","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze97epggnj30jg0ftgze"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"App","url":"https://github.com/huanxsd/flutter_shuqi","used":true,"who":"Daniel"},{"_id":"5c4574069d212264ce006f45","createdAt":"2019-01-21T07:25:58.32Z","desc":"一个flutter的音乐应用程序","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze98gfasag30a80i84qp"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"App","url":"https://github.com/singhbhavneet/Bungee","used":true,"who":"lijinshanmx"}],"IOS":[{"_id":"5c403e919d212264d4501d30","createdAt":"2019-01-17T08:36:33.526Z","desc":"一个完善的iOS UI敏捷开发框架，基于UIKit，包含常用控件的链式API拓展、一个数据驱动的列表框架、一个事件处理队列。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze97l9tq7g30ad0ih1ky","https://ww1.sinaimg.cn/large/0073sXn7ly1fze97p50emg30ad0ihkjl"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"IOS","url":"https://github.com/tbl00c/ZZFLEX","used":true,"who":"夜尽天明"},{"_id":"5c45754b9d212264cbcc5bda","createdAt":"2019-01-21T07:31:23.953Z","desc":"一个iOS菜单。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze98jrz88j31po0mix1b"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"IOS","url":"https://github.com/TwoLivesLeft/Menu","used":true,"who":"lijinshanmx"},{"_id":"5c4575829d212264ce006f47","createdAt":"2019-01-21T07:32:18.570Z","desc":"三行代码组件化集成 Flutter！可用于已有 IOS 项目，对原工程无侵入，无需更改原项目配置，集成后可直接以组件形式开发 Flutter 业务。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze98lym9lg308r0hph7t"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"IOS","url":"https://github.com/jiisd/YHFlutterAdapter","used":true,"who":"lijinshanmx"},{"_id":"5c4575989d212264d4501d3d","createdAt":"2019-01-21T07:32:40.819Z","desc":"秒级! 三行代码实现iOS视频压缩、变速、混音、合并、水印、旋转、换音、裁剪 ! 支持不同分辩率，支持你能想到的各种混合操作! 更多功能不断增加中... IOS 8.0 +","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze98nrqklg30ax0izh4o","https://ww1.sinaimg.cn/large/0073sXn7ly1fze98puthvg30aj0i3nkp"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"IOS","url":"https://github.com/CoderHenry66/WAVideoBox","used":true,"who":"lijinshanmx"},{"_id":"5c4575c69d212264ce006f49","createdAt":"2019-01-21T07:33:26.792Z","desc":"Swift / Python图像像素化器。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze98q9ydbj30ko10wmyn","https://ww1.sinaimg.cn/large/0073sXn7ly1fze98vh7v8j30u01hq7wi","https://ww1.sinaimg.cn/large/0073sXn7ly1fze98wi806j30ku112tcw","https://ww1.sinaimg.cn/large/0073sXn7ly1fze98xgwk8j30u01hggqe"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"IOS","url":"https://github.com/gsurma/pixelizator","used":true,"who":"lijinshanmx"},{"_id":"5c4575e69d212264cbcc5bdc","createdAt":"2019-01-21T07:33:58.771Z","desc":"适用于iOS的简单路由库。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze98xy35jj30u00u0gnp"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"IOS","url":"https://github.com/reececomo/XRouter","used":true,"who":"lijinshanmx"},{"_id":"5c45765e9d212264ce006f4a","createdAt":"2019-01-21T07:35:58.380Z","desc":"AnimatedTabBar是一个Swift UI模块库，用于向iOS tabBar项目和图标添加动画。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze98yh8yog30go03cjv6"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"IOS","url":"https://github.com/AlbGarciam/AnimatedTabBar","used":true,"who":"lijinshanmx"}],"休息视频":[{"_id":"5c4578ae9d212264ce006f4b","createdAt":"2019-01-21T07:45:50.59Z","desc":"#大早上的你是想笑死我吗 ","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"休息视频","url":"https://v.douyin.com/NwdsVy/","used":true,"who":"lijinshanmx"}],"拓展资源":[{"_id":"5a5570d8421aa9115b930657","createdAt":"2018-01-10T09:48:08.708Z","desc":"用Python爬取各Android市场应用下载量（3分钟学会）","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247489179&idx=1&sn=4b99a633b1f4fc1804782c6e85faa5bf","used":true,"who":"陈宇明"},{"_id":"5c2dcd159d212233034e2482","createdAt":"2019-01-03T08:51:33.346Z","desc":"状态管理探索篇\u2014\u2014Scoped Model","publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"拓展资源","url":"https://www.colabug.com/4587631.html","used":true,"who":"lijinshanmx"},{"_id":"5c2edcc59d21227592da3243","createdAt":"2019-01-21T07:12:59.715Z","desc":"漫画：30张图带你彻底理解红黑树","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://mp.weixin.qq.com/s/P6lNWTwkaxJmBIy1kkcm5g","used":true,"who":"codeGoogler"},{"_id":"5c32aa5e9d21225b988591e5","createdAt":"2019-01-16T05:10:06.92Z","desc":"煮酒论英雄----全面讲解屏幕适配","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://mp.weixin.qq.com/s/4AjG5LO_gwFKOz5w59MkOg","used":true,"who":"JavaBoyHW"},{"_id":"5c32dee79d21225b9b018704","createdAt":"2019-01-16T05:10:15.574Z","desc":"360插件化框架 RePlugin及demo实现","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://democome.com/replugin-classloader-hook","used":true,"who":"yangpeng"},{"_id":"5c32df1a9d21225b925fa5bc","createdAt":"2019-01-16T05:10:24.587Z","desc":"react native 动态下发bundle，前后端实现","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://democome.com/react-native-android-spring-boot","used":true,"who":"yangpeng"},{"_id":"5c32df599d21225b9b018705","createdAt":"2019-01-16T05:10:37.140Z","desc":"ddd 利用contentprovider实现同步binder","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://democome.com/use-contentprovider-implements-binder","used":true,"who":"yangpeng"},{"_id":"5c35915e9d212264d18bb25a","createdAt":"2019-01-09T06:14:54.466Z","desc":"一组匹配中国大陆手机号码的正则表达式","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://github.com/VincentSit/ChinaMobilePhoneNumberRegex","used":true,"who":"番茄你个马铃薯"},{"_id":"5c3802029d212264d18bb260","createdAt":"2019-01-16T05:11:33.25Z","desc":"深入研究 ddd 核心技术 之 JNI","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://mp.weixin.qq.com/s/8eyucFVmr0JExVorrPlPAg","used":true,"who":"codeGoogler"},{"_id":"5c393e019d212264cbcc5bc7","createdAt":"2019-01-21T07:16:41.899Z","desc":"LayoutInflater源码分析","publishedAt":"2019-01-21T00:00:00.0Z","source":"api","type":"拓展资源","url":"https://bboylin.github.io/2018/12/21/LayoutInflater源码分析/","used":true,"who":"bboylin"},{"_id":"5c4139029d212264cbcc5bd2","createdAt":"2019-01-21T07:15:59.733Z","desc":"2019校招Android面试题解（算法篇）","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://mp.weixin.qq.com/s/zvs8a1Ff41u22ATpL7JsPQ","used":true,"who":"codeGoogler"}],"瞎推荐":[{"_id":"5a614fc6421aa9115b930678","createdAt":"2019-01-21T07:51:14.120Z","desc":"12款堪称神器的 Chrome 插件","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247489341&idx=2&sn=f4c9ed88bbd724018e39e42fa1dc0fca","used":true,"who":"陈宇明"},{"_id":"5c2db16e9d212204a1d33f4f","createdAt":"2019-01-21T07:14:52.757Z","desc":"又撸一年的代码！尽管我秃头还白发，我还是坚持了","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://mp.weixin.qq.com/s/5Q7LJ_-AtWsw8p6gUgbGFg","used":true,"who":"codeGoogler"},{"_id":"5c2eab309d21227597139cf0","createdAt":"2019-01-21T07:15:09.80Z","desc":"加密混淆，应用就安全了嘛？","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://mp.weixin.qq.com/s/NVDeWbjIQsEB7iWbR9ZV4A","used":true,"who":"JavaBoyHW"},{"_id":"5c2f2d9c9d2122759d3e5ff0","createdAt":"2019-01-21T07:51:08.148Z","desc":"深度特征压缩&协作智能的发展历史","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://blog.csdn.net/dhsig552/article/details/85788671","used":true,"who":"Hao"},{"_id":"5c3441029d212264d18bb255","createdAt":"2019-01-16T05:06:58.593Z","desc":"一款高质量的高质量gif图生成工具","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze96x48bng30mw09y7b0","https://ww1.sinaimg.cn/large/0073sXn7ly1fze96ziljwg31dc0l8qu2"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://github.com/faressoft/terminalizer","used":true,"who":"Mr.Krabs"},{"_id":"5c3547f99d212264d4501d1d","createdAt":"2019-01-16T05:10:46.371Z","desc":"Gradle更小、更快构建APP的奇淫技巧","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://mp.weixin.qq.com/s/WDap7bmM6gP7koIjEIwTtQ","used":true,"who":"codeGoogler"},{"_id":"5c3592279d212264cbcc5bc0","createdAt":"2019-01-16T05:10:57.664Z","desc":"android产品研发过程中常用的技术，技巧，实践","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://github.com/yipianfengye/androidProject","used":true,"who":"番茄你个马铃薯"},{"_id":"5c3c130c9d212264d4501d29","createdAt":"2019-01-21T07:14:10.748Z","desc":"Flutter更新错误全面解决方案(图文+视频讲解)","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://www.jianshu.com/p/eadc13a650c1","used":true,"who":"阿韦"},{"_id":"5c41e20c9d212264d18bb26a","createdAt":"2019-01-21T07:51:00.895Z","desc":"Python实现的12306抢票工具","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"瞎推荐","url":"https://github.com/versionzhang/python_12306","used":true,"who":"versionzhang"}],"福利":[{"_id":"5c4578db9d212264d4501d40","createdAt":"2019-01-21T07:46:35.304Z","desc":"2019-01-21","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fze94uew3jj30qo10cdka.jpg","used":true,"who":"lijinshanmx"}]}
     */

    private boolean      error;
    private ResultsBean  results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean {
        private List<RecommendDailyArticleEntity> Android;
        private List<RecommendDailyArticleEntity> App;
        private List<RecommendDailyArticleEntity> IOS;
        @JSONField(name = "休息视频")
        private List<RecommendDailyArticleEntity> videoBeanList;
        @JSONField(name = "拓展资源")
        private List<RecommendDailyArticleEntity> expandBeanList;
        @JSONField(name = "瞎推荐")
        private List<RecommendDailyArticleEntity> recommendBeanList;
        @JSONField(name = "福利")
        private List<RecommendDailyArticleEntity> welfareBeanList;


        public List<RecommendDailyArticleEntity> getAndroid() {
            return Android;
        }

        public void setAndroid(List<RecommendDailyArticleEntity> android) {
            Android = android;
        }

        public List<RecommendDailyArticleEntity> getApp() {
            return App;
        }

        public void setApp(List<RecommendDailyArticleEntity> app) {
            App = app;
        }

        public List<RecommendDailyArticleEntity> getIOS() {
            return IOS;
        }

        public void setIOS(List<RecommendDailyArticleEntity> IOS) {
            this.IOS = IOS;
        }

        public List<RecommendDailyArticleEntity> getVideoBeanList() {
            return videoBeanList;
        }

        public void setVideoBeanList(List<RecommendDailyArticleEntity> videoBeanList) {
            this.videoBeanList = videoBeanList;
        }

        public List<RecommendDailyArticleEntity> getExpandBeanList() {
            return expandBeanList;
        }

        public void setExpandBeanList(List<RecommendDailyArticleEntity> expandBeanList) {
            this.expandBeanList = expandBeanList;
        }

        public List<RecommendDailyArticleEntity> getRecommendBeanList() {
            return recommendBeanList;
        }

        public void setRecommendBeanList(List<RecommendDailyArticleEntity> recommendBeanList) {
            this.recommendBeanList = recommendBeanList;
        }

        public List<RecommendDailyArticleEntity> getWelfareBeanList() {
            return welfareBeanList;
        }

        public void setWelfareBeanList(List<RecommendDailyArticleEntity> welfareBeanList) {
            this.welfareBeanList = welfareBeanList;
        }

        public static class AndroidBean implements Serializable {
            /**
             * _id : 5c2df1479d2122759a04b597
             * createdAt : 2019-01-03T11:25:59.115Z
             * desc : ddd 一键加入侧滑返回 (类似“小米MIX”和新版“即刻”滑动返回)
             * images : ["https://ww1.sinaimg.cn/large/0073sXn7ly1fze96rdfhmg308w0ft7wh","https://ww1.sinaimg.cn/large/0073sXn7ly1fze96s6tdag308w0ftjvw"]
             * publishedAt : 2019-01-21T00:00:00.0Z
             * source : web
             * type : ddd
             * url : https://github.com/qinci/AndroidSlideBack
             * used : true
             * who : qinci
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

        public static class AppBean implements Serializable {
            /**
             * _id : 5c370ae29d212264d18bb25e
             * createdAt : 2019-01-16T05:11:23.740Z
             * desc : 基于组件化 + MVP + Retrofit + RxKotlin + Dagger2实现的一款用Kotlin语言编写的影视类应用。
             * images : ["https://ww1.sinaimg.cn/large/0073sXn7ly1fze9706gdzj30ae0kqmyw","https://ww1.sinaimg.cn/large/0073sXn7ly1fze970ocgxj30ae0ks0tc","https://ww1.sinaimg.cn/large/0073sXn7ly1fze97124aej30af0kq404"]
             * publishedAt : 2019-01-21T00:00:00.0Z
             * source : web
             * type : App
             * url : https://github.com/guofudong/KotlinAndroid
             * used : true
             * who : guofudong
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

        public static class IOSBean implements Serializable {
            /**
             * _id : 5c403e919d212264d4501d30
             * createdAt : 2019-01-17T08:36:33.526Z
             * desc : 一个完善的iOS UI敏捷开发框架，基于UIKit，包含常用控件的链式API拓展、一个数据驱动的列表框架、一个事件处理队列。
             * images : ["https://ww1.sinaimg.cn/large/0073sXn7ly1fze97l9tq7g30ad0ih1ky","https://ww1.sinaimg.cn/large/0073sXn7ly1fze97p50emg30ad0ihkjl"]
             * publishedAt : 2019-01-21T00:00:00.0Z
             * source : chrome
             * type : IOS
             * url : https://github.com/tbl00c/ZZFLEX
             * used : true
             * who : 夜尽天明
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

        public static class VideoBean implements Serializable {
            /**
             * _id : 5c4578ae9d212264ce006f4b
             * createdAt : 2019-01-21T07:45:50.59Z
             * desc : #大早上的你是想笑死我吗
             * publishedAt : 2019-01-21T00:00:00.0Z
             * source : web
             * type : 休息视频
             * url : https://v.douyin.com/NwdsVy/
             * used : true
             * who : lijinshanmx
             */

            private String  _id;
            private String  createdAt;
            private String  desc;
            private String  publishedAt;
            private String  source;
            private String  type;
            private String  url;
            private boolean used;
            private String  who;

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
        }

        public static class ExpandBean {
            /**
             * _id : 5a5570d8421aa9115b930657
             * createdAt : 2018-01-10T09:48:08.708Z
             * desc : 用Python爬取各Android市场应用下载量（3分钟学会）
             * publishedAt : 2019-01-21T00:00:00.0Z
             * source : web
             * type : 拓展资源
             * url : https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247489179&idx=1&sn=4b99a633b1f4fc1804782c6e85faa5bf
             * used : true
             * who : 陈宇明
             */

            private String  _id;
            private String  createdAt;
            private String  desc;
            private String  publishedAt;
            private String  source;
            private String  type;
            private String  url;
            private boolean used;
            private String  who;

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
        }

        public static class RecommendBean implements Serializable {
            /**
             * _id : 5a614fc6421aa9115b930678
             * createdAt : 2019-01-21T07:51:14.120Z
             * desc : 12款堪称神器的 Chrome 插件
             * publishedAt : 2019-01-21T00:00:00.0Z
             * source : web
             * type : 瞎推荐
             * url : https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247489341&idx=2&sn=f4c9ed88bbd724018e39e42fa1dc0fca
             * used : true
             * who : 陈宇明
             * images : ["https://ww1.sinaimg.cn/large/0073sXn7ly1fze96x48bng30mw09y7b0","https://ww1.sinaimg.cn/large/0073sXn7ly1fze96ziljwg31dc0l8qu2"]
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

        public static class WelfareBean implements Serializable {
            /**
             * _id : 5c4578db9d212264d4501d40
             * createdAt : 2019-01-21T07:46:35.304Z
             * desc : 2019-01-21
             * publishedAt : 2019-01-21T00:00:00.0Z
             * source : web
             * type : 福利
             * url : https://ws1.sinaimg.cn/large/0065oQSqgy1fze94uew3jj30qo10cdka.jpg
             * used : true
             * who : lijinshanmx
             */

            private String  _id;
            private String  createdAt;
            private String  desc;
            private String  publishedAt;
            private String  source;
            private String  type;
            private String  url;
            private boolean used;
            private String  who;

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
        }
    }
}
