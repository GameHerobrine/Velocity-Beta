package us.velocity.client;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import us.velocity.client.api.events.bus.EventBus;
import us.velocity.client.api.events.bus.Listener;
import us.velocity.client.api.gui.main.Window;
import us.velocity.client.api.gui.theme.Theme;
import us.velocity.client.api.gui.util.FileUtil;
import us.velocity.client.api.module.ModuleManager;

import java.lang.reflect.Field;

public class Velocity implements ModInitializer, Listener {

    public static EventBus eventBus = new EventBus();
    public static Velocity INSTANCE = new Velocity();

    public static ModuleManager moduleManager = new ModuleManager();
    public static Logger LOGGER;
    public static Minecraft mc;

    @Override
    public void onInitialize() {
        register();
        Window.initGui();
        LOGGER.info("ClickGui Initialized!");
        Theme.initThemes();
        LOGGER.info("Gui Themes Initialized!");
        Runtime.getRuntime().addShutdownHook(new BaseShutdownHook());
        LOGGER.info("Shutdown Hook Initialized!");
        FileUtil.loadAll();
    }

    public void register() {
        eventBus.subscribe(this);
        eventBus.subscribe(Velocity.moduleManager);
    }

    static {
        try {
            Field f = Minecraft.class.getDeclaredField("instance");
            f.setAccessible(true);
            mc = (Minecraft) f.get(null);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        LOGGER = LogManager.getLogger("Velocity");
    }
}