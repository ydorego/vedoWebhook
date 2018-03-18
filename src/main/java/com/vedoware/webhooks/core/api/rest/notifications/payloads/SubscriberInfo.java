package com.vedoware.webhooks.core.api.rest.notifications.payloads;

public class SubscriberInfo {

    private String subscriberExternalId;
    private String subscriberId;
    private String description;

    private NotificationFilter notificationFilter;
    private String notificationUri;

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

    public String getNotificationUri() {
        return notificationUri;
    }

    public void setNotificationUri(String notificationUri) {
        this.notificationUri = notificationUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((notificationFilter == null) ? 0 : notificationFilter.hashCode());
        result = prime * result + ((notificationUri == null) ? 0 : notificationUri.hashCode());
        result = prime * result + ((subscriberExternalId == null) ? 0 : subscriberExternalId.hashCode());
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
        SubscriberInfo other = (SubscriberInfo) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (notificationFilter == null) {
            if (other.notificationFilter != null)
                return false;
        } else if (!notificationFilter.equals(other.notificationFilter))
            return false;
        if (notificationUri == null) {
            if (other.notificationUri != null)
                return false;
        } else if (!notificationUri.equals(other.notificationUri))
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
