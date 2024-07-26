package cn.mervyn.luca.module.erp.framework.web.config;

import cn.mervyn.luca.framework.swagger.config.LucaSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * erp 模块的 web 组件的 Configuration
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false)
public class ErpWebConfiguration {

    /**
     * erp 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi erpGroupedOpenApi() {
        return LucaSwaggerAutoConfiguration.buildGroupedOpenApi("erp");
    }

}
