package com.taotao.store.order.pojo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Table(name = "tb_invocie")
public class Invocie {

    @Id
    private String invocieId;
    /***
     * 收件人姓名
     */
    @Length(max = 20)
    private String invocieName;
    /***
     * 收件人电话
     */
    @Length(max = 20)
    private String invociePhone;
    
    /***
     * 发票类型
     */
    @Length(max = 20)
    private String invocieType;

    /***
     * 发票方式
     */
    @Length(max = 20)
    private String invocieMode;

    /***
     * 发票台头
     */
    @Length(max = 30)
    private String invocieHead;

    /***
     * 发票内容
     */
    @Length(max = 500)
    private String invocieContent;

    /**
     * 发票创建时间
     */
    private Date created;// 创建时间

    /***
     * 发票更新时间
     */
    private Date updated;// 更新时间
    
    public String getInvocieName() {
        return invocieName;
    }
    public void setInvocieName(String invocieName) {
        this.invocieName = invocieName;
    }
    public String getInvociePhone() {
        return invociePhone;
    }
    public void setInvociePhone(String invociePhone) {
        this.invociePhone = invociePhone;
    }
    private Long userId;
    
    public String getInvocieId() {
        return invocieId;
    }
    public void setInvocieId(String invocieId) {
        this.invocieId = invocieId;
    }
    public String getInvocieType() {
        return invocieType;
    }
    public void setInvocieType(String invocieType) {
        this.invocieType = invocieType;
    }
    public String getInvocieMode() {
        return invocieMode;
    }
    public void setInvocieMode(String invocieMode) {
        this.invocieMode = invocieMode;
    }
    public String getInvocieHead() {
        return invocieHead;
    }
    public void setInvocieHead(String invocieHead) {
        this.invocieHead = invocieHead;
    }
    public String getInvocieContent() {
        return invocieContent;
    }
    public void setInvocieContent(String invocieContent) {
        this.invocieContent = invocieContent;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    



}
