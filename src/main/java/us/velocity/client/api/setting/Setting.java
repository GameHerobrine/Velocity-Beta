package us.velocity.client.api.setting;

import java.util.*;

public class Setting<T>
{
    private String name;
    private T min;
    private T value;
    private T max;
    private boolean opened;
    private List<SubSetting<?>> subsettings;
    private int scale;
    private int modeIndex;
    private List<String> modes;

    public Setting(final String name, final T value) {
        this.opened = false;
        this.subsettings = new ArrayList<SubSetting<?>>();
        this.name = name;
        this.value = value;
    }

    public Setting(final String name, final T min, final T value, final T max) {
        this.opened = false;
        this.subsettings = new ArrayList<SubSetting<?>>();
        this.name = name;
        this.min = min;
        this.value = value;
        this.max = max;
    }

    public Setting(final String name, final T min, final T value, final T max, final int scale) {
        this.opened = false;
        this.subsettings = new ArrayList<SubSetting<?>>();
        this.name = name;
        this.min = min;
        this.value = value;
        this.max = max;
        this.scale = scale;
    }

    public Setting(final String name, final List<String> modes) {
        this.opened = false;
        this.subsettings = new ArrayList<SubSetting<?>>();
        this.name = name;
        this.modes = modes;
        this.modeIndex = 0;
        this.value = (T)modes.get(this.modeIndex);
    }

    public String getName() {
        return this.name;
    }

    public T getValue() {
        return this.value;
    }

    public T getMinValue() {
        return this.min;
    }

    public T getMaxValue() {
        return this.max;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    public int getRoundingScale() {
        return this.scale;
    }

    public boolean isOpen() {
        return this.opened;
    }

    public void toggleOpen() {
        this.opened = !this.opened;
    }

    public int nextMode() {
        return this.modeIndex + 1;
    }

    public String getMode(final int index) {
        return this.modes.get(index);
    }

    public void setMode(final int mode) {
        this.modeIndex = ((mode > this.modes.size() - 1) ? 0 : mode);
        this.setValue((T) this.modes.get(this.modeIndex));
    }

    public boolean hasSubSettings() {
        return this.subsettings.size() > 0;
    }

    public List<SubSetting<?>> getSubSettings() {
        return this.subsettings;
    }

    public void addSubSetting(final SubSetting<?> ss) {
        this.subsettings.add(ss);
    }
}