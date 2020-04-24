package com.wd.tech.model.bean.information;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期三/21:40
 * 功能：资讯推荐列表
 */
public class RecommendBean {

    /**
     * result : [{"collection":4,"id":65,"releaseTime":1572763883000,"share":28,"source":"ITBEAR科技资讯","summary":"双11在即，国内三大智能音箱品牌均面向用户大幅让利。天猫精灵X1和小米AI音箱到手价格均降至199元，带屏音箱天猫精灵CCL和小度在家1S到手价格低至299元，竞争进入肉搏战。","thumbnail":"http://www.itbear.com.cn/upload/2019-11/191106140480852.png","title":"2019年Q3智能音箱数据发布，天猫精灵再次国内第一","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":53,"id":54,"releaseTime":1539586683000,"share":24,"source":"中国企业家杂志©","summary":"谁说滴滴之后再无网约车？新的搅局者又来了。","thumbnail":"https://img.huxiucdn.com/article/cover/201810/13/190901169923.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"有摩拜的前车之鉴，为何哈啰仍要入局网约车？","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":36,"id":53,"releaseTime":1539585103000,"share":7,"source":"高街高参","summary":"两天前，马云现身杭州云栖小镇，这次他的露面不是因为阿里巴巴集团的活动，也不是业界峰会，而是为政府站台，参加杭州市为打造全国数字经济第一城举办的动员大会。","thumbnail":"https://img.huxiucdn.com/article/cover/201810/14/123637310014.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"马云力挺杭州\u201c数字经济\u201d第一城之后，我先咽下这口泡沫","whetherAdvertising":2,"whetherCollection":2,"whetherPay":1},{"collection":40,"id":52,"releaseTime":1539584990000,"share":6,"source":"Eastland","summary":"海底捞以17.8港元在港发售4.2亿新股，募集资金净额72.7亿港元，高瓴、景林、雪湖等基石投资者认购数占IPO发行量的38.95%。","thumbnail":"https://img.huxiucdn.com/article/cover/201810/15/073628459039.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"风雨飘摇中，海底捞能否成为投资避风港？","whetherAdvertising":2,"whetherCollection":2,"whetherPay":1},{"collection":26,"id":51,"releaseTime":1539584821000,"share":1,"source":"零售威观察©","summary":"作为一家英国零售商，Primark主要销售服装、配饰、美妆、箱包和家居用品。","thumbnail":"https://img.huxiucdn.com/article/cover/201810/15/081855659893.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"不玩电商的Primark是如何横扫美国零售市场的？","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * collection : 4
         * id : 65
         * releaseTime : 1572763883000
         * share : 28
         * source : ITBEAR科技资讯
         * summary : 双11在即，国内三大智能音箱品牌均面向用户大幅让利。天猫精灵X1和小米AI音箱到手价格均降至199元，带屏音箱天猫精灵CCL和小度在家1S到手价格低至299元，竞争进入肉搏战。
         * thumbnail : http://www.itbear.com.cn/upload/2019-11/191106140480852.png
         * title : 2019年Q3智能音箱数据发布，天猫精灵再次国内第一
         * whetherAdvertising : 2
         * whetherCollection : 2
         * whetherPay : 2
         */

        private int collection;
        private int id;
        private long releaseTime;
        private int share;
        private String source;
        private String summary;
        private String thumbnail;
        private String title;
        private int whetherAdvertising;
        private int whetherCollection;
        private int whetherPay;

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWhetherAdvertising() {
            return whetherAdvertising;
        }

        public void setWhetherAdvertising(int whetherAdvertising) {
            this.whetherAdvertising = whetherAdvertising;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }

        public int getWhetherPay() {
            return whetherPay;
        }

        public void setWhetherPay(int whetherPay) {
            this.whetherPay = whetherPay;
        }
    }
}
