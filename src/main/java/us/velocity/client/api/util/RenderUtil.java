package us.velocity.client.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TextRenderer;
import org.lwjgl.opengl.GL11;

@Environment(EnvType.CLIENT)
public class RenderUtil {
	protected float zOffset = 0.0F;

	protected void drawLineHorizontal(int x1, int y, int x2, int colour) {
		if (y < x1) {
			int var5 = x1;
			x1 = y;
			y = var5;
		}

		this.fill(x1, x2, y + 1, x2 + 1, colour);
	}

	protected void drawLineVertical(int x, int y1, int y2, int colour) {
		if (y2 < y1) {
			int var5 = y1;
			y1 = y2;
			y2 = var5;
		}

		this.fill(x, y1 + 1, x + 1, y2, colour);
	}

	public static void fill(int x1, int y1, int x2, int y2, int colour) {
		if (x1 < x2) {
			int var6 = x1;
			x1 = x2;
			x2 = var6;
		}

		if (y1 < y2) {
			int var11 = y1;
			y1 = y2;
			y2 = var11;
		}

		float var12 = (float)(colour >> 24 & 255) / 255.0F;
		float var7 = (float)(colour >> 16 & 255) / 255.0F;
		float var8 = (float)(colour >> 8 & 255) / 255.0F;
		float var9 = (float)(colour & 255) / 255.0F;
		Tessellator var10 = Tessellator.INSTANCE;
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(var7, var8, var9, var12);
		var10.start();
		var10.addVertex((double)x1, (double)y2, 0.0D);
		var10.addVertex((double)x2, (double)y2, 0.0D);
		var10.addVertex((double)x2, (double)y1, 0.0D);
		var10.addVertex((double)x1, (double)y1, 0.0D);
		var10.draw();
		GL11.glEnable(3553);
		GL11.glDisable(3042);
	}

	protected void fillGradient(int x1, int y1, int x2, int y2, int startColour, int endColour) {
		float var7 = (float)(startColour >> 24 & 255) / 255.0F;
		float var8 = (float)(startColour >> 16 & 255) / 255.0F;
		float var9 = (float)(startColour >> 8 & 255) / 255.0F;
		float var10 = (float)(startColour & 255) / 255.0F;
		float var11 = (float)(endColour >> 24 & 255) / 255.0F;
		float var12 = (float)(endColour >> 16 & 255) / 255.0F;
		float var13 = (float)(endColour >> 8 & 255) / 255.0F;
		float var14 = (float)(endColour & 255) / 255.0F;
		GL11.glDisable(3553);
		GL11.glEnable(3042);
		GL11.glDisable(3008);
		GL11.glBlendFunc(770, 771);
		GL11.glShadeModel(7425);
		Tessellator var15 = Tessellator.INSTANCE;
		var15.start();
		var15.colour(var8, var9, var10, var7);
		var15.addVertex((double)x2, (double)y1, 0.0D);
		var15.addVertex((double)x1, (double)y1, 0.0D);
		var15.colour(var12, var13, var14, var11);
		var15.addVertex((double)x1, (double)y2, 0.0D);
		var15.addVertex((double)x2, (double)y2, 0.0D);
		var15.draw();
		GL11.glShadeModel(7424);
		GL11.glDisable(3042);
		GL11.glEnable(3008);
		GL11.glEnable(3553);
	}

	public void drawTextWithShadowCentred(TextRenderer renderer, String text, int x, int y, int colour) {
		renderer.drawTextWithShadow(text, x - renderer.getTextWidth(text) / 2, y, colour);
	}

	public void drawTextWithShadow(TextRenderer renderer, String text, int x, int y, int colour) {
		renderer.drawTextWithShadow(text, x, y, colour);
	}

	public void blit(int x, int y, int u, int v, int width, int height) {
		float var7 = 0.00390625F;
		float var8 = 0.00390625F;
		Tessellator var9 = Tessellator.INSTANCE;
		var9.start();
		var9.vertex((double)(x + 0), (double)(y + height), (double)this.zOffset, (double)((float)(u + 0) * var7), (double)((float)(v + height) * var8));
		var9.vertex((double)(x + width), (double)(y + height), (double)this.zOffset, (double)((float)(u + width) * var7), (double)((float)(v + height) * var8));
		var9.vertex((double)(x + width), (double)(y + 0), (double)this.zOffset, (double)((float)(u + width) * var7), (double)((float)(v + 0) * var8));
		var9.vertex((double)(x + 0), (double)(y + 0), (double)this.zOffset, (double)((float)(u + 0) * var7), (double)((float)(v + 0) * var8));
		var9.draw();
	}
}