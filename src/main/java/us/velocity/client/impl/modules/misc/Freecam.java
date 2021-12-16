package us.velocity.client.impl.modules.misc;

import net.minecraft.entity.player.RemoteClientPlayer;
import net.minecraft.packet.Id10Packet;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.events.PacketEvent;
import us.velocity.client.api.events.bus.EventHandler;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.util.MessageUtil;

public class Freecam extends Module
{

    // didnt feel like finishing either :D

    public Freecam() {
        super("Freecam", Category.MISC, "I had sexual intercourse with your mother.");
        this.key = Keyboard.KEY_NONE;
    }

    @EventHandler
    public void onPacketSend(PacketEvent.PacketSendEvent event) {
        if(event.getPacket() instanceof Id10Packet) {
            event.setCancelled(true);
        }
    }
}
