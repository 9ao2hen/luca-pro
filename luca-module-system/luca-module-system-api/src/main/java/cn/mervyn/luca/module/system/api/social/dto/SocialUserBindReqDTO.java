package cn.mervyn.luca.module.system.api.social.dto;

import cn.mervyn.luca.framework.common.enums.UserTypeEnum;
import cn.mervyn.luca.framework.common.validation.InEnum;
import cn.mervyn.luca.module.system.enums.social.SocialTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * 取消绑定社交用户 Request DTO
 *
 * @author 9ao2hen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUserBindReqDTO {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;
    /**
     * 用户类型
     */
    @InEnum(UserTypeEnum.class)
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    /**
     * 社交平台的类型
     */
    @InEnum(SocialTypeEnum.class)
    @NotNull(message = "社交平台的类型不能为空")
    private Integer socialType;
    /**
     * 授权码
     */
    @NotEmpty(message = "授权码不能为空")
    private String code;
    /**
     * state
     */
    @NotNull(message = "state 不能为空")
    private String state;

}
