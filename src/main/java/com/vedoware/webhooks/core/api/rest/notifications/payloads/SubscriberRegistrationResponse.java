package com.vedoware.webhooks.core.api.rest.notifications.payloads;

import com.vedoware.webhooks.core.api.rest.operations.Response;

public class SubscriberRegistrationResponse extends Response {

    private String subscriberExternalId;
    private String subscriberId;
    private NotificationFilter notificationFilter;

    public String getSubscriberExternalId() {
        return subscriberExternalId;
    }

    public void setSubscriberExternalId(String subscriberExternalId) {
        this.subscriberExternalId = subscriberExternalId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public NotificationFilter getNotificationFilter() {
        return notificationFilter;
    }

    public void setNotificationFilter(NotificationFilter notificationFilter) {
        this.notificationFilter = notificationFilter;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((notificationFilter == null) ? 0 : notificationFilter.hashCode());
        result = prime * result + ((subscriberExternalId == null) ? 0 : subscriberExternalId.hashCode());
        result = prime * result + ((subscriberId == null) ? 0 : subscriberId.hashCode());
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
        SubscriberRegistrationResponse other = (SubscriberRegistrationResponse) obj;
        if (notificationFilter == null) {
            if (other.notificationFilter != null)
                return false;
        } else if (!notificationFilter.equals(other.notificationFilter))
            return false;
        if (subscriberExternalId == null) {
            if (other.subscriberExternalId != null)
                return false;
        } else if (!subscriberExternalId.equals(other.subscriberExternalId))
            return false;
        if (subscriberId == null) {
            if (other.subscriberId != null)
                return false;
        } else if (!subscriberId.equals(other.subscriberId))
            return false;
        return true;
    }

}
