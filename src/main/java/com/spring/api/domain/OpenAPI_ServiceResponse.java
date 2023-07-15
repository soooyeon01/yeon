package com.spring.api.domain;

import javax.xml.bind.annotation.*;

import lombok.Data;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OpenAPI_ServiceResponse")
public class OpenAPI_ServiceResponse {
    @XmlElement(name = "response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}