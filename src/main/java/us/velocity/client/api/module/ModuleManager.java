package us.velocity.client.api.module;

import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.events.bus.Listener;
import us.velocity.client.impl.modules.client.*;
import us.velocity.client.impl.modules.exploit.*;
import us.velocity.client.impl.modules.misc.*;
import us.velocity.client.impl.modules.render.NoRender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleManager implements Listener
{
    private static final List<Module> modules = Arrays.asList(
            //combat

            //combat

            //exploit
            new NoFall(),
            new Test(),
            //exploit

            //misc
            new Music(),
            //misc

            //movement

            //movement

            //render
            new NoRender(),
            //render

            //client
            new ClickGui(),
            new HUD(),
            new Notify(),
            new Watermark()
            //client
    );

    public static List<Module> getModules() {
        return new ArrayList<Module>(ModuleManager.modules);
    }

    public static List<Module> getModulesInCategory(final Module.Category cat) {
        final List<Module> module = new ArrayList<Module>();
        for (final Module m : ModuleManager.modules) {
            if (m.getCategory().equals(cat)) {
                module.add(m);
            }
        }
        return module;
    }

    public static Module getModuleByName(final String name) {
        return ModuleManager.modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static Module getModuleByClass(final Class<?> clazz) {
        return ModuleManager.modules.stream().filter(module -> module.getClass().equals(clazz)).findFirst().orElse(null);
    }

    public static void onUpdate() {
        for (final Module m : ModuleManager.modules) {
            try {
                if (!m.isEnabled()) {
                    continue;
                }
                m.onUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void onFastUpdate() {
        for (final Module m : ModuleManager.modules) {
            try {
                if (!m.isEnabled()) {
                    continue;
                }
                m.onFastUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void keyListen() {
        if (Velocity.mc.currentScreen == null) {
            for (Module m : ModuleManager.modules) {
                try {
                    if (Keyboard.isKeyDown(m.key)  && !m.isKeyDown) {
                        m.isKeyDown = true;
                        m.toggle();
                    } else {
                        if (Keyboard.isKeyDown(m.key)) {
                            continue;
                        }
                        m.isKeyDown = false;
                    }
                } catch (Exception ex) {
                }
            }
        }
    }
}
