package com.wd.tech.model.bean.information;

/**
 * date:2020/5/2 0002
 * author:胡锦涛(Administrator)
 * function:下单
 */
public class OrderBean {

    /**
     * orderId : 20181019120947890
     * message : 下单成功
     * status : 0000
     */

    private String orderId;
    private String message;
    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
