package us.velocity.client.impl.mixins.vanillafixes;

import net.minecraft.client.util.ThreadDownloadResources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import us.velocity.client.impl.vanillafixes.MinecraftUtil;

@Mixin(ThreadDownloadResources.class)
final class ThreadDownloadResourcesMixin {
    @ModifyConstant(method = "run", constant = @Constant(stringValue = "http://s3.amazonaws.com/MinecraftResources/"), remap = false, require = 0)
    private String changeURL(String url) {
        return MinecraftUtil.getResources();
    }
}