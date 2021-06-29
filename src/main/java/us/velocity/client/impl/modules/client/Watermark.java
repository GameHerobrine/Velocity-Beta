package us.velocity.client.impl.modules.client;

import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.util.ColorUtil;

public class Watermark extends Module
{

    public Watermark() {
        super("Watermark", Category.CLIENT, "Draws the client name on your screen.");
        this.key = Keyboard.KEY_Y;
    }

    @Override
    public void onRender2d() {
        Velocity.mc.textRenderer.drawTextWithShadow("Velocity-Fabric", 2, 2, ColorUtil.toRGBA(100, 0, 255, 255));
    }
}
