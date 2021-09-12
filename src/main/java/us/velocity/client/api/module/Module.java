package us.velocity.client.api.module;

import net.minecraft.client.Minecraft;
import us.velocity.client.Velocity;
import us.velocity.client.api.events.bus.Listener;
import us.velocity.client.api.setting.Setting;
import us.velocity.client.api.util.ChatFormatting;
import us.velocity.client.api.util.MessageUtil;
import us.velocity.client.impl.modules.client.Notify;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class Module implements Listener
{
	private final String name;
	private final Category category;
	private final String description;
	private boolean enabled;
	private boolean opened;
	public int key;
	protected boolean isKeyDown = false;
	private boolean isBinding;
	private String arraylistInfo;
	public List<Setting> settingsList;
	protected static final Minecraft mc = Velocity.mc;

	public Module(final String name, final Category category, @Nullable final String description) {
		this.settingsList = new ArrayList<Setting>();
		this.name = name;
		this.category = category;
		this.description = ((description != null) ? description : "null");
		this.enabled = false;
		this.opened = false;
		key = -1;

		this.setup();
	}

	public void setup() {
	}

	public void addSetting(final Setting s) {
		this.settingsList.add(s);
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void onEnable() {
		if (ModuleManager.getModuleByClass(Notify.class).isEnabled() && !this.name.equalsIgnoreCase("clickgui") && Velocity.mc.player != null) {
			MessageUtil.sendClientMessage(this.name + ChatFormatting.GREEN + " enabled");
		}
		Velocity.eventBus.subscribe(this);
	}

	public void onDisable() {
		if (ModuleManager.getModuleByClass(Notify.class).isEnabled() && !this.name.equalsIgnoreCase("clickgui")) {
			MessageUtil.sendClientMessage(this.name + ChatFormatting.RED + " disabled");
		}
		Velocity.eventBus.unSubscribe(this);
	}

	public void onUpdate() {
	}

	public void onFastUpdate() {
	}

	public void onRender2d() {

	}

	public void toggle() {
		this.enabled = !this.enabled;
		try {
			if (this.isEnabled()) {
				this.onEnable();
			}
			else {
				this.onDisable();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enable() {
		if (!this.isEnabled()) {
			this.enabled = true;
			try {
				this.onEnable();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void disable() {
		if (this.isEnabled()) {
			this.enabled = false;
			try {
				this.onDisable();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean Null() {
		return Module.mc.player == null || Module.mc.worldRenderer == null;
	}

	public String getName() {
		return this.name;
	}

	public Category getCategory() {
		return this.category;
	}

	public String getDescription() {
		return this.description;
	}

	public boolean isKeyDown() {
		return this.isKeyDown;
	}

	public void setKeyDown(boolean b) {
		this.isKeyDown = b;
	}

	public int getKeybind() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean hasSettings() {
		return this.settingsList.size() > 0;
	}

	public List<Setting> getSettings() {
		return this.settingsList;
	}

	public void toggleState() {
		this.opened = !this.opened;
	}

	public boolean isOpened() {
		return this.opened;
	}

	public boolean isBinding() {
		return this.isBinding;
	}
	protected void setArraylistInfo(final String info) {
		this.arraylistInfo = info;
	}

	public void setBinding(final boolean b) {
		this.isBinding = b;
	}
	public static boolean fullNullCheck() {
		return Module.mc.player == null || Module.mc.worldRenderer == null;
	}

	public enum Category
	{
		CLIENT("Client"),
		COMBAT("Combat"),
		EXPLOIT("Exploit"),
		MISC("Misc"),
		MOVEMENT("Movement"),
		RENDER("Render");

		private final String name;

		private Category(final String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}
}
