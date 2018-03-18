package com.vedoware.webhooks.core.api.notifications.impl;

import java.util.UUID;

import com.vedoware.webhooks.core.api.rest.notifications.INotificationProducer;

public class NotificationProducer implements INotificationProducer {

    private String handle;

    public NotificationProducer() {
        handle = UUID.randomUUID().toString();
    }

    @Override
    public String getUniqueIdentifier() {
        return handle;
    }

}
