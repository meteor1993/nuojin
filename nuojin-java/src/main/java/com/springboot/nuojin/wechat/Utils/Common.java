package com.springboot.nuojin.wechat.Utils;

import com.springboot.nuojin.system.utils.ContextHolderUtils;
import com.springboot.nuojin.system.utils.HttpUtils;
import com.springboot.nuojin.system.utils.StaffCacheUtil;
import com.springboot.nuojin.wechat.wxUser.model.WxUserModel;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class Common {

    public static String getOpenId() throws IOException {

        String token = ContextHolderUtils.getRequest().getHeader("token");
        WxUserModel WxUserModel = null;

        try {
            WxUserModel = (WxUserModel) StaffCacheUtil.create().get(token, new Callable<WxUserModel>() {
                @Override
                public WxUserModel call() throws Exception {
                    return null;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return WxUserModel.getOpenId();


    }



}
