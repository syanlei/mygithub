package com.taotao.comment.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Table(name="tb_comment")
public class ItemComment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long itemid;
    @Column
    private String itembuytime;
    @Column
    private Long userid;
    @Column
    private String content;
    @Column
    private Integer score;
    @Column
    private Date createdate;
    @Column
    private String pic;
    @Column
    private String tags;
    @Transient
    private Long orderid;
    
    public Long getOrderid() {
        return orderid;
    }
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getItemid() {
        return itemid;
    }
    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }
   
    public String getItembuytime() {
        return itembuytime;
    }
    public void setItembuytime(String itembuytime) {
        this.itembuytime = itembuytime;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
    public Date getCreatedate() {
        return createdate;
    }
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    
}
