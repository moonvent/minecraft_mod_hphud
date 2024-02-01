package minecraftlover.moonvent.HPHUD.config;

import minecraftlover.moonvent.HPHUD.util.Constant;

import java.io.*;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;
import java.nio.file.Path;
import java.nio.file.Files;

public final class ModConfig {
  // for singleton
  private static ModConfig instance;
  public static String outputIndicatorMode = "currentHP";
  public static int searchDistance = 20;
  public static String indicatorColor = "FFAFFF";
  public static String indicatorPosition = "LEFT_UPPER_NEAR_CROSSHAIR";
  public static boolean warningAfterPressMenuKey = true;
  public static boolean greetingsAfterEnterInWorld = true;

  public static ModConfig getInstance() {
    return instance;
  }

  private static final Path CONFIG_PATH = Paths.get(Constant.CONFIG_FILE_PATH);
  private Properties configProps = new Properties();

  public ModConfig() {
    instance = this;
    load();
  }

  private void load() {
    try {
      if (!Files.exists(CONFIG_PATH)) {
        Files.createDirectories(CONFIG_PATH.getParent());
        save();
      } else {
        configProps.load(new FileInputStream(CONFIG_PATH.toFile()));
        outputIndicatorMode = configProps.getProperty(Constant.ModConfigField.OUTPUT_INDICATOR_MODE,
            outputIndicatorMode);
        searchDistance = Integer.parseInt(configProps.getProperty(Constant.ModConfigField.SEARCH_DISTANCE,
            String.valueOf(searchDistance)));
        indicatorColor = configProps.getProperty(Constant.ModConfigField.INDICATOR_COLOR,
            String.valueOf(indicatorColor));
        indicatorPosition = configProps.getProperty(Constant.ModConfigField.INDICATOR_POSITION,
            indicatorPosition);
        warningAfterPressMenuKey = Objects.equals(configProps.getProperty(Constant.ModConfigField.WARNING_AFTER_PRESS_MENU_KEY,
                "1"), "1");
        greetingsAfterEnterInWorld = Objects.equals(configProps.getProperty(Constant.ModConfigField.GREETINGS_AFTER_ENTER_IN_WORLD,
                "1"), "1");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void save() {
    try {
      configProps.setProperty(Constant.ModConfigField.OUTPUT_INDICATOR_MODE, outputIndicatorMode);
      configProps.setProperty(Constant.ModConfigField.SEARCH_DISTANCE, String.valueOf(searchDistance));
      configProps.setProperty(Constant.ModConfigField.INDICATOR_COLOR, String.valueOf(indicatorColor));
      configProps.setProperty(Constant.ModConfigField.INDICATOR_POSITION, indicatorPosition);
      configProps.setProperty(Constant.ModConfigField.WARNING_AFTER_PRESS_MENU_KEY, String.valueOf(warningAfterPressMenuKey ? 1 : 0));
      configProps.setProperty(Constant.ModConfigField.GREETINGS_AFTER_ENTER_IN_WORLD, String.valueOf(greetingsAfterEnterInWorld ? 1 : 0));
      configProps.store(new FileOutputStream(CONFIG_PATH.toFile()), Constant.CONFIG_FILE_HEADER_DESCRIPTION);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
