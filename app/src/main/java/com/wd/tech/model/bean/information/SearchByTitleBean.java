package com.wd.tech.model.bean.information;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期一/14:44
 * 功能：根据标题模糊查询
 */
public class SearchByTitleBean {

    /**
     * result : [{"id":54,"releaseTime":1539586683000,"source":"中国企业家杂志©","title":"有摩拜的前车之鉴，为何哈啰仍要入局网约车？"}]
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
         * id : 54
         * releaseTime : 1539586683000
         * source : 中国企业家杂志©
         * title : 有摩拜的前车之鉴，为何哈啰仍要入局网约车？
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