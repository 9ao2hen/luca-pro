package cn.mervyn.luca.module.bpm.controller.admin.task.vo.instance;

import cn.mervyn.luca.framework.common.pojo.PageParam;
import cn.mervyn.luca.framework.common.validation.InEnum;
import cn.mervyn.luca.module.bpm.enums.task.BpmProcessInstanceStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.mervyn.luca.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 流程实例分页 Request VO")
@Data
public class BpmProcessInstancePageReqVO extends PageParam {

    @Schema(description = "流程名称", example = "墨文")
    private String name;

    @Schema(description = "流程定义的编号", example = "2048")
    private String processDefinitionId;

    @Schema(description = "流程实例的状态", example = "1")
    @InEnum(BpmProcessInstanceStatusEnum.class)
    private Integer status;

    @Schema(description = "流程分类", example = "1")
    private String category;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "发起用户编号", example = "1024")
    private Long startUserId; // 注意，只有在【流程实例】菜单，才使用该参数

}
