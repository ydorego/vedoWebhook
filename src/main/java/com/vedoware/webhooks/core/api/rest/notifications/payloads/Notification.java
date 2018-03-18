package com.vedoware.webhooks.core.api.rest.notifications.payloads;

import java.util.Date;

import com.vedoware.webhooks.core.api.rest.notifications.INotification;
//import com.vedoware.webhooks.core.model.common.Properties;

public class Notification implements INotification {

    private String type;
    private Date time;
    private String name;
    private String internalVnfInstanceId;
    private String externalVnfInstanceId;
    private String details;

    //Properties additionalParameters;

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInternalVnfInstanceId(String internalVnfInstanceId) {
        this.internalVnfInstanceId = internalVnfInstanceId;
    }

    public void setExternalVnfInstanceId(String externalVnfInstanceId) {
        this.externalVnfInstanceId = externalVnfInstanceId;
    }

    public void setDetails(String details) {
        this.details = details;
    }

//    public void setAdditionalParameters(Properties additionalParameters) {
//        this.additionalParameters = additionalParameters;
//    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Date getTime() {
        return time;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getInternalVnfInstanceId() {
        return internalVnfInstanceId;
    }

    @Override
    public String getExternalVnfInstanceId() {
        return externalVnfInstanceId;
    }

    @Override
    public String getDetails() {
        return details;
    }

//    @Override
//    public Properties getAdditionalParameters() {
//        return additionalParameters;
//    }

    @Override
    public String toString() {
        return "Notification [type=" + type + ", time=" + time + ", name=" + name + ", internalVnfInstanceId="
                + internalVnfInstanceId + ", externalVnfInstanceId=" + externalVnfInstanceId + ", details=" + details
                + "]";
    }

}
