package me.velocity.beta.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class TickMixin {

    @Inject(method = {"tick"}, at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        //((IMinecraft) mc).getInstance().world != null) { // have to find out if theres a world class.. if not idrc
            //System.out.println("tick test);
        //}
    }
    Minecraft mc; // this luckily doesn't throw an npe... pog
}