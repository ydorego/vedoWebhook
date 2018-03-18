package com.vedoware.webhooks.core.api.rest.notifications.payloads;

import com.vedoware.webhooks.core.api.rest.operations.Response;

/**
 * Result from a request to update the notification filter.
 * 
 * @author yvdorego
 *
 */
public class UpdateSubscriberNotificationFilterResponse extends Response {

    private SubscriberInfo subscriberInfo;

    public SubscriberInfo getSubscriberInfo() {
        return subscriberInfo;
    }

    public void setSubscriberInfo(SubscriberInfo subscriberInfo) {
        this.subscriberInfo = subscriberInfo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((subscriberInfo == null) ? 0 : subscriberInfo.hashCode());
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
        UpdateSubscriberNotificationFilterResponse other = (UpdateSubscriberNotificationFilterResponse) obj;
        if (subscriberInfo == null) {
            if (other.subscriberInfo != null)
                return false;
        } else if (!subscriberInfo.equals(other.subscriberInfo))
            return false;
        return true;
    }

}
