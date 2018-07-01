package com.taotao.comment.pojo;

/**
 * 商品id 商品标签回显数据：查询标签和评价表
 * 
 * @author qingbing
 *
 */
public class TagBack {
    // 标签id
    private Long id;

    // 标签name
    private String name;

    private String pin = "";

    private Integer status=1;

    // 标签rid=id
    private Long rid;

    // 商品id
    private Long productId;

    // 评价id
    private Long commentId;

    // 标签更新时间
    private String created;

    private String modified;

    public TagBack() {
        id = (long) 5993450;
        name = "后盖很漂亮";
        //下面3字段必须对应才能显示
        rid = (long) 5993450;
        productId = (long) 679533;
        commentId = (long) 664017354;
        created = null;
        modified = null;

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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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
