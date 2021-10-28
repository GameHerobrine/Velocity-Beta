package us.velocity.client.impl.modules.movement;

import net.minecraft.entity.EntityBase;
import net.minecraft.util.maths.MathHelper;
import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.events.PacketEvent;
import us.velocity.client.api.events.bus.EventHandler;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.setting.Setting;

public class Scaffold extends Module
{
    public static final Setting<Boolean> tower = new Setting<Boolean>("Tower", false);

    public int direction;

    public int playerY;
    public int changedY;

    public Scaffold() {
        super("Magic-Carpet-Old", Category.HIDDEN, "Automatically places blocks at your feet.");
        this.key = Keyboard.KEY_NONE;
    }

    @Override
    public void onUpdate() {
        try {
            if(Velocity.mc.player != null) playerY = (int) Velocity.mc.player.y;
            assert Velocity.mc.player != null;

            if(!Velocity.mc.player.onGround && Keyboard.isKeyDown(Keyboard.KEY_SPACE) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) Velocity.mc.player.velocityY = 0.3;

            Velocity.mc.interactionManager.method_1713(Velocity.mc.player, Velocity.mc.level, Velocity.mc.player.getHeldItem(), (int) Velocity.mc.player.x - 1, changedY, (int) Velocity.mc.player.z - 1, getDirection());
        } catch(Throwable t) { t.printStackTrace(); }
    }

    public int getDirection() {
        direction = (MathHelper.floor((double)(Velocity.mc.player.yaw * 4.0F / 360.0F) + 0.5D) & 3);
        if(direction == 0) direction = 3;
        if(direction == 1) direction = 4;
        if(direction == 3) direction = 5;
        if(!Velocity.mc.player.onGround && tower.getValue() && Keyboard.isKeyDown(Keyboard.KEY_SPACE) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) direction = 1;
        if(!Velocity.mc.player.onGround) changedY = playerY - 3; else {
            changedY = playerY - 2;
        }
        return direction;
    }

    @Override
    public void setup() {
        this.addSetting(tower);
    }
}
