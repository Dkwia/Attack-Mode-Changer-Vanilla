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
abstract class MinecraftClientMixin {
    @Shadow
    @Final
    public GameOptions options;

    @Inject(method = "tick", at = @At("TAIL"))
    private void changeattackmode$handleAttackMode(CallbackInfo ci) {
        MinecraftClient client = (MinecraftClient) (Object) this;

        while (ToggleAttackModeKeybind.KEY.wasPressed()) {
            SimpleOption<Boolean> attackToggled = this.options.getAttackToggled();
            boolean toggled = Boolean.TRUE.equals(attackToggled.getValue());
            boolean next = !toggled;

            attackToggled.setValue(next);
            this.options.write();

            if (!next) {
                KeyBinding.untoggleStickyKeys();
                this.options.attackKey.setPressed(false);
                client.inGameHud.setOverlayMessage(Text.empty(), false);
            }
        }

        if (client.player != null && client.isHudEnabled() && Boolean.TRUE.equals(this.options.getAttackToggled().getValue())) {
            client.inGameHud.setOverlayMessage(ToggleAttackModeKeybind.getHudText(), false);
        }
    }
}
