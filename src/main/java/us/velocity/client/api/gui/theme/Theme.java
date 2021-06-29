package us.velocity.client.api.gui.theme;

import us.velocity.client.api.gui.theme.themes.BruceTheme;
import us.velocity.client.api.gui.theme.themes.CliffbaseTheme;
import us.velocity.client.api.module.Module;

import java.util.ArrayList;
import java.util.List;

public abstract class Theme
{
	private String name;
	private int width;
	private int height;
	public static final List<Theme> themes = new ArrayList<Theme>();;

	public Theme(final String name, final int width, final int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}

	public abstract void drawTitles(final String p0, final int p1, final int p2);

	public abstract void drawModules(final List<Module> p0, final int p1, final int p2);

	public static void initThemes() {
		Theme.themes.add(new BruceTheme());
		Theme.themes.add(new CliffbaseTheme());
	}

	public void addTheme(final Theme theme) {
		Theme.themes.add(theme);
	}

	public String getThemeName() {
		return this.name;
	}

	public int getThemeWidth() {
		return this.width;
	}

	public int getThemeHeight() {
		return this.height;
	}

	public static Theme getThemeByName(final String name) {
		return Theme.themes.stream().filter(theme -> theme.getThemeName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
}
