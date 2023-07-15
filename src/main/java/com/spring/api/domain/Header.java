package com.spring.api.domain;

import javax.xml.bind.annotation.*;

import lombok.Data;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Header {
    @XmlElement(name = "resultCode")
    private String resultCode;
    @XmlElement(name = "resultMsg")
    private String resultMsg;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}