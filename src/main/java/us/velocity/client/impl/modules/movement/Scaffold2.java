package us.velocity.client.impl.modules.movement;

import net.minecraft.packet.Id14Packet;
import net.minecraft.packet.Id15Packet;
import net.minecraft.util.maths.MathHelper;
import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.setting.Setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scaffold2 extends Module {
    public static final Setting<Boolean> tower = new Setting<Boolean>("Tower", false);

    public static int blocks;
    public static int flintandsteel;
    public static int tnt;
    public static int item;

    public static int stage = 0;

    public static int X;
    public static int Y;
    public static int Z;
    public static int A;
    public static int B;
    public static int C;
    public static int offsetX;
    public static int offsetZ;
    public static long timer = 0l;
    public static int side = 2;
    public static float yaw = 0f;

    public Scaffold2() {
        super("Magic-Carpet", Category.MOVEMENT, "Places blocks under your feet.");
        this.key = Keyboard.KEY_V;
    }

    List<Integer> itemIds = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 7, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 29, 33, 35, 41, 42, 43, 45, 46, 47, 48, 49, 52, 56, 57, 73, 74, 79, 80, 82, 84, 86, 87, 88, 89, 91));

    @Override
    public void onUpdate() {
        if (Velocity.mc.player == null) return;
        if (Velocity.mc.player.x < 0 && Velocity.mc.player.z < 0) {
            offsetX = -1;
            offsetZ = -1;
        }
        if (Velocity.mc.player.x > 0 && Velocity.mc.player.z > 0) {
            offsetX = 0;
            offsetZ = 0;
        }
        if (Velocity.mc.player.x > 0 && Velocity.mc.player.z < 0) {
            offsetX = 0;
            offsetZ = -1;
        }
        if (Velocity.mc.player.x < 0 && Velocity.mc.player.z > 0) {
            offsetX = -1;
            offsetZ = 0;
        }

        int direction = MathHelper.floor((double) (Velocity.mc.player.yaw * 4.0F / 360.0F) + 0.5D) & 3;


        X = (int) Velocity.mc.player.x;
        Y = (int) Velocity.mc.player.y;
        Z = (int) Velocity.mc.player.z;

        A = X + offsetX;
        B = Y - 2;
        C = Z + offsetZ;

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && tower.getValue() && Velocity.mc.player.getHeldItem() != null)
            Velocity.mc.player.velocityY = 0.3;
        if (!Velocity.mc.player.onGround && tower.getValue() && Velocity.mc.player.getHeldItem() != null && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            direction = 4;

        if (direction == 4) {
            Velocity.mc.getNetworkHandler().sendPacket(new Id15Packet(A, B - 1, C, 1, Velocity.mc.player.getHeldItem()));
        }
        if (direction == 2) {
            Velocity.mc.getNetworkHandler().sendPacket(new Id15Packet(A, B, C, 2, Velocity.mc.player.getHeldItem()));
        }
        if (direction == 0) {
            Velocity.mc.getNetworkHandler().sendPacket(new Id15Packet(A, B, C, 3, Velocity.mc.player.getHeldItem()));
        }
        if (direction == 1) {
            Velocity.mc.getNetworkHandler().sendPacket(new Id15Packet(A, B, C, 4, Velocity.mc.player.getHeldItem()));
        }
        if (direction == 3) {
            Velocity.mc.getNetworkHandler().sendPacket(new Id15Packet(A, B, C, 5, Velocity.mc.player.getHeldItem()));
//            if(Velocity.mc.player.getHeldItem() != null) {
//                flintandsteel = -1;
//                tnt = -1;
//
//                for (int i = 0; i < 9; i++) {
//
//                    if (flintandsteel != -1
//                            && tnt != -1) break;
//
//                    ItemInstance stack = Velocity.mc.player.inventory.getInventoryItem(i);
//
//                    if (stack != null) {
//                        item = (stack.itemId);
//                    }
//
//                    if (item == 46) {
//                        tnt = i;
//                    } else if (item == 259) {
//                        flintandsteel = i;
//                    }
//                }
//
//                if (flintandsteel == -1) {
//                    MessageUtil.sendClientMessage(TextFormatting.BLUE + "[AutoBomber] " + TextFormatting.RESET + "Missing Flint and Steel!");
//                } else if (tnt == -1) {
//                    MessageUtil.sendClientMessage(TextFormatting.BLUE + "[AutoBomber] " + TextFormatting.RESET + "Missing TnT!");
//                }
//                new Thread(() -> {
//                    try {
//                        Velocity.mc.player.inventory.selectedHotbarSlot = tnt;
//                        Velocity.mc.getNetworkHandler().sendPacket(new Id15Packet(A, B, C, 5, Velocity.mc.player.getHeldItem()));
//                        Velocity.mc.player.inventory.selectedHotbarSlot = flintandsteel;
//                        Thread.sleep(80);
//                        Velocity.mc.getNetworkHandler().sendPacket(new Id14Packet(0, A + 1, B, C, 1));
//                        Velocity.mc.player.inventory.selectedHotbarSlot = tnt; // temp
//                    } catch(Throwable t) {
//                        t.printStackTrace();
//                    }
//                }).start();
    }
}

    @Override
    public void setup() {
        this.addSetting(tower);
    }
}
