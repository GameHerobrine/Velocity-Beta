package me.velocity.beta;

import me.velocity.beta.mixin.IMinecraft;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBase;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

public class Velocity implements ModInitializer {
	@Override
	public void onInitialize() {
		//this uses the mixin to get the instance... u need to add "Minecraft mc;" to the last bottom bracket for this to work
		//((IMinecraft)mc).getInstance().
		GL11.glBindTexture(3553, ((IMinecraft)mc).getInstance().textureManager.getTextureId("/assets/modid/icon.png"));
	}
	Minecraft mc;
}
