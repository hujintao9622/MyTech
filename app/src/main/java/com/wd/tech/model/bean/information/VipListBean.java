package com.wd.tech.model.bean.information;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期三/23:47
 * 功能：查询所有会员商品
 */
public class VipListBean {

    /**
     * result : [{"commodityId":1001,"commodityName":"会员周卡","imageUrl":"http://172.17.8.100/images/tech/community_pic/vip_zk.jpg","price":6},{"commodityId":1002,"commodityName":"会员月卡","imageUrl":"http://172.17.8.100/images/tech/community_pic/vip_zk.jpg","price":20},{"commodityId":1003,"commodityName":"会员季卡","imageUrl":"http://172.17.8.100/images/tech/community_pic/vip_zk.jpg","price":66},{"commodityId":1004,"commodityName":"会员年卡","imageUrl":"http://172.17.8.100/images/tech/community_pic/vip_zk.jpg","price":99}]
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
         * commodityId : 1001
         * commodityName : 会员周卡
         * imageUrl : http://172.17.8.100/images/tech/community_pic/vip_zk.jpg
         * price : 6
         */

        private int commodityId;
        private String commodityName;
        private String imageUrl;
        private double price;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
