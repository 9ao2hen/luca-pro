package cn.mervyn.luca.module.member.convert.tag;

import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.module.member.controller.admin.tag.vo.MemberTagCreateReqVO;
import cn.mervyn.luca.module.member.controller.admin.tag.vo.MemberTagRespVO;
import cn.mervyn.luca.module.member.controller.admin.tag.vo.MemberTagUpdateReqVO;
import cn.mervyn.luca.module.member.dal.dataobject.tag.MemberTagDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 会员标签 Convert
 *
 * @author 9ao2hen
 */
@Mapper
public interface MemberTagConvert {

    MemberTagConvert INSTANCE = Mappers.getMapper(MemberTagConvert.class);

    MemberTagDO convert(MemberTagCreateReqVO bean);

    MemberTagDO convert(MemberTagUpdateReqVO bean);

    MemberTagRespVO convert(MemberTagDO bean);

    List<MemberTagRespVO> convertList(List<MemberTagDO> list);

    PageResult<MemberTagRespVO> convertPage(PageResult<MemberTagDO> page);

}
