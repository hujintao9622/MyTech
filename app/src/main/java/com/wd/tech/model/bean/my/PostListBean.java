package com.wd.tech.model.bean.my;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/五月/星期日/14:05
 * 功能：我的帖子
 */
public class PostListBean {

    /**
     * result : [{"comment":2,"content":"shima","file":"http://mobile.bwstudent.com/images/tech/community_pic/2020-04-30/8976020200430100326.png","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":1969,"nickName":"辣椒炒鸡蛋","praise":3,"publishTime":1588212206000,"userId":1388}]
     * message : 查詢成功
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
         * comment : 2
         * content : shima
         * file : http://mobile.bwstudent.com/images/tech/community_pic/2020-04-30/8976020200430100326.png
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * id : 1969
         * nickName : 辣椒炒鸡蛋
         * praise : 3
         * publishTime : 1588212206000
         * userId : 1388
         */

        private int comment;
        private String content;
        private String file;
        private String headPic;
        private int id;
        private String nickName;
        private int praise;
        private long publishTime;
        private int userId;

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
