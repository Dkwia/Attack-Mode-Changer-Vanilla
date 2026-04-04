# Change Attack Mode

Client-side Fabric mod for Minecraft `1.21.10`.

This mod adds a rebindable key that switches the vanilla `Attack/Destroy` mode between `Hold` and `Toggle`.

## Features

- Adds a keybind in `Options -> Controls -> Key Binds`
- Changes the real vanilla `Attack/Destroy` toggle setting
- Shows HUD text while attack toggle is enabled
- Clears the active sticky attack when switching back to hold mode

## Requirements

- Minecraft `1.21.10`
- Fabric Loader `0.17.0` or newer
- Fabric API `0.134.1+1.21.10`
- Java `21`

## Install

1. Install Fabric Loader for Minecraft `1.21.10`
2. Put Fabric API in the `mods` folder
3. Put this mod jar in the `mods` folder
4. Start the game

## Usage

1. Open `Options -> Controls -> Key Binds`
2. Bind `Attack Toggle: ON` to any key you want
3. Press that key in-game to switch vanilla `Attack/Destroy` between hold and toggle

When toggle mode is enabled, the HUD shows `Attack Toggle: ON`.

## Build

```powershell
.\gradlew clean build
```

The built jar will be in:

`build/libs/change-attack-mode-1.0.0.jar`

## Notes

- This is a client-only mod.
- The mod does not add a custom attack system. It changes the existing vanilla attack toggle option.
