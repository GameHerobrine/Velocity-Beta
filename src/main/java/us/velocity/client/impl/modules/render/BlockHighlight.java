package us.velocity.client.impl.modules.render;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Keyboard;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.setting.Setting;

public class BlockHighlight extends Module
{
    public static BlockHighlight blockHighlight = new BlockHighlight();

    public static final Setting<Double> r = new Setting<Double>("Red", 0.0, 0.3, 1.0, 1);
    public static final Setting<Double> g = new Setting<Double>("Green", 0.0, 0.5, 1.0, 1);
    public static final Setting<Double> b = new Setting<Double>("Blue", 0.0, 0.2, 1.0, 1);
    public static final Setting<Double> a = new Setting<Double>("Alpha", 0.0, 1.0, 1.0, 1);
    public static final Setting<Double> lineWidth = new Setting<Double>("LineWidth", 0.1, 4.0, 10.0, 1);
    public static final Setting<Boolean> depth = new Setting<Boolean>("Depth", true);

    public BlockHighlight() {
        super("BlockHighlight", Category.RENDER, "Allows you to change how the block highlight behaves.");
        this.key = Keyboard.KEY_NONE;
    }

    @Override
    public void setup() {
        this.addSetting(r);
        this.addSetting(g);
        this.addSetting(b);
        this.addSetting(a);
        this.addSetting(lineWidth);
        this.addSetting(depth);
    }
}
