package com.holary.utils;

/**
 * @Author: Holary
 * @Date: 2023/11/9 17:27
 * @Description: ThreadLocal工具类
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
    // 提供ThreadLocal对象,
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    /**
     * description: 根据键获取值
     *
     * @return: T
     */
    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    /**
     * description: 存储键值对
     *
     * @param value:
     * @return: void
     */
    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    /**
     * description: 清除ThreadLocal, 防止内存泄漏
     *
     * @return: void
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
