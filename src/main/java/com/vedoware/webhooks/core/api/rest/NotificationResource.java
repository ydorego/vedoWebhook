package com.vedoware.webhooks.core.api.rest;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vedoware.webhooks.core.api.exceptions.EscCoreApiException;
import com.vedoware.webhooks.core.api.notifications.ICoreApiLocalNotificationProducer;
import com.vedoware.webhooks.core.api.rest.exceptions.NotificationProblemDetailsException;
import com.vedoware.webhooks.core.api.rest.notifications.ICoreApiNotificationService;
import com.vedoware.webhooks.core.api.rest.notifications.INotificationProducer;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.Notification;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.NotificationFilter;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.NotificationProblemDetails;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberInfo;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberInfoList;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberRegistrationRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberRegistrationResponse;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UnregisterSubscriberResponse;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UpdateSubscriberNotificationFilterRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UpdateSubscriberNotificationFilterResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/escCore/notifications")
public class NotificationResource {

    static Logger log = LoggerFactory.getLogger(NotificationResource.class);

    @Autowired
    ICoreApiNotificationService coreApiNotificationServiceRef;

    @Autowired
    ICoreApiLocalNotificationProducer coreApiNotificationProducerRef;


    @RequestMapping(value = "/subscribers", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Get all subscribers", notes = "The method returns all subscriber information", response = SubscriberInfoList.class)
    @ApiResponses(value = { @ApiResponse(code = 202, message = "Request accepted"),
            @ApiResponse(code = 400, message = "Bad Request", response = NotificationProblemDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = NotificationProblemDetails.class),
            @ApiResponse(code = 503, message = "Service Unavailable", response = NotificationProblemDetails.class) })
    public ResponseEntity<SubscriberInfoList> getSubscribers() throws EscCoreApiException {

        SubscriberInfoList notificationSubscribers = coreApiNotificationServiceRef.getSubscribersInfo();

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<SubscriberInfoList>(notificationSubscribers, status);
    }

    @RequestMapping(value = "/subscribers", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Register Subscriber", notes = "The method registers a new subscriber", response = SubscriberRegistrationResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Request accepted"),
            @ApiResponse(code = 400, message = "Bad Request", response = NotificationProblemDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = NotificationProblemDetails.class),
            @ApiResponse(code = 503, message = "Service Unavailable", response = NotificationProblemDetails.class) })
    public ResponseEntity<SubscriberRegistrationResponse> registerSubscriber(
            @RequestBody SubscriberRegistrationRequest subscriberRegistration) throws EscCoreApiException {

        SubscriberRegistrationResponse registrationResponse = coreApiNotificationServiceRef
                .registerSubscriber(subscriberRegistration);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<SubscriberRegistrationResponse>(registrationResponse, status);
    }

    @RequestMapping(value = "/subscribers/{subscriberId}", method = RequestMethod.DELETE, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Unregister Subscriber", notes = "The method unregister a subscriber", response = UnregisterSubscriberResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Request accepted"),
            @ApiResponse(code = 400, message = "Bad Request", response = NotificationProblemDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = NotificationProblemDetails.class),
            @ApiResponse(code = 503, message = "Service Unavailable", response = NotificationProblemDetails.class) })
    public ResponseEntity<UnregisterSubscriberResponse> unregisterSubscriber(
            @PathVariable(value = "subscriberId") String subscriberId) throws EscCoreApiException {

        if (subscriberId == null || subscriberId.isEmpty()) {

            NotificationProblemDetails notifProblemDetails = new NotificationProblemDetails();
            notifProblemDetails.setMessage("Invalid arguments, subscriberId must be defined");

            throw new NotificationProblemDetailsException(notifProblemDetails);
        }

        SubscriberInfo subscriberInfo = coreApiNotificationServiceRef.getSubscriberInfo(subscriberId);

        UnregisterSubscriberResponse unregisterResponse = new UnregisterSubscriberResponse();

        unregisterResponse.setSubscriberInfo(subscriberInfo);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<UnregisterSubscriberResponse>(unregisterResponse, status);
    }

    @RequestMapping(value = "/subscribers/{subscriberId}", method = RequestMethod.PUT, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Unregister Subscriber", notes = "The method update the filter of a subscriber", response = UpdateSubscriberNotificationFilterResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Request accepted"),
            @ApiResponse(code = 400, message = "Bad Request", response = NotificationProblemDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = NotificationProblemDetails.class),
            @ApiResponse(code = 503, message = "Service Unavailable", response = NotificationProblemDetails.class) })
    public ResponseEntity<UpdateSubscriberNotificationFilterResponse> updateSubscriberNotificationFilter(
            @PathVariable(value = "subscriberId") String subscriberId,
            @RequestBody UpdateSubscriberNotificationFilterRequest updateNotificationFilter)
            throws EscCoreApiException {

        if (subscriberId == null || subscriberId.isEmpty()) {

            NotificationProblemDetails notifProblemDetails = new NotificationProblemDetails();
            notifProblemDetails.setMessage("Invalid arguments, subscriberId must be defined");

            throw new NotificationProblemDetailsException(notifProblemDetails);
        }

        UpdateSubscriberNotificationFilterResponse updateResponse = coreApiNotificationServiceRef
                .updateSubscriberNotificationFilter(updateNotificationFilter);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<UpdateSubscriberNotificationFilterResponse>(updateResponse, status);
    }

    // -----------------------------------------------------------------------------------------------
    // Convenience methods for designer test
    // -----------------------------------------------------------------------------------------------

    @RequestMapping(value = "/samplePayload", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<SubscriberRegistrationRequest> getSamplePayload() throws EscCoreApiException {

        SubscriberRegistrationRequest operationResponse = new SubscriberRegistrationRequest();

        operationResponse.setDescription("Sample Description");

        NotificationFilter notificationFilter = new NotificationFilter();

        notificationFilter.addMatchingFilter(NotificationFilter.FILTER_ITEM.VNF_CREATED);
        notificationFilter.addMatchingFilter(NotificationFilter.FILTER_ITEM.VNF_UPDATED);
        notificationFilter.addMatchingFilter(NotificationFilter.FILTER_ITEM.VNF_REBOOTED);
        notificationFilter.addMatchingFilter(NotificationFilter.FILTER_ITEM.VNF_DELETED);

        operationResponse.setNotificationFilter(notificationFilter);

        operationResponse.setNotificationUri("http://localhost:9595/escCore/notifications/receiver");
        operationResponse.setSubscriberExternalId("123-456-789");

        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<SubscriberRegistrationRequest>(operationResponse, status);

    }

    @RequestMapping(value = "/sampleNotificationPayload", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Notification> getSampleNotificationPayload() throws EscCoreApiException {

        Notification notificationPayloadResponse = new Notification();

        notificationPayloadResponse.setTime(new Date());
        notificationPayloadResponse.setType(NotificationFilter.FILTER_ITEM.VNF_CREATED.name());
        notificationPayloadResponse.setName("Sample Notification Payload");
        notificationPayloadResponse.setDetails("Sample Details");
        notificationPayloadResponse.setExternalVnfInstanceId("externalVnfInstanceId");
        notificationPayloadResponse.setInternalVnfInstanceId("VnfIntanceId");

        //Properties additionalParameters = new Properties();
        //additionalParameters.anySetter("samplePropertyOne", "One");
        //additionalParameters.anySetter("samplePropertyTwo", "Two");

        //notificationPayloadResponse.setAdditionalParameters(additionalParameters);

        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<Notification>(notificationPayloadResponse, status);

    }

    /**
     * Convenience method to kick start the notification process, used only for
     * demonstration purposes.
     * 
     * @param subscriberRegistration
     * @return
     * @throws EscCoreApiException
     */
    @RequestMapping(value = "/publishNotification", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> kickPublisher(@RequestBody Notification aNotification)
            throws EscCoreApiException {


        // --------------------------------------------------------------------------------------
        // No special validation, we are just using it for designer testing.
        // --------------------------------------------------------------------------------------

        // Simulate a local publisher
        //
        INotificationProducer localPublisher = coreApiNotificationProducerRef.createNotificationProducer();
        coreApiNotificationProducerRef.registerNotificationProducer(localPublisher);

        coreApiNotificationProducerRef.publish(localPublisher, aNotification);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<String>("All Good", status);
    }

    /**
     * Convenience method to kick start the notification process
     * 
     * @param subscriberRegistration
     * @return
     * @throws EscCoreApiException
     */
    @RequestMapping(value = "/receiver", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> notificationReceiver(@RequestBody Notification aNotification)
            throws EscCoreApiException {

        log.info("*****************************************************************************");
        log.info(aNotification.toString());
        log.info("*****************************************************************************");

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<String>("All Good", status);
    }

}
