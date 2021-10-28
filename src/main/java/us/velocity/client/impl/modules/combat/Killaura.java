package us.velocity.client.impl.modules.combat;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.packet.play.HandSwingC2S;
import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.events.PacketEvent;
import us.velocity.client.api.events.bus.EventHandler;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.setting.Setting;

public class Killaura extends Module {
    static EntityBase target;
    public static final Setting<Double> range = new Setting<Double>("Range", 0.0, 6.0, 6.0, 1);
    public static final Setting<Boolean> noSwing = new Setting<Boolean>("NoSwing", false);

    public Killaura() {
        super("Killaura", Category.COMBAT, "Attacks nearby entities.");
        this.key = Keyboard.KEY_R;
    }

    @Override
    public void onUpdate() {
        try {
            for (final Object o : Velocity.mc.level.getEntities()) {
                Living entity = null;
                if (o instanceof Living) {
                    entity = (Living) o;
                }
                if (entity != null && isWithinRange(range.getValue().floatValue(), entity) && entity.isAlive() && entity != Velocity.mc.player) {
                    Velocity.mc.interactionManager.method_1719(Velocity.mc.player, entity);
                    Velocity.mc.player.swingHand();
                }
            }
        } catch(Throwable t) { t.printStackTrace(); }
    }

    @EventHandler
    public void onPacketSend(PacketEvent.PacketSendEvent event) {
        if(event.getPacket() instanceof HandSwingC2S) {
            if(noSwing.getValue()) ((HandSwingC2S)event.getPacket()).field_1190 = 0;
        }
    }

    public boolean isWithinRange(float range, EntityBase e) {
        return Velocity.mc.player.distanceTo(e) <= range;
    }

    @Override
    public void setup() {
        this.addSetting(range);
        this.addSetting(noSwing);
    }
}
