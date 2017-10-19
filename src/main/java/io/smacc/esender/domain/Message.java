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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Message message = (Message) o;

		if (subject != null ? !subject.equals(message.subject) : message.subject != null) return false;
		return text != null ? text.equals(message.text) : message.text == null;
	}

	@Override
	public int hashCode() {
		int result = subject != null ? subject.hashCode() : 0;
		result = 31 * result + (text != null ? text.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Message{" +
				"subject='" + subject + '\'' +
				", text='" + text + '\'' +
				'}';
	}

}
