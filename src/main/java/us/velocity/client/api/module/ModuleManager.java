package us.velocity.client.api.module;

import us.velocity.client.impl.modules.TestModule;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author olliem5
 */

public final class ModuleManager {
    private static final ArrayList<Module> modules = new ArrayList<>();

    //Replace with Arrays.asList() when it's not just one module lol
    public static void init() {
        modules.addAll(Collections.singletonList(
                new TestModule()
        ));

        modules.sort(ModuleManager::alphabetize);
    }

    private static int alphabetize(Module module1, Module module2) {
        return module1.getName().compareTo(module2.getName());
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    public static Module getModuleByName(String name) {
        return modules.stream()
                .filter(module -> module.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
