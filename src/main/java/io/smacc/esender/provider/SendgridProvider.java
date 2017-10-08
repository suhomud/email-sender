package io.smacc.esender.provider;

import com.sendgrid.*;
import io.smacc.esender.ProviderProperties;
import io.smacc.esender.UserProperties;
import io.smacc.esender.domain.Message;
import io.smacc.esender.domain.Recipient;
import io.smacc.esender.exception.NoRecipientsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Component
public class SendgridProvider implements EmailProvider {

	private static final Logger log = LoggerFactory.getLogger(SendgridProvider.class);

	private static final String PROVIDER_NAME = "https://app.sendgrid.com";
	private static final String CONTENT_TYPE = "text/plain";
	private static final String END_POINT = "mail/send";

	private final UserProperties userProperties;
	private final ProviderProperties providerProperties;

	@Autowired
	public SendgridProvider(UserProperties up, ProviderProperties pp) {
		assertNotNull(up.getFrom(), "please, set up address from");
		assertNotNull(pp.getSendgridApiKey(), "please, set up api key");
		this.userProperties = up;
		this.providerProperties = pp;
	}

	@Override
	public void trySend(List<Recipient> recipients, Message message) throws Exception {
		log.debug("trying to send message from={} to recipients={} by provider={}",
				userProperties.getFrom(),
				Arrays.toString(recipients.toArray()),
				PROVIDER_NAME);
		Email from = new Email(userProperties.getFrom());
		String subject = message.getSubject();
		Content content = new Content(CONTENT_TYPE, message.getText());

		for (Recipient recipient : recipients) {
			Email to = new Email(recipient.getEmail());
			Mail mail = new Mail(from, subject, to, content);
			SendGrid sg = new SendGrid(System.getenv(providerProperties.getSendgridApiKey()));
			Request request = new Request();

			request.setMethod(Method.POST);
			request.setEndpoint(END_POINT);
			request.setBody(mail.build());
			sg.api(request);
		}
	}

	@Override
	public String toString() {
		return "SendgridProvider{" +
				"provider name=" + PROVIDER_NAME +
				"}";
	}

}
