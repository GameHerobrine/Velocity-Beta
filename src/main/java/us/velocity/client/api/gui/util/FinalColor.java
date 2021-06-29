package us.velocity.client.api.gui.util;

import us.velocity.client.impl.modules.client.ClickGui;

public final class FinalColor
{
    public static int COLOR;
    public static boolean GRADIENT;
    public static boolean RAINBOWONLY;
    
    public static void updateColors() {
        if (ClickGui.rainbow.getValue() && ClickGui.gradient.getValue()) {
            FinalColor.GRADIENT = true;
            FinalColor.RAINBOWONLY = true;
        }
        else if (ClickGui.rainbow.getValue() && !ClickGui.gradient.getValue()) {
            FinalColor.GRADIENT = false;
            FinalColor.RAINBOWONLY = true;
        }
        else if ((!ClickGui.rainbow.getValue() && !ClickGui.gradient.getValue()) || (!ClickGui.rainbow.getValue() && ClickGui.gradient.getValue())) {
            FinalColor.GRADIENT = false;
            FinalColor.RAINBOWONLY = false;
        }
    }
    
    public static int BESTCOLOR(final int alpha, final int boost) {
        return FinalColor.RAINBOWONLY ? (FinalColor.GRADIENT ? GuiUtil.rainbow(boost, alpha) : GuiUtil.rainbow(1L, alpha)) : GuiUtil.toRGBA((int)(Object)ClickGui.r.getValue(), (int)(Object)ClickGui.g.getValue(), (int)(Object)ClickGui.b.getValue(), alpha);
    }
}