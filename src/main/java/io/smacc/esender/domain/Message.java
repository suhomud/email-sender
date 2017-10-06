package io.smacc.esender.domain;

public class Message {

	private String subject;
	private String text;

	public Message() {
	}

	public Message(String subject, String text) {
		this.subject = subject;
		this.text = text;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message{" +
				"subject='" + subject + '\'' +
				", text='" + text + '\'' +
				'}';
	}

}
