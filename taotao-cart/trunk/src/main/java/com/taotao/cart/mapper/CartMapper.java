package com.taotao.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.taotao.cart.pojo.Cart;

public interface CartMapper extends Mapper<Cart>{
    public long delByItemIdsAndUserId(@Param("itemIds")List<Long> itemIds,@Param("userId")Long userId);
}
