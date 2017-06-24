package com.mayday.utils;

/**
 * Created by Mark on 2017/5/28 0028
 */
public class HttpClientResponseModel {
    private Integer httpCode;//失败为-1
    private String responseContent;

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }
}
