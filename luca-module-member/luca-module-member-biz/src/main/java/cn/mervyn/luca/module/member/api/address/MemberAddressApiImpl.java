package cn.mervyn.luca.module.member.api.address;

import cn.mervyn.luca.module.member.api.address.dto.MemberAddressRespDTO;
import cn.mervyn.luca.module.member.convert.address.AddressConvert;
import cn.mervyn.luca.module.member.service.address.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 用户收件地址 API 实现类
 *
 * @author 9ao2hen
 */
@Service
@Validated
public class MemberAddressApiImpl implements MemberAddressApi {

    @Resource
    private AddressService addressService;

    @Override
    public MemberAddressRespDTO getAddress(Long id, Long userId) {
        return AddressConvert.INSTANCE.convert02(addressService.getAddress(userId, id));
    }

    @Override
    public MemberAddressRespDTO getDefaultAddress(Long userId) {
        return AddressConvert.INSTANCE.convert02(addressService.getDefaultUserAddress(userId));
    }

}
