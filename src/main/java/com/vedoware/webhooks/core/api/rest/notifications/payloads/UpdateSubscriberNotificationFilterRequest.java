package com.vedoware.webhooks.core.api.rest.notifications.payloads;

import javax.validation.constraints.NotNull;

/**
 * Request to register a notification subscriber
 * 
 * 
 * @author yvdorego
 *
 */
public class UpdateSubscriberNotificationFilterRequest {

    @NotNull(message = "Subscriber Id can not be null")
    private String subscriberId;

    @NotNull(message = "Subscriber notification filter can not be null")
    private NotificationFilter notificationFilter;

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
        int result = 1;
        result = prime * result + ((notificationFilter == null) ? 0 : notificationFilter.hashCode());
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
        UpdateSubscriberNotificationFilterRequest other = (UpdateSubscriberNotificationFilterRequest) obj;
        if (notificationFilter == null) {
            if (other.notificationFilter != null)
                return false;
        } else if (!notificationFilter.equals(other.notificationFilter))
            return false;
        if (subscriberId == null) {
            if (other.subscriberId != null)
                return false;
        } else if (!subscriberId.equals(other.subscriberId))
            return false;
        return true;
    }


}
