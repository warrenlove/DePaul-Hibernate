package edu.depaul.hibernate.domain;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String message;
	private Message nextMessage;

	public long getId() {
		return id;
	}

	public void setId(long newId) {
		id = newId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String newMessage) {
		message = newMessage;
	}

	public Message getNextMessage() {
		return nextMessage;
	}

	public void setNextMessage(Message message) {
		nextMessage = message;
	}
}