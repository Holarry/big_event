package com.holary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Holary
 * @Date: 2023/11/9 16:45
 * @Description: 分页结果返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {
    private Long total;//总条数
    private List<T> items;//当前页数据集合
}
