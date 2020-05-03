package com.wd.tech.model.bean.my;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/五月/星期日/11:47
 * 功能：用户收藏列表
 */
public class AllCollectionBean {

    /**
     * result : [{"createTime":1587202616000,"id":4200,"infoId":67,"thumbnail":"http://www.itbear.com.cn/upload/2020-04/2004241500311714.png","title":"这次是杭州！快来看李彦宏的\u201cAI助盲\u201d路线图"}]
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
         * createTime : 1587202616000
         * id : 4200
         * infoId : 67
         * thumbnail : http://www.itbear.com.cn/upload/2020-04/2004241500311714.png
         * title : 这次是杭州！快来看李彦宏的“AI助盲”路线图
         */

        private long createTime;
        private int id;
        private int infoId;
        private String thumbnail;
        private String title;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
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
    }
}
