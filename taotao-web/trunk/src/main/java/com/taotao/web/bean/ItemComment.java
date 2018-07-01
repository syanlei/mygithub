package com.taotao.web.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class ItemComment {
    
    private Long id;
  
    private Long itemid;
   
    private String itembuytime;

    private Long userid;
    
    private String content;
  
    private Integer score;
   
    private Date createdate;
    
    private Date updatedate;
   
    private String pic;
   
    private String tags;
    
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
    @Override
    public String toString() {
        return "ItemComment [id=" + id + ", itemid=" + itemid + ", itembuytime=" + itembuytime + ", userid="
                + userid + ", content=" + content + ", score=" + score + ", createdate=" + createdate
                + ", updatedate=" + updatedate + ", pic=" + pic + ", tags=" + tags + "]";
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
    public Date getUpdatedate() {
        return updatedate;
    }
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
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
