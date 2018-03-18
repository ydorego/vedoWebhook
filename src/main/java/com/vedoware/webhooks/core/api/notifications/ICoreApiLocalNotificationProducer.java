package com.vedoware.webhooks.core.api.notifications;

import java.util.List;

import com.vedoware.webhooks.core.api.exceptions.EscCoreApiException;
import com.vedoware.webhooks.core.api.rest.notifications.INotification;
import com.vedoware.webhooks.core.api.rest.notifications.INotificationProducer;

/**
 * Interface to be used by the ESC Core to publish notifications through the ESC
 * Core API Layer. In this initial implementation, the notification will be
 * ultimately received by the ESC ETSI Service.
 * 
 * @author yvdorego
 *
 */
public interface ICoreApiLocalNotificationProducer {

    /**
     * Returns an object to be used by the EscCoreApi to publish notifications.
     * In this first release it is mainly a wrapper class providing mainly name
     * qualification. The intent is to have a placeholder where we could
     * eventually implements more complex dispatching.
     * 
     * @return
     * @throws EscCoreApiException
     */
    INotificationProducer createNotificationProducer() throws EscCoreApiException;

    /**
     * Returns the list of registered notifications subscribers.
     * 
     * @return
     */
    List<INotificationProducer> getRegisteredNotificationProducers();

    /**
     * Register a local notification producer with the notification service.
     * 
     * @param aNotificationProducer
     * @throws EscCoreApiException
     */
    void registerNotificationProducer(INotificationProducer aNotificationProducer) throws EscCoreApiException;

    /**
     * Unregister a previously notification producer from the notification
     * service.
     * 
     * @param aNotificationProducer
     * @throws EscCoreApiException
     */
    void unRegisterNotificationProducer(INotificationProducer aNotificationProducer) throws EscCoreApiException;

    /**
     * Publish a notification through the notification service.
     * 
     * @param producerHandle
     * @param aNotification
     * @throws EscCoreApiException
     */
    void publish(INotificationProducer producerHandle, INotification aNotification) throws EscCoreApiException;

}
