package com.vedoware.webhooks.core.api.rest.notifications.payloads;

import java.util.List;

import com.vedoware.webhooks.core.api.rest.operations.Response;

public class GetSubscriberInfoResponse extends Response {

    private List<SubscriberInfo> subscriberInfoList;

    public List<SubscriberInfo> getSubscriberInfoList() {
        return subscriberInfoList;
    }

    public void setSubscriberInfoList(List<SubscriberInfo> subscriberInfoList) {
        this.subscriberInfoList = subscriberInfoList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((subscriberInfoList == null) ? 0 : subscriberInfoList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        GetSubscriberInfoResponse other = (GetSubscriberInfoResponse) obj;
        if (subscriberInfoList == null) {
            if (other.subscriberInfoList != null)
                return false;
        } else if (!subscriberInfoList.equals(other.subscriberInfoList))
            return false;
        return true;
    }

}
