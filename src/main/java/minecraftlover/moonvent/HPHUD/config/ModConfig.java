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

    // private String __comment = "Its a config for setup mod [HPHUD], json doesnt
    // support quats, cause I use square brackets, but you need to use a double
    // quotes instead it, "
    // +
    // "I try to describe a field and it do with comment which start from double
    // underscore, this comments doesnt config something, "
    // +
    // "its just for understanding what doing field without double undersocre, for
    // example [__searchDistance]: [range for how far (in block) you can see hp from
    // entity], "
    // +
    // "and this var themself [searchDistance]: 20";

    // private String __outputIndicatorMod = "Mod output general amount of entity
    // health, for example, can accept a 3 type of string: "
    // +
    // "currentHP (install by default, display only current HP amount of entity,
    // example = [20]), " +
    // "currentWithMaxHP (display current hp / max hp, example = [18 / 20]), " +
    // "currentPercentageHP (display current HP amount in percentage, for example,
    // entity max hp is 20hp, and now entity has 2 hp, output in this case will be
    // [10%])";
    public String outputIndicatorMod = "currentPercentageHP";

    // private String __searchDistance = "How far (in blocks) mod will handle entity
    // for present amount of hp";
    public int searchDistance = 20;

    // private String __indicatorColor = "Color of indicator. I try a dozen colors,
    // but select exactly pink, because that color has the least chance of blending
    // in with the background. "
    // +
    // "Sets in int format, you can convert your color in some service, for example
    // from that http://www.shodor.org/~efarrow/trunk/html/rgbint.html";
    public int indicatorColor = 0xFFAFFF;

    // public String __indicatorPosition = "Position of indecator, can be:
    // [LEFT_UPPER_NEAR_CROSSHAIR | RIGHT_UPPER_NEAR_CROSSHAIR |
    // LEFT_BOTTOM_NEAR_CROSSHAIR | RIGHT_BOTTOM_NEAR_CROSSHAIR | "
    // +
    // "LEFT_NEAR_CROSSHAIR | RIGHT_NEAR_CROSSHAIR | TOP_NEAR_CROSSHAIR |
    // BOTTOM_NEAR_CROSSHAIR | " +
    // "LEFT_UPPER_OF_SCREEN | RIGHT_UPPER_OF_SCREEN | LEFT_BOTTOM_OF_SCREEN |
    // RIGHT_BOTTOM_OF_SCREEN | " +
    // "LEFT_OF_SCREEN | RIGHT_OF_SCREEN | TOP_OF_SCREEN | BOTTOM_OF_SCREEN |
    // CUSTOM_FROM_CENTER (its more advanced mode, for work with it check section
    // below) | FULL_CUSTOM (description same as previous mode)], "
    // +
    // "by default: [indicatorPosition]: [LEFT_UPPER_NEAR_CROSSHAIR]";
    public String indicatorPosition = "LEFT_UPPER_NEAR_CROSSHAIR";

    // public String __setupCustomPosition = "if you select CUSTOM_FROM_CENTER you
    // can setup a size from crosshair user, "
    // +
    // "for move indicator to left top side you need to decrease X and Y coordinate,
    // if you want move it to right bottom, you need increse this params; "
    // +
    // "if you select FULL_CUSTOM, you can setup X and Y coordinates for coodinates
    // of indicator not depend from center coordinates";
    public int indicatorPositionCustomX = 1;
    public int indicatorPositionCustomY = 2;

    public static ModConfig getInstance() {
        return instance;
    }

    private static final Path CONFIG_PATH = Paths.get("config/" + Constant.MOD_NAME + ".cfg");
    private Properties configProps = new Properties();

    public ModConfig() {
        instance = this;
        load();
    }

    private void load() {
        try {
            if (!Files.exists(CONFIG_PATH)) {
                Files.createDirectories(CONFIG_PATH.getParent());
                configProps.setProperty(Constant.ModConfigField.OUTPUT_INDICATOR_MODE, outputIndicatorMod);
                configProps.setProperty(Constant.ModConfigField.SEARCH_DISTANCE, String.valueOf(searchDistance));
                configProps.setProperty(Constant.ModConfigField.INDICATOR_COLOR, String.valueOf(indicatorColor));
                configProps.setProperty(Constant.ModConfigField.INDICATOR_POSITION, indicatorPosition);
                configProps.setProperty(Constant.ModConfigField.INDICATOR_POSITION_CUSTOM_X,
                        String.valueOf(indicatorPositionCustomX));
                configProps.setProperty(Constant.ModConfigField.INDICATOR_POSITION_CUSTOM_Y,
                        String.valueOf(indicatorPositionCustomY));
                save();
            } else {
                configProps.load(new FileInputStream(CONFIG_PATH.toFile()));
                // Читаем и преобразуем значения
                // setting1 = Integer.parseInt(configProps.getProperty("setting1", "5"));
                // setting2 = Boolean.parseBoolean(configProps.getProperty("setting2", "true"));
                outputIndicatorMod = configProps.getProperty(Constant.ModConfigField.OUTPUT_INDICATOR_MODE,
                        outputIndicatorMod);
                searchDistance = Integer.parseInt(configProps.getProperty(Constant.ModConfigField.SEARCH_DISTANCE,
                        String.valueOf(searchDistance)));
                indicatorColor = Integer.parseInt(configProps.getProperty(Constant.ModConfigField.INDICATOR_COLOR,
                        String.valueOf(indicatorColor)));
                indicatorPosition = configProps.getProperty(Constant.ModConfigField.INDICATOR_POSITION,
                        indicatorPosition);
                indicatorPositionCustomX = Integer
                        .parseInt(configProps.getProperty(Constant.ModConfigField.INDICATOR_POSITION_CUSTOM_X,
                                String.valueOf(indicatorPositionCustomX)));
                indicatorPositionCustomY = Integer
                        .parseInt(configProps.getProperty(Constant.ModConfigField.INDICATOR_POSITION_CUSTOM_Y,
                                String.valueOf(indicatorPositionCustomY)));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            configProps.store(new FileOutputStream(CONFIG_PATH.toFile()), "Mod Configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
