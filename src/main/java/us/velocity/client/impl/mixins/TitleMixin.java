package us.velocity.client.impl.mixins;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class TitleMixin {
    private static Minecraft mc;

    @Redirect(method = "start(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Ljava/awt/Frame;<init>(Ljava/lang/String;)V", ordinal = 0))
    private static void Frame(String title) {

    }
}
