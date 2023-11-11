package com.holary.controller;

import com.holary.pojo.Result;
import com.holary.utils.TencentCosUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: Holary
 * @Date: 2023/11/11 15:01
 * @Description: FileController
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private TencentCosUtil tencentCosUtil;

    /**
     * description: 文件上传
     *
     * @param file: 文件对象
     * @return: com.holary.pojo.Result
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String url = tencentCosUtil.uploadFile(file);
        return Result.success(url);
    }
}
