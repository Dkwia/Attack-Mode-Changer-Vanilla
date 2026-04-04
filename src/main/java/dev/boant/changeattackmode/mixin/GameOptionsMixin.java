package dev.boant.changeattackmode.mixin;

import dev.boant.changeattackmode.ToggleAttackModeKeybind;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(GameOptions.class)
abstract class GameOptionsMixin {
    @Shadow
    @Final
    @Mutable
    public KeyBinding[] allKeys;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeattackmode$registerToggleKeybind(CallbackInfo ci) {
        this.allKeys = Arrays.copyOf(this.allKeys, this.allKeys.length + 1);
        this.allKeys[this.allKeys.length - 1] = ToggleAttackModeKeybind.KEY;
        KeyBinding.updateKeysByCode();
    }
}
