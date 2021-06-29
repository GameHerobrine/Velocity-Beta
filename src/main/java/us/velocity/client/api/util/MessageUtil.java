package us.velocity.client.api.util;

import us.velocity.client.Velocity;

public class MessageUtil
{
	public static void sendClientMessage(String message) {
		Velocity.mc.overlay.addChatMessage((TextFormatting.DARK_PURPLE + "[Velocity] " + TextFormatting.RESET + message));
	}

	public static void sendPublicMessage(String message) {
		Velocity.mc.player.sendChatMessage(message);
	}
}