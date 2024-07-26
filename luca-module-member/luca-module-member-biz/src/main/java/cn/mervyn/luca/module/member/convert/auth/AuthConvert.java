package cn.mervyn.luca.module.member.convert.auth;

import cn.mervyn.luca.module.member.controller.app.auth.vo.*;
import cn.mervyn.luca.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import cn.mervyn.luca.module.member.controller.app.user.vo.AppMemberUserResetPasswordReqVO;
import cn.mervyn.luca.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import cn.mervyn.luca.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import cn.mervyn.luca.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import cn.mervyn.luca.module.system.api.sms.dto.code.SmsCodeValidateReqDTO;
import cn.mervyn.luca.module.system.api.social.dto.SocialUserBindReqDTO;
import cn.mervyn.luca.module.system.api.social.dto.SocialUserUnbindReqDTO;
import cn.mervyn.luca.module.system.api.social.dto.SocialWxJsapiSignatureRespDTO;
import cn.mervyn.luca.module.system.enums.sms.SmsSceneEnum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    SocialUserBindReqDTO convert(Long userId, Integer userType, AppAuthSocialLoginReqVO reqVO);
    SocialUserUnbindReqDTO convert(Long userId, Integer userType, AppSocialUserUnbindReqVO reqVO);

    SmsCodeSendReqDTO convert(AppAuthSmsSendReqVO reqVO);
    SmsCodeUseReqDTO convert(AppMemberUserResetPasswordReqVO reqVO, SmsSceneEnum scene, String usedIp);
    SmsCodeUseReqDTO convert(AppAuthSmsLoginReqVO reqVO, Integer scene, String usedIp);

    AppAuthLoginRespVO convert(OAuth2AccessTokenRespDTO bean, String openid);

    SmsCodeValidateReqDTO convert(AppAuthSmsValidateReqVO bean);

    SocialWxJsapiSignatureRespDTO convert(SocialWxJsapiSignatureRespDTO bean);

}
