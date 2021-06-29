package us.velocity.client.impl.modules.client;

import org.lwjgl.input.Keyboard;
import us.velocity.client.api.module.Module;

public class Notify extends Module
{
	public Notify() {
		super("Notify", Category.CLIENT, "Notifies in chat when modules are enabled");
		this.key = Keyboard.KEY_NONE;
	}
}