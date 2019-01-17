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
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
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

    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;

    @GetMapping(value = "/oauth2Wechat")
    public ModelAndView oauth2Wechat() {
        String url = wxMpService.oauth2buildAuthorizationUrl("http://fuhui.kaixindaka.com/mp/wxUserController/getCode", WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
        logger.info(">>>>>>>>>>>>>>>>>>>url:" + url);
        return new ModelAndView(new RedirectView(url));
    }

    @GetMapping(value = "/getCode")
    public ModelAndView getCode(@RequestParam String code, HttpServletResponse response) throws WxErrorException, ExecutionException, IOException {
        logger.info(">>>>>>>>>>>>>>>>WxUserController.getToken.getCode>>>>>>>>>>>>>>>code："+code);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>wxMpUser:" + wxMpUser.toString());
        WxUserModel wxUserModel = wxUserRepository.getByOpenIdIs(wxMpUser.getOpenId());
        if (wxUserModel == null) {
            wxUserModel = new WxUserModel();
        }

        wxUserModel.setOpenId(wxMpUser.getOpenId());
        // 更新微信信息
        wxUserModel.setNickName(wxMpUser.getNickname());
        wxUserModel.setSex(wxMpUser.getSex());
        wxUserModel.setWechatImageUrl(wxMpUser.getHeadImgUrl());

        wxUserModel = wxUserRepository.save(wxUserModel);


        //自动生成账户，用户信息





        // 生成token
        String token = UUID.randomUUID().toString();
        // 将WxUserModel存入缓存
        StaffCacheUtil.create().put(token, wxUserModel);
        response.sendRedirect("http://nuojin.mp.kaixindaka.com/#/shoppingMall?token=" + token);

        return null;
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

    @RequestMapping(value = "/ajaxJsapiSignature")
    @ResponseBody
    public CommonJson ajaxJsapiSignature() throws IOException {
        logger.info("WechatUserController.ajaxJsapiSignature>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("WechatUserController.ajaxJsapiSignature>>>>>>>>>>>>params:" + params);
        JSONObject jsonObject = JSON.parseObject(params);
        String locaHref = jsonObject.getString("locaHref");
        CommonJson j = new CommonJson();
        WxJsapiSignature jsapiSignature = null;
        String appId = "";
        try {
            appId = wxMpConfigStorage.getAppId();
            jsapiSignature = wxMpService.createJsapiSignature(locaHref);
            logger.info("----------ajaxJsapiSignature:"+ locaHref+",JsapiTicket:" + wxMpConfigStorage.getJsapiTicket());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        j.setResultCode("1");
        Map<String,Object> map = Maps.newHashMap();
        map.put("appId", appId);
        map.put("jsapiSignature", jsapiSignature);

        j.setResultData(map);
        logger.info("----------ajaxJsapiSignature:"+ JSON.toJSONString(j));
        return j;
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
