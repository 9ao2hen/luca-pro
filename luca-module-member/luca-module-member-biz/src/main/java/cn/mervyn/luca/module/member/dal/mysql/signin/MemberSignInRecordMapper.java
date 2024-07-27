package cn.mervyn.luca.module.member.dal.mysql.signin;

import cn.mervyn.luca.framework.common.pojo.PageParam;
import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.framework.mybatis.core.mapper.BaseMapperX;
import cn.mervyn.luca.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.mervyn.luca.module.member.controller.admin.signin.vo.record.MemberSignInRecordPageReqVO;
import cn.mervyn.luca.module.member.dal.dataobject.signin.MemberSignInRecordDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * 签到记录 Mapper
 *
 * @author 9ao2hen
 */
@Mapper
public interface MemberSignInRecordMapper extends BaseMapperX<MemberSignInRecordDO> {

    default PageResult<MemberSignInRecordDO> selectPage(MemberSignInRecordPageReqVO reqVO, Set<Long> userIds) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberSignInRecordDO>()
                .inIfPresent(MemberSignInRecordDO::getUserId, userIds)
                .eqIfPresent(MemberSignInRecordDO::getUserId, reqVO.getUserId())
                .eqIfPresent(MemberSignInRecordDO::getDay, reqVO.getDay())
                .betweenIfPresent(MemberSignInRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MemberSignInRecordDO::getId));
    }

    default PageResult<MemberSignInRecordDO> selectPage(Long userId, PageParam pageParam) {
        return selectPage(pageParam, new LambdaQueryWrapperX<MemberSignInRecordDO>()
                .eq(MemberSignInRecordDO::getUserId, userId)
                .orderByDesc(MemberSignInRecordDO::getId));
    }

    /**
     * 获取用户最近的签到记录信息，根据签到时间倒序
     *
     * @param userId 用户编号
     * @return 签到记录列表
     */
    default MemberSignInRecordDO selectLastRecordByUserId(Long userId) {
        return selectOne(new QueryWrapper<MemberSignInRecordDO>()
                .eq("user_id", userId)
                .orderByDesc("create_time")
                .last("limit 1"));
    }

    default Long selectCountByUserId(Long userId) {
        return selectCount(MemberSignInRecordDO::getUserId, userId);
    }

    /**
     * 获取用户的签到记录列表信息
     *
     * @param userId 用户编号
     * @return 签到记录信息
     */
    default List<MemberSignInRecordDO> selectListByUserId(Long userId) {
        return selectList(MemberSignInRecordDO::getUserId, userId);
    }

}
