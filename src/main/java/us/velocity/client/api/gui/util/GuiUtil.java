package us.velocity.client.api.gui.util;

import us.velocity.client.impl.modules.client.ClickGui;

import java.awt.*;

public class GuiUtil
{
    public static int mX;
    public static int mY;
    public static int keydown;
    public static boolean ldown;
    public static boolean lheld;
    public static boolean rdown;

    public static boolean mouseOver(final int minX, final int minY, final int maxX, final int maxY) {
        return GuiUtil.mX >= minX && GuiUtil.mY >= minY && GuiUtil.mX <= maxX && GuiUtil.mY <= maxY;
    }

    public static void mouseListen(final int mouseX, final int mouseY) {
        GuiUtil.mX = mouseX;
        GuiUtil.mY = mouseY;
        GuiUtil.ldown = false;
        GuiUtil.rdown = false;
        GuiUtil.keydown = -1;
    }

    public static void lclickListen() {
        GuiUtil.ldown = true;
        GuiUtil.lheld = true;
    }

    public static void rclickListen() {
        GuiUtil.rdown = true;
    }

    public static void releaseListen() {
        GuiUtil.lheld = false;
    }

    public static void keyListen(final int key) {
        GuiUtil.keydown = key;
    }

    public static int rainbow(final long offset, final int alpha) {
        final float hue = (float)((System.currentTimeMillis() * (ClickGui.speed.getValue() / 10.0) + offset * 500L) % (30000.0 / (ClickGui.difference.getValue() / 100.0)) / (30000.0 / (ClickGui.difference.getValue() / 20.0)));
        final int rgb = Color.HSBtoRGB(hue, (float)(Object)ClickGui.saturation.getValue(), (float)(Object) ClickGui.brightness.getValue());
        final int red = rgb >> 16 & 0xFF;
        final int green = rgb >> 8 & 0xFF;
        final int blue = rgb & 0xFF;
        final int color = toRGBA(red, green, blue, alpha);
        return color;
    }

    public static int toRGBA(final int r, final int g, final int b, final int a) {
        return (r << 16) + (g << 8) + (b << 0) + (a << 24);
    }
}
