package us.velocity.client.impl.mixins.vanillafixes;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.RemoteClientPlayer;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.velocity.client.impl.vanillafixes.MinecraftUtil;

@Mixin(RemoteClientPlayer.class)
abstract class RemoteClientPlayerMixin extends PlayerBase {
    private RemoteClientPlayerMixin(Level level) {
        super(level);
    }

    @Inject(method = "<init>", at = @At("RETURN"), require = 0)
    private void RemoteClientPlayer(Level level, String username, CallbackInfo ci) {
        if (username != null && username.length() > 0) {
            cloakUrl = MinecraftUtil.getPlayerCape(username);
        }
    }
}