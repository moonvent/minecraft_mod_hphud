package minecraftlover.moonvent.HPHUD.util;

/**
 * Class {@code Constant} need for store all constants in project.
 * <p>
 * This class need to store all constants in project
 * </p>
 */
public final class Constant {
  public static String MOD_NAME = "HPHUD";

  public static final String MOD_ID = "minecraftlover_moonvent_hphud";

  public static final String LOGGER_HUD_NAME = MOD_NAME + "| HUD";

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
    // public static final String RIGHT_UPPER_OF_SCREEN = "RIGHT_UPPER_OF_SCREEN";
    // public static final String LEFT_BOTTOM_OF_SCREEN = "LEFT_BOTTOM_OF_SCREEN";
    // public static final String RIGHT_BOTTOM_OF_SCREEN = "RIGHT_BOTTOM_OF_SCREEN";
    // public static final String LEFT_OF_SCREEN = "LEFT_OF_SCREEN";
    // public static final String RIGHT_OF_SCREEN = "RIGHT_OF_SCREEN";
    // public static final String TOP_OF_SCREEN = "TOP_OF_SCREEN";
    // public static final String BOTTOM_OF_SCREEN = "BOTTOM_OF_SCREEN";
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
}
