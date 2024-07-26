package cn.mervyn.luca.module.trade.convert.order;

import cn.mervyn.luca.module.trade.dal.dataobject.order.TradeOrderLogDO;
import cn.mervyn.luca.module.trade.service.order.bo.TradeOrderLogCreateReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TradeOrderLogConvert {

    TradeOrderLogConvert INSTANCE = Mappers.getMapper(TradeOrderLogConvert.class);

    TradeOrderLogDO convert(TradeOrderLogCreateReqBO bean);

}
