package us.velocity.client.impl.modules;

import us.velocity.client.api.module.Module;
import us.velocity.client.api.module.ModuleCategory;

/**
 * @author olliem5
 */

public class TestModule extends Module {
    public TestModule() {
        super("TestModule", ModuleCategory.Misc);
        this.setEnabled(true);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Override
    public void onUpdate() {
        System.out.println("Hi quill");
    }
}
