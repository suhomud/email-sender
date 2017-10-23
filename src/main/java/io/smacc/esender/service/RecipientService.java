package io.smacc.esender.service;

import io.smacc.esender.domain.Recipient;
import io.smacc.esender.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecipientService {

	private final RecipientRepository recipientRepository;

	@Autowired
	public RecipientService(RecipientRepository recipientRepository) {
		this.recipientRepository = recipientRepository;
	}

	public List<Recipient> getAll() {
		return recipientRepository.getAll();
	}


	public void clear() {
		recipientRepository.clear();
	}

	public void add(Recipient recipient) {
		recipientRepository.add(recipient);
	}

}
