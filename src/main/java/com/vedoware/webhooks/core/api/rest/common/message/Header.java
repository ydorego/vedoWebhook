package com.vedoware.webhooks.core.api.rest.common.message;

import java.util.Date;

/**
 * Header section contains attributes providing information about the message
 * payload, but also rules/directives for the message itself.
 * 
 * @author yvdorego
 *
 */
public class Header {

    private Date time;
    private ApiInfo apiInfo;
    private PayloadInfo payloadInfo;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ApiInfo getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(ApiInfo apiInfo) {
        this.apiInfo = apiInfo;
    }

    public PayloadInfo getPayloadInfo() {
        return payloadInfo;
    }

    public void setPayloadInfo(PayloadInfo payloadInfo) {
        this.payloadInfo = payloadInfo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((apiInfo == null) ? 0 : apiInfo.hashCode());
        result = prime * result + ((payloadInfo == null) ? 0 : payloadInfo.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Header other = (Header) obj;
        if (apiInfo == null) {
            if (other.apiInfo != null)
                return false;
        } else if (!apiInfo.equals(other.apiInfo))
            return false;
        if (payloadInfo == null) {
            if (other.payloadInfo != null)
                return false;
        } else if (!payloadInfo.equals(other.payloadInfo))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }

}
