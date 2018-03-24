package com.vedoware.webhooks.core.api.rest.notifications;

import com.vedoware.webhooks.core.api.exceptions.VedoHookApiException;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberInfo;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberInfoList;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberRegistrationRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberRegistrationResponse;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UnregisterSubscriberRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UnregisterSubscriberResponse;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UpdateSubscriberNotificationFilterRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UpdateSubscriberNotificationFilterResponse;

/**
 * Api Notification service
 * 
 * @author yvdorego
 *
 */
public interface IApiNotificationService {

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
     * @throws VedoHookApiException
     */
    SubscriberInfo getSubscriberInfo(String subscriberId) throws VedoHookApiException;

    /**
     * Register a subscriber.
     * 
     * @param registration
     * @return
     * @throws VedoHookApiException
     */
    SubscriberRegistrationResponse registerSubscriber(SubscriberRegistrationRequest registration)
            throws VedoHookApiException;

    /**
     * Update the notification filter.
     * 
     * @param updateFilter
     * @return
     * @throws VedoHookApiException
     */
    UpdateSubscriberNotificationFilterResponse updateSubscriberNotificationFilter(
            UpdateSubscriberNotificationFilterRequest updateFilter) throws VedoHookApiException;

    /**
     * Unregister a subscriber.
     * 
     * @param registration
     * @return
     * @throws VedoHookApiException
     */
    UnregisterSubscriberResponse unregisterSubscriber(UnregisterSubscriberRequest registration)
            throws VedoHookApiException;

    /**
     * Publish a notification to all subscribers registered with a filter
     * matching the notification type.
     * 
     * @param aNotification
     * @throws VedoHookApiException
     */
    void publish(INotification aNotification) throws VedoHookApiException;

}
