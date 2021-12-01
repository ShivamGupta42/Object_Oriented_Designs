package designs.notificationSystem;

import java.time.LocalDateTime;

public class Message {
	LocalDateTime timeStamp;
	String message;

	public Message(Message original) {
		this.message = original.getMessage();
		this.timeStamp = LocalDateTime.now();
	}

	public Message(String message) {
		this.timeStamp = LocalDateTime.now();
		this.message = message;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
