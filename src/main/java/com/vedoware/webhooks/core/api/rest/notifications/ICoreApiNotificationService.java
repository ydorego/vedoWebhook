package com.vedoware.webhooks.core.api.rest.notifications;

import com.vedoware.webhooks.core.api.exceptions.EscCoreApiException;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberInfo;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberInfoList;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberRegistrationRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberRegistrationResponse;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UnregisterSubscriberRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UnregisterSubscriberResponse;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UpdateSubscriberNotificationFilterRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UpdateSubscriberNotificationFilterResponse;

/**
 * EscCoreApi Notification service
 * 
 * @author yvdorego
 *
 */
public interface ICoreApiNotificationService {

    /**
     * Returns the list of subscriber.
     * 
     * @return
     */
    SubscriberInfoList getSubscribersInfo();

    /**
     * Returns the subscriber identify by subscriberId
     * 
     * @param subscriberId
     * @return
     * @throws EscCoreApiException
     */
    SubscriberInfo getSubscriberInfo(String subscriberId) throws EscCoreApiException;

    /**
     * Register a subscriber.
     * 
     * @param registration
     * @return
     * @throws EscCoreApiException
     */
    SubscriberRegistrationResponse registerSubscriber(SubscriberRegistrationRequest registration)
            throws EscCoreApiException;

    /**
     * Update the notification filter.
     * 
     * @param updateFilter
     * @return
     * @throws EscCoreApiException
     */
    UpdateSubscriberNotificationFilterResponse updateSubscriberNotificationFilter(
            UpdateSubscriberNotificationFilterRequest updateFilter) throws EscCoreApiException;

    /**
     * Unregister a subscriber.
     * 
     * @param registration
     * @return
     * @throws EscCoreApiException
     */
    UnregisterSubscriberResponse unregisterSubscriber(UnregisterSubscriberRequest registration)
            throws EscCoreApiException;

    /**
     * Publish a notification to all subscribers registered with a filter
     * matching the notification type.
     * 
     * @param aNotification
     * @throws EscCoreApiException
     */
    void publish(INotification aNotification) throws EscCoreApiException;

    // INotificationProducer getNotificationProducer() throws
    // EscCoreApiException;

    // void registerNotificationProducer(INotificationProducer
    // aNotificationProducer) throws EscCoreApiException;

    // void unRegisterNotificationProducer(INotificationProducer
    // aNotificationProducer) throws EscCoreApiException;

    // void publish(INotificationProducer producerHandle, INotification
    // aNotification) throws EscCoreApiException;

}
