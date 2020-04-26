package com.wd.tech.model.bean.information;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期六/16:47
 * 功能：我的积分
 */
public class IntegralBean {

    /**
     * result : {"amount":10,"id":1393,"updateTime":1587202647000,"userId":1388}
     * message : 查询成功
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
         * amount : 10
         * id : 1393
         * updateTime : 1587202647000
         * userId : 1388
         */

        private int amount;
        private int id;
        private long updateTime;
        private int userId;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
