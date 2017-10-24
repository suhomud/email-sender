package io.smacc.esender;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("email.sender.user")
public class UserProperties {

	/**
	 *  Sender email address
	 *
	 *  email.sender.user.from property
	 */
	@NotBlank
	@Email
	private String from;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
