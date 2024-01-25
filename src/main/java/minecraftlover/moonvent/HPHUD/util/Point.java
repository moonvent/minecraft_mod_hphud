package minecraftlover.moonvent.HPHUD.util;

import minecraftlover.moonvent.HPHUD.config.ModConfig;

class Point {
  private static Point instance;

  private int x;
  private int y;

  public Point(int new_x, int new_y) {
    x = new_x;
    y = new_y;
    instance = this;
  }

  public Point() {
    calculateCoordinates();
    instance = this;
  }

  public static Point getInstance() {
    return instance;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  private void calculateCoordinates() {
    ModConfig config = ModConfig.getInstance();

    int offsetX, offsetY;

    switch (config.indicatorPosition) {
      case Constant.INDICATOR_POSITION.LEFT_UPPER_NEAR_CROSSHAIR:
        offsetX = 3;
        offsetY = 2;
        break;
      case Constant.INDICATOR_POSITION.RIGHT_UPPER_NEAR_CROSSHAIR:
        offsetX = 3;
        offsetY = -2;
        break;
      case Constant.INDICATOR_POSITION.LEFT_BOTTOM_NEAR_CROSSHAIR:
        offsetX = -3;
        offsetY = 2;
        break;
      case Constant.INDICATOR_POSITION.RIGHT_BOTTOM_NEAR_CROSSHAIR:
        offsetX = -3;
        offsetY = -2;
        break;
      case Constant.INDICATOR_POSITION.LEFT_NEAR_CROSSHAIR:
        offsetX = 3;
        offsetY = 0;
        break;
      case Constant.INDICATOR_POSITION.RIGHT_NEAR_CROSSHAIR:
        offsetX = -3;
        offsetY = 0;
        break;
      case Constant.INDICATOR_POSITION.TOP_NEAR_CROSSHAIR:
        offsetX = 0;
        offsetY = 2;
        break;
      case Constant.INDICATOR_POSITION.BOTTOM_NEAR_CROSSHAIR:
        offsetX = 0;
        offsetY = -2;
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
      case Constant.INDICATOR_POSITION.CUSTOM_FROM_CENTER:
        break;
      case Constant.INDICATOR_POSITION.FULL_CUSTOM:
        break;
      default:
        break;
    }
  }
}
