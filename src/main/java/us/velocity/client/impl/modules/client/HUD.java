package us.velocity.client.impl.modules.client;

import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.util.ScreenScaler;
import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.module.ModuleManager;
import us.velocity.client.api.util.ColorUtil;
import us.velocity.client.api.util.RenderUtil;

public class HUD extends Module
{
    public HUD() {
        super("HUD", Category.CLIENT, "In game hud of information.");
        this.key = Keyboard.KEY_NONE;
    }

    @Override
    public void onRender2d() {
        ScreenScaler scaler = new ScreenScaler(Velocity.mc.options, Velocity.mc.actualWidth, Velocity.mc.actualHeight);
        TextRenderer fr = Velocity.mc.textRenderer;
        int yOff = 0;
        for(Module m : ModuleManager.getModules()) {
            if(!m.isEnabled()) continue;
            ColorUtil.drawGradientStringWithShadow(m.getName(), scaler.getScaledWidth() - fr.getTextWidth(m.getName()), 2 + yOff * 9);
            yOff++;
        }
    }
}
