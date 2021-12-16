package us.velocity.client.impl.mixins;

import net.minecraft.packet.AbstractPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import us.velocity.client.Velocity;
import us.velocity.client.api.events.PacketEvent;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerNetworkManager
{
    @Inject(method = "method_1139", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void onPacketReceive(CallbackInfoReturnable<Boolean> cir, int var1, AbstractPacket var2) {
        PacketEvent.PacketReceiveEvent event = new PacketEvent.PacketReceiveEvent(var2);
        Velocity.eventBus.post(event);
        if (event.isCancelled()) {
            cir.cancel();
        }
    }
}