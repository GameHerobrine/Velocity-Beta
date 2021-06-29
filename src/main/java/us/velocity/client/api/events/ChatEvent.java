package us.velocity.client.api.events;

import us.velocity.client.api.events.bus.Event;

public class ChatEvent extends Event
{
	public String message;
	
	public ChatEvent(String message) {
		this.message = message;
	}
}
