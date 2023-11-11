package com.holary.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Holary
 * @Date: 2023/11/11 15:02
 * @Description: TencentCosProperties
 */
@Component
@Data
@ConfigurationProperties(prefix = "tencent.cos")
public class TencentCosProperties {
    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;
}
