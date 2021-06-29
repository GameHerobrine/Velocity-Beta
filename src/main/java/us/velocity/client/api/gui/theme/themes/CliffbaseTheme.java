package us.velocity.client.api.gui.theme.themes;

import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.gui.theme.Theme;
import us.velocity.client.api.gui.util.FinalColor;
import us.velocity.client.api.gui.util.GuiUtil;
import us.velocity.client.api.gui.util.MathUtil;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.setting.Setting;
import us.velocity.client.api.setting.SubSetting;
import us.velocity.client.api.util.RenderUtil;
import us.velocity.client.impl.modules.client.ClickGui;

import java.util.List;

public class CliffbaseTheme extends Theme
{
    private static int boost;
    public static final String name = "Cliffbase";
    public static final int width = 90;
    public static final int height = 12;
    
    public CliffbaseTheme() {
        super("Cliffbase", 90, 12);
    }

    public static int BESTCOLOR(final int alpha) {
        return FinalColor.RAINBOWONLY ? (FinalColor.GRADIENT ? GuiUtil.rainbow(CliffbaseTheme.boost, alpha) : GuiUtil.rainbow(1L, alpha)) : GuiUtil.toRGBA(ClickGui.r.getValue().intValue(),ClickGui.g.getValue().intValue(),ClickGui.b.getValue().intValue(), alpha);
    }
    
    @Override
    public void drawTitles(final String name, final int x, final int y) {
        RenderUtil.fill(x, y + 1, x + 1, y + 12 - 1, -16777216);
        RenderUtil.fill(x + 90 - 1, y + 1, x + 90, y + 12 - 1, -16777216);
        RenderUtil.fill(x + 1, y, x + 90 - 1, y + 1, -16777216);
        RenderUtil.fill(x + 1, y + 12 - 1, x + 90 - 1, y + 12, -16777216);
        RenderUtil.fill(x + 1, y + 1, x + 90 - 1, y + 12 - 1, 1979711488);
        Velocity.mc.textRenderer.drawTextWithShadow(name, (x + (x + 90 - x) / 2 - Velocity.mc.textRenderer.getTextWidth(name) / 2), (y + 2), -1);
    }

    @Override
    public void drawModules(final List<Module> modules, final int x, final int y) {
        CliffbaseTheme.boost = 0;
        for (final Module m : modules) {
            if (GuiUtil.mouseOver(x, y + 12 + CliffbaseTheme.boost * 12 + 2, x + 90, y + 24 + CliffbaseTheme.boost * 12 + 1)) {
                if (GuiUtil.ldown) {
                    m.toggle();
                }
                if (GuiUtil.rdown) {
                    m.toggleState();
                }
            }
            RenderUtil.fill(x, y + 12 + CliffbaseTheme.boost * 12 + 2, x + 1, y + 24 + CliffbaseTheme.boost * 12 + 2, -16777216);
            RenderUtil.fill(x + 90 - 1, y + 12 + CliffbaseTheme.boost * 12 + 2, x + 90, y + 24 + CliffbaseTheme.boost * 12 + 2, -16777216);
            RenderUtil.fill(x + 1, y + 12 + CliffbaseTheme.boost * 12 + 2, x + 90 - 1, y + 24 + CliffbaseTheme.boost * 12 + 2, m.isEnabled() ? BESTCOLOR(190) : 1979711488);
            Velocity.mc.textRenderer.drawTextWithShadow(m.getName(), (x + 2), (y + 12 + 2 + CliffbaseTheme.boost * 12 + 2), -1);
            if (m.isOpened()) {
                if (m.hasSettings()) {
                    drawDropdown(m, x, y + 3);
                }
                ++CliffbaseTheme.boost;
                renderKeybind(m, GuiUtil.keydown, x, y + 3);
            }
            ++CliffbaseTheme.boost;
        }
        RenderUtil.fill(x + 1, y + 12 + 1, x + 90 - 1, y + 12 + 2, -16777216);
        RenderUtil.fill(x + 1, y + 12 + CliffbaseTheme.boost * 12 + 2, x + 90 - 1, y + 12 + CliffbaseTheme.boost * 12 + 3, -16777216);
    }

    public static void drawDropdown(final Module m, final int x, final int y) {
        for (final Setting<?> s : m.getSettings()) {
            if (s.getValue() instanceof Boolean) {
                final Setting<Boolean> b = (Setting<Boolean>)s;
                ++CliffbaseTheme.boost;
                renderBoolean(b, x, y);
                for (final SubSetting ss : b.getSubSettings()) {
                    if (b.isOpen()) {
                        ++CliffbaseTheme.boost;
                        if (ss.getValue() instanceof Boolean) {
                            renderSubBoolean(ss, x, y);
                        }
                        if (ss.getValue() instanceof String) {
                            renderSubMode(ss, x, y);
                        }
                        if (ss.getValue() instanceof Integer) {
                            renderSubSlider(ss, x, y);
                        }
                        if (!(ss.getValue() instanceof Double)) {
                            continue;
                        }
                        renderSubDouble(ss, x, y);
                    }
                }
            }
            if (s.getValue() instanceof String) {
                final Setting<String> str = (Setting<String>)s;
                ++CliffbaseTheme.boost;
                renderMode(str, x, y);
                for (final SubSetting ss : str.getSubSettings()) {
                    if (str.isOpen()) {
                        ++CliffbaseTheme.boost;
                        if (ss.getValue() instanceof Boolean) {
                            renderSubBoolean(ss, x, y);
                        }
                        if (ss.getValue() instanceof String) {
                            renderSubMode(ss, x, y);
                        }
                        if (ss.getValue() instanceof Integer) {
                            renderSubSlider(ss, x, y);
                        }
                        if (!(ss.getValue() instanceof Double)) {
                            continue;
                        }
                        renderSubDouble(ss, x, y);
                    }
                }
            }
            if (s.getValue() instanceof Integer) {
                final Setting<Integer> sn = (Setting<Integer>)s;
                ++CliffbaseTheme.boost;
                renderIntegerSlider(sn, x, y);
                for (final SubSetting ss : sn.getSubSettings()) {
                    if (sn.isOpen()) {
                        ++CliffbaseTheme.boost;
                        if (ss.getValue() instanceof Boolean) {
                            renderSubBoolean(ss, x, y);
                        }
                        if (ss.getValue() instanceof String) {
                            renderSubMode(ss, x, y);
                        }
                        if (ss.getValue() instanceof Integer) {
                            renderSubSlider(ss, x, y);
                        }
                        if (!(ss.getValue() instanceof Double)) {
                            continue;
                        }
                        renderSubDouble(ss, x, y);
                    }
                }
            }
            if (s.getValue() instanceof Double) {
                final Setting<Double> sn2 = (Setting<Double>)s;
                ++CliffbaseTheme.boost;
                renderDouble(sn2, x, y);
                for (final SubSetting ss : sn2.getSubSettings()) {
                    if (sn2.isOpen()) {
                        ++CliffbaseTheme.boost;
                        if (ss.getValue() instanceof Boolean) {
                            renderSubBoolean(ss, x, y);
                        }
                        if (ss.getValue() instanceof String) {
                            renderSubMode(ss, x, y);
                        }
                        if (ss.getValue() instanceof Integer) {
                            renderSubSlider(ss, x, y);
                        }
                        if (!(ss.getValue() instanceof Double)) {
                            continue;
                        }
                        renderSubDouble(ss, x, y);
                    }
                }
            }
        }
    }

    private static void renderBoolean(final Setting<Boolean> s, final int x, final int y) {
        if (GuiUtil.mouseOver(x, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 12 + 12 - 1 + CliffbaseTheme.boost * 12)) {
            if (GuiUtil.ldown) {
                if (ClickGui.click.getValue()) {
                    Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
                }
                s.setValue(!s.getValue());
            }
            if (GuiUtil.rdown) {
                if (ClickGui.click.getValue()) {
                    Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
                }
                s.toggleOpen();
            }
        }
        RenderUtil.fill(x, y + 12 + CliffbaseTheme.boost * 12, x + 1, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 90 - 1, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 1, y + 12 + CliffbaseTheme.boost * 12 - 1, x + 90 - 1, y + 24 + CliffbaseTheme.boost * 12 - 1, ((boolean)s.getValue()) ? BESTCOLOR(190) : 1979711488);
        Velocity.mc.textRenderer.drawTextWithShadow(s.getName(), (x + 7), (y + 12 + 2 + CliffbaseTheme.boost * 12), -1);
    }

    private static void renderSubBoolean(final SubSetting<Boolean> ss, final int x, final int y) {
        if (GuiUtil.mouseOver(x, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 12 + 12 - 1 + CliffbaseTheme.boost * 12) && GuiUtil.ldown) {
            if (ClickGui.click.getValue()) {
                Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
            }
            ss.setValue(!ss.getValue());
        }
        RenderUtil.fill(x, y + 12 + CliffbaseTheme.boost * 12, x + 1, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 90 - 1, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 1, y + 12 + CliffbaseTheme.boost * 12 - 1, x + 90 - 1, y + 24 + CliffbaseTheme.boost * 12 - 1, ((boolean)ss.getValue()) ? BESTCOLOR(190) : 1979711488);
        Velocity.mc.textRenderer.drawTextWithShadow(ss.getName(), (x + 10), (y + 12 + 2 + CliffbaseTheme.boost * 12), -1);
    }

    private static void renderMode(final Setting<String> s, final int x, final int y) {
        if (GuiUtil.mouseOver(x + 1, y + 12 + CliffbaseTheme.boost * 12, x + 90 - 1, y + 12 + 12 - 1 + CliffbaseTheme.boost * 12)) {
            if (GuiUtil.ldown) {
                if (ClickGui.click.getValue()) {
                    Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
                }
                s.setMode(s.nextMode());
            }
            if (GuiUtil.rdown) {
                s.toggleOpen();
            }
        }
        RenderUtil.fill(x, y + 12 + CliffbaseTheme.boost * 12, x + 1, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 90 - 1, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 1, y + 12 + CliffbaseTheme.boost * 12 - 1, x + 90 - 1, y + 12 + 12 + CliffbaseTheme.boost * 12 - 1, 1979711488);
        Velocity.mc.textRenderer.drawTextWithShadow(s.getName(), (x + 3), (y + 12 + 2 + CliffbaseTheme.boost * 12), -1);
        Velocity.mc.textRenderer.drawTextWithShadow((String)s.getValue(), (x + 90 - Velocity.mc.textRenderer.getTextWidth((String)s.getValue()) - 2), (y + 12 + 2 + CliffbaseTheme.boost * 12), -9013642);
    }

    private static void renderSubMode(final SubSetting<String> ss, final int x, final int y) {
        if (GuiUtil.mouseOver(x + 1, y + 12 + CliffbaseTheme.boost * 12, x + 90 - 1, y + 12 + 12 - 1 + CliffbaseTheme.boost * 12) && GuiUtil.ldown) {
            if (ClickGui.click.getValue()) {
                Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
            }
            ss.setMode(ss.nextMode());
        }
        RenderUtil.fill(x, y + 12 + CliffbaseTheme.boost * 12, x + 1, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 90 - 1, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 1, y + 12 + CliffbaseTheme.boost * 12 - 1, x + 90 - 1, y + 12 + 12 + CliffbaseTheme.boost * 12 - 1, 1979711488);
        Velocity.mc.textRenderer.drawTextWithShadow(ss.getName(), (x + 10), (y + 12 + 2 + CliffbaseTheme.boost * 12), -1);
        Velocity.mc.textRenderer.drawTextWithShadow((String)ss.getValue(), (x + 90 - Velocity.mc.textRenderer.getTextWidth((String)ss.getValue()) - 2), (y + 12 + 2 + CliffbaseTheme.boost * 12), -9013642);
    }

    private static void renderIntegerSlider(final Setting<Integer> s, final int x, final int y) {
        int pixAdd = (x + 90 - x) * (s.getValue() - s.getMinValue()) / (s.getMaxValue() - s.getMinValue());
        if (GuiUtil.mouseOver(x + 1, y + 12 + CliffbaseTheme.boost * 12 + 1, x + 90 - 1, y + 12 + 12 - 1 + CliffbaseTheme.boost * 12)) {
            if (GuiUtil.ldown && ClickGui.click.getValue()) {
                Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
            }
            if (GuiUtil.lheld) {
                final int percentError = (GuiUtil.mX - x) * 100 / (x + 90 - 4 - x);
                s.setValue((int)(percentError * ((s.getMaxValue() - s.getMinValue()) / 100.0) + s.getMinValue()));
            }
            if (GuiUtil.rdown) {
                if (ClickGui.click.getValue()) {
                    Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
                }
                s.toggleOpen();
            }
        }
        if (pixAdd < 3) {
            pixAdd = 3;
        }
        if (pixAdd > 89) {
            pixAdd = 89;
        }
        RenderUtil.fill(x, y + 12 + CliffbaseTheme.boost * 12, x + 1, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 90 - 1, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 1, y + 12 + CliffbaseTheme.boost * 12 - 1, x + 90 - 1, y + 12 + 12 + CliffbaseTheme.boost * 12 - 1, 1979711488);
        RenderUtil.fill(x + 2, y + 12 + CliffbaseTheme.boost * 12, x - 1 + pixAdd, y + 12 + 12 + CliffbaseTheme.boost * 12 - 1, BESTCOLOR(190));
        Velocity.mc.textRenderer.drawTextWithShadow(s.getName(), (x + 3), (y + 12 + 2 + CliffbaseTheme.boost * 12), -1);
        Velocity.mc.textRenderer.drawTextWithShadow(String.valueOf((s).getValue()), (x + 90 - Velocity.mc.textRenderer.getTextWidth(String.valueOf((s).getValue())) - 2), (y + 12 + 2 + CliffbaseTheme.boost * 12), -9013642);
    }

    private static void renderSubSlider(final SubSetting<Integer> ss, final int x, final int y) {
        int pixAdd = (x + 90 - x) * (ss.getValue() - ss.getMinValue()) / (ss.getMaxValue() - ss.getMinValue());
        if (GuiUtil.mouseOver(x, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 12 + 12 - 1 + CliffbaseTheme.boost * 12)) {
            if (GuiUtil.ldown && ClickGui.click.getValue()) {
                Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
            }
            if (GuiUtil.lheld) {
                final int percentError = (GuiUtil.mX - x) * 100 / (x + 90 - x);
                ss.setValue((int)(percentError * ((ss.getMaxValue() - ss.getMinValue()) / 100.0) + ss.getMinValue()));
            }
        }
        if (pixAdd < 4) {
            pixAdd = 4;
        }
        RenderUtil.fill(x, y + 12 + CliffbaseTheme.boost * 12, x + 1, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 90 - 1, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 1, y + 12 + CliffbaseTheme.boost * 12 - 1, x + 90 - 1, y + 12 + 12 + CliffbaseTheme.boost * 12 - 1, 1979711488);
        RenderUtil.fill(x + 2, y + 12 + CliffbaseTheme.boost * 12, x - 2 + pixAdd, y + 12 + 12 + CliffbaseTheme.boost * 12 - 1, BESTCOLOR(190));
        Velocity.mc.textRenderer.drawTextWithShadow(ss.getName(), (x + 10), (y + 12 + 2 + CliffbaseTheme.boost * 12), -1);
        Velocity.mc.textRenderer.drawTextWithShadow(String.valueOf((ss).getValue()), (x + 90 - Velocity.mc.textRenderer.getTextWidth(String.valueOf((ss).getValue())) - 2), (y + 12 + 2 + CliffbaseTheme.boost * 12), -9013642);
    }

    private static void renderDouble(final Setting<Double> s, final int x, final int y) {
        int pixAdd = (int)((x + 90 - x) * (s.getValue() - s.getMinValue()) / (s.getMaxValue() - s.getMinValue()));
        if (GuiUtil.mouseOver(x + 1, y + 12 + CliffbaseTheme.boost * 12 + 1, x + 90 - 1, y + 12 + 12 - 1 + CliffbaseTheme.boost * 12)) {
            if (GuiUtil.ldown && ClickGui.click.getValue()) {
                Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
            }
            if (GuiUtil.lheld) {
                final int percentError = (GuiUtil.mX - x) * 100 / (x + 90 - 4 - x);
                s.setValue(MathUtil.roundDouble(percentError * ((s.getMaxValue() - s.getMinValue()) / 100.0) + s.getMinValue(), s.getRoundingScale()));
            }
            if (GuiUtil.rdown) {
                if (ClickGui.click.getValue()) {
                    Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
                }
                s.toggleOpen();
            }
        }
        if (pixAdd < 3) {
            pixAdd = 3;
        }
        if (pixAdd > 89) {
            pixAdd = 89;
        }
        if (s.getValue() > s.getMaxValue()) {
            s.setValue(s.getMaxValue());
        }
        RenderUtil.fill(x, y + 12 + CliffbaseTheme.boost * 12, x + 1, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 90 - 1, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 1, y + 12 + CliffbaseTheme.boost * 12 - 1, x + 90 - 1, y + 12 + 12 + CliffbaseTheme.boost * 12 - 1, 1979711488);
        RenderUtil.fill(x + 2, y + 12 + CliffbaseTheme.boost * 12, x - 1 + pixAdd, y + 12 + 12 + CliffbaseTheme.boost * 12 - 1, BESTCOLOR(190));
        Velocity.mc.textRenderer.drawTextWithShadow(s.getName(), (x + 3), (y + 12 + 2 + CliffbaseTheme.boost * 12), -1);
        Velocity.mc.textRenderer.drawTextWithShadow(String.valueOf((s).getValue()), (x + 90 - Velocity.mc.textRenderer.getTextWidth(String.valueOf((s).getValue())) - 2), (y + 12 + 2 + CliffbaseTheme.boost * 12), -9013642);
    }

    private static void renderSubDouble(final SubSetting<Double> ss, final int x, final int y) {
        int pixAdd = (int)((x + 90 - x) * (ss.getValue() - ss.getMinValue()) / (ss.getMaxValue() - ss.getMinValue()));
        if (GuiUtil.mouseOver(x, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 12 + 12 - 1 + CliffbaseTheme.boost * 12)) {
            if (GuiUtil.ldown && ClickGui.click.getValue()) {
                Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
            }
            if (GuiUtil.lheld) {
                final int percentError = (GuiUtil.mX - x) * 100 / (x + 90 - x);
                ss.setValue(MathUtil.roundDouble(percentError * ((ss.getMaxValue() - ss.getMinValue()) / 100.0) + ss.getMinValue(), ss.getRoundingScale()));
            }
        }
        if (pixAdd < 4) {
            pixAdd = 4;
        }
        RenderUtil.fill(x, y + 12 + CliffbaseTheme.boost * 12, x + 1, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 90 - 1, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 1, y + 12 + CliffbaseTheme.boost * 12 - 1, x + 90 - 1, y + 12 + 12 + CliffbaseTheme.boost * 12 - 1, 1979711488);
        RenderUtil.fill(x + 2, y + 12 + CliffbaseTheme.boost * 12, x - 2 + pixAdd, y + 12 + 12 + CliffbaseTheme.boost * 12 - 1, BESTCOLOR(190));
        Velocity.mc.textRenderer.drawTextWithShadow(ss.getName(), (x + 10), (y + 12 + 2 + CliffbaseTheme.boost * 12), -1);
        Velocity.mc.textRenderer.drawTextWithShadow(String.valueOf((ss).getValue()), (x + 90 - Velocity.mc.textRenderer.getTextWidth(String.valueOf((ss).getValue())) - 2), (y + 12 + 2 + CliffbaseTheme.boost * 12), -9013642);
    }

    public static void renderKeybind(final Module m, final int key, final int x, final int y) {
        String name = Keyboard.getKeyName(m.getKeybind());
        if (GuiUtil.mouseOver(x + 1, y + 12 + CliffbaseTheme.boost * 12, x + 90 - 1, y + 12 + 12 - 1 + CliffbaseTheme.boost * 12) && GuiUtil.ldown) {
            if (ClickGui.click.getValue()) {
                Velocity.mc.soundHelper.playSound("random.click", 1.0f, 0.1f);
            }
            m.setBinding(true);
        }
        if (m.isBinding() && key != -1 && key != 1 && key != 211) {
            m.setKey(key);
            m.setBinding(false);
        }
        if (m.isBinding() && key == 1) {
            m.setBinding(false);
        }
        RenderUtil.fill(x, y + 12 + CliffbaseTheme.boost * 12, x + 1, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 90 - 1, y + 12 + CliffbaseTheme.boost * 12, x + 90, y + 24 + CliffbaseTheme.boost * 12, -16777216);
        RenderUtil.fill(x + 1, y + 12 + CliffbaseTheme.boost * 12 - 1, x + 90 - 1, y + 12 + 12 + CliffbaseTheme.boost * 12 - 1, 1979711488);
        if (!m.isBinding()) {
            Velocity.mc.textRenderer.drawTextWithShadow("Keybind", (x + 4), (y + 12 + 2 + CliffbaseTheme.boost * 12), -1);
            Velocity.mc.textRenderer.drawTextWithShadow(name, (x + 90 - Velocity.mc.textRenderer.getTextWidth(name) - 2), (y + 12 + 2 + CliffbaseTheme.boost * 12), -9013642);
        }
        else {
            Velocity.mc.textRenderer.drawTextWithShadow("Listening...", (x + 4), (y + 12 + 2 + CliffbaseTheme.boost * 12), -9013642);
        }
    }
    
    static {
        CliffbaseTheme.boost = 0;
    }
}
