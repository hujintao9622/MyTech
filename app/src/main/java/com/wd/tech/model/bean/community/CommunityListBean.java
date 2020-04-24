package com.wd.tech.model.bean.community;

import java.util.List;

/**
 * date:2020/4/23 0023
 * author:胡锦涛(Administrator)
 * function:社区列表
 */
public class CommunityListBean {


    /**
     * result : [{"comment":0,"communityCommentVoList":[],"content":"123","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-22/20200422003552.jpg","id":1901,"nickName":"花花公子","praise":0,"publishTime":1587574078000,"signature":"爱是一道光","userId":1402,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"再次测试","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417204020.png","id":1900,"nickName":"幻影月缺醉几何","praise":1,"publishTime":1587515293000,"signature":"一起走向科技","userId":1383,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"这是我发的帖子","file":"http://mobile.bwstudent.com/images/tech/community_pic/2020-04-21/6423720200421201411.png","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":1899,"nickName":"袁晨冉","praise":1,"publishTime":1587471251000,"userId":1401,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"兄弟们，集图啦！","file":"http://mobile.bwstudent.com/images/tech/community_pic/2020-04-21/9781020200421180028.png","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417204020.png","id":1898,"nickName":"幻影月缺醉几何","praise":2,"publishTime":1587463228000,"signature":"一起走向科技","userId":1383,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":1,"communityCommentVoList":[{"content":"奥利给","nickName":"花花23","userId":1414}],"content":"奥利给","file":"http://mobile.bwstudent.com/images/tech/community_pic/2020-04-21/0745820200421141329.png","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":1897,"nickName":"花花23","praise":1,"publishTime":1587449609000,"userId":1414,"whetherFollow":2,"whetherGreat":2,"whetherVip":2}]
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
         * comment : 0
         * communityCommentVoList : []
         * content : 123
         * file :
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-22/20200422003552.jpg
         * id : 1901
         * nickName : 花花公子
         * praise : 0
         * publishTime : 1587574078000
         * signature : 爱是一道光
         * userId : 1402
         * whetherFollow : 2
         * whetherGreat : 2
         * whetherVip : 2
         */

        private int comment;
        private String content;
        private String file;
        private String headPic;
        private int id;
        private String nickName;
        private int praise;
        private long publishTime;
        private String signature;
        private int userId;
        private int whetherFollow;
        private int whetherGreat;
        private int whetherVip;
        private List<?> communityCommentVoList;

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

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherFollow() {
            return whetherFollow;
        }

        public void setWhetherFollow(int whetherFollow) {
            this.whetherFollow = whetherFollow;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }

        public List<?> getCommunityCommentVoList() {
            return communityCommentVoList;
        }

        public void setCommunityCommentVoList(List<?> communityCommentVoList) {
            this.communityCommentVoList = communityCommentVoList;
        }
    }
}
