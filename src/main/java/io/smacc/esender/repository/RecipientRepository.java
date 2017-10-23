package io.smacc.esender.repository;

import io.smacc.esender.domain.Recipient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RecipientRepository {

	private Set<Recipient> recipients = new HashSet<>();

	public List<Recipient> getAll() {
		return new ArrayList<>(recipients);
	}

	public void clear() {
		recipients.clear();
	}

	public void add(Recipient recipient) {
		recipients.add(recipient);
	}
}
