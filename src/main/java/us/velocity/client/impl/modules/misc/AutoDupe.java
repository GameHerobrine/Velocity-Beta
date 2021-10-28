package us.velocity.client.impl.modules.misc;

import net.minecraft.packet.Id0Packet;
import net.minecraft.packet.Id10Packet;
import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.events.PacketEvent;
import us.velocity.client.api.events.bus.EventHandler;
import us.velocity.client.api.module.Module;

public class AutoDupe extends Module
{
    public AutoDupe() {
        super("AutoDupe", Category.MISC, "Does the chest dupe automatically.");
        this.key = Keyboard.KEY_NONE;
    }

    @Override
    public void onUpdate() {
        //Velocity.mc.getNetworkHandler().sendPacket(new Id0Packet());
        //Velocity.mc.getNetworkHandler().sendPacket(new Id14Packet(0, (int) Velocity.mc.player.x, (int) Velocity.mc.player.y - 2, (int) Velocity.mc.player.z, 1));
    }
}
