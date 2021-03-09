package us.velocity.client.api.module;

import net.minecraft.client.Minecraft;

/**
 * @author olliem5
 */

public abstract class Module {
    protected Minecraft mc;

    private final String name;
    private final ModuleCategory moduleCategory;

    private int key;

    private boolean enabled;

    protected Module(String name, ModuleCategory moduleCategory) {
        this.name = name;
        this.moduleCategory = moduleCategory;
    }

    public void enable() {
        enabled = true;
        onEnable();
    }

    public void disable() {
        enabled = false;
        onDisable();
    }

    public void toggle() {
        try {
            if (enabled) {
                disable();
            } else {
                enable();
            }
        } catch (Exception ignored) {}
    }

    public String getName() {
        return name;
    }

    public ModuleCategory getModuleCategory() {
        return moduleCategory;
    }

    public int getKey() {
        return key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    protected abstract void onEnable();

    protected abstract void onDisable();

    public abstract void onUpdate();
}
