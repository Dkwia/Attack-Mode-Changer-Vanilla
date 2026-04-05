package dev.boant.changeattackmode;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

@SuppressWarnings("unused")
public final class ChangeAttackModeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(ToggleAttackModeKeybind.KEY);
    }
}
