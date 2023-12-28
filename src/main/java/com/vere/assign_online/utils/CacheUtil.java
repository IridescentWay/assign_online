package com.vere.assign_online.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * ClassName:CacheUtil
 * Package:com.vere.demo.utils
 * Description:
 *
 * @Date:2022/4/20 18:18
 * @Author:3046990030@qq.com
 */
@Component
public class CacheUtil {
    static final Cache<String, String> localCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(5, TimeUnit.MINUTES).build();
    public static boolean saveAttribute(String key, String value) {
        System.out.println(key + ": " + value);
        localCache.put(key, value);
        return true;
    }

    public static String loadAttribute(String key) {
        return localCache.getIfPresent(key);
    }
}
