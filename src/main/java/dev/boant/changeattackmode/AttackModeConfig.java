package dev.boant.changeattackmode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class AttackModeConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("changeattackmode.json");

    private AttackMode mode = AttackMode.HOLD;

    public AttackMode getMode() {
        return mode;
    }

    public void setMode(AttackMode mode) {
        this.mode = mode;
    }

    public static AttackModeConfig load() {
        if (!Files.exists(PATH)) {
            AttackModeConfig config = new AttackModeConfig();
            config.save();
            return config;
        }

        try (Reader reader = Files.newBufferedReader(PATH)) {
            AttackModeConfig config = GSON.fromJson(reader, AttackModeConfig.class);
            if (config == null || config.mode == null) {
                throw new JsonParseException("Config did not contain a valid attack mode");
            }
            return config;
        } catch (IOException | JsonParseException exception) {
            AttackModeConfig config = new AttackModeConfig();
            config.save();
            return config;
        }
    }

    public void save() {
        try {
            Files.createDirectories(PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(PATH)) {
                GSON.toJson(this, writer);
            }
        } catch (IOException ignored) {
        }
    }
}
