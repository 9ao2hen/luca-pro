package cn.mervyn.luca.module.promotion.convert.reward;

import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.module.promotion.controller.admin.reward.vo.RewardActivityCreateReqVO;
import cn.mervyn.luca.module.promotion.controller.admin.reward.vo.RewardActivityRespVO;
import cn.mervyn.luca.module.promotion.controller.admin.reward.vo.RewardActivityUpdateReqVO;
import cn.mervyn.luca.module.promotion.dal.dataobject.reward.RewardActivityDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 满减送活动 Convert
 *
 * @author 9ao2hen
 */
@Mapper
public interface RewardActivityConvert {

    RewardActivityConvert INSTANCE = Mappers.getMapper(RewardActivityConvert.class);

    RewardActivityDO convert(RewardActivityCreateReqVO bean);

    RewardActivityDO convert(RewardActivityUpdateReqVO bean);

    RewardActivityRespVO convert(RewardActivityDO bean);

    PageResult<RewardActivityRespVO> convertPage(PageResult<RewardActivityDO> page);

}
