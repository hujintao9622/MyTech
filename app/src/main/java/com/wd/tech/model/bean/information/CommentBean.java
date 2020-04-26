package com.wd.tech.model.bean.information;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期五/13:15
 * 功能：资讯评论列表
 */
public class CommentBean {

    /**
     * result : [{"commentTime":1587385840000,"content":"你giao窝里giaogiao","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-20/20200420172724.png","id":2791,"infoId":65,"nickName":"android小老弟","userId":1416},{"commentTime":1587085505000,"content":"%E5%8F%AB%E7%88%B8%E7%88%B8%E7%BB%99%E4%BD%A0%E5%A5%BD%E4%B8%9C%E8%A5%BF","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2785,"infoId":65,"nickName":"難","userId":1375},{"commentTime":1584409280000,"content":"第一","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-03-17/20200317095502.png","id":2727,"infoId":65,"nickName":"范红磊100861111","userId":1321},{"commentTime":1584409280000,"content":"第一","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-03-17/20200317095502.png","id":2726,"infoId":65,"nickName":"范红磊100861111","userId":1321},{"commentTime":1584409280000,"content":"第一","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-03-17/20200317095502.png","id":2725,"infoId":65,"nickName":"范红磊100861111","userId":1321}]
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
         * commentTime : 1587385840000
         * content : 你giao窝里giaogiao
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-20/20200420172724.png
         * id : 2791
         * infoId : 65
         * nickName : android小老弟
         * userId : 1416
         */

        private long commentTime;
        private String content;
        private String headPic;
        private int id;
        private int infoId;
        private String nickName;
        private int userId;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
