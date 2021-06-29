package us.velocity.client.impl.modules.client;

import org.lwjgl.input.Keyboard;
import us.velocity.client.api.module.Module;

public class HUD extends Module
{
    public HUD() {
        super("HUD", Category.CLIENT, "In game hud of information.");
        this.key = Keyboard.KEY_NONE;
    }
}
