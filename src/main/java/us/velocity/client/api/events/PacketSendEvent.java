package us.velocity.client.api.events;

import net.minecraft.packet.AbstractPacket;
import us.velocity.client.api.events.bus.Cancellable;

public class PacketSendEvent extends PacketEvent implements Cancellable
{
    public PacketSendEvent(AbstractPacket packet) {
        super(packet);
    }
}