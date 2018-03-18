package com.vedoware.webhooks.core.api.rest.notifications.payloads;

import java.util.List;

public class SubscriberInfoList {

    private List<SubscriberInfo> subscribers;

    public List<SubscriberInfo> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<SubscriberInfo> subscribers) {
        this.subscribers = subscribers;
    }

}
