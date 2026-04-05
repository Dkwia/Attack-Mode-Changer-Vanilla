package dev.boant.changeattackmode.mixin;

import dev.boant.changeattackmode.ToggleAttackModeKeybind;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
@SuppressWarnings("unused")
abstract class MinecraftClientMixin {
    private static final int CHANGEATTACKMODE_OVERLAY_REFRESH_TICKS = 10;

    @Shadow
    @Final
    public GameOptions options;

    private boolean changeattackmode$lastAttackToggled;
    private int changeattackmode$overlayRefreshTicks;

    @Inject(method = "tick", at = @At("TAIL"))
    @SuppressWarnings("unused")
    private void changeattackmode$handleAttackMode(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        while (ToggleAttackModeKeybind.KEY.wasPressed()) {
            SimpleOption<Boolean> attackToggled = this.options.getAttackToggled();
            boolean toggled = Boolean.TRUE.equals(attackToggled.getValue());
            boolean next = !toggled;

            attackToggled.setValue(next);
            this.options.write();

            if (!next) {
                KeyBinding.untoggleStickyKeys();
                this.options.attackKey.setPressed(false);
            }
        }

        boolean attackToggled = Boolean.TRUE.equals(this.options.getAttackToggled().getValue());
        if (!MinecraftClient.isHudEnabled() || client.player == null || client.currentScreen != null) {
            this.changeattackmode$lastAttackToggled = attackToggled;
            return;
        }

        if (attackToggled) {
            if (!this.changeattackmode$lastAttackToggled || this.changeattackmode$overlayRefreshTicks <= 0) {
                client.inGameHud.setOverlayMessage(ToggleAttackModeKeybind.getHudText(), false);
                this.changeattackmode$overlayRefreshTicks = CHANGEATTACKMODE_OVERLAY_REFRESH_TICKS;
            } else {
                this.changeattackmode$overlayRefreshTicks--;
            }
        } else if (this.changeattackmode$lastAttackToggled) {
            client.inGameHud.setOverlayMessage(Text.empty(), false);
            this.changeattackmode$overlayRefreshTicks = 0;
        }

        this.changeattackmode$lastAttackToggled = attackToggled;
    }
}
