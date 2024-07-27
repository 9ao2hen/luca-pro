package cn.mervyn.luca.module.infra.framework.file.config;

import cn.mervyn.luca.module.infra.framework.file.core.client.FileClientFactory;
import cn.mervyn.luca.module.infra.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件配置类
 *
 * @author 9ao2hen
 */
@Configuration(proxyBeanMethods = false)
public class LucaFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
