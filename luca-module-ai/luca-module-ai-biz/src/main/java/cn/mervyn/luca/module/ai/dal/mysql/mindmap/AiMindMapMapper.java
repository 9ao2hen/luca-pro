package cn.mervyn.luca.module.ai.dal.mysql.mindmap;

import cn.mervyn.luca.framework.mybatis.core.mapper.BaseMapperX;
import cn.mervyn.luca.module.ai.dal.dataobject.mindmap.AiMindMapDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AI 思维导图 Mapper
 *
 * @author xiaoxin
 */
@Mapper
public interface AiMindMapMapper extends BaseMapperX<AiMindMapDO> {
}
