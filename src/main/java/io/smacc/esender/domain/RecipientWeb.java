package io.smacc.esender.domain;

import java.util.ArrayList;
import java.util.List;

public class RecipientWeb {

	List<Recipient> recipients;

	public RecipientWeb(List<Recipient> recipients) {
		this.recipients = new ArrayList<>(recipients);
	}

	public List<Recipient> getRecipients() {
		return recipients;
	}

}
