package us.velocity.client.impl.modules.render;

import org.lwjgl.input.Keyboard;
import us.velocity.client.api.module.Module;

public class Fullbright extends Module
{
    public Fullbright() {
        super("Fullbright", Category.RENDER, "Completely brightens everything.");
        this.key = Keyboard.KEY_NONE;
    }
}
