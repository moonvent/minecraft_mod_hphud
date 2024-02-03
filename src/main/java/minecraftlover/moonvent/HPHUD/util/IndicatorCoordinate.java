package minecraftlover.moonvent.HPHUD.util;

import minecraftlover.moonvent.HPHUD.config.ModConfig;

import net.minecraft.client.gui.screen.Screen;

import java.util.Objects;

public class IndicatorCoordinate {
  private static IndicatorCoordinate instance = null;

  private ModConfig config;

  private int offsetXMultiplier;
  private int offsetYMultiplier;

  private int x;
  private int y;

  private int centerX;
  private int centerY;

  private int offsetX;
  private int offsetY;

  private int horizontalGuiScale;
  private int verticalGuiScale;

  private Screen cachedPlayerScreen;

  public IndicatorCoordinate() {
    instance = this;
  }

  public static IndicatorCoordinate getInstance() {
    return instance;
  }

  public void setCachedPlayerScreen(Screen screen) {
    cachedPlayerScreen = screen;
    calculateCoordinates();
  }

  public int getCachedPlayerScreenWidth() {
    return (cachedPlayerScreen != null) ? cachedPlayerScreen.width : 0;
  }

  public int getCachedPlayerScreenHeight() {
    return (cachedPlayerScreen != null) ? cachedPlayerScreen.height : 0;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  private void calculateCoordinates() {
    config = ModConfig.getInstance();

    calculateOffsetCoordinates();

    horizontalGuiScale = !Objects.equals(config.outputIndicatorMode, Constant.IndicatorType.CURRENT_HP) ? 14 : 7;
    verticalGuiScale = 7;

    centerX = cachedPlayerScreen.width / 2;
    centerY = cachedPlayerScreen.height / 2;

    offsetX = offsetXMultiplier * horizontalGuiScale;
    offsetY = offsetYMultiplier * verticalGuiScale;

    x = centerX - offsetX;
    y = centerY - offsetY;
  }

  private void calculateOffsetCoordinates() {

    switch (config.indicatorPosition) {
      case Constant.IndicatorPosition.LEFT_UPPER_NEAR_CROSSHAIR:
        offsetXMultiplier = 3;
        offsetYMultiplier = 2;
        break;
      case Constant.IndicatorPosition.RIGHT_UPPER_NEAR_CROSSHAIR:
        offsetXMultiplier = -3;
        offsetYMultiplier = 2;
        break;
      case Constant.IndicatorPosition.LEFT_BOTTOM_NEAR_CROSSHAIR:
        offsetXMultiplier = 3;
        offsetYMultiplier = -3;
        break;
      case Constant.IndicatorPosition.RIGHT_BOTTOM_NEAR_CROSSHAIR:
        offsetXMultiplier = -3;
        offsetYMultiplier = -3;
        break;
      case Constant.IndicatorPosition.LEFT_NEAR_CROSSHAIR:
        offsetXMultiplier = 3;
        offsetYMultiplier = 0;
        break;
      case Constant.IndicatorPosition.RIGHT_NEAR_CROSSHAIR:
        offsetXMultiplier = -3;
        offsetYMultiplier = 0;
        break;
      case Constant.IndicatorPosition.TOP_NEAR_CROSSHAIR:
        offsetXMultiplier = 0;
        offsetYMultiplier = 2;
        break;
      case Constant.IndicatorPosition.BOTTOM_NEAR_CROSSHAIR:
        offsetXMultiplier = 0;
        offsetYMultiplier = -3;
        break;
      // case Constant.INDICATOR_POSITIONS.RIGHT_UPPER_OF_SCREEN:
      // // offsetX = 0;
      // // offsetY = -2;
      // break;
      // case Constant.INDICATOR_POSITIONS.LEFT_BOTTOM_OF_SCREEN:
      // break;
      // case Constant.INDICATOR_POSITIONS.RIGHT_BOTTOM_OF_SCREEN:
      // break;
      // case Constant.INDICATOR_POSITIONS.LEFT_OF_SCREEN:
      // break;
      // case Constant.INDICATOR_POSITIONS.RIGHT_OF_SCREEN:
      // break;
      // case Constant.INDICATOR_POSITIONS.TOP_OF_SCREEN:
      // break;
      // case Constant.INDICATOR_POSITIONS.BOTTOM_OF_SCREEN:
      // break;
      // case Constant.INDICATOR_POSITION.CUSTOM_FROM_CENTER:
      // break;
      // case Constant.INDICATOR_POSITION.FULL_CUSTOM:
      // break;
      default:
        offsetXMultiplier = 3;
        offsetYMultiplier = 2;
        break;
    }
  }
}
