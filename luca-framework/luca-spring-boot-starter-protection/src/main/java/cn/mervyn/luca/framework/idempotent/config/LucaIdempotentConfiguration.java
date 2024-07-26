package cn.mervyn.luca.framework.idempotent.config;

import cn.mervyn.luca.framework.idempotent.core.aop.IdempotentAspect;
import cn.mervyn.luca.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import cn.mervyn.luca.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import cn.mervyn.luca.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import cn.mervyn.luca.framework.idempotent.core.keyresolver.impl.UserIdempotentKeyResolver;
import cn.mervyn.luca.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import cn.mervyn.luca.framework.redis.config.LucaRedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = LucaRedisAutoConfiguration.class)
public class LucaIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public UserIdempotentKeyResolver userIdempotentKeyResolver() {
        return new UserIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
