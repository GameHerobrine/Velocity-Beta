package us.velocity.client.api.gui.main;

import net.minecraft.client.gui.screen.ScreenBase;
import us.velocity.client.api.gui.util.FileUtil;
import us.velocity.client.api.gui.util.GuiUtil;
import us.velocity.client.api.module.ModuleManager;
import us.velocity.client.impl.modules.client.ClickGui;

public class BaseGui extends ScreenBase
{
	public void render(int mouseX, int mouseY, float delta) {
		super.render(mouseX, mouseY, delta);
		for (final Window w : Window.windows) {
			w.drawGui(mouseX, mouseY);
		}
		GuiUtil.mouseListen(mouseX, mouseY);
	}

	public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if (mouseButton == 0) {
			for (final Window w : Window.windows) {
				w.lclickListen();
			}
			GuiUtil.lclickListen();
		}
		if (mouseButton == 1) {
			GuiUtil.rclickListen();
		}
	}

	public void mouseReleased(final int mouseX, final int mouseY, final int state) {
		super.mouseReleased(mouseX, mouseY, state);
		if (state == 0) {
			for (final Window w : Window.windows) {
				w.releaseListen();
			}
			GuiUtil.releaseListen();
		}
	}

	public void keyPressed(final char typedChar, final int keyCode) {
		super.keyPressed(typedChar, keyCode);
		GuiUtil.keyListen(keyCode);
	}

	public void onClose() {
		super.onClose();
		ModuleManager.getModuleByClass(ClickGui.class).disable();
		FileUtil.saveAll();
	}

	public boolean doesGuiPauseGame() {
		return false;
	}
}
