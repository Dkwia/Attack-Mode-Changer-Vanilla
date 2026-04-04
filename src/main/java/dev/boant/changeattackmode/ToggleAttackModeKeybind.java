package dev.boant.changeattackmode;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public final class ToggleAttackModeKeybind {
    private static final Text HUD_TEXT = Text.translatable("changeattackmode.hud.toggle_on");

    public static final KeyBinding KEY = new KeyBinding(
        "key.changeattackmode.toggle_attack_mode",
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_UNKNOWN,
        KeyBinding.Category.GAMEPLAY
    );

    private ToggleAttackModeKeybind() {
    }

    public static Text getHudText() {
        return HUD_TEXT;
    }
}
