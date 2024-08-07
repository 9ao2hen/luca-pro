package cn.mervyn.luca.module.pay.service.app;

import cn.mervyn.luca.framework.common.enums.CommonStatusEnum;
import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.module.pay.controller.admin.app.vo.PayAppCreateReqVO;
import cn.mervyn.luca.module.pay.controller.admin.app.vo.PayAppPageReqVO;
import cn.mervyn.luca.module.pay.controller.admin.app.vo.PayAppUpdateReqVO;
import cn.mervyn.luca.module.pay.convert.app.PayAppConvert;
import cn.mervyn.luca.module.pay.dal.dataobject.app.PayAppDO;
import cn.mervyn.luca.module.pay.dal.mysql.app.PayAppMapper;
import cn.mervyn.luca.module.pay.enums.ErrorCodeConstants;
import cn.mervyn.luca.module.pay.service.order.PayOrderService;
import cn.mervyn.luca.module.pay.service.refund.PayRefundService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.mervyn.luca.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.mervyn.luca.module.pay.enums.ErrorCodeConstants.*;

/**
 * 支付应用 Service 实现类
 *
 * @author aquan
 */
@Service
@Validated
public class PayAppServiceImpl implements PayAppService {

    @Resource
    private PayAppMapper appMapper;

    @Resource
    @Lazy // 延迟加载，避免循环依赖报错
    private PayOrderService orderService;
    @Resource
    @Lazy // 延迟加载，避免循环依赖报错
    private PayRefundService refundService;

    @Override
    public Long createApp(PayAppCreateReqVO createReqVO) {
        // 插入
        PayAppDO app = PayAppConvert.INSTANCE.convert(createReqVO);
        appMapper.insert(app);
        // 返回
        return app.getId();
    }

    @Override
    public void updateApp(PayAppUpdateReqVO updateReqVO) {
        // 校验存在
        validateAppExists(updateReqVO.getId());
        // 更新
        PayAppDO updateObj = PayAppConvert.INSTANCE.convert(updateReqVO);
        appMapper.updateById(updateObj);
    }

    @Override
    public void updateAppStatus(Long id, Integer status) {
        // 校验商户存在
        validateAppExists(id);
        // 更新状态
        appMapper.updateById(new PayAppDO().setId(id).setStatus(status));
    }

    @Override
    public void deleteApp(Long id) {
        // 校验存在
        validateAppExists(id);
        // 校验关联数据是否存在
        if (orderService.getOrderCountByAppId(id) > 0) {
            throw exception(APP_EXIST_ORDER_CANT_DELETE);
        }
        if (refundService.getRefundCountByAppId(id) > 0) {
            throw exception(APP_EXIST_REFUND_CANT_DELETE);
        }

        // 删除
        appMapper.deleteById(id);
    }

    private void validateAppExists(Long id) {
        if (appMapper.selectById(id) == null) {
            throw exception(APP_NOT_FOUND);
        }
    }

    @Override
    public PayAppDO getApp(Long id) {
        return appMapper.selectById(id);
    }

    @Override
    public List<PayAppDO> getAppList(Collection<Long> ids) {
        return appMapper.selectBatchIds(ids);
    }

    @Override
    public List<PayAppDO> getAppList() {
         return appMapper.selectList();
    }

    @Override
    public PageResult<PayAppDO> getAppPage(PayAppPageReqVO pageReqVO) {
        return appMapper.selectPage(pageReqVO);
    }

    @Override
    public PayAppDO validPayApp(Long id) {
        PayAppDO app = appMapper.selectById(id);
        // 校验是否存在
        if (app == null) {
            throw exception(ErrorCodeConstants.APP_NOT_FOUND);
        }
        // 校验是否禁用
        if (CommonStatusEnum.DISABLE.getStatus().equals(app.getStatus())) {
            throw exception(ErrorCodeConstants.APP_IS_DISABLE);
        }
        return app;
    }

}
