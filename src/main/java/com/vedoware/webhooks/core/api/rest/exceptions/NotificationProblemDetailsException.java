package com.vedoware.webhooks.core.api.rest.exceptions;

import com.vedoware.webhooks.core.api.exceptions.EscCoreApiException;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.NotificationProblemDetails;

public class NotificationProblemDetailsException extends EscCoreApiException {

    private static final long serialVersionUID = 1L;
    private final NotificationProblemDetails notificationProblemDetails;

    public NotificationProblemDetailsException(NotificationProblemDetails notificationProblemDetails) {
        this.notificationProblemDetails = notificationProblemDetails;
    }

    public NotificationProblemDetails getNotificationProblemDetails() {
        return notificationProblemDetails;
    }


}
