package com.taotao.comment.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.taotao.comment.mapper.ItemCommentMapper;
import com.taotao.comment.mapper.ItemCommentTagMapper;
import com.taotao.comment.mapper.ItemMapper;
import com.taotao.comment.pojo.CommentBack;
import com.taotao.comment.pojo.HotCommentTag;
import com.taotao.comment.pojo.Item;
import com.taotao.comment.pojo.ItemComment;
import com.taotao.comment.pojo.ItemCommentTag;
import com.taotao.comment.pojo.ProductCommentSummary;
import com.taotao.comment.pojo.Result;
import com.taotao.comment.pojo.TagBack;
/**
 * 根据商品id构造数据
 * 查询三张表：标签，商品，评价
 * 查询redis获取前排标签
 * @author qingbing
 *
 */
@Service
public class ResutService {
    @Autowired
    private ItemCommentTagMapper itemCommentTagMapper;
    @Autowired
    private ItemCommentMapper itemCommentMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private TagService tagService;
    public Result queryAll(Long itemId) {
        //构建数据类
        Result result = new Result();
        Item item = itemMapper.selectByPrimaryKey(itemId);
        //查询商品评价
        Example itemCommentexample=new Example(ItemComment.class);
        itemCommentexample.createCriteria().andEqualTo("itemid", itemId);
        itemCommentexample.setOrderByClause("createdate DESC"); 
        List<ItemComment> commentlist =itemCommentMapper.selectByExample(itemCommentexample);
        
        List<CommentBack> comments=new ArrayList<CommentBack>();
        for (ItemComment itemComment : commentlist) {
            //回填评价
            CommentBack commentBack = new CommentBack();
            commentBack.setId(itemComment.getId());
            commentBack.setContent(itemComment.getContent());
            //评价时间
            DateTime dateTime=new DateTime(itemComment.getCreatedate());
            commentBack.setCreationTime(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
            commentBack.setReferenceId(itemId);
            commentBack.setReferenceImage(item.getImage());
            commentBack.setReferenceName(item.getTitle());
            //购买时间
            commentBack.setReferenceTime(itemComment.getItembuytime());
            commentBack.setScore(itemComment.getScore());
            commentBack.setStatus(item.getStatus());
            
            //查询商品标签：根据评价tags获得所要标签
            String tags = itemComment.getTags();
            //判断是否有标签
            if(StringUtils.isNoneEmpty(tags)){
            String[] commentTags=tags.split(",");
            List<TagBack> tagsBack=new ArrayList<TagBack>();
            for (String tagname : commentTags) {  
                //回填标签
                TagBack tagBack = new TagBack();
                ItemCommentTag tag=new ItemCommentTag();
                tag.setTagname(tagname);
                ItemCommentTag itemCommentTag = itemCommentTagMapper.selectOne(tag);
                tagBack.setId(itemCommentTag.getId());
                tagBack.setName(itemCommentTag.getTagname());
                //rid都写成标签id
                tagBack.setRid(itemCommentTag.getId());
                //商品id
                tagBack.setProductId(itemId);
                //标签id
                tagBack.setCommentId(itemComment.getId());
                tagBack.setCreated(itemCommentTag.getCreatedate().toString());
                tagBack.setModified(itemCommentTag.getUpdatedate().toString());
                tagsBack.add(tagBack);
              
            }
            //商品标签加入商品评价
            commentBack.setCommentTags(tagsBack);
            }else{
            commentBack.setCommentTags(null);
            }
            comments.add(commentBack);
           
        }
        List<HotCommentTag> hots = new ArrayList<HotCommentTag>();
        //从redis查询数据:取前五个
        List<ItemCommentTag> tagList = tagService.queryTagList(itemId);
        for (ItemCommentTag itemCommentTag : tagList) {
            //回填热门标签
            HotCommentTag hot = new HotCommentTag();
            hot.setId(itemCommentTag.getId()); 
            hot.setName(itemCommentTag.getTagname());
            //rid都写成标签id
            hot.setRid(itemCommentTag.getId());
            hot.setProductId(itemId);
            hot.setCount(itemCommentTag.getCount());
            hots.add(hot);
        }
        // 回填其他类
        ProductCommentSummary summary = new ProductCommentSummary();
        //商品id
        summary.setSkuId(itemId);
        summary.setProductId(itemId);
        int showCount=0;
        int goodCount=0;
        int generalCount=0;
        int poorCount=0;
        for (ItemComment itemComment : commentlist) {
            if(StringUtils.isNotEmpty(itemComment.getPic())){
                showCount++;
            }
            if(itemComment.getScore()>3&&itemComment.getScore()<=5){
                goodCount++;
            }
            if(itemComment.getScore()==3){
                generalCount++;
            }
            if(itemComment.getScore()>=0&&itemComment.getScore()<3){
                poorCount++;
            }
            continue;
        }
        //评价含有图片数量
        summary.setShowCount(showCount);
        //评价总数量
        summary.setCommentCount(commentlist.size());
        //好评数量
        summary.setGoodCount(goodCount);
        //好评百分率
        DecimalFormat df = new DecimalFormat("0.000");
        String format1 = df.format(goodCount*1.0/commentlist.size());
        summary.setGoodRate(format1);
        //好评百分数
        summary.setGoodRateShow(format1.substring(2, 4));  
        summary.setGoodRateStyle(Double.valueOf(format1.substring(2, 4))*1.5);
        //中评数量
        summary.setGeneralCount(generalCount);
        //中评百分率
        String format2 = df.format(generalCount*1.0/commentlist.size());
        summary.setGoodRate(format2);
        //中评百分数
        summary.setGeneralRateShow(format2.substring(2, 4));
        summary.setGeneralRateStyle(Double.valueOf(format2.substring(2, 4))*1.5);
        //差评数量
        summary.setPoorCount(poorCount);
        //差评百分率
        String format3 = df.format(poorCount*1.0/commentlist.size());
        summary.setPoorRate(format3);
        //差评百分数
        summary.setPoorRateShow(format3.substring(2, 4));
        summary.setPoorRateStyle(Double.valueOf(format3.substring(2, 4))*1.5);
        // 结果
        result.setComments(comments);
        result.setHotCommentTagStatistics(hots);
        result.setProductCommentSummary(summary);
        return result;
    }

}
