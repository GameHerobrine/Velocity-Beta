package us.velocity.client.impl.mixins;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.velocity.client.impl.modules.render.NoRender;

@Mixin(WorldRenderer.class)
public class WorldRenderMixin
{

    //no use but will keep incase
//    @Inject(at = @At("HEAD"), method = "method_1547", cancellable = true)
//    private void setupBockBreakOverlay(CallbackInfo ci) {
//        ci.cancel();
//    }

    //could be used for xray?
//    @Inject(at = @At("HEAD"), method = "method_1543", cancellable = true)
//    private void setupBlockRender(CallbackInfo ci) {
//        ci.cancel();
//    }
}
