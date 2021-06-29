package us.velocity.client.impl.modules.misc;

import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.module.Module;

public class Music extends Module
{
    public Music() {
        super("Music", Category.EXPLOIT, "Plays record discs.");
        this.key = Keyboard.KEY_NONE;
    }

    public void onEnable() {
        Velocity.mc.soundHelper.playSound("note.bd", 1.0f, 0.1f);
    }
}
