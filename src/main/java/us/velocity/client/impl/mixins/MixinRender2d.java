package us.velocity.client.impl.mixins;

import net.minecraft.client.gui.InGame;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.util.ScreenScaler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.velocity.client.Velocity;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.module.ModuleManager;
import us.velocity.client.api.util.ColorUtil;
import us.velocity.client.api.util.RenderUtil;
import us.velocity.client.impl.modules.client.HUD;
import org.lwjgl.opengl.GL11;

@Mixin(InGame.class)
public class MixinRender2d
{

    @Inject(method = "renderHud", at = @At("TAIL"))
    public void drawText(CallbackInfo ci) {
        if(!Velocity.mc.options.debugHud) {
            for (Module module : ModuleManager.getModules()) {
                if (module.isEnabled()) {
                    module.onRender2d();
                }
            }
        }
    }
}