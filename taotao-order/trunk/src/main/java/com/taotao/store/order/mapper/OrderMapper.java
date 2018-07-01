package com.taotao.store.order.mapper;

import java.util.Date;




import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.taotao.store.order.bean.Where;
import com.taotao.store.order.pojo.Order;

public interface OrderMapper extends IMapper<Order>{
    
	
    public void paymentOrderScan(@Param("date") Date date);
    
    public PageList<Order> queryListByWhere1(PageBounds bounds, @Param("build1") Where build1,
            @Param("build2") Where build2);
    public PageList<Order> queryListByWhere2(PageBounds bounds, @Param("where")Where where);
    public PageList<Order> queryListByWhere3(PageBounds bounds, @Param("where")Where where);
    public PageList<Order> queryListByWhere4(PageBounds bounds, @Param("where")Where where);
    public PageList<Order> queryListByWhere5(PageBounds bounds, @Param("where")Where where);
}
