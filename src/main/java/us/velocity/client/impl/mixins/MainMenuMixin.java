package us.velocity.client.impl.mixins;

import net.minecraft.client.gui.screen.menu.MainMenu;
import net.minecraft.client.render.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import us.velocity.client.Velocity;
import us.velocity.client.api.util.ColorUtil;

@Mixin(MainMenu.class)
public class MainMenuMixin
{
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/menu/MainMenu;drawTextWithShadow(Lnet/minecraft/client/render/TextRenderer;Ljava/lang/String;III)V", ordinal = 0))
    public void drawText(MainMenu mainMenu, TextRenderer renderer, String text, int x, int y, int colour) {
        Velocity.mc.textRenderer.drawTextWithShadow("Velocity-Fabric", 2, 2, ColorUtil.toRGBA(100, 0, 255, 255));
    }
}
