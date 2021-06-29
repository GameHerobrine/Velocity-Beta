package us.velocity.client.impl.mixins.vanillafixes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.Session;
import net.minecraft.entity.player.AbstractClientPlayer;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.velocity.client.impl.vanillafixes.MinecraftUtil;

@Mixin(AbstractClientPlayer.class)
abstract class AbstractClientPlayerMixin extends PlayerBase {
    private AbstractClientPlayerMixin(Level level) {
        super(level);
    }

    @Inject(method = "<init>", at = @At("RETURN"), require = 0)
    private void AbstractClientPlayer(Minecraft minecraft, Level level, Session session, int integer, CallbackInfo ci) {
        if (session != null && session.username != null && session.username.length() > 0) {
            cloakUrl = MinecraftUtil.getPlayerCape(session.username);
        }
    }
}