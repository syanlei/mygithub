package com.taotao.comment.pojo;

/**
 * 商品id 热门标签类：查询标签表
 * 
 * @author qingbing
 *
 */
public class HotCommentTag {
    // 标签id
    private Long id;

    // 标签name
    private String name;

    private int status=1;

    // 标签rid=id
    private Long rid;

    // 商品id
    private Long productId;

    // 标签数量
    private Long count;

    // 标签createdate
    private String created;

    // 标签updateTime
    private String modified;

    public HotCommentTag() {
        id = (long) 293048;
        name = "后盖很漂亮";
        rid = (long) 5993450;
        productId = (long) 679533;
        count = (long) 721;
        created = "2014-06-06 10=49=37";
        modified = "2015-04-18 10=16=49";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

}
