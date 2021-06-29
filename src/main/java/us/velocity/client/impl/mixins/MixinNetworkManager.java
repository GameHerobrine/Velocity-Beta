package us.velocity.client.impl.mixins;

import net.minecraft.client.gui.InGame;
import net.minecraft.network.ClientPlayNetworkHandler;
import net.minecraft.packet.AbstractPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.velocity.client.Velocity;
import us.velocity.client.api.events.PacketEvent;
import us.velocity.client.api.events.PacketReceiveEvent;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinNetworkManager
{
    @Inject(method = { "sendPacket" }, at = { @At("HEAD") }, cancellable = true)
    public void onPacketSend(AbstractPacket packet, CallbackInfo ci) {
        PacketEvent.PacketSendEvent event = new PacketEvent.PacketSendEvent(packet);
        Velocity.eventBus.post(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method = { "method_1646" }, at = { @At("HEAD") }, cancellable = true)
    public void onPacketReceive(AbstractPacket packet, CallbackInfo ci) {
        PacketReceiveEvent event = new PacketReceiveEvent(packet);
        Velocity.eventBus.post(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
}