package us.velocity.client.api.events;

import us.velocity.client.api.events.bus.Event;

public class ChatEvent extends Event
{
	private String message;

	public ChatEvent(String message) {
		this.message = message;
	}

	public static class Send extends ChatEvent
	{
		public Send(String message) {
			super(message);
		}
	}

	public static class Receive extends ChatEvent
	{
		private String sender;

		public Receive(String sender, String message) {
			super(message);
			this.sender = sender;
		}

		public String getSender() {
			return this.sender;
		}
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
