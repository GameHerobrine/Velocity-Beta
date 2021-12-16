package us.velocity.client.impl.mixins;

import net.minecraft.level.Level;
import net.minecraft.level.TileView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import us.velocity.client.api.module.ModuleManager;
import us.velocity.client.impl.modules.render.Fullbright;

@Mixin(Level.class)
public class LevelMixin
{
    @Inject(method = "getBrightness", at = @At("TAIL"), cancellable = true)
    public void getBrightness(int x, int y, int z, CallbackInfoReturnable<Float> cir) {
        if(ModuleManager.getModuleByClass(Fullbright.class).isEnabled())
            cir.setReturnValue(1F);
    }
}
