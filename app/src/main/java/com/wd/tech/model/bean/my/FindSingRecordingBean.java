package com.wd.tech.model.bean.my;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/五月/星期四/09:55
 * 功能：查询当月签到日期
 */
public class FindSingRecordingBean {
    /**
     * result : ["2020-05-07","2020-05-08","2020-05-09"]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<String> result;

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

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
