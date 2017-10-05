package io.smacc.esender.repository;

import io.smacc.esender.domain.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SenderRepository {

	private static final Logger log = LoggerFactory.getLogger(SenderRepository.class);

	public void sendToAll(List<Recipient> recipients, String text) {
		log.info("send to recipients={}, text={}", recipients.toString(), text);
	}

}
