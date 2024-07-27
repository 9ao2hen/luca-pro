package cn.mervyn.luca.module.erp.dal.redis;

/**
 * ERP Redis Key 枚举类
 *
 * @author 9ao2hen
 */
public interface RedisKeyConstants {

    /**
     * 序号的缓存
     *
     * KEY 格式：trade_no:{prefix}
     * VALUE 数据格式：编号自增
     */
    String NO = "erp:seq_no:";

}
