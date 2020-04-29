package com.wd.tech.model.bean.information;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期一/14:45
 * 功能：根据作者名模糊查询
 */
public class SearchBySourceBean {

    /**
     * result : [{"id":63,"releaseTime":1553064683000,"source":"哈哈网","title":"脱离时代需求的搜索引擎，会不会被人工智能APP取代？"},{"id":62,"releaseTime":1553064558000,"source":"亚马逊网","title":"亚马逊：用AI瞄准全球10万亿美元的医疗健康大机遇"},{"id":58,"releaseTime":1553063178000,"source":"中原网","title":"5G元年话5G：让生活再快一些"},{"id":48,"releaseTime":1539582496000,"source":"网事风云","title":"区块链落地实体经济，这个领域可能是先锋"},{"id":35,"releaseTime":1539396095000,"source":"砍柴网","title":"VR/AR将我们带到了令人惊叹的新地方"},{"id":34,"releaseTime":1539395910000,"source":"砍柴网","title":"到2020年全球1/3的消费者将使用VR"},{"id":33,"releaseTime":1539394649000,"source":"腾讯网","title":"苹果AR挡风玻璃系统细节：全新UI交互界面，支持FaceTime通话"},{"id":32,"releaseTime":1539393683000,"source":"腾讯网","title":"VizMove系统更新为大规模和基于位置的VR体验引入更多功能"},{"id":22,"releaseTime":1539248227000,"source":"雷锋网","title":"亮风台2D AR算法新突破，夺冠世界权威评测"},{"id":21,"releaseTime":1539247754000,"source":"砍柴网 ","title":"加拿大开设了首个VR验光实验室"}]
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
         * id : 63
         * releaseTime : 1553064683000
         * source : 哈哈网
         * title : 脱离时代需求的搜索引擎，会不会被人工智能APP取代？
         */

        private int id;
        private long releaseTime;
        private String source;
        private String title;

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

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
