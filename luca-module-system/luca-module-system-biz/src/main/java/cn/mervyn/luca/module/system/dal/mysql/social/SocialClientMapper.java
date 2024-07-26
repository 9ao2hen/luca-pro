package cn.mervyn.luca.module.system.dal.mysql.social;

import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.framework.mybatis.core.mapper.BaseMapperX;
import cn.mervyn.luca.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.mervyn.luca.module.system.controller.admin.socail.vo.client.SocialClientPageReqVO;
import cn.mervyn.luca.module.system.dal.dataobject.social.SocialClientDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SocialClientMapper extends BaseMapperX<SocialClientDO> {

    default SocialClientDO selectBySocialTypeAndUserType(Integer socialType, Integer userType) {
        return selectOne(SocialClientDO::getSocialType, socialType,
                SocialClientDO::getUserType, userType);
    }

    default PageResult<SocialClientDO> selectPage(SocialClientPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SocialClientDO>()
                .likeIfPresent(SocialClientDO::getName, reqVO.getName())
                .eqIfPresent(SocialClientDO::getSocialType, reqVO.getSocialType())
                .eqIfPresent(SocialClientDO::getUserType, reqVO.getUserType())
                .likeIfPresent(SocialClientDO::getClientId, reqVO.getClientId())
                .eqIfPresent(SocialClientDO::getStatus, reqVO.getStatus())
                .orderByDesc(SocialClientDO::getId));
    }

}
