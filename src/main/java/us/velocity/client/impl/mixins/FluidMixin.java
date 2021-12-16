package us.velocity.client.impl.mixins;

import net.minecraft.block.Fluid;
import net.minecraft.block.material.Material;
import net.minecraft.level.TileView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import us.velocity.client.api.module.ModuleManager;
import us.velocity.client.impl.modules.movement.Jesus;

@Mixin(Fluid.class)
public class FluidMixin
{
    @Inject(method = "isSolid", at = @At("HEAD"), cancellable = true)
    public void getBrightness(TileView tileView, int x, int y, int z, int meta, CallbackInfoReturnable<Boolean> cir) {
        cir.cancel();
        if(ModuleManager.getModuleByClass(Jesus.class).isEnabled())
            cir.setReturnValue(true); // didnt work and then I gave up
    }
}
