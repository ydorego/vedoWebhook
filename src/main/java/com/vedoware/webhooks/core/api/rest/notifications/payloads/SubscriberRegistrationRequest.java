package com.vedoware.webhooks.core.api.rest.notifications.payloads;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Request to register a notification subscriber
 * 
 * 
 * @author yvdorego
 *
 */
public class SubscriberRegistrationRequest {

    @NotNull(message = "Subscriber Identifier can not be null")
    private String subscriberExternalId;

    private String description;

    @Valid
    @NotNull(message = "Notification filter can not be null")
    private NotificationFilter notificationFilter;

    @NotNull(message = "Notification URI can not be null")
    private String notificationUri;

    public String getSubscriberExternalId() {
        return subscriberExternalId;
    }

    public void setSubscriberExternalId(String subscriberExternalId) {
        this.subscriberExternalId = subscriberExternalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((notificationFilter == null) ? 0 : notificationFilter.hashCode());
        result = prime * result + ((notificationUri == null) ? 0 : notificationUri.hashCode());
        result = prime * result + ((subscriberExternalId == null) ? 0 : subscriberExternalId.hashCode());
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
        SubscriberRegistrationRequest other = (SubscriberRegistrationRequest) obj;
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
        return true;
    }

}
