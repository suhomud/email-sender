package io.smacc.esender;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("email.sender.provider")
public class ProviderProperties {

	/**
	 *  https://app.sendgrid.com api key
	 *
	 *  email.sender.provider.sendgridApiKey property
	 *
	 */
	@NotBlank
	private String sendgridApiKey;

	public String getSendgridApiKey() {
		return sendgridApiKey;
	}

	public void setSendgridApiKey(String sendgridApiKey) {
		this.sendgridApiKey = sendgridApiKey;
	}

}
