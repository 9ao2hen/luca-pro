package cn.mervyn.luca.module.pay.api.refund.dto;

import cn.mervyn.luca.module.pay.enums.refund.PayRefundStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 退款单信息 Response DTO
 *
 * @author 9ao2hen
 */
@Data
public class PayRefundRespDTO {

    /**
     * 退款单编号
     */
    private Long id;

    // ========== 退款相关字段 ==========
    /**
     * 退款状态
     *
     * 枚举 {@link PayRefundStatusEnum}
     */
    private Integer status;
    /**
     * 退款金额，单位：分
     */
    private Integer refundPrice;

    // ========== 商户相关字段 ==========
    /**
     * 商户订单编号
     */
    private String merchantOrderId;
    /**
     * 退款成功时间
     */
    private LocalDateTime successTime;

}
