package io.smacc.esender.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.smacc.esender.Application;
import io.smacc.esender.domain.Message;
import io.smacc.esender.domain.Recipient;
import io.smacc.esender.provider.SendgridProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmailSenderControllerTest {

	@InjectMocks
	RecipientController recipientController;
	@InjectMocks
	SenderController senderController;

	@MockBean
	SendgridProvider mockProvider;

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
	public void shouldTryToSendEmailToRecipients() throws Exception {
		// GIVEN
		List<Recipient> recipients = new ArrayList<>(generateRecipients(1));
		Message message = new Message("test subject", "test text");
		byte[] recipientsJson = toJson(recipients);
		byte[] messageJson = toJson(message);
		// ADD
		mvc.perform(post("/recipient")
				.content(recipientsJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		// SEND
		mvc.perform(post("/sender")
				.content(messageJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		// VERIFY
		verify(mockProvider, times(1)).trySend(recipients, message);
	}

	@Test
	public void shouldReturnBadRequestWhenSendWithoutRecipients() throws Exception {
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