package us.velocity.client.impl.modules.movement;


import org.lwjgl.input.Keyboard;
import us.velocity.client.api.module.Module;

public class Jesus extends Module
{
    public Jesus() {
        super("Jesus", Category.RENDER, "Walk on water");
        this.key = Keyboard.KEY_NONE;
    }


}

