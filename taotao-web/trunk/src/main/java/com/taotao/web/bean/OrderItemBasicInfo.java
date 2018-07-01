package com.taotao.web.bean;
/**
 * 待評價商品基礎信息
 * @author xrz
 *
 */
public class OrderItemBasicInfo {
    
    private Long itemId;
    private String itemTitle;
    private String itemBuyTime;
    private String itemImageUrl;
    private Long orderId;
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getItemImageUrl() {
        return itemImageUrl;
    }
    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public String getItemTitle() {
        return itemTitle;
    }
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
    public String getItemBuyTime() {
        return itemBuyTime;
    }
    public void setItemBuyTime(String itemBuyTime) {
        this.itemBuyTime = itemBuyTime;
    }
}
