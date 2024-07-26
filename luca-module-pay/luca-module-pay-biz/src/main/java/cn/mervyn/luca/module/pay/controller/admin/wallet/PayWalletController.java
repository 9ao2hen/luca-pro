package cn.mervyn.luca.module.pay.controller.admin.wallet;

import cn.mervyn.luca.framework.common.pojo.CommonResult;
import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.module.pay.controller.admin.wallet.vo.wallet.PayWalletPageReqVO;
import cn.mervyn.luca.module.pay.controller.admin.wallet.vo.wallet.PayWalletRespVO;
import cn.mervyn.luca.module.pay.controller.admin.wallet.vo.wallet.PayWalletUserReqVO;
import cn.mervyn.luca.module.pay.convert.wallet.PayWalletConvert;
import cn.mervyn.luca.module.pay.dal.dataobject.wallet.PayWalletDO;
import cn.mervyn.luca.module.pay.service.wallet.PayWalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.mervyn.luca.framework.common.enums.UserTypeEnum.MEMBER;
import static cn.mervyn.luca.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 用户钱包")
@RestController
@RequestMapping("/pay/wallet")
@Validated
@Slf4j
public class PayWalletController {

    @Resource
    private PayWalletService payWalletService;

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('pay:wallet:query')")
    @Operation(summary = "获得用户钱包明细")
    public CommonResult<PayWalletRespVO> getWallet(PayWalletUserReqVO reqVO) {
        PayWalletDO wallet = payWalletService.getOrCreateWallet(reqVO.getUserId(), MEMBER.getValue());
        return success(PayWalletConvert.INSTANCE.convert02(wallet));
    }

    @GetMapping("/page")
    @Operation(summary = "获得会员钱包分页")
    @PreAuthorize("@ss.hasPermission('pay:wallet:query')")
    public CommonResult<PageResult<PayWalletRespVO>> getWalletPage(@Valid PayWalletPageReqVO pageVO) {
        PageResult<PayWalletDO> pageResult = payWalletService.getWalletPage(pageVO);
        return success(PayWalletConvert.INSTANCE.convertPage(pageResult));
    }

}
