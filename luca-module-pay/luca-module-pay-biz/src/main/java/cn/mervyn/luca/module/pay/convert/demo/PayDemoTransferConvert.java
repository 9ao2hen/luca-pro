package cn.mervyn.luca.module.pay.convert.demo;

import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import cn.mervyn.luca.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferRespVO;
import cn.mervyn.luca.module.pay.dal.dataobject.demo.PayDemoTransferDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jason
 */
@Mapper
public interface PayDemoTransferConvert {

    PayDemoTransferConvert INSTANCE = Mappers.getMapper(PayDemoTransferConvert.class);

    PayDemoTransferDO convert(PayDemoTransferCreateReqVO bean);

    PageResult<PayDemoTransferRespVO> convertPage(PageResult<PayDemoTransferDO> pageResult);
}
