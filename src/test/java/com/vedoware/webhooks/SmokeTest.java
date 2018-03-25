package com.vedoware.webhooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vedoware.webhooks.core.VedoWebhookApplication;
import com.vedoware.webhooks.core.api.notifications.impl.ApiNotificationService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { VedoWebhookApplication.class })
@WebAppConfiguration
public class SmokeTest {

	@Autowired
    private ApiNotificationService apiNotificationService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

    @Test
    public void contexLoads() throws Exception {
    		Assert.assertNotNull(apiNotificationService);
    }
    
}
