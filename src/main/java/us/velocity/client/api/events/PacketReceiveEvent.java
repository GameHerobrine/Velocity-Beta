package us.velocity.client.api.events;

import net.minecraft.packet.AbstractPacket;

public class PacketReceiveEvent extends PacketEvent
{
    public PacketReceiveEvent(AbstractPacket packet) {
        super(packet);
    }
}