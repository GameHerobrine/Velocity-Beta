package us.velocity.client.impl.mixins;

import net.fabricmc.loader.FabricLoader;
import net.fabricmc.loader.launch.common.FabricLauncher;
import net.minecraft.block.material.Material;
import net.minecraft.class_214;
import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import us.velocity.client.Velocity;
import us.velocity.client.api.module.ModuleManager;
import us.velocity.client.impl.modules.render.NoRender;

import java.nio.FloatBuffer;

@Mixin(net.minecraft.sortme.GameRenderer.class)
public class GameRendererMixin
{
    @Shadow
    private float field_2350 = 0.0F;

    @Shadow
    private boolean field_2330 = false;

    @Shadow
    float field_2346;

    @Shadow
    float field_2347;

    @Shadow
    float field_2348;

    @Shadow
    FloatBuffer field_2345 = class_214.method_746(16);

    @Shadow
    private FloatBuffer method_1839(float f, float f1, float f2, float f3) {
        this.field_2345.clear();
        this.field_2345.put(f).put(f1).put(f2).put(f3);
        this.field_2345.flip();
        return this.field_2345;
    }

    @Inject(at = @At("HEAD"), method = "method_1849", cancellable = true)
    private void setupHurtCam(CallbackInfo ci) {
        if(NoRender.hurtcamera.getValue() && ModuleManager.getModuleByClass(NoRender.class).isEnabled())
            ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "method_1846", cancellable = true)
    private void setupRain(CallbackInfo ci) {
        if(NoRender.weather.getValue() && ModuleManager.getModuleByClass(NoRender.class).isEnabled())
            ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "method_1847", cancellable = true)
    private void setupSnow(CallbackInfo ci) {
        if(NoRender.weather.getValue() && ModuleManager.getModuleByClass(NoRender.class).isEnabled())
            ci.cancel();
    }

    @Inject(at = @At("TAIL"), method = "method_1842", cancellable = true)
    private void setupFog(CallbackInfo ci) {
        if(NoRender.fog.getValue() && ModuleManager.getModuleByClass(NoRender.class).isEnabled())
            GL11.glFogi(2916, 9729);
    }
}
