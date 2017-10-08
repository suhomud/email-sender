package io.smacc.esender.repository;

import io.smacc.esender.domain.Message;
import io.smacc.esender.domain.Recipient;
import io.smacc.esender.exception.NoRecipientsException;
import io.smacc.esender.exception.NoWorkedProvidersException;
import io.smacc.esender.provider.EmailProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SenderRepository {

	private static final Logger log = LoggerFactory.getLogger(SenderRepository.class);

	final List<EmailProvider> emailProviders;

	@Autowired
	public SenderRepository(List<EmailProvider> emailProviders) {
		this.emailProviders = emailProviders;
	}

	public void sendToAll(List<Recipient> recipients, Message message) {
		if (recipients.isEmpty()) {
			throw new NoRecipientsException();
		}
		boolean finished = false;
		for (EmailProvider provider : emailProviders) {
			try {
				provider.trySend(recipients, message);
				finished = true;
			} catch (Exception e) {
				log.error("Sending has failed with provider={}. error message={}", provider.toString(), e.getMessage());
			}
		}
		if (!finished) {
			reportError();
		}

	}

	private void reportError() {
		throw new NoWorkedProvidersException();
	}

}
