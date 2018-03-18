package com.vedoware.webhooks.core.api.rest.notifications.payloads;

import javax.validation.constraints.NotNull;

/**
 * Request to register a notification subscriber
 * 
 * 
 * @author yvdorego
 *
 */
public class UnregisterSubscriberRequest {

    @NotNull(message = "Subscriber Id can not be null")
    private String subscriberId;

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((subscriberId == null) ? 0 : subscriberId.hashCode());
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
        UnregisterSubscriberRequest other = (UnregisterSubscriberRequest) obj;
        if (subscriberId == null) {
            if (other.subscriberId != null)
                return false;
        } else if (!subscriberId.equals(other.subscriberId))
            return false;
        return true;
    }
}
