package com.spring.api.domain;

import javax.xml.bind.annotation.*;

import lombok.Data;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {
    @XmlElement(name = "header")
    private Header header;
    @XmlElement(name = "body")
    private Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}