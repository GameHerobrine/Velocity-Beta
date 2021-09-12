package us.velocity.client.impl.mixins;

import net.minecraft.sortme.GameRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.velocity.client.impl.modules.render.NoRender;

@Mixin(GameRenderer.class)
public class EntityRenderer
{
    @Inject(at = @At("HEAD"), method = "method_1849", cancellable = true)
    private void setupHurtCam(CallbackInfo ci) {
        if(NoRender.hurtcamera.getValue())
            ci.cancel();
    }

    @Inject(at = @At("TAIL"), method = "method_1842", cancellable = true)
    private void setupFog(CallbackInfo ci) {
        if(NoRender.fog.getValue())
            GL11.glFogi(2916, 9729);
    }
}
