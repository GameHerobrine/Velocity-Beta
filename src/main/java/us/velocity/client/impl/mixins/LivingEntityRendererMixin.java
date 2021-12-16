package us.velocity.client.impl.mixins;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.Living;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import us.velocity.client.Velocity;
import us.velocity.client.api.events.PacketEvent;
import us.velocity.client.api.events.RenderNamePlateEvent;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin
{
    @Inject(method = "method_821", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void onRenderNamePlate(Living living, double x, double y, double z, CallbackInfo ci) {
        RenderNamePlateEvent event = new RenderNamePlateEvent();
        Velocity.eventBus.post(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
}
