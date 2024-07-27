package cn.mervyn.luca.module.promotion.enums.common;

import cn.mervyn.luca.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 营销的条件类型枚举
 *
 * @author 9ao2hen
 */
@AllArgsConstructor
@Getter
public enum PromotionConditionTypeEnum implements IntArrayValuable {

    PRICE(10, "满 N 元"),
    COUNT(20, "满 N 件");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(PromotionConditionTypeEnum::getType).toArray();

    /**
     * 类型值
     */
    private final Integer type;
    /**
     * 类型名
     */
    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
