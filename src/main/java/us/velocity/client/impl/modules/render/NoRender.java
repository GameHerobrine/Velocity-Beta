package us.velocity.client.impl.modules.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.setting.Setting;

public class NoRender extends Module
{
    public static NoRender noRender = new NoRender();

    public static final Setting<Boolean> fog = new Setting<Boolean>("Fog", false);
    public static final Setting<Boolean> hurtcamera = new Setting<Boolean>("HurtCam", false);
    public static final Setting<Boolean> weather = new Setting<Boolean>("Weather", false);

    public NoRender() {
        super("NoRender", Category.RENDER, "Cancel certain things from rendering.");
        this.key = Keyboard.KEY_NONE;
    }

    @Override
    public void setup() {
        this.addSetting(fog);
        this.addSetting(hurtcamera);
        this.addSetting(weather);
    }

    public void onUpdate() {
        //GL11.glDisable(GL11.GL_FOG);
    }
}
