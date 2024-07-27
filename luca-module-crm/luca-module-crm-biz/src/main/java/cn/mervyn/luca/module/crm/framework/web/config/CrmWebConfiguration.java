package cn.mervyn.luca.module.crm.framework.web.config;

import cn.mervyn.luca.framework.swagger.config.LucaSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * crm 模块的 web 组件的 Configuration
 *
 * @author 9ao2hen
 */
@Configuration(proxyBeanMethods = false)
public class CrmWebConfiguration {

    /**
     * crm 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi crmGroupedOpenApi() {
        return LucaSwaggerAutoConfiguration.buildGroupedOpenApi("crm");
    }

}
