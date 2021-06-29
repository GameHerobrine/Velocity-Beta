package us.velocity.client.api.gui.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil
{
	public static double roundDouble(final double number, final int scale) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(scale, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}