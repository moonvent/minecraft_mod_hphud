package minecraftlover.moonvent.HPHUD.config;

import minecraftlover.moonvent.HPHUD.util.Constant;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;
import java.nio.file.Path;
import java.nio.file.Files;

public final class ModConfig {

    // for singleton
    private static ModConfig instance;

    public String outputIndicatorMode = "currentPercentageHP";

    public int searchDistance = 20;

    public int indicatorColor = 0xFFAFFF;

    public String indicatorPosition = "LEFT_UPPER_NEAR_CROSSHAIR";

    // public int indicatorPositionCustomX = 1;
    // public int indicatorPositionCustomY = 2;

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
                configProps.setProperty(Constant.ModConfigField.OUTPUT_INDICATOR_MODE, outputIndicatorMode);
                configProps.setProperty(Constant.ModConfigField.SEARCH_DISTANCE, String.valueOf(searchDistance));
                configProps.setProperty(Constant.ModConfigField.INDICATOR_COLOR, String.valueOf(indicatorColor));
                configProps.setProperty(Constant.ModConfigField.INDICATOR_POSITION, indicatorPosition);
                // configProps.setProperty(Constant.ModConfigField.INDICATOR_POSITION_CUSTOM_X,
                // String.valueOf(indicatorPositionCustomX));
                // configProps.setProperty(Constant.ModConfigField.INDICATOR_POSITION_CUSTOM_Y,
                // String.valueOf(indicatorPositionCustomY));
                save();
            } else {
                configProps.load(new FileInputStream(CONFIG_PATH.toFile()));
                outputIndicatorMode = configProps.getProperty(Constant.ModConfigField.OUTPUT_INDICATOR_MODE,
                        outputIndicatorMode);
                searchDistance = Integer.parseInt(configProps.getProperty(Constant.ModConfigField.SEARCH_DISTANCE,
                        String.valueOf(searchDistance)));
                indicatorColor = Integer.parseInt(configProps.getProperty(Constant.ModConfigField.INDICATOR_COLOR,
                        String.valueOf(indicatorColor)), 16);
                indicatorPosition = configProps.getProperty(Constant.ModConfigField.INDICATOR_POSITION,
                        indicatorPosition);
                // indicatorPositionCustomX = Integer
                // .parseInt(configProps.getProperty(Constant.ModConfigField.INDICATOR_POSITION_CUSTOM_X,
                // String.valueOf(indicatorPositionCustomX)));
                // indicatorPositionCustomY = Integer
                // .parseInt(configProps.getProperty(Constant.ModConfigField.INDICATOR_POSITION_CUSTOM_Y,
                // String.valueOf(indicatorPositionCustomY)));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            configProps.store(new FileOutputStream(CONFIG_PATH.toFile()), Constant.CONFIG_FILE_HEADER_DESCRIPTION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
