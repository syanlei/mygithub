package com.taotao.comment.pojo;

import java.util.Date;

public class Order {
    private String orderId;// id,rowKye:id+时间戳

    private Integer status;// 状态:1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭

    private Date paymentTime;// 付款时间

    private Integer buyerRate;// 买家是否已经评价

    public Integer getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(Integer buyerRate) {
        this.buyerRate = buyerRate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

}
