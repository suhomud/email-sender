package io.smacc.esender.provider;

import io.smacc.esender.domain.Message;
import io.smacc.esender.domain.Recipient;

import java.util.List;

public interface EmailProvider {

	void trySend(List<Recipient> recipients, Message message) throws Exception;

}
