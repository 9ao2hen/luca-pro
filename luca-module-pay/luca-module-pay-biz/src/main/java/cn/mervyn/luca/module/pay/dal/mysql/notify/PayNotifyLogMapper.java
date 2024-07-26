package cn.mervyn.luca.module.pay.dal.mysql.notify;

import cn.mervyn.luca.module.pay.dal.dataobject.notify.PayNotifyLogDO;
import cn.mervyn.luca.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayNotifyLogMapper extends BaseMapperX<PayNotifyLogDO> {

    default List<PayNotifyLogDO> selectListByTaskId(Long taskId) {
        return selectList(PayNotifyLogDO::getTaskId, taskId);
    }

}
