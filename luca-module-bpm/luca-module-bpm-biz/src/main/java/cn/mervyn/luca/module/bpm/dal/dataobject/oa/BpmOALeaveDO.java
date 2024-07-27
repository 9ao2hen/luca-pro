package cn.mervyn.luca.module.bpm.dal.dataobject.oa;

import cn.mervyn.luca.framework.mybatis.core.dataobject.BaseDO;
import cn.mervyn.luca.module.bpm.enums.task.BpmTaskStatusEnum;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * OA 请假申请 DO
 *
 * {@link #day} 请假天数，目前先简单做。一般是分成请假上午和下午，可以是 1 整天，可以是 0.5 半天
 *
 * @author jason
 * @author 9ao2hen
 */
@TableName("bpm_oa_leave")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BpmOALeaveDO extends BaseDO {

    /**
     * 请假表单主键
     */
    @TableId
    private Long id;
    /**
     * 申请人的用户编号
     *
     * 关联 AdminUserDO 的 id 属性
     */
    private Long userId;
    /**
     * 请假类型
     */
    private String type;
    /**
     * 原因
     */
    private String reason;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 请假天数
     */
    private Long day;
    /**
     * 审批结果
     *
     * 枚举 {@link BpmTaskStatusEnum}
     * 考虑到简单，所以直接复用了 BpmProcessInstanceStatusEnum 枚举，也可以自己定义一个枚举哈
     */
    private Integer status;

    /**
     * 对应的流程编号
     *
     * 关联 ProcessInstance 的 id 属性
     */
    private String processInstanceId;

}
