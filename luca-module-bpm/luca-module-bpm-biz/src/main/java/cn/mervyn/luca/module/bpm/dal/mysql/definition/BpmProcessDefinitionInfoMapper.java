package cn.mervyn.luca.module.bpm.dal.mysql.definition;

import cn.mervyn.luca.module.bpm.dal.dataobject.definition.BpmProcessDefinitionInfoDO;
import cn.mervyn.luca.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface BpmProcessDefinitionInfoMapper extends BaseMapperX<BpmProcessDefinitionInfoDO> {

    default List<BpmProcessDefinitionInfoDO> selectListByProcessDefinitionIds(Collection<String> processDefinitionIds) {
        return selectList(BpmProcessDefinitionInfoDO::getProcessDefinitionId, processDefinitionIds);
    }

    default BpmProcessDefinitionInfoDO selectByProcessDefinitionId(String processDefinitionId) {
        return selectOne(BpmProcessDefinitionInfoDO::getProcessDefinitionId, processDefinitionId);
    }

}
