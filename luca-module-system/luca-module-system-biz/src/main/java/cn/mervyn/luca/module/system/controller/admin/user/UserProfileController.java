package cn.mervyn.luca.module.system.controller.admin.user;

import cn.hutool.core.collection.CollUtil;
import cn.mervyn.luca.framework.common.enums.UserTypeEnum;
import cn.mervyn.luca.framework.common.pojo.CommonResult;
import cn.mervyn.luca.framework.datapermission.core.annotation.DataPermission;
import cn.mervyn.luca.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import cn.mervyn.luca.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import cn.mervyn.luca.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import cn.mervyn.luca.module.system.convert.user.UserConvert;
import cn.mervyn.luca.module.system.dal.dataobject.dept.DeptDO;
import cn.mervyn.luca.module.system.dal.dataobject.dept.PostDO;
import cn.mervyn.luca.module.system.dal.dataobject.permission.RoleDO;
import cn.mervyn.luca.module.system.dal.dataobject.social.SocialUserDO;
import cn.mervyn.luca.module.system.dal.dataobject.user.AdminUserDO;
import cn.mervyn.luca.module.system.service.dept.DeptService;
import cn.mervyn.luca.module.system.service.dept.PostService;
import cn.mervyn.luca.module.system.service.permission.PermissionService;
import cn.mervyn.luca.module.system.service.permission.RoleService;
import cn.mervyn.luca.module.system.service.social.SocialUserService;
import cn.mervyn.luca.module.system.service.user.AdminUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.List;

import static cn.mervyn.luca.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.mervyn.luca.framework.common.pojo.CommonResult.success;
import static cn.mervyn.luca.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.mervyn.luca.module.infra.enums.ErrorCodeConstants.FILE_IS_EMPTY;

@Tag(name = "管理后台 - 用户个人中心")
@RestController
@RequestMapping("/system/user/profile")
@Validated
@Slf4j
public class UserProfileController {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PostService postService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;
    @Resource
    private SocialUserService socialService;

    @GetMapping("/get")
    @Operation(summary = "获得登录用户信息")
    @DataPermission(enable = false) // 关闭数据权限，避免只查看自己时，查询不到部门。
    public CommonResult<UserProfileRespVO> getUserProfile() {
        // 获得用户基本信息
        AdminUserDO user = userService.getUser(getLoginUserId());
        // 获得用户角色
        List<RoleDO> userRoles = roleService.getRoleListFromCache(permissionService.getUserRoleIdListByUserId(user.getId()));
        // 获得部门信息
        DeptDO dept = user.getDeptId() != null ? deptService.getDept(user.getDeptId()) : null;
        // 获得岗位信息
        List<PostDO> posts = CollUtil.isNotEmpty(user.getPostIds()) ? postService.getPostList(user.getPostIds()) : null;
        // 获得社交用户信息
        List<SocialUserDO> socialUsers = socialService.getSocialUserList(user.getId(), UserTypeEnum.ADMIN.getValue());
        return success(UserConvert.INSTANCE.convert(user, userRoles, dept, posts, socialUsers));
    }

    @PutMapping("/update")
    @Operation(summary = "修改用户个人信息")
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateReqVO reqVO) {
        userService.updateUserProfile(getLoginUserId(), reqVO);
        return success(true);
    }

    @PutMapping("/update-password")
    @Operation(summary = "修改用户个人密码")
    public CommonResult<Boolean> updateUserProfilePassword(@Valid @RequestBody UserProfileUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(getLoginUserId(), reqVO);
        return success(true);
    }

    @RequestMapping(value = "/update-avatar",
            method = {RequestMethod.POST, RequestMethod.PUT}) // 解决 uni-app 不支持 Put 上传文件的问题
    @Operation(summary = "上传用户个人头像")
    public CommonResult<String> updateUserAvatar(@RequestParam("avatarFile") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw exception(FILE_IS_EMPTY);
        }
        String avatar = userService.updateUserAvatar(getLoginUserId(), file.getInputStream());
        return success(avatar);
    }

}
