package cn.mervyn.luca.framework.apilog.config;

import cn.mervyn.luca.framework.apilog.core.filter.ApiAccessLogFilter;
import cn.mervyn.luca.framework.apilog.core.interceptor.ApiAccessLogInterceptor;
import cn.mervyn.luca.framework.apilog.core.service.ApiAccessLogFrameworkService;
import cn.mervyn.luca.framework.apilog.core.service.ApiAccessLogFrameworkServiceImpl;
import cn.mervyn.luca.framework.apilog.core.service.ApiErrorLogFrameworkService;
import cn.mervyn.luca.framework.apilog.core.service.ApiErrorLogFrameworkServiceImpl;
import cn.mervyn.luca.framework.common.enums.WebFilterOrderEnum;
import cn.mervyn.luca.framework.web.config.WebProperties;
import cn.mervyn.luca.framework.web.config.LucaWebAutoConfiguration;
import cn.mervyn.luca.module.infra.api.logger.ApiAccessLogApi;
import cn.mervyn.luca.module.infra.api.logger.ApiErrorLogApi;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AutoConfiguration(after = LucaWebAutoConfiguration.class)
public class LucaApiLogAutoConfiguration implements WebMvcConfigurer {

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ApiAccessLogFrameworkService apiAccessLogFrameworkService(ApiAccessLogApi apiAccessLogApi) {
        return new ApiAccessLogFrameworkServiceImpl(apiAccessLogApi);
    }

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ApiErrorLogFrameworkService apiErrorLogFrameworkService(ApiErrorLogApi apiErrorLogApi) {
        return new ApiErrorLogFrameworkServiceImpl(apiErrorLogApi);
    }

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     */
    @Bean
    @ConditionalOnProperty(prefix = "luca.access-log", value = "enable", matchIfMissing = true) // 允许使用 luca.access-log.enable=false 禁用访问日志
    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
                                                                         @Value("${spring.application.name}") String applicationName,
                                                                         ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiAccessLogInterceptor());
    }

}
