package minecraftlover.moonvent.HPHUD.config;

import minecraftlover.moonvent.HPHUD.util.Constant;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.spongepowered.include.com.google.gson.Gson;
import org.spongepowered.include.com.google.gson.GsonBuilder;

public class ConfigManager {
  private final Path configPath = Path.of("config/" + Constant.MOD_NAME + ".cfg");
  private File configFile = configPath.toFile();
  private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
  private ModConfig config;

  public ConfigManager() {
    loadConfig();
  }

  public void loadConfig() {
    if (!configFile.exists()) {
      saveDefaultConfig();
    }

    try (FileReader reader = new FileReader(configFile)) {
      config = GSON.fromJson(reader, ModConfig.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveDefaultConfig() {
    config = new ModConfig();
    try (FileWriter writer = new FileWriter(configFile)) {
      GSON.toJson(config, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
