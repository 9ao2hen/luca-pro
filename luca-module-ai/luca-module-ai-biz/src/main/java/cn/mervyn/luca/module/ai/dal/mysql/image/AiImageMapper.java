package cn.mervyn.luca.module.ai.dal.mysql.image;

import cn.mervyn.luca.framework.common.pojo.PageParam;
import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.framework.mybatis.core.mapper.BaseMapperX;
import cn.mervyn.luca.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.mervyn.luca.module.ai.controller.admin.image.vo.AiImagePageReqVO;
import cn.mervyn.luca.module.ai.dal.dataobject.image.AiImageDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AI 绘图 Mapper
 *
 * @author fansili
 */
@Mapper
public interface AiImageMapper extends BaseMapperX<AiImageDO> {

    default AiImageDO selectByTaskId(String taskId) {
        return this.selectOne(AiImageDO::getTaskId, taskId);
    }

    default PageResult<AiImageDO> selectPage(AiImagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiImageDO>()
                .eqIfPresent(AiImageDO::getUserId, reqVO.getUserId())
                .eqIfPresent(AiImageDO::getPlatform, reqVO.getPlatform())
                .eqIfPresent(AiImageDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AiImageDO::getPublicStatus, reqVO.getPublicStatus())
                .betweenIfPresent(AiImageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiImageDO::getId));
    }

    default PageResult<AiImageDO> selectPage(Long userId, PageParam pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<AiImageDO>()
                .eq(AiImageDO::getUserId, userId)
                .orderByDesc(AiImageDO::getId));
    }

    default List<AiImageDO> selectListByStatusAndPlatform(Integer status, String platform) {
        return selectList(AiImageDO::getStatus, status,
                AiImageDO::getPlatform, platform);
    }

}
