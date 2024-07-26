package cn.mervyn.luca.module.trade.dal.mysql.order;

import cn.mervyn.luca.framework.mybatis.core.mapper.BaseMapperX;
import cn.mervyn.luca.module.trade.dal.dataobject.order.TradeOrderLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeOrderLogMapper extends BaseMapperX<TradeOrderLogDO> {

    default List<TradeOrderLogDO> selectListByOrderId(Long orderId) {
        return selectList(TradeOrderLogDO::getOrderId, orderId);
    }

}
