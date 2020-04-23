package com.wd.tech.model.bean.info;

import java.util.List;

/**
 * date:2020/4/21 0021
 * author:胡锦涛(Administrator)
 * function:
 */
public class FriendNoticeBean {

    /**
     * result : [{"fromHeadPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417201518.jpg","fromNickName":"黎明将之","fromUid":1381,"noticeId":1058,"noticeTime":1587128276000,"receiveUid":1372,"remark":"我是。。","status":1},{"fromHeadPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","fromNickName":"小猪猪！","fromUid":1374,"noticeId":1057,"noticeTime":1587094215000,"receiveUid":1372,"remark":"","status":2}]
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
         * fromHeadPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417201518.jpg
         * fromNickName : 黎明将之
         * fromUid : 1381
         * noticeId : 1058
         * noticeTime : 1587128276000
         * receiveUid : 1372
         * remark : 我是。。
         * status : 1
         */

        private String fromHeadPic;
        private String fromNickName;
        private int fromUid;
        private int noticeId;
        private long noticeTime;
        private int receiveUid;
        private String remark;
        private int status;

        public String getFromHeadPic() {
            return fromHeadPic;
        }

        public void setFromHeadPic(String fromHeadPic) {
            this.fromHeadPic = fromHeadPic;
        }

        public String getFromNickName() {
            return fromNickName;
        }

        public void setFromNickName(String fromNickName) {
            this.fromNickName = fromNickName;
        }

        public int getFromUid() {
            return fromUid;
        }

        public void setFromUid(int fromUid) {
            this.fromUid = fromUid;
        }

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public long getNoticeTime() {
            return noticeTime;
        }

        public void setNoticeTime(long noticeTime) {
            this.noticeTime = noticeTime;
        }

        public int getReceiveUid() {
            return receiveUid;
        }

        public void setReceiveUid(int receiveUid) {
            this.receiveUid = receiveUid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
