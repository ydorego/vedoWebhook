package com.vedoware.webhooks.core.api.rest.notifications;

import java.util.Date;


/**
 * Notification fields mainly coming from some of the existing Core
 * modifications.
 * 
 * @author yvdorego
 *
 */
public interface INotification {

    String getType();

    Date getTime();

    String getName();

    String getInternalVnfInstanceId();

    String getExternalVnfInstanceId();

    String getDetails();

    //Properties getAdditionalParameters();

}
