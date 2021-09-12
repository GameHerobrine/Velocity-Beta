package us.velocity.client.impl.mixins;

import net.minecraft.entity.Living;
import org.checkerframework.checker.units.qual.A;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.module.ModuleManager;

@Mixin(Living.class)
public abstract class FastTickMixin
{
    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    private void tick(CallbackInfo ci) {
        for (Module module : ModuleManager.getModules()) {
            if (module.isEnabled()) {
                module.onFastUpdate();
            }
        }
    }
}
