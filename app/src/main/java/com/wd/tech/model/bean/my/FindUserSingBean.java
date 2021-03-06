package com.wd.tech.model.bean.my;

/**
 * 用户名：恆亙
 * 时间：2020/五月/星期四/02:04
 * 功能：查询当天签到状态
 */
public class FindUserSingBean {
    /**
     * result : 1
     * message : 查询成功
     * status : 0000
     */

    private int result;
    private String message;
    private String status;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
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
}
