package us.velocity.client.impl.modules.client;

import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.gui.main.BaseGui;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.setting.Setting;
import us.velocity.client.api.setting.SubSetting;

import java.util.Arrays;
import java.util.List;

public class ClickGui extends Module
{

    private static final List<String> modes = Arrays.asList("Bruce", "Cliffbase");
    public static final Setting<String> theme = new Setting<String>("Theme", ClickGui.modes);;
    public static final Setting<Double> r = new Setting<Double>("R", 0.0, 0.0, 255.0, 1);
    public static final Setting<Double> g = new Setting<Double>("G", 0.0, 103.0, 255.0, 1);
    public static final Setting<Double> b = new Setting<Double>("B", 0.0, 119.0, 255.0, 1);

    public static final Setting<Boolean> rainbow = new Setting<Boolean>("Rainbow", false);
    public static final Setting<Boolean> click = new Setting<Boolean>("Click", true);
    public static final SubSetting<Boolean> gradient = new SubSetting<Boolean>(rainbow, "Gradient", true);
    public static final SubSetting<Double> saturation = new SubSetting<Double>(rainbow, "Saturation", 0.01, 0.8, 1.0, 2);
    public static final SubSetting<Double> brightness = new SubSetting<Double>(rainbow, "Brightness", 0.01, 0.8, 1.0, 2);
    public static final SubSetting<Double> difference = new SubSetting<Double>(rainbow, "Difference", 0.1, 25.0, 100.0, 1);
    public static final SubSetting<Double> speed = new SubSetting<Double>(rainbow, "Speed", 0.1, 25.0, 100.0, 1);


    public static BaseGui clickGui = new BaseGui();;

    public ClickGui() {
        super("ClickGui", Category.CLIENT, "The ClickGui");
        this.key = Keyboard.KEY_GRAVE;
    }

    @Override
    public void setup() {
        this.addSetting(theme);
        this.addSetting(click);
        this.addSetting(rainbow);
        this.addSetting(r);
        this.addSetting(g);
        this.addSetting(b);
    }

    @Override
    public void onEnable() {
        Velocity.mc.openScreen(ClickGui.clickGui);
    }
}
