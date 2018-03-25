package com.vedoware.webhooks.core.api.notifications.impl;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vedoware.webhooks.core.api.exceptions.VedoHookApiException;
import com.vedoware.webhooks.core.api.exceptions.InvalidArgumentsException;
import com.vedoware.webhooks.core.api.notifications.impl.ApiNotificationService;
import com.vedoware.webhooks.core.api.notifications.impl.NotificationProducer;
import com.vedoware.webhooks.core.api.rest.exceptions.NotificationProblemDetailsException;
import com.vedoware.webhooks.core.api.rest.notifications.INotificationProducer;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.NotificationFilter;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberInfo;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberInfoList;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.SubscriberRegistrationRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UnregisterSubscriberRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.UpdateSubscriberNotificationFilterRequest;
import com.vedoware.webhooks.core.api.rest.notifications.payloads.NotificationFilter.FILTER_ITEM;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebhookNotificationServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    // -----------------------
    // Notification Producer
    // -----------------------

    @Test(expected = InvalidArgumentsException.class)
    public void testRegisterNotificationProducerNullArg() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        INotificationProducer aNotificationProducer = null;
        notificationServiceUnderTest.registerNotificationProducer(aNotificationProducer);

    }

    @Test(expected = VedoHookApiException.class)
    public void testRegisterNotificationProducerAlreadyRegister() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        INotificationProducer aNotificationProducer = new NotificationProducer();
        notificationServiceUnderTest.registerNotificationProducer(aNotificationProducer);
        notificationServiceUnderTest.registerNotificationProducer(aNotificationProducer);

    }

    @Test(expected = VedoHookApiException.class)
    public void testRegisterNotificationProducer() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        INotificationProducer aNotificationProducer = new NotificationProducer();
        notificationServiceUnderTest.registerNotificationProducer(aNotificationProducer);
        
        List<INotificationProducer> registeredProducers = notificationServiceUnderTest
                .getRegisteredNotificationProducers();

        Assert.assertEquals(registeredProducers.size(), 1);

        Assert.assertEquals(registeredProducers.get(0).getUniqueIdentifier(),
                aNotificationProducer.getUniqueIdentifier());
        notificationServiceUnderTest.registerNotificationProducer(aNotificationProducer);        

    }

    // --------------------------
    // Subscriber Registration
    // ---------------------------

    @Test(expected = NotificationProblemDetailsException.class)
    public void testSubscriberRegistrationNullArgs() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        SubscriberRegistrationRequest registration = null;
        notificationServiceUnderTest.registerSubscriber(registration);

    }

    @Test(expected = NotificationProblemDetailsException.class)
    public void testSubscriberRegistrationNullOrEmptyUriField() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        SubscriberRegistrationRequest registration = new SubscriberRegistrationRequest();
        registration.setNotificationUri("");
        notificationServiceUnderTest.registerSubscriber(registration);

    }

    @Test(expected = NotificationProblemDetailsException.class)
    public void testSubscriberRegistrationNullOrEmptyFilterField() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        SubscriberRegistrationRequest registration = new SubscriberRegistrationRequest();
        registration.setNotificationUri("Fake URI");
        registration.setNotificationFilter(null);

        notificationServiceUnderTest.registerSubscriber(registration);

    }

    @Test(expected = NotificationProblemDetailsException.class)
    public void testSubscriberRegistrationNullOrEmptyExternalIdField() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        SubscriberRegistrationRequest registration = new SubscriberRegistrationRequest();
        registration.setNotificationUri("Fake URI");
        registration.setNotificationFilter(new NotificationFilter());
        registration.setSubscriberExternalId(null);

        notificationServiceUnderTest.registerSubscriber(registration);

    }

    @Test(expected = NotificationProblemDetailsException.class)
    public void testSubscriberRegistrationInvalidNotificationFilter() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        SubscriberRegistrationRequest registration = new SubscriberRegistrationRequest();
        registration.setNotificationUri("Fake URI");
        NotificationFilter notifFilter = new NotificationFilter();
        registration.setNotificationFilter(notifFilter);

        notificationServiceUnderTest.registerSubscriber(registration);
    }

    @Test(expected = NotificationProblemDetailsException.class)
    public void testSubscriberRegistrationInvalidNotificationNullFilter() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        SubscriberRegistrationRequest registration = new SubscriberRegistrationRequest();
        registration.setNotificationUri("Fake URI");
        NotificationFilter notifFilter = new NotificationFilter();
        notifFilter.setMatchingFilter(null);
        registration.setNotificationFilter(notifFilter);

        notificationServiceUnderTest.registerSubscriber(registration);
    }

    @Test
    public void testSubscriberRegistration() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        SubscriberRegistrationRequest registration = new SubscriberRegistrationRequest();
        registration.setNotificationUri("Fake URI");
        registration.setSubscriberExternalId("My fake ExternalId");

        NotificationFilter notifFilter = new NotificationFilter();
        notifFilter.addMatchingFilter(FILTER_ITEM.VNF_);
        registration.setNotificationFilter(notifFilter);

        notificationServiceUnderTest.registerSubscriber(registration);

        SubscriberInfoList mySubscribers = notificationServiceUnderTest.getSubscribersInfo();

        Assert.assertNotNull(mySubscribers);
        Assert.assertNotNull(mySubscribers.getSubscribers());
        Assert.assertEquals(mySubscribers.getSubscribers().size(), 1);

        SubscriberInfo subscriberInfo = mySubscribers.getSubscribers().get(0);

        Assert.assertNotNull(subscriberInfo);

        Assert.assertEquals(registration.getNotificationUri(), subscriberInfo.getNotificationUri());
        Assert.assertEquals(registration.getSubscriberExternalId(), subscriberInfo.getSubscriberExternalId());
        Assert.assertEquals(registration.getNotificationFilter(), subscriberInfo.getNotificationFilter());

    }

    @Test
    public void testSubscriberUnRegistration() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        SubscriberRegistrationRequest registration = new SubscriberRegistrationRequest();
        registration.setNotificationUri("Fake URI");
        registration.setSubscriberExternalId("My fake ExternalId");

        NotificationFilter notifFilter = new NotificationFilter();
        notifFilter.addMatchingFilter(FILTER_ITEM.VNF_);
        registration.setNotificationFilter(notifFilter);

        notificationServiceUnderTest.registerSubscriber(registration);

        SubscriberInfoList mySubscribers = notificationServiceUnderTest.getSubscribersInfo();

        Assert.assertNotNull(mySubscribers);
        Assert.assertNotNull(mySubscribers.getSubscribers());
        Assert.assertEquals(mySubscribers.getSubscribers().size(), 1);

        SubscriberInfo subscriberInfo = mySubscribers.getSubscribers().get(0);

        Assert.assertNotNull(subscriberInfo);

        Assert.assertEquals(registration.getNotificationUri(), subscriberInfo.getNotificationUri());
        Assert.assertEquals(registration.getSubscriberExternalId(), subscriberInfo.getSubscriberExternalId());
        Assert.assertEquals(registration.getNotificationFilter(), subscriberInfo.getNotificationFilter());

        UnregisterSubscriberRequest unRegistration = new UnregisterSubscriberRequest();
        unRegistration.setSubscriberId(subscriberInfo.getSubscriberId());

        notificationServiceUnderTest.unregisterSubscriber(unRegistration);

    }

    @Test
    public void testSubscriberUpdateFilterRegistration() throws VedoHookApiException {

        ApiNotificationService notificationServiceUnderTest = new ApiNotificationService();

        SubscriberRegistrationRequest registration = new SubscriberRegistrationRequest();
        registration.setNotificationUri("Fake URI");
        registration.setSubscriberExternalId("My fake ExternalId");

        NotificationFilter notifFilter = new NotificationFilter();
        notifFilter.addMatchingFilter(FILTER_ITEM.VNF_);
        registration.setNotificationFilter(notifFilter);

        notificationServiceUnderTest.registerSubscriber(registration);

        SubscriberInfoList mySubscribers = notificationServiceUnderTest.getSubscribersInfo();

        Assert.assertNotNull(mySubscribers);
        Assert.assertNotNull(mySubscribers.getSubscribers());
        Assert.assertEquals(mySubscribers.getSubscribers().size(), 1);

        SubscriberInfo subscriberInfo = mySubscribers.getSubscribers().get(0);

        Assert.assertNotNull(subscriberInfo);

        Assert.assertEquals(registration.getNotificationUri(), subscriberInfo.getNotificationUri());
        Assert.assertEquals(registration.getSubscriberExternalId(), subscriberInfo.getSubscriberExternalId());
        Assert.assertEquals(registration.getNotificationFilter(), subscriberInfo.getNotificationFilter());

        // Update Filter
        //
        UpdateSubscriberNotificationFilterRequest updateFilter = new UpdateSubscriberNotificationFilterRequest();
        updateFilter.setSubscriberId(subscriberInfo.getSubscriberId());
        subscriberInfo.getNotificationFilter().addMatchingFilter(FILTER_ITEM.VNF_VM_CREATED);
        subscriberInfo.getNotificationFilter().addMatchingFilter(FILTER_ITEM.VNF_VM_TERMINATED);

        updateFilter.setNotificationFilter(subscriberInfo.getNotificationFilter());

        notificationServiceUnderTest.updateSubscriberNotificationFilter(updateFilter);

        // Get the newly pushed, filter...
        //
        mySubscribers = notificationServiceUnderTest.getSubscribersInfo();
        List<FILTER_ITEM> myFilter = mySubscribers.getSubscribers().get(0).getNotificationFilter().getMatchingFilter();

        Assert.assertTrue(myFilter.contains(FILTER_ITEM.VNF_VM_CREATED));
        Assert.assertTrue(myFilter.contains(FILTER_ITEM.VNF_VM_TERMINATED));
        Assert.assertTrue(myFilter.contains(FILTER_ITEM.VNF_));

        Assert.assertEquals(registration.getNotificationUri(), subscriberInfo.getNotificationUri());

        UnregisterSubscriberRequest unRegistration = new UnregisterSubscriberRequest();
        unRegistration.setSubscriberId(subscriberInfo.getSubscriberId());

        notificationServiceUnderTest.unregisterSubscriber(unRegistration);

    }

}
