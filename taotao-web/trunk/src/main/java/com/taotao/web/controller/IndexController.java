package com.taotao.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.web.bean.ArrayContent;
import com.taotao.web.service.IndexService;
import com.taotao.web.utils.PackagingUtils;

@RequestMapping("index")
@Controller
public class IndexController {
    
    @Autowired
    private IndexService indexService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        
        ModelAndView mv = new ModelAndView("index");
        //查询大广告位数据
        String jsonData = this.indexService.queryIndexAd1();
        mv.addObject("indexAd1", jsonData);
        //查询右上角广告
        mv.addObject("indexAd2", this.indexService.queryIndexAd2());
        
        //查询滚动广告
        mv.addObject("indexScroll",this.indexService.queryScroll());
        
        //查询中央广告
        mv.addObject("centerAD",this.indexService.queryCenterAD());
        
        //左边框1F内容和图片
        List<String[]> contentAndImg1 = this.indexService.leftContentAndImg1();
        if(contentAndImg1!=null&&contentAndImg1.size()>0){
            mv.addObject("leftContentAndImgUrl1", contentAndImg1.get(1));
            mv.addObject("leftContentAndImgTitle1",contentAndImg1.get(0));
            mv.addObject("leftContentAndImgImg1", contentAndImg1.get(2));
        }
        List<String[]> contentAndImg2 = this.indexService.leftContentAndImg1();
        if(contentAndImg2!=null&&contentAndImg1.size()>0){
            mv.addObject("leftContentAndImgUrl1", contentAndImg2.get(1));
            mv.addObject("leftContentAndImgTitle1",contentAndImg2.get(0));
            mv.addObject("leftContentAndImgImg1", contentAndImg2.get(2));
        }
        List<String[]> contentAndImg3 = this.indexService.leftContentAndImg1();
        if(contentAndImg3!=null&&contentAndImg1.size()>0){
            mv.addObject("leftContentAndImgUrl1", contentAndImg3.get(1));
            mv.addObject("leftContentAndImgTitle1",contentAndImg3.get(0));
            mv.addObject("leftContentAndImgImg1", contentAndImg3.get(2));
        }
       /* String[] contentAndImgUrl1 = contentAndImg1.get(1);
        String[] contentAndImgTitle1 = contentAndImg1.get(0);
        String[] img1 = contentAndImg1.get(2);*/
        /*mv.addObject("leftContentAndImgUrl1", contentAndImgUrl1);
        mv.addObject("leftContentAndImgTitle1", contentAndImgTitle1);
        mv.addObject("leftContentAndImgImg1", img1);*/
        
        //左边框2F内容和图片
       /* List<String[]> contentAndImg2 = this.indexService.leftContentAndImg2();
        String[] contentAndImgUrl2 = contentAndImg2.get(1);
        String[] contentAndImgTitle2 = contentAndImg2.get(0);
        String[] img2 = contentAndImg2.get(2);
        mv.addObject("leftContentAndImgUrl2", contentAndImgUrl2);
        mv.addObject("leftContentAndImgTitle2", contentAndImgTitle2);
        mv.addObject("leftContentAndImgImg2", img2);
        
        //左边框3F内容和图片
        List<String[]> contentAndImg3 = this.indexService.leftContentAndImg3();
        String[] contentAndImgUrl3 = contentAndImg3.get(1);
        String[] contentAndImgTitle3 = contentAndImg3.get(0);
        String[] img3 = contentAndImg3.get(2);
        mv.addObject("leftContentAndImgUrl3", contentAndImgUrl3);
        mv.addObject("leftContentAndImgTitle3", contentAndImgTitle3); 
        mv.addObject("leftContentAndImgImg3", img3);
        
        //左边框4F内容和图片
        List<String[]> contentAndImg4 = this.indexService.leftContentAndImg4();
        String[] contentAndImgUrl4 = contentAndImg4.get(1);
        String[] contentAndImgTitle4 = contentAndImg4.get(0);
        String[] img4 = contentAndImg4.get(2);
        mv.addObject("leftContentAndImgUrl4", contentAndImgUrl4);
        mv.addObject("leftContentAndImgTitle4", contentAndImgTitle4);
        mv.addObject("leftContentAndImgImg4", img4);
        
        //左边框5F内容和图片
        List<String[]> contentAndImg5 = this.indexService.leftContentAndImg5();
        String[] contentAndImgUrl5 = contentAndImg5.get(1);
        String[] contentAndImgTitle5 = contentAndImg5.get(0);
        String[] img5 = contentAndImg5.get(2);
        mv.addObject("leftContentAndImgUrl5", contentAndImgUrl5);
        mv.addObject("leftContentAndImgTitle5", contentAndImgTitle5);
        mv.addObject("leftContentAndImgImg5", img5);*/
        
        // 导航栏
        ArrayContent arrayContent = this.indexService.queryIndexAd3();
        String[] url1 = arrayContent.url;
        String[] title1 = arrayContent.title;
        mv.addObject("url", url1);
        mv.addObject("title", title1);
        
        
        // 淘淘快报
        mv.addObject("indexAd4", this.indexService.queryIndexAd4());
        //查询右侧品牌旗舰店
        mv.addObject("indexAdRight1", this.indexService.queryIndexAdRight1());
        mv.addObject("indexAdRight2", this.indexService.queryIndexAdRight2());
        mv.addObject("indexAdRight3", this.indexService.queryIndexAdRight3());
        mv.addObject("indexAdRight4", this.indexService.queryIndexAdRight4());
        mv.addObject("indexAdRight5", this.indexService.queryIndexAdRight5());
        
     // 右下角小图片
        List<Map<String, Object>> queryIndexAd6 = this.indexService.queryIndexAd6();
        try {
            if (queryIndexAd6.size()>0){
            String list1 = PackagingUtils.twoDataList(0, 1, queryIndexAd6);
            mv.addObject("list1", list1);
            String list2 = PackagingUtils.twoDataList(2, 3, queryIndexAd6);
            mv.addObject("list2", list2);
            String list3 = PackagingUtils.twoDataList(4, 5, queryIndexAd6);
            mv.addObject("list3", list3);
            String list4 = PackagingUtils.twoDataList(6, 7, queryIndexAd6);
            mv.addObject("list4", list4);
            String list5 = PackagingUtils.twoDataList(8, 9, queryIndexAd6);
            mv.addObject("list5", list5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

}
