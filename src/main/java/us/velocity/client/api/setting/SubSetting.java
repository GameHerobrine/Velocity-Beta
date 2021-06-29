package us.velocity.client.api.setting;

import java.util.*;

public class SubSetting<T>
{
    private Setting<?> parent;
    private String name;
    private T min;
    private T value;
    private T max;
    private int scale;
    private int modeIndex;
    private List<String> modes;

    public SubSetting(final Setting<?> parent, final String name, final T value) {
        this.parent = parent;
        this.name = name;
        this.value = value;
        parent.addSubSetting(this);
    }

    public SubSetting(final Setting<?> parent, final String name, final T min, final T value, final T max) {
        this.parent = parent;
        this.name = name;
        this.min = min;
        this.value = value;
        this.max = max;
        parent.addSubSetting(this);
    }

    public SubSetting(final Setting<?> parent, final String name, final T min, final T value, final T max, final int scale) {
        this.parent = parent;
        this.name = name;
        this.min = min;
        this.value = value;
        this.max = max;
        this.scale = scale;
        parent.addSubSetting(this);
    }

    public SubSetting(final Setting<?> parent, final String name, final List<String> modes) {
        this.parent = parent;
        this.name = name;
        this.modes = modes;
        this.modeIndex = 0;
        this.value = (T)modes.get(0);
        parent.addSubSetting(this);
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

    public int getRoundingScale() {
        return this.scale;
    }

    public Setting<?> getParent() {
        return this.parent;
    }
}
