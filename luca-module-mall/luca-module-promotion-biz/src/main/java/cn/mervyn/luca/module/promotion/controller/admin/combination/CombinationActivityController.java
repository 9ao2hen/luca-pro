package cn.mervyn.luca.module.promotion.controller.admin.combination;

import cn.hutool.core.collection.CollUtil;
import cn.mervyn.luca.framework.common.pojo.CommonResult;
import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.module.product.api.spu.ProductSpuApi;
import cn.mervyn.luca.module.product.api.spu.dto.ProductSpuRespDTO;
import cn.mervyn.luca.module.promotion.controller.admin.combination.vo.activity.*;
import cn.mervyn.luca.module.promotion.convert.combination.CombinationActivityConvert;
import cn.mervyn.luca.module.promotion.dal.dataobject.combination.CombinationActivityDO;
import cn.mervyn.luca.module.promotion.dal.dataobject.combination.CombinationProductDO;
import cn.mervyn.luca.module.promotion.dal.dataobject.combination.CombinationRecordDO;
import cn.mervyn.luca.module.promotion.enums.combination.CombinationRecordStatusEnum;
import cn.mervyn.luca.module.promotion.service.combination.CombinationActivityService;
import cn.mervyn.luca.module.promotion.service.combination.CombinationRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.hutool.core.collection.CollectionUtil.newArrayList;
import static cn.mervyn.luca.framework.common.pojo.CommonResult.success;
import static cn.mervyn.luca.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "管理后台 - 拼团活动")
@RestController
@RequestMapping("/promotion/combination-activity")
@Validated
public class CombinationActivityController {

    @Resource
    private CombinationActivityService combinationActivityService;
    @Resource
    private CombinationRecordService combinationRecordService;

    @Resource
    private ProductSpuApi productSpuApi;

    @PostMapping("/create")
    @Operation(summary = "创建拼团活动")
    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:create')")
    public CommonResult<Long> createCombinationActivity(@Valid @RequestBody CombinationActivityCreateReqVO createReqVO) {
        return success(combinationActivityService.createCombinationActivity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新拼团活动")
    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:update')")
    public CommonResult<Boolean> updateCombinationActivity(@Valid @RequestBody CombinationActivityUpdateReqVO updateReqVO) {
        combinationActivityService.updateCombinationActivity(updateReqVO);
        return success(true);
    }

    @PutMapping("/close")
    @Operation(summary = "关闭拼团活动")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:close')")
    public CommonResult<Boolean> closeCombinationActivity(@RequestParam("id") Long id) {
        combinationActivityService.closeCombinationActivityById(id);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除拼团活动")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:delete')")
    public CommonResult<Boolean> deleteCombinationActivity(@RequestParam("id") Long id) {
        combinationActivityService.deleteCombinationActivity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得拼团活动")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:query')")
    public CommonResult<CombinationActivityRespVO> getCombinationActivity(@RequestParam("id") Long id) {
        CombinationActivityDO activity = combinationActivityService.getCombinationActivity(id);
        List<CombinationProductDO> products = combinationActivityService.getCombinationProductListByActivityIds(newArrayList(id));
        return success(CombinationActivityConvert.INSTANCE.convert(activity, products));
    }

    @GetMapping("/page")
    @Operation(summary = "获得拼团活动分页")
    @PreAuthorize("@ss.hasPermission('promotion:combination-activity:query')")
    public CommonResult<PageResult<CombinationActivityPageItemRespVO>> getCombinationActivityPage(
            @Valid CombinationActivityPageReqVO pageVO) {
        // 查询拼团活动
        PageResult<CombinationActivityDO> pageResult = combinationActivityService.getCombinationActivityPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 统计数据
        Set<Long> activityIds = convertSet(pageResult.getList(), CombinationActivityDO::getId);
        Map<Long, Integer> groupCountMap = combinationRecordService.getCombinationRecordCountMapByActivity(
                activityIds, null, CombinationRecordDO.HEAD_ID_GROUP);
        Map<Long, Integer> groupSuccessCountMap = combinationRecordService.getCombinationRecordCountMapByActivity(
                activityIds, CombinationRecordStatusEnum.SUCCESS.getStatus(), CombinationRecordDO.HEAD_ID_GROUP);
        Map<Long, Integer> recordCountMap = combinationRecordService.getCombinationRecordCountMapByActivity(
                activityIds, null, null);
        // 拼接数据
        List<CombinationProductDO> products = combinationActivityService.getCombinationProductListByActivityIds(
                convertSet(pageResult.getList(), CombinationActivityDO::getId));
        List<ProductSpuRespDTO> spus = productSpuApi.getSpuList(
                convertSet(pageResult.getList(), CombinationActivityDO::getSpuId));
        return success(CombinationActivityConvert.INSTANCE.convertPage(pageResult, products,
                groupCountMap, groupSuccessCountMap, recordCountMap, spus));
    }

}
