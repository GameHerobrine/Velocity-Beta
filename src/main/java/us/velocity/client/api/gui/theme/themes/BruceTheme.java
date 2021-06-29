package us.velocity.client.api.gui.theme.themes;

import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.render.TextRenderer;
import org.lwjgl.input.Keyboard;
import us.velocity.client.Velocity;
import us.velocity.client.api.gui.theme.Theme;
import us.velocity.client.api.gui.util.GuiUtil;
import us.velocity.client.api.gui.util.MathUtil;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.setting.Setting;
import us.velocity.client.api.setting.SubSetting;
import us.velocity.client.api.util.RenderUtil;

import java.util.List;

public class BruceTheme extends Theme
{
    public static BruceTheme theme = new BruceTheme();

    private static int boost;
    public static final String name = "Bruce";
    public static final int width = 105;
    public static final int height = 15;

    public BruceTheme() {
        super("Bruce", 105, 15);
    }

    @Override
    public void drawTitles(final String name, final int x, final int y) {
        RenderUtil.fill(x, y, x + 105, y + 15, -857497856);
        RenderUtil.fill(x - 1, y - 1, x, y + 15 + 1, -859343872);
        RenderUtil.fill(x + 105, y - 1, x + 105 + 1, y + 15 + 1, -859343872);
        RenderUtil.fill(x, y - 1, x + 105, y, -859343872);
        RenderUtil.fill(x, y + 15, x + 105, y + 15 + 1, -859343872);
        Velocity.mc.textRenderer.drawTextWithShadow(name, (x + 3), (y + 4), -1);
    }

    @Override
    public void drawModules(final List<Module> modules, final int x, final int y) {
        BruceTheme.boost = 0;
        for (final Module m : modules) {
            int color = -1719516928;
            if (m.isEnabled()) {
                color = -538544891;
            }
            if (GuiUtil.mouseOver(x + 1, y + 15 + 3 + BruceTheme.boost * 15, x + 105 - 1, y + 30 + BruceTheme.boost * 15 + 2)) {
                if (GuiUtil.ldown) {
                    m.toggle();
                }
                if (GuiUtil.rdown) {
                    m.toggleState();
                }
            }
            RenderUtil.fill(x, y + 15 + 1 + BruceTheme.boost * 15, x + 105, y + 30 + BruceTheme.boost * 15 + 1, 1195853639);
            RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15 + 1, x, y + 30 + BruceTheme.boost * 15 + 1, -859343872);
            RenderUtil.fill(x + 105, y + 15 + BruceTheme.boost * 15 + 1, x + 105 + 1, y + 30 + BruceTheme.boost * 15 + 1, -859343872);
            RenderUtil.fill(x + 1, y + 15 + 2 + BruceTheme.boost * 15, x + 105 - 1, y + 30 + BruceTheme.boost * 15 + 1, color);
            Velocity.mc.textRenderer.drawTextWithShadow(m.getName(), (x + 3), (y + 15 + 6 + BruceTheme.boost * 15), -1);
            if (m.hasSettings()) {
                if(!m.isOpened()) {
                    Velocity.mc.textRenderer.drawTextWithShadow(">", (x + 105 - 10), (y + 15 + 5 + BruceTheme.boost * 15), -1);
                }
            }
            if (m.isOpened()) {
                if(m.hasSettings()) {
                    Velocity.mc.textRenderer.drawTextWithShadow("V", (x + 105 - 10), (y + 15 + 5 + BruceTheme.boost * 15), -1);
                }
                drawDropdown(m, x, y + 1);
                ++BruceTheme.boost;
                drawBind(m, GuiUtil.keydown, x, y + 1);
            }
            ++BruceTheme.boost;
        }
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15 + 1, x + 105 + 1, y + 15 + BruceTheme.boost * 15 + 2, 1195853639);
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15 + 2, x + 105 + 1, y + 15 + BruceTheme.boost * 15 + 3, -859343872);
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15 + 1, x, y + 15 + BruceTheme.boost * 15 + 2, -859343872);
        RenderUtil.fill(x + 105, y + 15 + BruceTheme.boost * 15 + 1, x + 105 + 1, y + 15 + BruceTheme.boost * 15 + 2, -859343872);
    }

    public static void drawDropdown(final Module m, final int x, final int y) {
        for (final Setting<?> s : m.getSettings()) {
            if (s.getValue() instanceof Boolean) {
                final Setting<Boolean> b = (Setting<Boolean>)s;
                ++BruceTheme.boost;
                drawBoolean(b, x, y);
                for (final SubSetting ss : b.getSubSettings()) {
                    if (b.isOpen()) {
                        ++BruceTheme.boost;
                        if (ss.getValue() instanceof Boolean) {
                            drawSubBoolean(ss, x, y);
                        }
                        if (ss.getValue() instanceof String) {
                            drawSubMode(ss, x, y);
                        }
                        if (ss.getValue() instanceof Integer) {
                            drawIntegerSubSlider(ss, x, y);
                        }
                        if (!(ss.getValue() instanceof Double)) {
                            continue;
                        }
                        drawDoubleSubSlider(ss, x, y);
                    }
                }
            }
            if (s.getValue() instanceof String) {
                final Setting<String> str = (Setting<String>)s;
                ++BruceTheme.boost;
                drawMode(str, x, y);
                for (final SubSetting ss : str.getSubSettings()) {
                    if (str.isOpen()) {
                        ++BruceTheme.boost;
                        if (ss.getValue() instanceof Boolean) {
                            drawSubBoolean(ss, x, y);
                        }
                        if (ss.getValue() instanceof String) {
                            drawSubMode(ss, x, y);
                        }
                        if (ss.getValue() instanceof Integer) {
                            drawIntegerSubSlider(ss, x, y);
                        }
                        if (!(ss.getValue() instanceof Double)) {
                            continue;
                        }
                        drawDoubleSubSlider(ss, x, y);
                    }
                }
            }
            if (s.getValue() instanceof Integer) {
                final Setting<Integer> sn = (Setting<Integer>)s;
                ++BruceTheme.boost;
                drawIntegerSlider(sn, x, y);
                for (final SubSetting ss : sn.getSubSettings()) {
                    if (sn.isOpen()) {
                        ++BruceTheme.boost;
                        if (ss.getValue() instanceof Boolean) {
                            drawSubBoolean(ss, x, y);
                        }
                        if (ss.getValue() instanceof String) {
                            drawSubMode(ss, x, y);
                        }
                        if (ss.getValue() instanceof Integer) {
                            drawIntegerSubSlider(ss, x, y);
                        }
                        if (!(ss.getValue() instanceof Double)) {
                            continue;
                        }
                        drawDoubleSubSlider(ss, x, y);
                    }
                }
            }
            if (s.getValue() instanceof Double) {
                final Setting<Double> sn2 = (Setting<Double>)s;
                ++BruceTheme.boost;
                drawDoubleSlider(sn2, x, y);
                for (final SubSetting ss : sn2.getSubSettings()) {
                    if (sn2.isOpen()) {
                        ++BruceTheme.boost;
                        if (ss.getValue() instanceof Boolean) {
                            drawSubBoolean(ss, x, y);
                        }
                        if (ss.getValue() instanceof String) {
                            drawSubMode(ss, x, y);
                        }
                        if (ss.getValue() instanceof Integer) {
                            drawIntegerSubSlider(ss, x, y);
                        }
                        if (!(ss.getValue() instanceof Double)) {
                            continue;
                        }
                        drawDoubleSubSlider(ss, x, y);
                    }
                }
            }
        }
    }

    private static void drawBoolean(final Setting<Boolean> s, final int x, final int y) {
        int color = -1719516928;
        if (s.getValue()) {
            color = -538544891;
        }
        if (GuiUtil.mouseOver(x + 1, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15 - 1)) {
            if (GuiUtil.ldown) {
                s.setValue(!s.getValue());
            }
            if (GuiUtil.rdown) {
                s.toggleOpen();
            }
        }
        RenderUtil.fill(x, y + 15 + BruceTheme.boost * 15, x + 105, y + 30 + BruceTheme.boost * 15, 1195853639);
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15, x, y + 30 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 105, y + 15 + BruceTheme.boost * 15, x + 105 + 1, y + 30 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 3, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15, color);
        Velocity.mc.textRenderer.drawTextWithShadow(s.getName(), (x + 6), (y + 15 + 5 + BruceTheme.boost * 15), -1);
        if (s.hasSubSettings()) {
            Velocity.mc.textRenderer.drawText("\u22c5\u22c5\u22c5", (x + 105 - 10), (y + 15 + 4 + BruceTheme.boost * 15), -1, false);
        }
    }

    private static void drawSubBoolean(final SubSetting<Boolean> ss, final int x, final int y) {
        int color = -1719516928;
        if (ss.getValue()) {
            color = -538544891;
        }
        if (GuiUtil.mouseOver(x + 5, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.ldown) {
            ss.setValue(!ss.getValue());
        }
        RenderUtil.fill(x, y + 15 + BruceTheme.boost * 15 + 1, x + 105, y + 30 + BruceTheme.boost * 15 + 1, 1195853639);
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15, x, y + 30 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 105, y + 15 + BruceTheme.boost * 15, x + 105 + 1, y + 30 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 5, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15, color);
        Velocity.mc.textRenderer.drawTextWithShadow(ss.getName(), (x + 8), (y + 15 + 5 + BruceTheme.boost * 15), -1);
    }

    private static void drawMode(final Setting<String> s, final int x, final int y) {
        final int color = -1719516928;
        if (GuiUtil.mouseOver(x + 3, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15)) {
            if (GuiUtil.ldown) {
                s.setMode(s.nextMode());
            }
            if (GuiUtil.rdown) {
                s.toggleOpen();
            }
        }
        RenderUtil.fill(x, y + 15 + BruceTheme.boost * 15, x + 105, y + 30 + BruceTheme.boost * 15 + 1, 1195853639);
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15, x, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 105, y + 15 + BruceTheme.boost * 15, x + 105 + 1, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 3, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15, color);
        Velocity.mc.textRenderer.drawTextWithShadow(s.getName(), (x + 6), (y + 15 + 4 + BruceTheme.boost * 15), -1);
        Velocity.mc.textRenderer.drawTextWithShadow((String)s.getValue(), (x + 12 + Velocity.mc.textRenderer.getTextWidth(s.getName())), (y + 15 + 4 + BruceTheme.boost * 15), -9013642);
    }

    private static void drawSubMode(final SubSetting<String> ss, final int x, final int y) {
        final int color = -1719516928;
        if (GuiUtil.mouseOver(x + 5, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15) && GuiUtil.ldown) {
            ss.setMode(ss.nextMode());
        }
        RenderUtil.fill(x, y + 15 + BruceTheme.boost * 15, x + 105, y + 30 + BruceTheme.boost * 15 + 1, 1195853639);
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15, x, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 105, y + 15 + BruceTheme.boost * 15, x + 105 + 1, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 5, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15, color);
        Velocity.mc.textRenderer.drawTextWithShadow(ss.getName(), (x + 8), (y + 15 + 4 + BruceTheme.boost * 15), -1);
        Velocity.mc.textRenderer.drawTextWithShadow((String)ss.getValue(), (x + 12 + Velocity.mc.textRenderer.getTextWidth(ss.getName())), (y + 15 + 4 + BruceTheme.boost * 15), -9013642);
    }

    private static void drawIntegerSlider(final Setting<Integer> s, final int x, final int y) {
        final int color = -1719516928;
        int pixAdd = (x + 105 - x) * (s.getValue() - s.getMinValue()) / (s.getMaxValue() - s.getMinValue());
        if (GuiUtil.mouseOver(x + 3, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15 - 1)) {
            if (GuiUtil.lheld) {
                final int percentError = (GuiUtil.mX - x) * 100 / (x + 105 - x);
                s.setValue((int)(percentError * ((s.getMaxValue() - s.getMinValue()) / 100.0) + s.getMinValue()));
            }
            if (GuiUtil.rdown) {
                s.toggleOpen();
            }
        }
        if (GuiUtil.mouseOver(x + 3, y + 15 + BruceTheme.boost * 15 + 1, x + 6, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.lheld) {
            s.setValue(s.getMinValue());
            pixAdd = 4;
        }
        if (GuiUtil.mouseOver(x + 105 - 3, y + 15 + BruceTheme.boost * 15 + 1, x + 105, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.lheld) {
            s.setValue(s.getMaxValue());
            pixAdd = 105;
        }
        if (pixAdd < 4) {
            pixAdd = 4;
        }
        RenderUtil.fill(x, y + 15 + BruceTheme.boost * 15, x + 105, y + 30 + BruceTheme.boost * 15, 1195853639);
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15, x, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 105, y + 15 + BruceTheme.boost * 15, x + 105 + 1, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 3, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15, color);
        RenderUtil.fill(x + 3, y + 15 + BruceTheme.boost * 15 + 1, x - 1 + pixAdd, y + 15 + 15 + BruceTheme.boost * 15, -538544891);
        Velocity.mc.textRenderer.drawTextWithShadow(s.getName(), (x + 4 + 2), (y + 15 + 4 + BruceTheme.boost * 15), -1);
        Velocity.mc.textRenderer.drawTextWithShadow(String.valueOf(s.getValue()), (x + 4 + Velocity.mc.textRenderer.getTextWidth(s.getName()) + 6), (y + 15 + 4 + BruceTheme.boost * 15), -9013642);
    }

    private static void drawIntegerSubSlider(final SubSetting<Integer> ss, final int x, final int y) {
        final int color = -1719516928;
        int pixAdd = (x + 105 - x) * (ss.getValue() - ss.getMinValue()) / (ss.getMaxValue() - ss.getMinValue());
        if (GuiUtil.mouseOver(x + 5, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.lheld) {
            final int percentError = (GuiUtil.mX - x) * 100 / (x + 105 - x);
            ss.setValue((int)(percentError * ((ss.getMaxValue() - ss.getMinValue()) / 100.0) + ss.getMinValue()));
        }
        if (GuiUtil.mouseOver(x + 5, y + 15 + BruceTheme.boost * 15 + 1, x + 6, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.lheld) {
            ss.setValue(ss.getMinValue());
            pixAdd = 6;
        }
        if (GuiUtil.mouseOver(x + 105 - 5, y + 15 + BruceTheme.boost * 15 + 1, x + 105, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.lheld) {
            ss.setValue(ss.getMaxValue());
            pixAdd = 105;
        }
        if (pixAdd < 6) {
            pixAdd = 6;
        }
        RenderUtil.fill(x, y + 15 + BruceTheme.boost * 15 + 1, x + 105, y + 30 + BruceTheme.boost * 15, 1195853639);
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15, x, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 105, y + 15 + BruceTheme.boost * 15, x + 105 + 1, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 5, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15, color);
        RenderUtil.fill(x + 5, y + 15 + BruceTheme.boost * 15 + 1, x - 1 + pixAdd, y + 15 + 15 + BruceTheme.boost * 15, -538544891);
        Velocity.mc.textRenderer.drawTextWithShadow(ss.getName(), (x + 6 + 2), (y + 15 + 4 + BruceTheme.boost * 15), -1);
        Velocity.mc.textRenderer.drawTextWithShadow(String.valueOf(ss.getValue()), (x + 4 + Velocity.mc.textRenderer.getTextWidth(ss.getName()) + 8), (y + 15 + 4 + BruceTheme.boost * 15), -9013642);
    }

    private static void drawDoubleSlider(final Setting<Double> s, final int x, final int y) {
        final int color = -1719516928;
        int pixAdd = (int)((x + 105 - x) * (s.getValue() - s.getMinValue()) / (s.getMaxValue() - s.getMinValue()));
        if (GuiUtil.mouseOver(x + 3, y + 15 + BruceTheme.boost * 15, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.lheld) {
            final int percentError = (GuiUtil.mX - x) * 100 / (x + 105 - x);
            s.setValue(MathUtil.roundDouble(percentError * ((s.getMaxValue() - s.getMinValue()) / 100.0) + s.getMinValue(), s.getRoundingScale()));
            if (GuiUtil.rdown) {
                s.toggleOpen();
            }
        }
        if (GuiUtil.mouseOver(x + 3, y + 15 + BruceTheme.boost * 15, x + 6, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.lheld) {
            s.setValue(s.getMinValue());
            pixAdd = 4;
        }
        if (GuiUtil.mouseOver(x + 105 - 3, y + 15 + BruceTheme.boost * 15, x + 105, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.lheld) {
            s.setValue(s.getMaxValue());
            pixAdd = 105;
        }
        if (pixAdd < 4) {
            pixAdd = 4;
        }
        RenderUtil.fill(x, y + 15 + BruceTheme.boost * 15, x + 105, y + 30 + BruceTheme.boost * 15, 1195853639);
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15, x, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 105, y + 15 + BruceTheme.boost * 15, x + 105 + 1, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 3, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15, color);
        RenderUtil.fill(x + 3, y + 15 + BruceTheme.boost * 15 + 1, x - 1 + pixAdd, y + 15 + 15 + BruceTheme.boost * 15, -538544891);
        Velocity.mc.textRenderer.drawTextWithShadow(s.getName(), (x + 4 + 2), (y + 15 + 4 + BruceTheme.boost * 15), -1);
        Velocity.mc.textRenderer.drawTextWithShadow(String.valueOf(s.getValue()), (x + 4 + Velocity.mc.textRenderer.getTextWidth(s.getName()) + 6), (y + 15 + 4 + BruceTheme.boost * 15), -9013642);
    }

    private static void drawDoubleSubSlider(final SubSetting<Double> ss, final int x, final int y) {
        final int color = -1719516928;
        int pixAdd = (int)((x + 105 - x) * (ss.getValue() - ss.getMinValue()) / (ss.getMaxValue() - ss.getMinValue()));
        if (GuiUtil.mouseOver(x + 5, y + 15 + BruceTheme.boost * 15, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.lheld) {
            final int percentError = (GuiUtil.mX - x) * 100 / (x + 105 - x);
            ss.setValue(MathUtil.roundDouble(percentError * ((ss.getMaxValue() - ss.getMinValue()) / 100.0) + ss.getMinValue(), ss.getRoundingScale()));
        }
        if (GuiUtil.mouseOver(x + 5, y + 15 + BruceTheme.boost * 15, x + 6, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.lheld) {
            ss.setValue(ss.getMinValue());
            pixAdd = 6;
        }
        if (GuiUtil.mouseOver(x + 105 - 5, y + 15 + BruceTheme.boost * 15, x + 105, y + 15 + 15 + BruceTheme.boost * 15 - 1) && GuiUtil.lheld) {
            ss.setValue(ss.getMaxValue());
            pixAdd = 105;
        }
        if (pixAdd < 6) {
            pixAdd = 6;
        }
        RenderUtil.fill(x, y + 15 + BruceTheme.boost * 15, x + 105, y + 30 + BruceTheme.boost * 15, 1195853639);
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15, x, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 105, y + 15 + BruceTheme.boost * 15, x + 105 + 1, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 5, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15, color);
        RenderUtil.fill(x + 5, y + 15 + BruceTheme.boost * 15 + 1, x - 1 + pixAdd, y + 15 + 15 + BruceTheme.boost * 15, -538544891);
        Velocity.mc.textRenderer.drawTextWithShadow(ss.getName(), (x + 4 + 4), (y + 15 + 4 + BruceTheme.boost * 15), -1);
        Velocity.mc.textRenderer.drawTextWithShadow(String.valueOf(ss.getValue()), (x + 6 + Velocity.mc.textRenderer.getTextWidth(ss.getName()) + 6), (y + 15 + 4 + BruceTheme.boost * 15), -9013642);
    }

    public void drawBind(final Module m, final int key, final int x, final int y) {
        String name = Keyboard.getKeyName(m.getKeybind());
        final int color = -1719516928;
        if (GuiUtil.mouseOver(x + 3, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15) && GuiUtil.ldown) {
            m.setBinding(true);
        }
        if (m.isBinding() && key != -1 && key != 1 && key != 211) {
            m.setKey(key);
            m.setBinding(false);
        }
        if (m.isBinding() && key == 1) {
            m.setBinding(false);
        }
        RenderUtil.fill(x, y + 15 + BruceTheme.boost * 15 + 1, x + 105, y + 30 + BruceTheme.boost * 15, 1195853639);
        RenderUtil.fill(x - 1, y + 15 + BruceTheme.boost * 15, x, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 105, y + 15 + BruceTheme.boost * 15, x + 105 + 1, y + 15 + 15 + BruceTheme.boost * 15, -859343872);
        RenderUtil.fill(x + 3, y + 15 + BruceTheme.boost * 15 + 1, x + 105 - 1, y + 15 + 15 + BruceTheme.boost * 15, color);
        if (!m.isBinding()) {
            Velocity.mc.textRenderer.drawTextWithShadow("Keybind", (x + 7), (y + 15 + 4 + BruceTheme.boost * 15), -1);
            Velocity.mc.textRenderer.drawTextWithShadow(name, (x + 7 + Velocity.mc.textRenderer.getTextWidth("Keybind") + 3), (y + 15 + 4 + BruceTheme.boost * 15), -9013642);
        }
        else {
            Velocity.mc.textRenderer.drawTextWithShadow("Listening...", (x + 4), (y + 15 + 7 + BruceTheme.boost * 15), -9013642);
        }
    }
    static {
        BruceTheme.boost = 0;
    }
}