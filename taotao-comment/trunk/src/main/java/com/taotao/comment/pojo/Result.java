package com.taotao.comment.pojo;

import java.util.List;

/**
 * 构造返回的数据类型
 * 
 * @author qingbing
 *
 */

public class Result {
    private String productAttr;

    private ProductCommentSummary productCommentSummary;

    // 热门标签
    private List<HotCommentTag> hotCommentTagStatistics;

    private Integer score;

    // 评价回显
    private List<CommentBack> comments;
    
    // 不用字段
    //private List<StringBuffer> topFiveCommentVos;

    public Result() {
        this.productAttr = null;
        this.score = 0;

    }

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
    }

    public ProductCommentSummary getProductCommentSummary() {
        return productCommentSummary;
    }

    public void setProductCommentSummary(ProductCommentSummary productCommentSummary) {
        this.productCommentSummary = productCommentSummary;
    }

    public List<HotCommentTag> getHotCommentTagStatistics() {
        return hotCommentTagStatistics;
    }

    public void setHotCommentTagStatistics(List<HotCommentTag> hotCommentTagStatistics) {
        this.hotCommentTagStatistics = hotCommentTagStatistics;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<CommentBack> getComments() {
        return comments;
    }

    public void setComments(List<CommentBack> comments) {
        this.comments = comments;
    }

}
