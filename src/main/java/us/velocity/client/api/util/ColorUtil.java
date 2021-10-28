package us.velocity.client.api.util;

import us.velocity.client.Velocity;

import java.awt.*;
import java.util.Random;

public abstract class ColorUtil
{
	public static int rainbow(int alpha) {
		final float[] hue = { System.currentTimeMillis() % 22300L / 22300.0f };
		final int rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
		final int r = rgb >> 16 & 0xFF;
		final int g = rgb >> 8 & 0xFF;
		final int b = rgb & 0xFF;
		final int color = toRGBA(r, g, b, alpha);
		return color;
	}

	public static int rollingRainbow(long offset) {
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

	public static int rainbow(long offset, int alpha) {
		float hue = (float) ((((System.currentTimeMillis() * (15.0D / 10)) + (offset * 500)) % (30000L / (30.0D / 100))) / (30000.0f / (30.0D / 20)));
		int rgb = Color.HSBtoRGB(hue, 0.5f, 1.0f);
		int red = rgb >> 16 & 255;
		int green = rgb >> 8 & 255;
		int blue = rgb & 255;
		int color = toRGBA(red, green, blue, alpha);
		return color;
	}

	public static void drawGradientStringWithShadow(String s, int i, int j) {
		for (char c : s.toCharArray()) {
			Velocity.mc.textRenderer.drawTextWithShadow(String.valueOf(c), i, j, rainbow(i / 8, 255));
			i += Velocity.mc.textRenderer.getTextWidth(String.valueOf(c));
		}
	}
}