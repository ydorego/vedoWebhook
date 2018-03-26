package com.vedoware.webhooks.core.api.notifications.impl;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.vedoware.webhooks.core.VedoWebhookApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { VedoWebhookApplication.class })
@WebAppConfiguration
@AutoConfigureMockMvc
public class VedoWebhookApplicationTest {

	@Autowired
	private MockMvc mockMvc;
		
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
    public void shouldReturnSamplePayload() throws Exception {
        this.mockMvc.perform(get("/core/notifications/samplePayload")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sample Description")));
    }
	
}
