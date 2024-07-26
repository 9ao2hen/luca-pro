package cn.mervyn.luca.module.pay.convert.transfer;

import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.framework.pay.core.client.dto.transfer.PayTransferUnifiedReqDTO;
import cn.mervyn.luca.module.pay.api.transfer.dto.PayTransferCreateReqDTO;
import cn.mervyn.luca.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import cn.mervyn.luca.module.pay.controller.admin.transfer.vo.PayTransferCreateReqVO;
import cn.mervyn.luca.module.pay.controller.admin.transfer.vo.PayTransferPageItemRespVO;
import cn.mervyn.luca.module.pay.controller.admin.transfer.vo.PayTransferRespVO;
import cn.mervyn.luca.module.pay.dal.dataobject.transfer.PayTransferDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayTransferConvert {

    PayTransferConvert INSTANCE = Mappers.getMapper(PayTransferConvert.class);

    PayTransferDO convert(PayTransferCreateReqDTO dto);

    PayTransferUnifiedReqDTO convert2(PayTransferDO dto);

    PayTransferCreateReqDTO convert(PayTransferCreateReqVO vo);

    PayTransferCreateReqDTO convert(PayDemoTransferCreateReqVO vo);

    PayTransferRespVO  convert(PayTransferDO bean);

    PageResult<PayTransferPageItemRespVO> convertPage(PageResult<PayTransferDO> pageResult);
}
