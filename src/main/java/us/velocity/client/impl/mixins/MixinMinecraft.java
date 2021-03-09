package us.velocity.client.impl.mixins;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.module.ModuleManager;

/**
 * @author olliem5
 */

@Mixin(Minecraft.class)
public class MixinMinecraft {
    private static Minecraft mc;

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo callbackInfo) {
            for (Module module : ModuleManager.getModules()) {
                if (module.isEnabled()) {
                    module.onUpdate();
                }
            }
        }
    }
