package cn.mervyn.luca.module.trade.job.brokerage;

import cn.hutool.core.util.StrUtil;
import cn.mervyn.luca.framework.quartz.core.handler.JobHandler;
import cn.mervyn.luca.framework.tenant.core.job.TenantJob;
import cn.mervyn.luca.module.trade.service.brokerage.BrokerageRecordService;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 佣金解冻 Job
 *
 * @author owen
 */
@Component
public class BrokerageRecordUnfreezeJob implements JobHandler {

    @Resource
    private BrokerageRecordService brokerageRecordService;

    @Override
    @TenantJob
    public String execute(String param) {
        int count = brokerageRecordService.unfreezeRecord();
        return StrUtil.format("解冻佣金 {} 个", count);
    }

}
