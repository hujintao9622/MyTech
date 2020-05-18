package com.wd.tech.model.bean.my;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/五月/星期日/22:12
 * 功能：用户关注列表
 */
public class FollowListBean {
    /**
     * result : [{"focusTime":1589723939000,"focusUid":1385,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417210315.jpg","nickName":"人间惊鸿客","signature":"篝火旁","userId":1388,"whetherMutualFollow":2,"whetherVip":2},{"focusTime":1589723924000,"focusUid":1372,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","nickName":"诗和远方","signature":"剑与远征","userId":1388,"whetherMutualFollow":2,"whetherVip":2},{"focusTime":1589723895000,"focusUid":1377,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"大前门","userId":1388,"whetherMutualFollow":2,"whetherVip":2},{"focusTime":1589444217000,"focusUid":1378,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"饮酒醉","userId":1388,"whetherMutualFollow":2,"whetherVip":2}]
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
         * focusTime : 1589723939000
         * focusUid : 1385
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417210315.jpg
         * nickName : 人间惊鸿客
         * signature : 篝火旁
         * userId : 1388
         * whetherMutualFollow : 2
         * whetherVip : 2
         */

        private long focusTime;
        private int focusUid;
        private String headPic;
        private String nickName;
        private String signature;
        private int userId;
        private int whetherMutualFollow;
        private int whetherVip;

        public long getFocusTime() {
            return focusTime;
        }

        public void setFocusTime(long focusTime) {
            this.focusTime = focusTime;
        }

        public int getFocusUid() {
            return focusUid;
        }

        public void setFocusUid(int focusUid) {
            this.focusUid = focusUid;
        }

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

        public int getWhetherMutualFollow() {
            return whetherMutualFollow;
        }

        public void setWhetherMutualFollow(int whetherMutualFollow) {
            this.whetherMutualFollow = whetherMutualFollow;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }
    }
}
