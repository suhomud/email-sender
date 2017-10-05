package io.smacc.esender.service;

import io.smacc.esender.domain.Recipient;
import io.smacc.esender.repository.SenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SenderService {

	private final RecipientService recipientService;
	private final SenderRepository senderRepository;

	@Autowired
	public SenderService(RecipientService recipientService, SenderRepository senderRepository) {
		this.recipientService = recipientService;
		this.senderRepository = senderRepository;
	}

	public void send(String text) {
		List<Recipient> recipients = recipientService.getAll();
		senderRepository.sendToAll(recipients, text);
	}

}
