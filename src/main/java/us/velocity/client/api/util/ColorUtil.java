package us.velocity.client.api.util;

import java.awt.*;

public abstract class ColorUtil
{
	public static int rainbow(final int alpha) {
		final float[] hue = { System.currentTimeMillis() % 22300L / 22300.0f };
		final int rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
		final int r = rgb >> 16 & 0xFF;
		final int g = rgb >> 8 & 0xFF;
		final int b = rgb & 0xFF;
		final int color = toRGBA(r, g, b, alpha);
		return color;
	}

	public static int rollingRainbow(final long offset) {
		final float hue = (System.currentTimeMillis() + offset * 500L) % 90000L / 30000.0f;
		final int rgb = Color.HSBtoRGB(hue, 0.85f, 0.85f);
		final int red = rgb >> 16 & 0xFF;
		final int green = rgb >> 8 & 0xFF;
		final int blue = rgb & 0xFF;
		final int color = toRGBA(red, green, blue, 255);
		return color;
	}

	public static int toRGBA(final int r, final int g, final int b, final int a) {
		return (r << 16) + (g << 8) + (b << 0) + (a << 24);
	}
}