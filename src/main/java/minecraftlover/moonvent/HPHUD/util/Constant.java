package minecraftlover.moonvent.HPHUD.util;

/**
 * Class {@code Constant} need for store all constants in project.
 * <p>
 * This class need to store all constants in project
 * </p>
 */
public final class Constant {
  public static String MOD_NAME = "HPHUD";
  public static String MOD_NAME_FOR_LOCALIZATION = "hphud";

  public static final String MOD_ID = "moonvent_hp_hud_mod";

  public static final String LOGGER_HUD_NAME = MOD_NAME + "| HUD";

  public static final String CONFIG_FILE_PATH = "config/" + MOD_NAME + ".cfg";

  public static final String CONFIG_FILE_HEADER_DESCRIPTION = "[HPHUP]\n" +
      "Mod configuration file\n\n"
      +
      "This config sets a few detail which help you tailor the mod more for your playstyle,\n"
      +
      "below, I have describe a several fields and all possible values for them,\n"
      +
      "this documentation should help you in configuring the mod;\n"
      +
      "Happy configuring :)\n\n"
      +
      "==================================================\n\n"
      +
      "outputIndicatorMode - mod has 3 different modes to output you amount of entity health:\n\n"
      +
      "  Available values:\n"
      +
      "    1) currentHP - display amount of current entity HP, default value for parametr\n"
      +
      "      Example:\n"
      +
      "      10\n"
      +
      "    2) currentWithMaxHP - display current hp, slash, max hp\n"
      +
      "      Example:\n"
      +
      "        10 / 20\n"
      +
      "    3) currentPercentageHP - display amount of entity hp in percentage\n"
      +
      "      Example:\n"
      +
      "        50%\n\n"
      +
      "searchDistance - How far (in blocks) mod will be search entity for display amount of HP\n"
      +
      "  Available values:\n\n"
      +
      "    Positive Integer, 1 - 100, if set someting wrong\n"
      +
      "  Example:\n"
      +
      "    20\n\n"
      +
      "indicatorColor - Color of indicator, I try a several colors, and find that pink :)\n"
      +
      "  is more suitable for most cases, but, if you think otherwise and have a suitable replacement, let me know.\n"
      +
      "  Our staff of one employee will check it, and if necessary, change the default color\n\n"
      +
      "  Available values:\n"
      +
      "    Any color in hex view, for example, and by default sets FFAFFF\n"
      +
      "  Example:\n"
      +
      "    FFAFFF\n\n"
      +
      "indicatorPosition - Indicator position, now, in relation from crosshair\n\n"
      +
      "  Available values:\n"
      +
      "    LEFT_UPPER_NEAR_CROSSHAIR, RIGHT_UPPER_NEAR_CROSSHAIR, LEFT_BOTTOM_NEAR_CROSSHAIR, RIGHT_BOTTOM_NEAR_CROSSHAIR,\n"
      +
      "    RIGHT_NEAR_CROSSHAIR, TOP_NEAR_CROSSHAIR, BOTTOM_NEAR_CROSSHAIR, LEFT_NEAR_CROSSHAIR\n"
      +
      "  Example:\n"
      +
      "    LEFT_UPPER_NEAR_CROSSHAIR\n\n"
      +
      "Final default config example seem like that:\n\n"
      +
      "indicatorColor=FFAFFF\n"
      +
      "indicatorPosition=LEFT_UPPER_NEAR_CROSSHAIR\n"
      +
      "outputIndicatorMode=currentHP\n"
      +
      "searchDistance=20\n\n"
      +
      "If something went wrong, you are set the wrong type of parametr or wrong parametr,\n"
      +
      "mod will be use default value, which describe in a config example above\n\n"
      +
      "Thank you for playing with this mod!\n";

  public static final Integer MaxSearchDistance = 50;
  public static final Integer minSearchDistance = 5;

  public static final class INDICATOR_TYPE {
    public static final String CURRENT_HP = "currentHP";
    public static final String CURRENT_WITH_MAX_HP = "currentWithMaxHP";
    public static final String CURRENT_PERCENTAGE_HP = "currentPercentageHP";
  }

  public static final class INDICATOR_POSITION {
    public static final String LEFT_UPPER_NEAR_CROSSHAIR = "LEFT_UPPER_NEAR_CROSSHAIR";
    public static final String RIGHT_UPPER_NEAR_CROSSHAIR = "RIGHT_UPPER_NEAR_CROSSHAIR";
    public static final String LEFT_BOTTOM_NEAR_CROSSHAIR = "LEFT_BOTTOM_NEAR_CROSSHAIR";
    public static final String RIGHT_BOTTOM_NEAR_CROSSHAIR = "RIGHT_BOTTOM_NEAR_CROSSHAIR";
    public static final String RIGHT_NEAR_CROSSHAIR = "RIGHT_NEAR_CROSSHAIR";
    public static final String TOP_NEAR_CROSSHAIR = "TOP_NEAR_CROSSHAIR";
    public static final String BOTTOM_NEAR_CROSSHAIR = "BOTTOM_NEAR_CROSSHAIR";
    public static final String LEFT_NEAR_CROSSHAIR = "LEFT_NEAR_CROSSHAIR";
    // public static final String
    // RIGHT_UPPER_OF_SCREEN =
    // "RIGHT_UPPER_OF_SCREEN";
    // public static final String
    // LEFT_BOTTOM_OF_SCREEN =
    // "LEFT_BOTTOM_OF_SCREEN";
    // public static final String
    // RIGHT_BOTTOM_OF_SCREEN =
    // "RIGHT_BOTTOM_OF_SCREEN";
    // public static final String
    // LEFT_OF_SCREEN =
    // "LEFT_OF_SCREEN";
    // public static final String
    // RIGHT_OF_SCREEN =
    // "RIGHT_OF_SCREEN";
    // public static final String
    // TOP_OF_SCREEN =
    // "TOP_OF_SCREEN";
    // public static final String
    // BOTTOM_OF_SCREEN =
    // "BOTTOM_OF_SCREEN";
    public static final String CUSTOM_FROM_CENTER = "CUSTOM_FROM_CENTER";
    public static final String FULL_CUSTOM = "FULL_CUSTOM";
  }

  public static final class ModConfigField {
    public static final String OUTPUT_INDICATOR_MODE = "outputIndicatorMode";
    public static final String SEARCH_DISTANCE = "searchDistance";
    public static final String INDICATOR_COLOR = "indicatorColor";
    public static final String INDICATOR_POSITION = "indicatorPosition";
    public static final String INDICATOR_POSITION_CUSTOM_X = "indicatorPositionCustomX";
    public static final String INDICATOR_POSITION_CUSTOM_Y = "indicatorPositionCustomY";
  }

  public static final class LocalizationKey {
    public static final String GREETINGS_IN_WORLD = "chat." + MOD_NAME_FOR_LOCALIZATION
        + ".welcome";
    public static final String SETTINGS_CATEGORY_NAME = "category."
        + MOD_NAME_FOR_LOCALIZATION
        + ".general";
    public static final String SETTINGS_DESCRIPTION_CONFIGURE_KEY = "key."
        + MOD_NAME_FOR_LOCALIZATION
        + ".configure_mod";

    public static final String CONFIGURATION_MENU_NAME = "menu." + MOD_NAME_FOR_LOCALIZATION
        + ".configuration";

    public static final String WARNING_IN_CHAT_ABOUT_CONFLICTS = "chat." + MOD_NAME_FOR_LOCALIZATION
        + ".keybind_config_menu_warging";

    public static final String INDICATOR_COLOR_TEXTFIELD = "textfield."
        + MOD_NAME_FOR_LOCALIZATION + ".indicatorcolor";

    public static final String MODE_BUTTON_CURRENT_HP = "button."
        + MOD_NAME_FOR_LOCALIZATION +
        ".currentHP";

    public static final String MODE_BUTTON_CURRENT_WITH_MAX_HP = "button."
        + MOD_NAME_FOR_LOCALIZATION +
        ".currentWithMaxHP";

    public static final String MODE_BUTTON_CURRENT_PERCENTAGE_HP = "button."
        + MOD_NAME_FOR_LOCALIZATION +
        ".currentPercentageHP";

    public static final String MODE_BUTTON_DESCRIPTION = "button."
        + MOD_NAME_FOR_LOCALIZATION +
        ".mods";
    public static final String MODE_BUTTON_POSITION = "button."
        + MOD_NAME_FOR_LOCALIZATION +
        ".position";

    public static final String LEFT_UPPER_NEAR_CROSSHAIR = "button."
        + MOD_NAME_FOR_LOCALIZATION
        + "." +
        INDICATOR_POSITION.LEFT_UPPER_NEAR_CROSSHAIR;
    public static final String RIGHT_UPPER_NEAR_CROSSHAIR = "button."
        + MOD_NAME_FOR_LOCALIZATION
        + "." +
        INDICATOR_POSITION.RIGHT_UPPER_NEAR_CROSSHAIR;
    public static final String LEFT_BOTTOM_NEAR_CROSSHAIR = "button."
        + MOD_NAME_FOR_LOCALIZATION
        + "." +
        INDICATOR_POSITION.LEFT_BOTTOM_NEAR_CROSSHAIR;
    public static final String RIGHT_BOTTOM_NEAR_CROSSHAIR = "button."
        + MOD_NAME_FOR_LOCALIZATION
        + "." +
        INDICATOR_POSITION.RIGHT_BOTTOM_NEAR_CROSSHAIR;
    public static final String RIGHT_NEAR_CROSSHAIR = "button."
        + MOD_NAME_FOR_LOCALIZATION
        + "." +
        INDICATOR_POSITION.RIGHT_NEAR_CROSSHAIR;
    public static final String TOP_NEAR_CROSSHAIR = "button."
        + MOD_NAME_FOR_LOCALIZATION
        + "." +
        INDICATOR_POSITION.TOP_NEAR_CROSSHAIR;
    public static final String BOTTOM_NEAR_CROSSHAIR = "button."
        + MOD_NAME_FOR_LOCALIZATION
        + "." +
        INDICATOR_POSITION.BOTTOM_NEAR_CROSSHAIR;
    public static final String LEFT_NEAR_CROSSHAIR = "button."
        + MOD_NAME_FOR_LOCALIZATION
        + "." +
        INDICATOR_POSITION.LEFT_NEAR_CROSSHAIR;

    public static final String VISIBILITY_RANGE = "scroll." + MOD_NAME_FOR_LOCALIZATION + ".visibility_range";
    public static final String VISIBILITY_RANGE_VALUE = "scroll." + MOD_NAME_FOR_LOCALIZATION + ".visibility_range_value";

  }

  public static final class ResourcesPath {
    public static final Integer MOD_TEXTURE_SIZE = 64;
    public static final String MOD_CURRENT_HP = "textures/gui/modes/current_hp.png";
    public static final String MOD_CURRENT_WITH_MAX_HP = "textures/gui/modes/current_with_max_hp.png";
    public static final String MOD_CURRENT_PERCANTAGE_HP = "textures/gui/modes/current_percentage_hp.png";
    public static final String RIGHT_BOTTOM_NEAR_CROSSHAIR = "textures/gui/position/right_bottom.png";
    public static final String RIGHT_UPPER_NEAR_CROSSHAIR = "textures/gui/position/right_upper.png";
    public static final String LEFT_BOTTOM_NEAR_CROSSHAIR = "textures/gui/position/left_bottom.png";
    public static final String LEFT_UPPER_NEAR_CROSSHAIR = "textures/gui/position/left_upper.png";
    public static final String BOTTOM_NEAR_CROSSHAIR = "textures/gui/position/bottom.png";
    public static final String TOP_NEAR_CROSSHAIR = "textures/gui/position/top.png";
    public static final String RIGHT_NEAR_CROSSHAIR = "textures/gui/position/right.png";
    public static final String LEFT_NEAR_CROSSHAIR = "textures/gui/position/left.png";
  }

  public static enum LayoutType {
    VERTICAL, HORIZONTAL
  }

}
