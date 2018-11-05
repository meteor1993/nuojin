package com.springboot.nuojin.wechat.wxUser.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.springboot.nuojin.sms.model.SMSModel;
import com.springboot.nuojin.sms.repository.SMSRepository;
import com.springboot.nuojin.sms.utils.AliyunSMSUtils;
import com.springboot.nuojin.system.model.CommonJson;
import com.springboot.nuojin.system.utils.ContextHolderUtils;
import com.springboot.nuojin.system.utils.HttpUtils;
import com.springboot.nuojin.system.utils.StaffCacheUtil;
import com.springboot.nuojin.wechat.wxUser.model.WxUserModel;
import com.springboot.nuojin.wechat.wxUser.repository.WxUserRepository;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @Author: daixueyun
 * @Description:
 * @Date: Create in 23:04 2018/11/4
 */
@RestController
@RequestMapping(path = "/mp/wxUserController")
public class WxUserController {

    private final Logger logger = LoggerFactory.getLogger(WxUserController.class);

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    SMSRepository smsRepository;

    @Autowired
    WxUserRepository wxUserRepository;

    @GetMapping(value = "/oauth2Wechat")
    public ModelAndView oauth2Wechat() {
        String url = wxMpService.oauth2buildAuthorizationUrl("http://alpaca.s1.natapp.cc/mp/wxUserController/getCode", WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
        logger.info(">>>>>>>>>>>>>>>>>>>url:" + url);
        return new ModelAndView(new RedirectView(url));
    }

    @GetMapping(value = "/getCode")
    public ModelAndView getCode(@RequestParam String code, HttpServletResponse response) throws WxErrorException, ExecutionException, IOException {
        logger.info(">>>>>>>>>>>>>>>>WxUserController.getToken.getCode>>>>>>>>>>>>>>>");
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>wxMpUser:" + wxMpUser.toString());
        WxUserModel wxUserModel = wxUserRepository.getByOpenIdIs(wxMpUser.getOpenId());
        wxUserModel = new WxUserModel();
        wxUserModel.setOpenId(wxMpUser.getOpenId());
        // 更新微信信息
        wxUserModel.setNickName(wxMpUser.getNickname());
        wxUserModel.setSex(wxMpUser.getSex());
        wxUserModel.setWechatImageUrl(wxMpUser.getHeadImgUrl());

        wxUserModel = wxUserRepository.save(wxUserModel);

        // 生成token
        String token = UUID.randomUUID().toString();
        // 将WxUserModel存入缓存
        StaffCacheUtil.create().put(token, wxUserModel);
        response.sendRedirect("http://localhost:8080/#/home?token=" + token);

        return null;
    }


    /**
     * 获取token，完成腾讯交互获取微信用户信息，并相关数据存入缓存
     * @return
     * @throws IOException
     * @throws ExecutionException
     */
    @PostMapping(value = "/getToken")
    @ResponseBody
    public CommonJson getToken() throws IOException, ExecutionException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("WxUserController.getToken>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String key = jsonObject.getString("key");
        CommonJson json = new CommonJson();
        // 如果key相符合
        if ("NA2i760YXSgfsiOlQl8z4ps5Zll73FfM".equals(key)) {
            // 和腾讯交互，获取WxMpUser，并更新或者保存WxUserModel
            WxMpUser wxMpUser = new WxMpUser();
            wxMpUser.setOpenId("111111111");
            wxMpUser.setHeadImgUrl("http://storage.360buyimg.com/i.imageUpload/4d6574656f723139393331353134323936363537373539_mid.jpg");

            WxUserModel WxUserModel = wxUserRepository.getByOpenIdIs(wxMpUser.getOpenId());

            WxUserModel = new WxUserModel();
            WxUserModel.setOpenId(wxMpUser.getOpenId());
            // 更新微信信息
            WxUserModel.setNickName(wxMpUser.getNickname());
            WxUserModel.setSex(wxMpUser.getSex());
            WxUserModel.setWechatImageUrl(wxMpUser.getHeadImgUrl());

            WxUserModel = wxUserRepository.save(WxUserModel);

            // 生成token
            String token = UUID.randomUUID().toString();
            // 将WxUserModel存入缓存
            StaffCacheUtil.create().put(token, WxUserModel);
            // 将token返回
            Map<String, Object> map = Maps.newHashMap();
            map.put("token", token);
            json.setResultCode("1");
            json.setResultMsg("success");
            json.setResultData(map);
            return json;
        }

        return null;
    }

    /**
     * 获取用户信息
     * @return
     */
    @PostMapping(value = "/getUserInfo")
    @ResponseBody
    public CommonJson getUserInfo() {
        String token = ContextHolderUtils.getRequest().getHeader("token");

        logger.info("WxUserController.getUserInfo>>>>>>>>>>>>token:" + token);

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

        CommonJson json = new CommonJson();

        Map<String, Object> map = Maps.newHashMap();

        if (WxUserModel != null) {
            WxUserModel = wxUserRepository.getByOpenIdIs(WxUserModel.getOpenId());
            map.put("info", WxUserModel);
            json.setResultCode("1");
            json.setResultMsg("success");
            json.setResultData(map);
        } else {
            json.setResultCode("0");
            json.setResultMsg("fail");
        }

        return json;
    }

    /**
     * 获取随机数
     * @return
     */
    private String getRandomCode() {
        Random rad = new Random();

        String result = rad.nextInt(1000000) + "";

        if(result.length() != 6){
            return getRandomCode();
        }
        return result;
    }

    /**
     * 发送短信
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/sendSMS")
    @ResponseBody
    public CommonJson sendSMS() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("WxUserController.sendSMS>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);

        String phone = jsonObject.getString("phone");

        CommonJson json = new CommonJson();
        if (Strings.isNullOrEmpty(phone) || phone.length() != 11 || !"1".equals(phone.substring(0,1))) {
            json.setResultCode("0");
            json.setResultMsg("请输入正确的手机号");
            return json;
        }

        AliyunSMSUtils smsUtils = AliyunSMSUtils.getInstance();

        String code = getRandomCode();

        logger.info("---------------sendSMS--------------phone:" + phone + ",code:" + code);

        boolean flag = smsUtils.sendAliyunSMS(phone, code);

        // 测试短信发送成功
        flag = true;
        if (flag) {
            SMSModel smsModel = new SMSModel();
            smsModel.setCreateDate(new Date());
            smsModel.setMobile(phone);
            smsModel.setCreateSessionID(token);
            smsModel.setIsValid("1");
            smsModel.setVerificationCode(code);
            smsRepository.save(smsModel);
        }

        if (flag) {
            json.setResultCode("1");
            json.setResultMsg("短信发送成功");
        } else {
            json.setResultCode("0");
            json.setResultMsg("网络拥堵，请稍后重试");
        }

        return json;
    }

    /**
     * 绑定手机
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/bindPhone")
    @ResponseBody
    public CommonJson bindPhone() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("WxUserController.bindPhone>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);

        String phone = jsonObject.getString("phone");
        String code = jsonObject.getString("code");

        CommonJson json = new CommonJson();

        if (Strings.isNullOrEmpty(phone) || Strings.isNullOrEmpty(code)) {
            json.setResultCode("0");
            json.setResultMsg("手机号或验证码为空");
            return json;
        }

        int checkMinutes = 5; // 校验短信有效期

        // 校验短信验证码
        List<SMSModel> smsModelList = smsRepository.findAllByCreateSessionIDAndIsValidAndVerificationCodeOrderByCreateDateDesc(token, "1", code);

        try {
            if (smsModelList.size() > 0) { // 查询到相关数据
                SMSModel smsModel = smsModelList.get(0);
                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String createDate = simpleFormat.format(smsModel.getCreateDate());
                String nowDate = simpleFormat.format(new Date());
                long create = simpleFormat.parse(createDate).getTime();
                long now = simpleFormat.parse(nowDate).getTime();
                int times = (int) ((now - create)/(1000 * 60));
                if ("1".equals(smsModel.getIsValid())) { // 判断当前验证码是否有效
                    if (times < checkMinutes) { // 判断时间是否小于有效时间
                        // 将验证码变为无效
                        smsModel.setIsValid("0");
                        smsRepository.save(smsModel);
                        json.setResultCode("1");
                        json.setResultMsg("验证码输入正确");
                    } else {
                        json.setResultCode("0");
                        json.setResultMsg("当前验证码已过期，请重新获取");
                        return json;
                    }
                } else {
                    json.setResultCode("0");
                    json.setResultMsg("您的验证码已失效，请重新获取");
                    return json;
                }
            } else {
                json.setResultCode("0");
                json.setResultMsg("验证码输入有误，请核对后重新输入");
                return json;
            }
        } catch (ParseException e) {
            logger.error("error", e);
        }

        WxUserModel wxUserModel = null;

        try {
            wxUserModel = (WxUserModel) StaffCacheUtil.create().get(token, new Callable<WxUserModel>() {
                @Override
                public WxUserModel call() throws Exception {
                    return null;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (wxUserModel == null) {
            json.setResultCode("0");
            json.setResultMsg("网络异常，请稍后重试");
            return json;
        }

        if ("1".equals(json.getResultCode())) { // 验证码校验通过
            wxUserModel = wxUserRepository.getByOpenIdIs(wxUserModel.getOpenId());
            if (wxUserModel != null) {
                wxUserModel.setPhone(phone);
                wxUserRepository.save(wxUserModel);
                json.setResultCode("1");
                json.setResultMsg("手机绑定成功");
                json.setResultData(null);
            } else {
                json.setResultCode("0");
                json.setResultMsg("验证码输入有误，请核对后重新输入");
                json.setResultData(null);
            }

        }

        return json;
    }
}
