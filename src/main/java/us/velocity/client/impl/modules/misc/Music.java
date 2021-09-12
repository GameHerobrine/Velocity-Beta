package us.velocity.client.impl.modules.misc;

import net.minecraft.packet.Id9Packet;
import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.setting.Setting;
import us.velocity.client.impl.modules.client.ClickGui;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Music extends Module
{
    private static final List<String> modes = Arrays.asList("Chirp", "Cat", "Stal");
    public static final Setting<String> theme = new Setting<String>("Song", Music.modes);

    public Music() {
        super("Music", Category.MISC, "Plays record discs.");
        this.key = Keyboard.KEY_NONE;
    }

    public void onEnable() {
        try {
            if (theme.getValue().equalsIgnoreCase("Chirp")) {
                Velocity.mc.soundHelper.playStreaming("chirp",(int) Velocity.mc.player.x, (int) Velocity.mc.player.y, (int) Velocity.mc.player.z, 1.0f, 1.0f);
            } else if(theme.getValue().equalsIgnoreCase("Cat")) {
                Velocity.mc.soundHelper.playStreaming("cat",(int) Velocity.mc.player.x, (int) Velocity.mc.player.y, (int) Velocity.mc.player.z, 1.0f, 1.0f);
            } else if(theme.getValue().equalsIgnoreCase("Stal")) {
                Velocity.mc.soundHelper.playStreaming("stal",(int) Velocity.mc.player.x, (int) Velocity.mc.player.y, (int) Velocity.mc.player.z, 1.0f, 1.0f);
            }
        } catch ( Throwable t ) {
            t.printStackTrace();
        }
    }

    @Override
    public void setup() {
        this.addSetting(theme);
    }
}
