package cn.mervyn.luca.module.system.api.logger;

import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.framework.common.util.object.BeanUtils;
import cn.mervyn.luca.module.system.api.logger.dto.OperateLogCreateReqDTO;
import cn.mervyn.luca.module.system.api.logger.dto.OperateLogPageReqDTO;
import cn.mervyn.luca.module.system.api.logger.dto.OperateLogRespDTO;
import cn.mervyn.luca.module.system.dal.dataobject.logger.OperateLogDO;
import cn.mervyn.luca.module.system.service.logger.OperateLogService;
import com.fhs.core.trans.anno.TransMethodResult;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 操作日志 API 实现类
 *
 * @author 9ao2hen
 */
@Service
@Validated
public class OperateLogApiImpl implements OperateLogApi {

    @Resource
    private OperateLogService operateLogService;

    @Override
    @Async
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        operateLogService.createOperateLog(createReqDTO);
    }

    @Override
    @TransMethodResult
    public PageResult<OperateLogRespDTO> getOperateLogPage(OperateLogPageReqDTO pageReqDTO) {
        PageResult<OperateLogDO> operateLogPage = operateLogService.getOperateLogPage(pageReqDTO);
        return BeanUtils.toBean(operateLogPage, OperateLogRespDTO.class);
    }

}
