package com.itdom.cloud.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itdom.cloud.order.model.TOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TOrderMapper extends BaseMapper<TOrder> {

    /**
     * 创建订单
     *
     * @Param: order 订单信息
     * @Return:
     */
    void createOrder(@Param("order") TOrder order);
}