package com.springboot.nuojin.system.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @program: fuhui
 * @description: Guava缓存工具类
 * @author: weishiyao
 * @create: 2018-09-30 23:22
 **/
public class StaffCacheUtil {

    private static StaffCacheUtil staffCacheUtil = null;

    public static StaffCacheUtil create() {
        if (staffCacheUtil == null) {
            staffCacheUtil = new StaffCacheUtil();
        }
        return staffCacheUtil;
    }

    private static Cache<String, Object> staffCache;

    private StaffCacheUtil() {
        staffCache = CacheBuilder.newBuilder()
                .expireAfterWrite(6, TimeUnit.HOURS)
                .build();
    }

    public void cleanCache() {
        staffCache.cleanUp();
    }

    public void put(String key, Object value) throws ExecutionException {
        staffCache.put(key, value);
    }

    public void remove(String key) {
        staffCache.invalidate(key);
    }
}
