package cn.mervyn.luca.framework.pay.config;

import cn.mervyn.luca.framework.pay.core.client.PayClientFactory;
import cn.mervyn.luca.framework.pay.core.client.impl.PayClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 支付配置类
 *
 * @author 9ao2hen
 */
@AutoConfiguration
public class LucaPayAutoConfiguration {

    @Bean
    public PayClientFactory payClientFactory() {
        return new PayClientFactoryImpl();
    }

}
