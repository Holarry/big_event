package com.holary.config;

import com.holary.properties.TencentCosProperties;
import com.holary.utils.TencentCosUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Holary
 * @Date: 2023/11/11 15:00
 * @Description: TencentCosConfig
 */
@Configuration
public class TencentCosConfig {
    @Bean
    @ConditionalOnMissingBean
    public TencentCosUtil tencentCosUtil(TencentCosProperties tencentCosProperties) {
        return new TencentCosUtil(
                tencentCosProperties.getSecretId(),
                tencentCosProperties.getSecretKey(),
                tencentCosProperties.getRegion(),
                tencentCosProperties.getBucketName()
        );
    }
}
