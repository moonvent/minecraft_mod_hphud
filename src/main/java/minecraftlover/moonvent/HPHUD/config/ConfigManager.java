package minecraftlover.moonvent.HPHUD.config;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;

import minecraftlover.moonvent.HPHUD.util.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
  private final Path configPath = Path.of("config/" + Constant.MOD_NAME + ".cfg");
  private final TomlWriter tomlWriter = new TomlWriter();
  private File configFile;

  public ConfigManager() throws IOException {
    loadConfig();
  }

  private void createConfig() throws IOException {
    Files.createDirectories(configPath.getParent());
    saveDefaultConfig();
  }

  public void saveDefaultConfig() {
    try {
      tomlWriter.write(new ModConfig(), configPath.toFile());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void loadConfig() throws IOException {
    configFile = configPath.toFile();

    if (configFile.exists()) {
      Toml toml = new Toml().read(configFile);
//      ModConfig.outputGeneralAmountEnemyHp = toml.getBoolean("outputGeneralAmountEnemyHp");
    } else {
      createConfig();
    }
  }

  public void saveConfig() {
    try {
      tomlWriter.write(new ModConfig(), configPath.toFile());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
