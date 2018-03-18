package com.vedoware.webhooks.core.api.rest.notifications.payloads;

import java.util.Date;

import com.vedoware.webhooks.core.api.rest.notifications.INotification;
//import com.vedoware.webhooks.core.model.common.Properties;

/**
 * A special notification to stop the internal notification publisher used by
 * the notification service. Upon application shutdown, the application must
 * ensure that a StopPublisher notification is sent to the notification service
 * for its shutdown sequences.
 * 
 * @author yvdorego
 *
 */
public class StopPublisher implements INotification {

    @Override
    public String getType() {
        return null;
    }

    @Override
    public Date getTime() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getInternalVnfInstanceId() {
        return null;
    }

    @Override
    public String getExternalVnfInstanceId() {
        return null;
    }

    @Override
    public String getDetails() {
        return null;
    }


}
