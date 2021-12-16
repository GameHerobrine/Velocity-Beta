package us.velocity.client.impl.modules.misc;

import net.minecraft.item.ItemInstance;
import net.minecraft.packet.Id10Packet;
import net.minecraft.packet.Id14Packet;
import net.minecraft.tileentity.TileEntityChest;
import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.events.PacketEvent;
import us.velocity.client.api.events.bus.EventHandler;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.util.MessageUtil;
import us.velocity.client.api.util.TextFormatting;

public class AutoDupe extends Module {
    public static int chest;
    public static int item;
    public static int itembeforeenable;
    public boolean cancel = false;

    public AutoDupe() {
        super("AutoDupe", Category.MISC, "Does the chest dupe automatically.");
        this.key = Keyboard.KEY_NONE;
    }

    //couldn't be bothered to fix/finish it

//    public void onEnable() {
//        if (Velocity.mc.player == null || Velocity.mc.level == null) this.disable();
//
//        if (Velocity.mc.player.getHeldItem() != null) {
//            itembeforeenable = Velocity.mc.player.inventory.selectedHotbarSlot;
//            chest = -1;
//            for (int i = 0; i < 9; i++) {
//                if (chest != -1) break;
//                ItemInstance stack = Velocity.mc.player.inventory.getInventoryItem(i);
//                if (stack != null) {
//                    item = (stack.itemId);
//                }
//                if (item == 54) {
//                    chest = i;
//                }
//            }
//            if (chest == -1) {
//                MessageUtil.sendClientMessage(TextFormatting.BLUE + "[AutoDupe] " + TextFormatting.RESET + "Missing chests!");
//            }
//
//            Velocity.mc.player.inventory.selectedHotbarSlot = chest;
//            Velocity.mc.interactionManager.method_1713(Velocity.mc.player, Velocity.mc.level, Velocity.mc.player.getHeldItem(), (int) Velocity.mc.player.x - 1, (int) Velocity.mc.player.y - 2, (int) Velocity.mc.player.z, 1);
//            Velocity.mc.player.inventory.selectedHotbarSlot = itembeforeenable;
//            Velocity.mc.interactionManager.method_1713(Velocity.mc.player, Velocity.mc.level, Velocity.mc.player.getHeldItem(), (int) Velocity.mc.player.x - 1, (int) Velocity.mc.player.y - 1, (int) Velocity.mc.player.z, 1);
//            cancel = true;
//
//            TileEntityChest tec = (TileEntityChest) Velocity.mc.level.tileEntities.stream().filter(e -> e instanceof TileEntityChest).filter(e -> Velocity.mc.player.distanceTo(((TileEntityChest) e).x, ((TileEntityChest) e).y, ((TileEntityChest) e).z) <= 3).findFirst().orElse(null);
//            Velocity.mc.getNetworkHandler().sendPacket(new Id14Packet(0, tec.x, tec.y - 1, tec.z, 1));
//
//            new Thread(() -> {
//                try {
//                    Thread.sleep(6000);
//                    cancel = false;
//                } catch (Throwable t) {
//                    t.printStackTrace();
//                }
//            }).start();
//        }
//    }

    public void onDisable() {
        cancel = false;
    }

    @Override
    public void onUpdate() {
        if (Velocity.mc.player == null || Velocity.mc.level == null) this.disable();

//        Minecart minecart = (Minecart) Velocity.mc.level.entities.stream().filter(e -> e instanceof Minecart).filter(e -> Velocity.mc.player.distanceTo((EntityBase) e) <= 8).findFirst().orElse(null);
//        MessageUtil.sendClientMessage(minecart.x + " " + minecart.z);
//        if(minecart.velocityX != 0 && minecart.velocityZ != 0) open = false; else {
//            open = true;
//        }
        //Velocity.mc.getNetworkHandler().sendPacket(new Id0Packet());
        //Velocity.mc.getNetworkHandler().sendPacket(new Id14Packet(0, (int) Velocity.mc.player.x, (int) Velocity.mc.player.y - 2, (int) Velocity.mc.player.z, 1));
    }

    @EventHandler
    public void onPacketSend(PacketEvent.PacketSendEvent event) {
        if (event.getPacket() instanceof Id10Packet) {
            event.setCancelled(cancel);
        }
    }
}
