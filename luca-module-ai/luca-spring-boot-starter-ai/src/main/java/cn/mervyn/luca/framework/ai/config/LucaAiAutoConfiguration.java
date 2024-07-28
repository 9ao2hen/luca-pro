package cn.mervyn.luca.framework.ai.config;

import cn.mervyn.luca.framework.ai.core.factory.AiModelFactory;
import cn.mervyn.luca.framework.ai.core.factory.AiModelFactoryImpl;
import cn.mervyn.luca.framework.ai.core.model.deepseek.DeepSeekChatModel;
import cn.mervyn.luca.framework.ai.core.model.deepseek.DeepSeekChatOptions;
import cn.mervyn.luca.framework.ai.core.model.midjourney.api.MidjourneyApi;
import cn.mervyn.luca.framework.ai.core.model.suno.api.SunoApi;
import cn.mervyn.luca.framework.ai.core.model.xinghuo.XingHuoChatModel;
import cn.mervyn.luca.framework.ai.core.model.xinghuo.XingHuoChatOptions;
import com.alibaba.cloud.ai.tongyi.TongYiAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * 墨文 AI 自动配置
 *
 * @author fansili
 */
@AutoConfiguration
@EnableConfigurationProperties(LucaAiProperties.class)
@Slf4j
@Import(TongYiAutoConfiguration.class)
public class LucaAiAutoConfiguration {

    @Bean
    public AiModelFactory aiModelFactory() {
        return new AiModelFactoryImpl();
    }

    // ========== 各种 AI Client 创建 ==========

    @Bean
    @ConditionalOnProperty(value = "luca.ai.deepseek.enable", havingValue = "true")
    public DeepSeekChatModel deepSeekChatModel(LucaAiProperties lucaAiProperties) {
        LucaAiProperties.DeepSeekProperties properties = lucaAiProperties.getDeepSeek();
        DeepSeekChatOptions options = DeepSeekChatOptions.builder()
                .model(properties.getModel())
                .temperature(properties.getTemperature())
                .maxTokens(properties.getMaxTokens())
                .topP(properties.getTopP())
                .build();
        return new DeepSeekChatModel(properties.getApiKey(), options);
    }

    @Bean
    @ConditionalOnProperty(value = "luca.ai.xinghuo.enable", havingValue = "true")
    public XingHuoChatModel xingHuoChatClient(LucaAiProperties lucaAiProperties) {
        LucaAiProperties.XingHuoProperties properties = lucaAiProperties.getXinghuo();
        XingHuoChatOptions options = XingHuoChatOptions.builder()
                .model(properties.getModel())
                .temperature(properties.getTemperature())
                .maxTokens(properties.getMaxTokens())
                .topK(properties.getTopK())
                .build();
        return new XingHuoChatModel(properties.getAppKey(), properties.getSecretKey(), options);
    }

    @Bean
    @ConditionalOnProperty(value = "luca.ai.midjourney.enable", havingValue = "true")
    public MidjourneyApi midjourneyApi(LucaAiProperties lucaAiProperties) {
        LucaAiProperties.MidjourneyProperties config = lucaAiProperties.getMidjourney();
        return new MidjourneyApi(config.getBaseUrl(), config.getApiKey(), config.getNotifyUrl());
    }

    @Bean
    @ConditionalOnProperty(value = "luca.ai.suno.enable", havingValue = "true")
    public SunoApi sunoApi(LucaAiProperties lucaAiProperties) {
        return new SunoApi(lucaAiProperties.getSuno().getBaseUrl());
    }

}