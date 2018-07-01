package com.taotao.store.order.pojo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Table(name="tb_take")
public class Take {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//收获单ID
    @Length(max = 20)
    private String name;//收货人姓名
    @Length(max = 20)
    private String phone;//联系方式
    @Length(max = 10)
    private String provinceName;//省
    @Length(max = 10)
    private String cityName;//市
    @Length(max = 20)
    private String countyName;//区/县
    @Length(max = 200)
    private String townName;//地址
    
    private Long userId;
    private Date created;//创建时间
    private Date updated;//更新时间，最新更新时间为默认的收货单
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getCountyName() {
        return countyName;
    }
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
    public String getTownName() {
        return townName;
    }
    public void setTownName(String townName) {
        this.townName = townName;
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
    @Override
    public String toString() {
        return "Take [id=" + id + ", name=" + name + ", phone=" + phone + ", provinceName=" + provinceName
                + ", cityName=" + cityName + ", countyName=" + countyName + ", townName=" + townName
                + ", created=" + created + ", updated=" + updated + "]";
    }
    

}
