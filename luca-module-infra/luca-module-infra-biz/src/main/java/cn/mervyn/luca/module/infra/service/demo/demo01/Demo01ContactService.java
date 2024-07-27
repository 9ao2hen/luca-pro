package cn.mervyn.luca.module.infra.service.demo.demo01;

import jakarta.validation.*;

import cn.mervyn.luca.module.infra.controller.admin.demo.demo01.vo.Demo01ContactPageReqVO;
import cn.mervyn.luca.module.infra.controller.admin.demo.demo01.vo.Demo01ContactSaveReqVO;
import cn.mervyn.luca.module.infra.dal.dataobject.demo.demo01.Demo01ContactDO;
import cn.mervyn.luca.framework.common.pojo.PageResult;

/**
 * 示例联系人 Service 接口
 *
 * @author 9ao2hen
 */
public interface Demo01ContactService {

    /**
     * 创建示例联系人
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDemo01Contact(@Valid Demo01ContactSaveReqVO createReqVO);

    /**
     * 更新示例联系人
     *
     * @param updateReqVO 更新信息
     */
    void updateDemo01Contact(@Valid Demo01ContactSaveReqVO updateReqVO);

    /**
     * 删除示例联系人
     *
     * @param id 编号
     */
    void deleteDemo01Contact(Long id);

    /**
     * 获得示例联系人
     *
     * @param id 编号
     * @return 示例联系人
     */
    Demo01ContactDO getDemo01Contact(Long id);

    /**
     * 获得示例联系人分页
     *
     * @param pageReqVO 分页查询
     * @return 示例联系人分页
     */
    PageResult<Demo01ContactDO> getDemo01ContactPage(Demo01ContactPageReqVO pageReqVO);

}