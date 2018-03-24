package com.vedoware.webhooks.core.api.notifications;

import java.util.List;

import com.vedoware.webhooks.core.api.exceptions.VedoHookApiException;
import com.vedoware.webhooks.core.api.rest.notifications.INotification;
import com.vedoware.webhooks.core.api.rest.notifications.INotificationProducer;

/**
 * Interface to be used by the Core to publish notifications through the Core API Layer. 
 * 
 * @author yvdorego
 *
 */
public interface IApiLocalNotificationProducer {

    /**
     * Returns an object to be used to publish notifications.
     * In this first release it is mainly a wrapper class providing mainly name
     * qualification. The intent is to have a placeholder where we could
     * eventually implements more complex dispatching.
     * 
     * @return
     * @throws VedoHookApiException
     */
    INotificationProducer createNotificationProducer() throws VedoHookApiException;

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
     * @throws VedoHookApiException
     */
    void registerNotificationProducer(INotificationProducer aNotificationProducer) throws VedoHookApiException;

    /**
     * Unregister a previously notification producer from the notification
     * service.
     * 
     * @param aNotificationProducer
     * @throws VedoHookApiException
     */
    void unRegisterNotificationProducer(INotificationProducer aNotificationProducer) throws VedoHookApiException;

    /**
     * Publish a notification through the notification service.
     * 
     * @param producerHandle
     * @param aNotification
     * @throws VedoHookApiException
     */
    void publish(INotificationProducer producerHandle, INotification aNotification) throws VedoHookApiException;

}
