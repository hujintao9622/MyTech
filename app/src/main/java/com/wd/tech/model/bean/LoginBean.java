package com.wd.tech.model.bean;

/**
 * date:2020/4/19 0019
 * author:胡锦涛(Administrator)
 * function:
 */
public class LoginBean {

    /**
     * result : {"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"春暖花开","phone":"18532186702","pwd":"bOI5BAk67nxPvY18c/iJKcZxKs31PJQGrh+HOVIzI/2ZPghqdmA1hXnqziZ4x/h++E2L2gxGHOWXTJFvWMc0kthWBWAK5/2v+7Kxgyge7QkA2ganv3QXHEQBcVlN7eyoE3hmKqcbcpWNNfd6wq/kx9ShpS5cL5egJgLEXXUr4s0=","sessionId":"15871232282781372","userId":1372,"userName":"q60Anb18532186702","whetherFaceId":0,"whetherVip":2}
     * message : 登录成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * nickName : 春暖花开
         * phone : 18532186702
         * pwd : bOI5BAk67nxPvY18c/iJKcZxKs31PJQGrh+HOVIzI/2ZPghqdmA1hXnqziZ4x/h++E2L2gxGHOWXTJFvWMc0kthWBWAK5/2v+7Kxgyge7QkA2ganv3QXHEQBcVlN7eyoE3hmKqcbcpWNNfd6wq/kx9ShpS5cL5egJgLEXXUr4s0=
         * sessionId : 15871232282781372
         * userId : 1372
         * userName : q60Anb18532186702
         * whetherFaceId : 0
         * whetherVip : 2
         */

        private String headPic;
        private String nickName;
        private String phone;
        private String pwd;
        private String sessionId;
        private int userId;
        private String userName;
        private int whetherFaceId;
        private int whetherVip;

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getWhetherFaceId() {
            return whetherFaceId;
        }

        public void setWhetherFaceId(int whetherFaceId) {
            this.whetherFaceId = whetherFaceId;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }
    }
}
