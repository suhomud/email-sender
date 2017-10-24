package io.smacc.esender.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import static org.hamcrest.Matchers.hasSize;
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

	private static Gson GSON = new GsonBuilder().create();


	@Before
	public void initTests() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
		mvc.perform(delete("/recipient"));
	}

	@Test
	public void shouldReturnEmptyRecipientsList() throws Exception {
		mvc.perform(delete("/recipient"));
		mvc.perform(get("/recipient"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.recipients", hasSize(0)));
	}

	@Test
	public void shouldAddRecipientsAndReturnIt() throws Exception {
		// BEFORE
		String email = "test@email.com";
		Recipient recipient = new Recipient(email);
		String recipientJson = GSON.toJson(recipient);

		// ADD
		mvc.perform(post("/recipient")
				.content(recipientJson)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		// GET
		mvc.perform(get("/recipient"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.recipients", hasSize(1)))
				.andExpect(jsonPath("$.recipients[0].email").value(email));
	}

	@Test
	public void shouldTryToSendEmailToRecipients() throws Exception {
		// BEFORE
		String email = "test@email.com";
		Recipient recipient = new Recipient(email);
		Message message = new Message("test subject", "test text");
		String recipientsJson = GSON.toJson(recipient);
		String messageJson = GSON.toJson(message);

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
	}

	@Test
	public void shouldReturnBadRequestWhenSendWithoutRecipients() throws Exception {

		// SEND
		mvc.perform(post("/sender"))
				.andExpect(status().isBadRequest());
	}

}