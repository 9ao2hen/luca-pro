package cn.mervyn.luca.module.system.framework.web.config;

import cn.mervyn.luca.framework.swagger.config.LucaSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * system 模块的 web 组件的 Configuration
 *
 * @author 9ao2hen
 */
@Configuration(proxyBeanMethods = false)
public class SystemWebConfiguration {

    /**
     * system 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi systemGroupedOpenApi() {
        return LucaSwaggerAutoConfiguration.buildGroupedOpenApi("system");
    }

}
