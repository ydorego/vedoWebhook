package com.vedoware.webhooks.core.api.notifications.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vedoware.webhooks.core.api.CoreApiOperationStatus;
import com.vedoware.webhooks.core.api.exceptions.VedoHookApiException;
import com.vedoware.webhooks.core.api.exceptions.InvalidArgumentsException;
import com.vedoware.webhooks.core.api.notifications.IApiLocalNotificationProducer;
import com.vedoware.webhooks.core.api.rest.exceptions.NotificationProblemDetailsException;
import com.vedoware.webhooks.core.api.rest.notifications.IApiNotificationService;
import com.vedoware.webhooks.core.api.rest.notifications.INotification;
import com.vedoware.webhooks.core.api.rest.notifications.INotificationProducer;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.Notification;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.NotificationProblemDetails;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.StopPublisher;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberInfo;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberInfoList;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberRegistrationRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberRegistrationResponse;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UnregisterSubscriberRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UnregisterSubscriberResponse;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UpdateSubscriberNotificationFilterRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UpdateSubscriberNotificationFilterResponse;

/**
 * NotificationService is responsible for publishing notification generated and
 * published by the Core.
 * 
 * In order to maintain clear separation between the core and that service, and
 * adaptation/mapping from Core Notification to API notification will
 * always be done.
 * 
 * @author yvdorego
 *
 */
@Service("ApiNotificationService")
public class ApiNotificationService implements IApiNotificationService, IApiLocalNotificationProducer {

    static Logger log = LoggerFactory.getLogger(ApiNotificationService.class);

    private Map<String, SubscriberInfo> subscribers;

    private Map<String, INotificationProducer> notificationProducers;

    private ExecutorService publisherService;

    private SynchronousQueue<INotification> notificationQueue;

    private NotificationPublisher notificationPublisher;

    public ApiNotificationService() {

        notificationProducers = new HashMap<String, INotificationProducer>();
        notificationQueue = new SynchronousQueue<>();

        subscribers = new HashMap<String, SubscriberInfo>();

        // Local notification publisher
        //
        notificationPublisher = new NotificationPublisher(notificationQueue);
        notificationPublisher.start();

        publisherService = Executors.newFixedThreadPool(10);
    }

    @Override
    public SubscriberInfo getSubscriberInfo(String subscriberId) throws VedoHookApiException {

        if (subscriberId == null || subscriberId.isEmpty()) {

            NotificationProblemDetails notifProblemDetails = new NotificationProblemDetails();
            notifProblemDetails.setMessage("Invalid arguments, subscriberId must be defined");

            throw new NotificationProblemDetailsException(notifProblemDetails);
        }

        return subscribers.get(subscriberId);
    }

    @Override
    public SubscriberInfoList getSubscribersInfo() {

        SubscriberInfoList subscribersList = new SubscriberInfoList();
        subscribersList.setSubscribers(new ArrayList<SubscriberInfo>(subscribers.values()));
        return subscribersList;
    }

    @Override
    public SubscriberRegistrationResponse registerSubscriber(SubscriberRegistrationRequest registration)
            throws VedoHookApiException {

        if (registration == null || registration.getNotificationUri() == null
                || registration.getNotificationUri().isEmpty()) {
            
            NotificationProblemDetails notifProblemDetails = new NotificationProblemDetails();
            notifProblemDetails.setMessage("Registration can not be null");

            throw new NotificationProblemDetailsException(notifProblemDetails);
        }

        if (registration.getNotificationUri() == null || registration.getNotificationUri().isEmpty()) {

            NotificationProblemDetails notifProblemDetails = new NotificationProblemDetails();
            notifProblemDetails.setMessage("Notification URI must be defined");

            throw new NotificationProblemDetailsException(notifProblemDetails);
        }

        if (registration.getNotificationFilter() == null
                || registration.getNotificationFilter().getMatchingFilter() == null
                || registration.getNotificationFilter().getMatchingFilter().isEmpty()) {

            NotificationProblemDetails notifProblemDetails = new NotificationProblemDetails();
            notifProblemDetails.setMessage("Notification filter must be defined");

            throw new NotificationProblemDetailsException(notifProblemDetails);
        }

        SubscriberInfo subscriberInfo = new SubscriberInfo();
        subscriberInfo.setSubscriberExternalId(registration.getSubscriberExternalId());
        subscriberInfo.setDescription(registration.getDescription());
        subscriberInfo.setSubscriberId(UUID.randomUUID().toString());
        subscriberInfo.setNotificationFilter(registration.getNotificationFilter());
        subscriberInfo.setNotificationUri(registration.getNotificationUri());

        subscribers.put(subscriberInfo.getSubscriberId(), subscriberInfo);

        SubscriberRegistrationResponse registrationResponse = new SubscriberRegistrationResponse();

        registrationResponse.setDate(new Date());
        registrationResponse.setSubscriberId(subscriberInfo.getSubscriberId());
        registrationResponse.setSubscriberExternalId(subscriberInfo.getSubscriberExternalId());
        registrationResponse.setNotificationFilter(subscriberInfo.getNotificationFilter());

        registrationResponse.setOperationName("Register Subscriber");
        registrationResponse.setStatus(CoreApiOperationStatus.ACCEPTED);
        registrationResponse.setMessage("Registration was successful");

        return registrationResponse;
    }

    @Override
    public UpdateSubscriberNotificationFilterResponse updateSubscriberNotificationFilter(
            UpdateSubscriberNotificationFilterRequest updateFilter)
            throws VedoHookApiException {

        if (updateFilter == null || updateFilter.getSubscriberId() == null
                || updateFilter.getSubscriberId().isEmpty()) {

            NotificationProblemDetails notifProblemDetails = new NotificationProblemDetails();
            notifProblemDetails.setMessage("UpdateSubscriberNotificationFilterRequest can not be null");

            throw new NotificationProblemDetailsException(notifProblemDetails);
        }

        if (updateFilter.getNotificationFilter() == null
                || updateFilter.getNotificationFilter().getMatchingFilter() == null
                || updateFilter.getNotificationFilter().getMatchingFilter().isEmpty()) {

            NotificationProblemDetails notifProblemDetails = new NotificationProblemDetails();
            notifProblemDetails.setMessage("Notification filter can not be null or empty");

            throw new NotificationProblemDetailsException(notifProblemDetails);
        }

        UpdateSubscriberNotificationFilterResponse updateNotificationFilterResponse = new UpdateSubscriberNotificationFilterResponse();

        if (!subscribers.containsKey(updateFilter.getSubscriberId())) {

            // Error
            //
            NotificationProblemDetails notifProblemDetails = new NotificationProblemDetails();
            notifProblemDetails.setMessage("SubscriberId:" + updateFilter.getSubscriberId() + " not found");

            throw new NotificationProblemDetailsException(notifProblemDetails);

        } else {

            SubscriberInfo foundSubscriber = subscribers.get(updateFilter.getSubscriberId());

            // TODO: Lock the resource, before proceeding with the update
            foundSubscriber.setNotificationFilter(updateFilter.getNotificationFilter());

            updateNotificationFilterResponse.setDate(new Date());
            updateNotificationFilterResponse.setSubscriberInfo(foundSubscriber);

            updateNotificationFilterResponse.setOperationName("Update Subscriber Notification Filter");
            updateNotificationFilterResponse.setStatus(CoreApiOperationStatus.ACCEPTED);
            updateNotificationFilterResponse.setMessage("Update of Notification Filter was successful");

        }

        return updateNotificationFilterResponse;
    }

    @Override
    public UnregisterSubscriberResponse unregisterSubscriber(UnregisterSubscriberRequest registration)
            throws VedoHookApiException {

        if (registration == null || registration.getSubscriberId() == null
                || registration.getSubscriberId().isEmpty()) {

            NotificationProblemDetails notifProblemDetails = new NotificationProblemDetails();
            notifProblemDetails.setMessage("UnregisterSubscriberRequest can not be null");

            throw new NotificationProblemDetailsException(notifProblemDetails);
        }

        UnregisterSubscriberResponse unRegistrationResponse = new UnregisterSubscriberResponse();

        SubscriberInfo removedSubscriber = subscribers.remove(registration.getSubscriberId());

        if (removedSubscriber == null) {

            // Error
            //
            NotificationProblemDetails notifProblemDetails = new NotificationProblemDetails();
            notifProblemDetails.setMessage("SubscriberId:" + registration.getSubscriberId() + " not found");

            throw new NotificationProblemDetailsException(notifProblemDetails);

        } else {

            unRegistrationResponse.setDate(new Date());
            unRegistrationResponse.setSubscriberInfo(removedSubscriber);

            unRegistrationResponse.setOperationName("Unregister Subscriber");
            unRegistrationResponse.setStatus(CoreApiOperationStatus.ACCEPTED);
            unRegistrationResponse.setMessage("Unregistration was successful");

        }

        return unRegistrationResponse;
    }

    @Override
    public void publish(INotification aNotification) throws VedoHookApiException {

        for (SubscriberInfo curSubscriber : subscribers.values()) {


            // TODO: Do it properly...
            //
            if (curSubscriber.getNotificationFilter().getMatchingFilter().toString()
                    .contains(aNotification.getType().toUpperCase())) {
                log.info("Notification sent to remote site for subscriber {} notification type:{}",
                        curSubscriber.getSubscriberExternalId(), aNotification.getType().toUpperCase());

                sendNotification(aNotification);
            } else {
                log.info("Notification not matching subscriberId; {} notification type:{}",
                        curSubscriber.getSubscriberExternalId(), aNotification.getType().toUpperCase());
            }
        }

    }


    /**
     * Convenience method retuning a NotificationProducer to the caller. In our
     * current flow the object will have to be used to register "local"
     * notification producers.
     */
    @Override
    public INotificationProducer createNotificationProducer() throws VedoHookApiException {

        // Create a new notification producer
        //
        return new NotificationProducer();

    }


    @Override
    public void registerNotificationProducer(INotificationProducer aNotificationProducer) throws VedoHookApiException {

        if (aNotificationProducer == null) {
            throw new InvalidArgumentsException("Notification producer can not be null");
        }

        if (notificationProducers.containsKey(aNotificationProducer.getUniqueIdentifier())) {
            throw new VedoHookApiException("Notification producer is already registered");
        }

        notificationProducers.put(aNotificationProducer.getUniqueIdentifier(), aNotificationProducer);

    }

    @Override
    public List<INotificationProducer> getRegisteredNotificationProducers() {
        return new ArrayList<>(notificationProducers.values());
    }

    @Override
    public void unRegisterNotificationProducer(INotificationProducer aNotificationProducer) throws VedoHookApiException {

        if (aNotificationProducer == null) {
            throw new InvalidArgumentsException("Notification producer can not be null");
        }

        if (!notificationProducers.containsKey(aNotificationProducer.getUniqueIdentifier())) {
            throw new VedoHookApiException("Notification producer is not registered");
        }

        notificationProducers.remove(aNotificationProducer.getUniqueIdentifier());

    }

    /**
     * Method to be used by the notification producers
     */
    @Override
    public void publish(INotificationProducer producerHandle, INotification aNotification) throws VedoHookApiException {

        if (producerHandle == null || aNotification == null) {
            throw new InvalidArgumentsException("Invalid arguments");
        }

        if (!notificationProducers.containsKey(producerHandle.getUniqueIdentifier())) {
            throw new VedoHookApiException("Unknown notification producer");
        }

        // All good, we are now ready to add the notification within the queue
        //
        try {

            log.info("Publisher {} added a new notification to the queue", producerHandle.getUniqueIdentifier());

            // Will unblock the remote publishers
            //
            notificationQueue.put(aNotification);

            // StopPublisher stopPublisher = new StopPublisher();
            // notificationQueue.put(stopPublisher);

        } catch (InterruptedException e) {
            throw new VedoHookApiException("Publishing Thread exception issue");
        }

    }

    private void sendNotification(final INotification notification) {

        final String endpointUrl = "http://localhost:9595/core/notifications/receiver";

        try {
            publisherService.execute(new Runnable() {
                @Override
                public void run() {

                    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
                    factory.setReadTimeout(30);
                    factory.setConnectTimeout(5);
                    RestTemplate restTemplate = new RestTemplate(factory);

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

                    try {
                        ((Notification) notification).setTime(new Date());
                        String payload = new ObjectMapper().writeValueAsString(notification);
                        log.info("Sending notification: {}", payload);
                        HttpEntity<Notification> httpRequest = new HttpEntity<>((Notification) notification, headers);
                        ResponseEntity<String> receivedInformation = restTemplate.postForEntity(endpointUrl,
                                httpRequest, String.class);
                        log.info("Remote server {} responded with [{}].", endpointUrl,
                                receivedInformation.getStatusCode());
                        if (receivedInformation.getStatusCode() != HttpStatus.NO_CONTENT
                                && receivedInformation.getStatusCode() != HttpStatus.OK) {
                            log.error("Notification was not accepted by remote server: {}", endpointUrl);
                        } else {
                            log.info("Notification has been accepted by remote server: {}", endpointUrl);
                        }
                    } catch (Exception ex) {
                        log.error("Notification failed wtih exception.");
                        log.error("Error during post notification. {}", ex);
                    }
                }
            });
        } catch (IllegalThreadStateException e) {
            log.error("Error during post notification." + e);
        }
    }

    private class NotificationPublisher extends Thread {

        private BlockingQueue<INotification> notificationQueue;

        NotificationPublisher(BlockingQueue<INotification> notificationQueue) {
            this.notificationQueue = notificationQueue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    
                    INotification aNotification = notificationQueue.take();
                    
                    if (aNotification instanceof StopPublisher) {
                        log.info("Notification publisher has been stopped");
                        break;
                    }

                    for (SubscriberInfo curSubscriber : subscribers.values()) {

                        // TODO: Look into proper infrastructure around
                        // filtering of notification
                        //
                        if (curSubscriber.getNotificationFilter().getMatchingFilter().toString()
                                .contains(aNotification.getType().toUpperCase())) {
                            log.info("Notification sent to remote site for subscriberId:{} notification type:{}",
                                    curSubscriber.getSubscriberExternalId(), aNotification.getType().toUpperCase());

                            sendNotification(aNotification);
                        } else {
                            log.info("Notification not matching subscriberId:{} notification type:{}",
                                    curSubscriber.getSubscriberExternalId(), aNotification.getType().toUpperCase());
                        }
                    }
                }
                
            } catch (InterruptedException e) {
                log.error("Notification publisher has been interrupted {}", e.getMessage());
            }
        }
    }

}
