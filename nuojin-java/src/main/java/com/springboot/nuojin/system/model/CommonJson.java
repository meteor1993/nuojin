package com.springboot.nuojin.system.model;

import java.util.Map;

/**
 * @program: fuhui
 * @description:
 * @author: weishiyao
 * @create: 2018-09-29 20:28
 **/
public class CommonJson {
    private String resultCode;

    private Map<String, Object> resultData;

    private String resultMsg;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Map<String, Object> getResultData() {
        return resultData;
    }

    public void setResultData(Map<String, Object> resultData) {
        this.resultData = resultData;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
