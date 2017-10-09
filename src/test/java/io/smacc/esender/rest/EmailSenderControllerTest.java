package io.smacc.esender.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.smacc.esender.Application;
import io.smacc.esender.domain.Recipient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class EmailSenderControllerTest {

	@InjectMocks
	RecipientController recipientController;
	@InjectMocks
	SenderController senderController;

	@Autowired
	WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void initTests() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}


	@Test
	public void shouldReturnEmptyRecipientsList() throws Exception {
		mvc.perform(delete("/recipient"));
		mvc.perform(get("/recipient"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void shouldAddRecipientsAndReturnIt() throws Exception {
		// GIVEN
		int countOfRecipients = 2;
		List<Recipient> recipients = new ArrayList<>(generateRecipients(countOfRecipients));
		byte[] json = toJson(recipients);
		mvc.perform(delete("/recipient"));

		// ADD
		mvc.perform(post("/recipient")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		// GET
		mvc.perform(get("/recipient"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(countOfRecipients)));
	}

	@Test
	public void shouldReturnBadRequestWhenThereAreNoRecipients() throws Exception {
		// GIVEN
		mvc.perform(delete("/recipient"));

		// SEND
		mvc.perform(post("/sender"))
				.andExpect(status().isBadRequest());
	}

	private List<Recipient> generateRecipients(int count) {
		List<Recipient> recipients = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			recipients.add(new Recipient("testEmail" + i + "@email.com"));
		}
		return recipients;
	}

	private byte[] toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(r).getBytes();
	}

}