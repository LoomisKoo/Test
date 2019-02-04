package com.example.administrator.test.entity;

import java.util.List;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.entity
 * @ClassName: MovieDetailEntity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 2:53 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 2:53 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieDetailEntity {

    /**
     * rating : {"max":10,"average":9.5,"stars":"50","min":0}
     * reviews_count : 3010
     * wish_count : 222679
     * douban_site :
     * year : 1997
     * images : {"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p510861873.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p510861873.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p510861873.jpg"}
     * alt : https://movie.douban.com/subject/1292063/
     * id : 1292063
     * mobile_url : https://movie.douban.com/subject/1292063/mobile
     * title : 美丽人生
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/1292063
     * seasons_count : null
     * schedule_url :
     * episodes_count : null
     * countries : ["意大利"]
     * genres : ["剧情","喜剧","爱情"]
     * collect_count : 757515
     * casts : [{"alt":"https://movie.douban.com/celebrity/1041004/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg"},"name":"罗伯托·贝尼尼","id":"1041004"},{"alt":"https://movie.douban.com/celebrity/1000375/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9548.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9548.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9548.jpg"},"name":"尼可莱塔·布拉斯基","id":"1000375"},{"alt":"https://movie.douban.com/celebrity/1000368/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p45590.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p45590.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p45590.jpg"},"name":"乔治·坎塔里尼","id":"1000368"},{"alt":"https://movie.douban.com/celebrity/1082051/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547655550.27.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547655550.27.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1547655550.27.jpg"},"name":"朱斯蒂诺·杜拉诺","id":"1082051"}]
     * current_season : null
     * original_title : La vita è bella
     * summary : 犹太青年圭多（罗伯托·贝尼尼）邂逅美丽的女教师多拉（尼可莱塔·布拉斯基），他彬彬有礼的向多拉鞠躬：“早安！公主！”。历经诸多令人啼笑皆非的周折后，天遂人愿，两人幸福美满的生活在一起。
     * 然而好景不长，法西斯政权下，圭多和儿子被强行送往犹太人集中营。多拉虽没有犹太血统，毅然同行，与丈夫儿子分开关押在一个集中营里。聪明乐天的圭多哄骗儿子这只是一场游戏，奖品就是一辆大坦克，儿子快乐、天真的生活在纳粹的阴霾之中。尽管集中营的生活艰苦寂寞，圭多仍然带给他人很多快乐，他还趁机在纳粹的广播里问候妻子：“早安！公主！”
     * 法西斯政权即将倾覆，纳粹的集中营很快就要接受最后的清理，圭多编给儿子的游戏该怎么结束？他们一家能否平安的度过这黑暗的年代呢？©豆瓣
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1041004/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg"},"name":"罗伯托·贝尼尼","id":"1041004"}]
     * comments_count : 126716
     * ratings_count : 597160
     * aka : ["一个快乐的传说(港)","Life Is Beautiful"]
     */

    private RatingBean        rating;
    private int               reviews_count;
    private int               wish_count;
    private String            douban_site;
    private String            year;
    private ImagesBean        images;
    private String            alt;
    private String            id;
    private String            mobile_url;
    private String            title;
    private Object            do_count;
    private String            share_url;
    private Object            seasons_count;
    private String            schedule_url;
    private Object            episodes_count;
    private int               collect_count;
    private Object            current_season;
    private String            original_title;
    private String            summary;
    private String            subtype;
    private int               comments_count;
    private int               ratings_count;
    private List<String>      countries;
    private List<String>      genres;
    private List<ClerkEntity> casts;
    private List<ClerkEntity> directors;
    private List<String>      aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<ClerkEntity> getCasts() {
        int directorSize = casts.size();
        for (int i = 0; i < directorSize; i++) {
            casts.get(i).clerkType = ClerkEntity.CLERK_TYPE_ACTOR;
        }
        return casts;
    }

    public void setCasts(List<ClerkEntity> casts) {
        this.casts = casts;
    }

    public List<ClerkEntity> getDirectors() {
        int directorSize = directors.size();
        for (int i = 0; i < directorSize; i++) {
            directors.get(i).clerkType = ClerkEntity.CLERK_TYPE_DIRECTOR;
        }
        return directors;
    }

    public void setDirectors(List<ClerkEntity> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 9.5
         * stars : 50
         * min : 0
         */

        private int    max;
        private double average;
        private String stars;
        private int    min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p510861873.jpg
         * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p510861873.jpg
         * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p510861873.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class ClerkEntity {
        public static final int          CLERK_TYPE_DIRECTOR = 0;
        public static final int          CLERK_TYPE_ACTOR    = 1;
        /**
         * alt : https://movie.douban.com/celebrity/1041004/
         * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg"}
         * name : 罗伯托·贝尼尼
         * id : 1041004
         */
        private             String       alt;
        private             AvatarsBeanX avatars;
        private             String       name;
        private             String       id;
        /**
         * 人员职位。这个是自己加的属性，用于区分导演和主演
         */
        private             int          clerkType;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getClerkType() {
            return clerkType;
        }

        public void setClerkType(int clerkType) {
            this.clerkType = clerkType;
        }

        public static class AvatarsBeanX {
            /**
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26764.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
