package us.velocity.client.impl.mixins;

import net.minecraft.client.MinecraftApplet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.velocity.client.Velocity;

@Mixin(MinecraftApplet.class)
public class InitializationMixin
{
	@Inject(at = @At("RETURN"), method = "init", remap = false)
	private void init(CallbackInfo ci) {
		Velocity.INSTANCE.onInitialize();
	}
}