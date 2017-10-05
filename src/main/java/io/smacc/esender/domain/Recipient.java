package io.smacc.esender.domain;

public class Recipient {

	private String email;

	public Recipient() {
	}

	public Recipient(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Recipient recipient = (Recipient) o;

		return email.equals(recipient.email);
	}

	@Override
	public int hashCode() {
		return email.hashCode();
	}

	@Override
	public String toString() {
		return "Recipient{" +
				"email='" + email + '\'' +
				'}';
	}

}
