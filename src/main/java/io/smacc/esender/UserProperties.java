package io.smacc.esender;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("email.sender")
public class UserProperties {

	/**
	 *  Sender email address
	 *
	 *  email.sender.user.from property
	 */
	private String userFrom;

	public String getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}

}
