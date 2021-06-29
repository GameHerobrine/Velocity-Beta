package us.velocity.client.api.events;

import net.minecraft.packet.AbstractPacket;
import us.velocity.client.api.events.bus.Cancellable;
import us.velocity.client.api.events.bus.Event;

public class PacketEvent extends Event implements Cancellable
{
    private final AbstractPacket packet;

    public PacketEvent(AbstractPacket packet) {
        this.packet = packet;
    }

    public AbstractPacket getPacket() {
        return this.packet;
    }

    public static class PacketReceiveEvent extends PacketEvent
    {
        public PacketReceiveEvent(AbstractPacket packet) {
            super(packet);
        }
    }

    public static class PacketSendEvent extends PacketEvent
    {
        public PacketSendEvent(AbstractPacket packet) {
            super(packet);
        }
    }
}