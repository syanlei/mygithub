package com.taotao.comment.pojo;

/**
 * ProductCommentSummary对象数据
 * 
 * @author qingbing
 *
 */
public class ProductCommentSummary {
    private Integer beginRowNumber=0;

    private Integer endRowNumber=0;

    private Long skuId;

    private Long productId;

    private Integer score1Count=null;

    private Integer score2Count=null;

    private Integer score3Count=null;

    private Integer score4Count=null;

    private Integer score5Count=null;

    private Integer showCount;

    private Integer commentCount;

    private Integer averageScore;

    private Integer goodCount;

    private String goodRate;

    private String goodRateShow;

    private Double goodRateStyle;

    private Integer generalCount;

    private String generalRate;

    private String generalRateShow;

    private Double generalRateStyle;

    private Integer poorCount;

    private String poorRate;

    private String poorRateShow;

    private Double poorRateStyle;

    public ProductCommentSummary() {
        //商品id
        this.skuId = (long) 1138529;
        this.productId = (long) 1138529;
        //含有图片数量
        this.showCount = 409;
        //标签数量
        this.commentCount = 10082;
        this.averageScore = 5;
        //好评数量
        this.goodCount = 9396;
        //好评百分率
        this.goodRate = "0.933";
        //好评百分数
        this.goodRateShow = "94";
        this.goodRateStyle = 140.0;
        //中评数量
        this.generalCount = 448;
        //中评百分率
        this.generalRate = "0.044";
        //中评百分数
        this.generalRateShow = "4";
        this.generalRateStyle = 7.0;
        //差评数量
        this.poorCount = 238;
        //差评百分率
        this.poorRate = "0.023";
        //差评百分数
        this.poorRateShow = "2";
        this.poorRateStyle = 3.0;

    }

    public Integer getBeginRowNumber() {
        return beginRowNumber;
    }

    public void setBeginRowNumber(Integer beginRowNumber) {
        this.beginRowNumber = beginRowNumber;
    }

    public Integer getEndRowNumber() {
        return endRowNumber;
    }

    public void setEndRowNumber(Integer endRowNumber) {
        this.endRowNumber = endRowNumber;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getScore1Count() {
        return score1Count;
    }

    public void setScore1Count(Integer score1Count) {
        this.score1Count = score1Count;
    }

    public Integer getScore2Count() {
        return score2Count;
    }

    public void setScore2Count(Integer score2Count) {
        this.score2Count = score2Count;
    }

    public Integer getScore3Count() {
        return score3Count;
    }

    public void setScore3Count(Integer score3Count) {
        this.score3Count = score3Count;
    }

    public Integer getScore4Count() {
        return score4Count;
    }

    public void setScore4Count(Integer score4Count) {
        this.score4Count = score4Count;
    }

    public Integer getScore5Count() {
        return score5Count;
    }

    public void setScore5Count(Integer score5Count) {
        this.score5Count = score5Count;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public String getGoodRate() {
        return goodRate;
    }

    public void setGoodRate(String goodRate) {
        this.goodRate = goodRate;
    }

    public String getGoodRateShow() {
        return goodRateShow;
    }

    public void setGoodRateShow(String goodRateShow) {
        this.goodRateShow = goodRateShow;
    }

    public Double getGoodRateStyle() {
        return goodRateStyle;
    }

    public void setGoodRateStyle(Double goodRateStyle) {
        this.goodRateStyle = goodRateStyle;
    }

    public Integer getGeneralCount() {
        return generalCount;
    }

    public void setGeneralCount(Integer generalCount) {
        this.generalCount = generalCount;
    }

    public String getGeneralRate() {
        return generalRate;
    }

    public void setGeneralRate(String generalRate) {
        this.generalRate = generalRate;
    }

    public String getGeneralRateShow() {
        return generalRateShow;
    }

    public void setGeneralRateShow(String generalRateShow) {
        this.generalRateShow = generalRateShow;
    }

    public Double getGeneralRateStyle() {
        return generalRateStyle;
    }

    public void setGeneralRateStyle(Double generalRateStyle) {
        this.generalRateStyle = generalRateStyle;
    }

    public Integer getPoorCount() {
        return poorCount;
    }

    public void setPoorCount(Integer poorCount) {
        this.poorCount = poorCount;
    }

    public String getPoorRate() {
        return poorRate;
    }

    public void setPoorRate(String poorRate) {
        this.poorRate = poorRate;
    }

    public String getPoorRateShow() {
        return poorRateShow;
    }

    public void setPoorRateShow(String poorRateShow) {
        this.poorRateShow = poorRateShow;
    }

    public Double getPoorRateStyle() {
        return poorRateStyle;
    }

    public void setPoorRateStyle(Double poorRateStyle) {
        this.poorRateStyle = poorRateStyle;
    }

   
   

}
