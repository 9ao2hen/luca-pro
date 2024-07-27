package cn.mervyn.luca.module.promotion.api.discount;

import cn.mervyn.luca.module.promotion.api.discount.dto.DiscountProductRespDTO;

import java.util.Collection;
import java.util.List;

/**
 * 限时折扣 API 接口
 *
 * @author 9ao2hen
 */
public interface DiscountActivityApi {

    /**
     * 获得商品匹配的的限时折扣信息
     *
     * @param skuIds 商品 SKU 编号数组
     * @return 限时折扣信息
     */
    List<DiscountProductRespDTO> getMatchDiscountProductList(Collection<Long> skuIds);

}
