package us.velocity.client.api.gui.main;

import us.velocity.client.api.gui.theme.Theme;
import us.velocity.client.api.gui.util.GuiUtil;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.module.ModuleManager;
import us.velocity.client.impl.modules.client.ClickGui;

import java.util.ArrayList;
import java.util.List;

public class Window
{
	public int x;
	public int y;
	private int mX;
	private int mY;
	private boolean ldown;
	private boolean rdown;
	private boolean dragging;
	private int lastmX;
	private int lastmY;
	private String name;
	private Module.Category category;
	private List<Module> modules;
	public static final List<Window> windows = new ArrayList<Window>();;

	public Window(final String name, final int x, final int y, final Module.Category category) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.category = category;
		this.modules = ModuleManager.getModulesInCategory(category);
	}

	public static void initGui() {
		Window.windows.add(new Window(Module.Category.CLIENT.getName(), 12, 22, Module.Category.CLIENT));
		Window.windows.add(new Window(Module.Category.COMBAT.getName(), 122, 22, Module.Category.COMBAT));
		Window.windows.add(new Window(Module.Category.EXPLOIT.getName(), 232, 22, Module.Category.EXPLOIT));
		Window.windows.add(new Window(Module.Category.MISC.getName(), 342, 22, Module.Category.MISC));
		Window.windows.add(new Window(Module.Category.MOVEMENT.getName(), 452, 22, Module.Category.MOVEMENT));
		Window.windows.add(new Window(Module.Category.RENDER.getName(), 562, 22, Module.Category.RENDER));
	}

	public void drawGui(final int mouseX, final int mouseY) {
		this.mouseListen();
		final String currentTheme = ClickGui.theme.getValue();
		final Theme current = Theme.getThemeByName(currentTheme);
		current.drawTitles(this.name, this.x, this.y);
		current.drawModules(this.modules, this.x, this.y);
		this.reset();
	}

	private void mouseListen() {
		if (this.dragging) {
			this.x = GuiUtil.mX - (this.lastmX - this.x);
			this.y = GuiUtil.mY - (this.lastmY - this.y);
		}
		this.lastmX = GuiUtil.mX;
		this.lastmY = GuiUtil.mY;
	}

	private void reset() {
		this.ldown = false;
		this.rdown = false;
	}

	public void lclickListen() {
		final String currentTheme = ClickGui.theme.getValue();
		final Theme current = Theme.getThemeByName(currentTheme);
		if (GuiUtil.mouseOver(this.x, this.y, this.x + current.getThemeWidth(), this.y + current.getThemeHeight())) {
			this.dragging = true;
		}
	}

	public void releaseListen() {
		this.ldown = false;
		this.dragging = false;
	}
}