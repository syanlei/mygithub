package com.taotao.comment.pojo;

import java.util.List;

/**
 * 商品评价回显数据:商品表和评价表
 * 
 * @author qingbing
 *
 */
public class CommentBack {
    // 评价id
    private Long id;

    private String guid = "21c73b45-f5ad-4903-8746-ae031593f5c0";

    // 评价心得
    private String content;

    // 评价日期
    private String creationTime;

    private Boolean isTop = false;

    // 商品id
    private Long referenceId;

    // 商品image
    private String referenceImage;

    // 商品name
    private String referenceName;

    // 商品购买时间
    private String referenceTime;

    private String referenceType = "Product";

    private Integer referenceTypeId = 0;

    // 商品目录
    private Long firstCategory = null;

    private Long secondCategory=null;

    private Long thirdCategory=null;

    private Integer replyCount = 0;

    // 评价score
    private Integer score;
    
    //商品status
    private Integer status=1;

    private Integer usefulVoteCount = 0;

    private Integer uselessVoteCount = 0;

    private String userImage = "http://item.jd.com/1138529.html";

    private String userImageUrl = "http://item.jd.com/1138529.html";

    private String userLevelId = "56";

    private String userProvince = "";

    private Integer viewCount = 0;

    private Integer orderId = 0;

    private Boolean isReplyGrade = false;

    // 用户name
    private String nickname;

    private Integer userClient = 0;

    // 标签回显
    private List<TagBack> commentTags;

    private String productColor = "釉白";

    private String productSize = "移动4G";

    private Integer integral = -30;

    private Integer anonymousFlag = 1;

    private String userLevelName = "金牌会员";

    private Boolean recommend = false;

    private String userLevelColor = "#088000";

    private String userClientShow = "来自手机QQ购物";

    private Boolean isMobile = false;

    public CommentBack() {
        //书写以下字段
        this.id= (long) 664017354;
        this.content= "就是耳机有点问题";
        this.creationTime= "2015-04-18 11:43:22";
        this.referenceId= (long) 679533;
        this.referenceImage= "g16/M00/0C/07/rBEbRlOIP_MIAAAAAAFWXorXF4YAACYlQLZhz0AAVZ2428.jpg";
        this.referenceName= "三星 Galaxy Grand 2 (G7108V) 白色 移动4G手机";
        this.referenceTime= "2014-11-06 10:27:10";
        this.score= 5;
        this.status=1;
        //以下字段可暂时不写
        this.usefulVoteCount= 0;
        this.uselessVoteCount= 0;
        this.userImage= "misc.360buyimg.com/lib/img/u/b56.gif";
        this.userImageUrl= "misc.360buyimg.com/lib/img/u/b56.gif";
        this.nickname="me";
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }


    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceImage() {
        return referenceImage;
    }

    public void setReferenceImage(String referenceImage) {
        this.referenceImage = referenceImage;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getReferenceTime() {
        return referenceTime;
    }

    public void setReferenceTime(String referenceTime) {
        this.referenceTime = referenceTime;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public Integer getReferenceTypeId() {
        return referenceTypeId;
    }

    public void setReferenceTypeId(Integer referenceTypeId) {
        this.referenceTypeId = referenceTypeId;
    }

    public Long getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(Long firstCategory) {
        this.firstCategory = firstCategory;
    }

    public Long getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(Long secondCategory) {
        this.secondCategory = secondCategory;
    }

    public Long getThirdCategory() {
        return thirdCategory;
    }

    public void setThirdCategory(Long thirdCategory) {
        this.thirdCategory = thirdCategory;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUsefulVoteCount() {
        return usefulVoteCount;
    }

    public void setUsefulVoteCount(Integer usefulVoteCount) {
        this.usefulVoteCount = usefulVoteCount;
    }

    public Integer getUselessVoteCount() {
        return uselessVoteCount;
    }

    public void setUselessVoteCount(Integer uselessVoteCount) {
        this.uselessVoteCount = uselessVoteCount;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(String userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Boolean getIsReplyGrade() {
        return isReplyGrade;
    }

    public void setIsReplyGrade(Boolean isReplyGrade) {
        this.isReplyGrade = isReplyGrade;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getUserClient() {
        return userClient;
    }

    public void setUserClient(Integer userClient) {
        this.userClient = userClient;
    }

    public List<TagBack> getCommentTags() {
        return commentTags;
    }

    public void setCommentTags(List<TagBack> commentTags) {
        this.commentTags = commentTags;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getAnonymousFlag() {
        return anonymousFlag;
    }

    public void setAnonymousFlag(Integer anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }

    public String getUserLevelName() {
        return userLevelName;
    }

    public void setUserLevelName(String userLevelName) {
        this.userLevelName = userLevelName;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public String getUserLevelColor() {
        return userLevelColor;
    }

    public void setUserLevelColor(String userLevelColor) {
        this.userLevelColor = userLevelColor;
    }

    public String getUserClientShow() {
        return userClientShow;
    }

    public void setUserClientShow(String userClientShow) {
        this.userClientShow = userClientShow;
    }

    public Boolean getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(Boolean isMobile) {
        this.isMobile = isMobile;
    }

   

}
