package us.velocity.client.impl.mixins;

import net.minecraft.block.BlockBase;
import net.minecraft.level.TileView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBase.class)
public class BrightnessMixin
{
    @Inject(method = "getBrightness", at = @At("TAIL"), cancellable = true)
    public void getBrightness(TileView tileView, int x, int y, int z, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(1F);
    }
}
