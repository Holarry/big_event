package com.holary.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Holary
 * @Date: 2023/11/11 15:03
 * @Description: 腾讯云COS工具类
 */
@Data
@AllArgsConstructor
public class TencentCosUtil {
    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;

    /**
     * description: 文件上传
     *
     * @param file: 文件对象
     * @return: java.lang.String
     */
    public String uploadFile(MultipartFile file) throws IOException {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的地域
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);

        // 指定文件上传到COS
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String key = System.currentTimeMillis() + suffix;

        InputStream is = file.getInputStream();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(is.available());
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, is, objectMetadata);

        cosClient.putObject(putObjectRequest);

        return "https://" + bucketName + "." + "cos" + "." + region + ".myqcloud.com/" + key;
    }
}
