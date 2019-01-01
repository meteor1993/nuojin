import wx from 'weixin-js-sdk'
const jsApiList = [
  'checkJsApi',
  'onMenuShareTimeline',
  'onMenuShareAppMessage',
  'onMenuShareQQ',
  'onMenuShareWeibo',
  'hideMenuItems',
  'showMenuItems',
  'hideAllNonBaseMenuItem',
  'showAllNonBaseMenuItem',
  'translateVoice',
  'startRecord',
  'stopRecord',
  'onRecordEnd',
  'playVoice',
  'pauseVoice',
  'stopVoice',
  'uploadVoice',
  'downloadVoice',
  'chooseImage',
  'previewImage',
  'uploadImage',
  'downloadImage',
  'getNetworkType',
  'openLocation',
  'getLocation',
  'hideOptionMenu',
  'showOptionMenu',
  'closeWindow',
  'scanQRCode',
  'chooseWXPay',
  'openProductSpecificView',
  'addCard',
  'chooseCard',
  'openCard'
]
import { ajaxJsapiSignature } from '@/api/wechatUser'

function getSDK() {
  ajaxJsapiSignature(location.href.split('#')[0]).then(res => {
    console.log(res)
    wx.config({
      debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
      appId: res.resultData.appId, // 必填，公众号的唯一标识
      timestamp: res.resultData.jsapiSignature.timestamp, // 必填，生成签名的时间戳
      nonceStr: res.resultData.jsapiSignature.nonceStr, // 必填，生成签名的随机串
      signature: res.resultData.jsapiSignature.signature, // 必填，签名
      jsApiList: jsApiList // 必填，需要使用的JS接口列表
    })
    wx.ready(function () {
      wx.hideOptionMenu();
      wxReadyCallBack();
    })
    wx.error(function (res) {
      console.log("微信验证失败");
    });
  })
}

/**
 * 微信回调
 */
function wxReadyCallBack(){
}

export function getJSSDK() {
  // 获取JSSDK
  return getSDK()
}