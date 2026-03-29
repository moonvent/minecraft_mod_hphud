package minecraftlover.moonvent.HPHUD.util;

import minecraftlover.moonvent.HPHUD.config.ModConfig;
import java.util.Objects;

public class IndicatorCoordinate {
  private static IndicatorCoordinate instance = null;

  private ModConfig config;
  private int offsetXMultiplier;
  private int offsetYMultiplier;
  private int x;
  private int y;

  public IndicatorCoordinate() {
    instance = this;
  }

  public static IndicatorCoordinate getInstance() {
    return instance;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void updateFromWindow(int width, int height) {
    config = ModConfig.getInstance();
    calculateOffsetCoordinates();

    int hGuiScale = !Objects.equals(config.outputIndicatorMode,
        Constant.IndicatorType.CURRENT_HP) ? 14 : 7;
    int vGuiScale = 7;

    x = width / 2 - offsetXMultiplier * hGuiScale;
    y = height / 2 - offsetYMultiplier * vGuiScale;
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
      default:
        offsetXMultiplier = 3;
        offsetYMultiplier = 2;
        break;
    }
  }
}
