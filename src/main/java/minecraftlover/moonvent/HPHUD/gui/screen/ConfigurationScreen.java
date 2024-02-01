package minecraftlover.moonvent.HPHUD.gui.screen;

import minecraftlover.moonvent.HPHUD.config.ModConfig;
import minecraftlover.moonvent.HPHUD.util.Constant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Arrays;

import static minecraftlover.moonvent.HPHUD.util.Constant.*;
import static minecraftlover.moonvent.HPHUD.util.Constant.LocalizationKey;

public class ConfigurationScreen extends Screen {
  private ModConfig modConfig = ModConfig.getInstance();

  private static Identifier MOD_CURRENT_HP_TEXTURE, MOD_CURRENT_WITH_MAX_HP_TEXTURE, MOD_CURRENT_PERCENTAGE_HP_TEXTURE,
      LEFT_NEAR_CROSSHAIR_TEXTURE, RIGHT_NEAR_CROSSHAIR_TEXTURE, TOP_NEAR_CROSSHAIR_TEXTURE,
      BOTTOM_NEAR_CROSSHAIR_TEXTURE, LEFT_UPPER_NEAR_CROSSHAIR_TEXTURE, LEFT_BOTTOM_NEAR_CROSSHAIR_TEXTURE,
      RIGHT_UPPER_NEAR_CROSSHAIR_TEXTURE, RIGHT_BOTTOM_NEAR_CROSSHAIR_TEXTURE;

  private static Identifier currentModeImage;
  private static Identifier currentPositionImage;
  private static int currentIndicatorColor;

  private static String newSelectedPosition, newSelectedHPMode;

  private static Integer newIndicatorColor;

  private static TextWidget indicatorColorLabelWidget;
  private static TextFieldWidget indicatorColorFieldWidget;

  private static boolean needRefreshAfterUpdateColor = false;

  private int currentRowX, currentRowY, currentX, currentY, indicatorModeX, indicatorModeY, indicatorPositionX,
      indicatorPositionY;

  public ConfigurationScreen() {
    super(Text.translatable(LocalizationKey.CONFIGURATION_MENU_NAME));
  }

  @Override
  protected void init() {
    super.init();

    int widgetWidth = 150;
    int rowHeight = 20;
    int spacing = 4;

    currentX = currentRowX = this.width / 2 - widgetWidth - spacing;
    currentY = currentRowY = this.height / 2 - rowHeight * 5 - spacing * 5;

    currentIndicatorColor = Integer.parseInt(ModConfig.indicatorColor, 16);

    indicatorColorLabelWidget = new TextWidget(currentRowX,
        currentY,
        widgetWidth,
        rowHeight,
        Text
                .translatable(LocalizationKey.INDICATOR_COLOR_TEXTFIELD)
                .withColor(currentIndicatorColor),
        textRenderer);

    currentRowX += widgetWidth + spacing;
    this.addDrawableChild(indicatorColorLabelWidget);

    indicatorColorFieldWidget = new TextFieldWidget(this.textRenderer, currentRowX,
        currentY,
        widgetWidth,
        rowHeight,
        Text.of(ModConfig.indicatorColor));
    indicatorColorFieldWidget.setMaxLength(COLOR_LENGTH);
    indicatorColorFieldWidget.setPlaceholder(Text.translatable(ModConfig.indicatorColor).withColor(currentIndicatorColor));
    indicatorColorFieldWidget.setText(ModConfig.indicatorColor);
    indicatorColorFieldWidget.setEditableColor(currentIndicatorColor);
    indicatorColorFieldWidget.setChangedListener(this::setupNewColor);

    this.addDrawableChild(indicatorColorFieldWidget);

    MOD_CURRENT_HP_TEXTURE = new Identifier(Constant.MOD_ID, Constant.ResourcesPath.MOD_CURRENT_HP);
    MOD_CURRENT_WITH_MAX_HP_TEXTURE = new Identifier(Constant.MOD_ID, Constant.ResourcesPath.MOD_CURRENT_WITH_MAX_HP);
    MOD_CURRENT_PERCENTAGE_HP_TEXTURE = new Identifier(Constant.MOD_ID,
        Constant.ResourcesPath.MOD_CURRENT_PERCANTAGE_HP);

    LEFT_NEAR_CROSSHAIR_TEXTURE = new Identifier(Constant.MOD_ID, Constant.ResourcesPath.LEFT_NEAR_CROSSHAIR);
    RIGHT_NEAR_CROSSHAIR_TEXTURE = new Identifier(Constant.MOD_ID, Constant.ResourcesPath.RIGHT_NEAR_CROSSHAIR);
    TOP_NEAR_CROSSHAIR_TEXTURE = new Identifier(Constant.MOD_ID, Constant.ResourcesPath.TOP_NEAR_CROSSHAIR);
    BOTTOM_NEAR_CROSSHAIR_TEXTURE = new Identifier(Constant.MOD_ID, Constant.ResourcesPath.BOTTOM_NEAR_CROSSHAIR);
    LEFT_UPPER_NEAR_CROSSHAIR_TEXTURE = new Identifier(Constant.MOD_ID,
        Constant.ResourcesPath.LEFT_UPPER_NEAR_CROSSHAIR);
    LEFT_BOTTOM_NEAR_CROSSHAIR_TEXTURE = new Identifier(Constant.MOD_ID,
        Constant.ResourcesPath.LEFT_BOTTOM_NEAR_CROSSHAIR);
    RIGHT_UPPER_NEAR_CROSSHAIR_TEXTURE = new Identifier(Constant.MOD_ID,
        Constant.ResourcesPath.RIGHT_UPPER_NEAR_CROSSHAIR);
    RIGHT_BOTTOM_NEAR_CROSSHAIR_TEXTURE = new Identifier(Constant.MOD_ID,
        Constant.ResourcesPath.RIGHT_BOTTOM_NEAR_CROSSHAIR);

    switch (ModConfig.outputIndicatorMode) {
      case INDICATOR_TYPE.CURRENT_HP:
        currentModeImage = MOD_CURRENT_HP_TEXTURE;
        break;
      case INDICATOR_TYPE.CURRENT_WITH_MAX_HP:
        currentModeImage = MOD_CURRENT_WITH_MAX_HP_TEXTURE;
        break;
      case INDICATOR_TYPE.CURRENT_PERCENTAGE_HP:
        currentModeImage = MOD_CURRENT_PERCENTAGE_HP_TEXTURE;
        break;
    }

    switch (ModConfig.indicatorPosition) {
      case INDICATOR_POSITION.LEFT_UPPER_NEAR_CROSSHAIR:
        currentPositionImage = LEFT_UPPER_NEAR_CROSSHAIR_TEXTURE;
        break;
      case INDICATOR_POSITION.LEFT_NEAR_CROSSHAIR:
        currentPositionImage = LEFT_NEAR_CROSSHAIR_TEXTURE;
        break;
      case INDICATOR_POSITION.LEFT_BOTTOM_NEAR_CROSSHAIR:
        currentPositionImage = LEFT_BOTTOM_NEAR_CROSSHAIR_TEXTURE;
        break;
      case INDICATOR_POSITION.BOTTOM_NEAR_CROSSHAIR:
        currentPositionImage = BOTTOM_NEAR_CROSSHAIR_TEXTURE;
        break;
      case INDICATOR_POSITION.RIGHT_BOTTOM_NEAR_CROSSHAIR:
        currentPositionImage = RIGHT_BOTTOM_NEAR_CROSSHAIR_TEXTURE;
        break;
      case INDICATOR_POSITION.RIGHT_NEAR_CROSSHAIR:
        currentPositionImage = RIGHT_NEAR_CROSSHAIR_TEXTURE;
        break;
      case INDICATOR_POSITION.RIGHT_UPPER_NEAR_CROSSHAIR:
        currentPositionImage = RIGHT_UPPER_NEAR_CROSSHAIR_TEXTURE;
        break;
      case INDICATOR_POSITION.TOP_NEAR_CROSSHAIR:
        currentPositionImage = TOP_NEAR_CROSSHAIR_TEXTURE;
        break;
    }

    TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();

    for (Identifier image : Arrays.asList(MOD_CURRENT_HP_TEXTURE,
        MOD_CURRENT_WITH_MAX_HP_TEXTURE,
        MOD_CURRENT_PERCENTAGE_HP_TEXTURE,
        LEFT_NEAR_CROSSHAIR_TEXTURE, RIGHT_NEAR_CROSSHAIR_TEXTURE, TOP_NEAR_CROSSHAIR_TEXTURE,
        BOTTOM_NEAR_CROSSHAIR_TEXTURE, LEFT_UPPER_NEAR_CROSSHAIR_TEXTURE, LEFT_BOTTOM_NEAR_CROSSHAIR_TEXTURE,
        RIGHT_UPPER_NEAR_CROSSHAIR_TEXTURE, RIGHT_BOTTOM_NEAR_CROSSHAIR_TEXTURE)) {
      textureManager.bindTexture(image);
    }

    currentY += rowHeight + spacing;
    currentRowY = currentY;
    currentRowX = currentX;

    int cyclingButtonWidgetWidth = widgetWidth * 2;

    CyclingButtonWidget<String> outputModeButton = CyclingButtonWidget.<String>builder(Text::translatable)
        .values(LocalizationKey.MODE_BUTTON_CURRENT_HP, LocalizationKey.MODE_BUTTON_CURRENT_WITH_MAX_HP,
            LocalizationKey.MODE_BUTTON_CURRENT_PERCENTAGE_HP)
          .initially("button." + MOD_NAME_FOR_LOCALIZATION + "." + ModConfig.outputIndicatorMode)
        .build(this.width / 2 - cyclingButtonWidgetWidth / 2, currentY, cyclingButtonWidgetWidth, rowHeight,
            Text.translatable(LocalizationKey.MODE_BUTTON_DESCRIPTION),
            ((button, value) -> changeIndicatorMode(button, value)));
    this.addDrawableChild(outputModeButton);

    currentY += rowHeight + spacing;

    indicatorModeY = currentY;
    indicatorModeX = this.width / 2 - ResourcesPath.MOD_TEXTURE_SIZE / 2;

    currentY += ResourcesPath.MOD_TEXTURE_SIZE + spacing;

    indicatorPositionY = currentY;
    indicatorPositionX = indicatorModeX;

    CyclingButtonWidget<String> outputPositionButton = CyclingButtonWidget.<String>builder(Text::translatable)
        .values(LocalizationKey.LEFT_UPPER_NEAR_CROSSHAIR,
            LocalizationKey.LEFT_NEAR_CROSSHAIR,
            LocalizationKey.LEFT_BOTTOM_NEAR_CROSSHAIR,
            LocalizationKey.BOTTOM_NEAR_CROSSHAIR,
            LocalizationKey.RIGHT_BOTTOM_NEAR_CROSSHAIR,
            LocalizationKey.RIGHT_NEAR_CROSSHAIR,
            LocalizationKey.RIGHT_UPPER_NEAR_CROSSHAIR,
            LocalizationKey.TOP_NEAR_CROSSHAIR)
        .initially("button." + MOD_NAME_FOR_LOCALIZATION + "." + ModConfig.indicatorPosition)
        .build(this.width / 2 - cyclingButtonWidgetWidth / 2, currentY, cyclingButtonWidgetWidth, rowHeight,
            Text.translatable(LocalizationKey.MODE_BUTTON_POSITION),
            ((button, value) -> changeIndicatorPosition(button, value)));
    this.addDrawableChild(outputPositionButton);

    currentY += rowHeight + spacing;
    indicatorPositionY = currentY;
    indicatorPositionX = indicatorModeX;

    currentY += ResourcesPath.MOD_TEXTURE_SIZE + spacing;

    int sliderWidth = widgetWidth * 2;
    SliderWidget sliderWidget = new SliderWidget(this.width / 2 - sliderWidth / 2,
        currentY,
        sliderWidth,
        rowHeight,
        Text.translatable(LocalizationKey.VISIBILITY_RANGE_VALUE, ModConfig.searchDistance),
            ModConfig.searchDistance / (float) MAX_SEARCH_DISTANCE) {
      @Override
      protected void updateMessage() {
        setMessage(Text.translatable(LocalizationKey.VISIBILITY_RANGE_VALUE, (int) (value * MAX_SEARCH_DISTANCE)));
      }

      @Override
      protected void applyValue() {
      }

      @Override
      public void onRelease(double mouseX, double mouseY) {
        super.onRelease(mouseX, mouseY);
        ModConfig.searchDistance = (int) (value * MAX_SEARCH_DISTANCE);

        if (ModConfig.searchDistance == 0) ModConfig.searchDistance = MIN_SEARCH_DISTANCE;
      }
    };
    this.addDrawableChild(sliderWidget);
  }

  private void changeIndicatorMode(CyclingButtonWidget button, String value) {
    newSelectedHPMode = value.split("\\.")[2];
    switch (newSelectedHPMode) {
      case INDICATOR_TYPE.CURRENT_PERCENTAGE_HP:
        currentModeImage = MOD_CURRENT_PERCENTAGE_HP_TEXTURE;
        break;
      case INDICATOR_TYPE.CURRENT_WITH_MAX_HP:
        currentModeImage = MOD_CURRENT_WITH_MAX_HP_TEXTURE;
        break;
      default:
        currentModeImage = MOD_CURRENT_HP_TEXTURE;
      }
    ModConfig.outputIndicatorMode = newSelectedHPMode;
  }

  private void changeIndicatorPosition(CyclingButtonWidget button, String value) {
    newSelectedPosition = value.split("\\.")[2];
    switch (newSelectedPosition) {
      case "LEFT_NEAR_CROSSHAIR":
        currentPositionImage = LEFT_NEAR_CROSSHAIR_TEXTURE;
        break;
      case "LEFT_UPPER_NEAR_CROSSHAIR":
        currentPositionImage = LEFT_UPPER_NEAR_CROSSHAIR_TEXTURE;
        break;
      case "LEFT_BOTTOM_NEAR_CROSSHAIR":
        currentPositionImage = LEFT_BOTTOM_NEAR_CROSSHAIR_TEXTURE;
        break;
      case "RIGHT_NEAR_CROSSHAIR":
        currentPositionImage = RIGHT_NEAR_CROSSHAIR_TEXTURE;
        break;
      case "RIGHT_BOTTOM_NEAR_CROSSHAIR":
        currentPositionImage = RIGHT_BOTTOM_NEAR_CROSSHAIR_TEXTURE;
        break;
      case "RIGHT_UPPER_NEAR_CROSSHAIR":
        currentPositionImage = RIGHT_UPPER_NEAR_CROSSHAIR_TEXTURE;
        break;
      case "TOP_NEAR_CROSSHAIR":
        currentPositionImage = TOP_NEAR_CROSSHAIR_TEXTURE;
        break;
      case "BOTTOM_NEAR_CROSSHAIR":
        currentPositionImage = BOTTOM_NEAR_CROSSHAIR_TEXTURE;
        break;
    }
    ModConfig.indicatorPosition = newSelectedPosition;
  }

  @Override
  public void render(DrawContext context, int mouseX, int mouseY, float delta) {
    super.render(context, mouseX, mouseY, delta);
    context.drawTexture(currentModeImage, indicatorModeX, indicatorModeY, 0, 0, ResourcesPath.MOD_TEXTURE_SIZE,
        ResourcesPath.MOD_TEXTURE_SIZE, ResourcesPath.MOD_TEXTURE_SIZE, ResourcesPath.MOD_TEXTURE_SIZE);
    context.drawTexture(currentPositionImage, indicatorPositionX, indicatorPositionY, 0, 0,
        ResourcesPath.MOD_TEXTURE_SIZE,
        ResourcesPath.MOD_TEXTURE_SIZE, ResourcesPath.MOD_TEXTURE_SIZE, ResourcesPath.MOD_TEXTURE_SIZE);

//    if (needRefreshAfterUpdateColor) {
//      indicatorColorLabelWidget.render(context, mouseX, mouseY, delta);
//    }
  }

  // public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
  // {
  // this.renderBackground(matrices); // Фон
  // drawCenteredText(matrices, this.textRenderer, "Мое меню", this.width / 2, 15,
  // 16777215);
  // super.render(matrices, mouseX, mouseY, delta);
  // }

  private void setupNewColor(String newColorText) {
    if (newColorText.length() == COLOR_LENGTH) {
      try {
        newIndicatorColor = Integer.parseInt(newColorText, 16);
      } catch (NumberFormatException e) {
        return;
      }
      indicatorColorLabelWidget.setTextColor(newIndicatorColor);
//      this.remove(indicatorColorLabelWidget);
//
//      indicatorColorLabelWidget = new TextWidget(currentRowX,
//              currentY,
//              indicatorColorLabelWidget.getWidth(),
//              indicatorColorLabelWidget.getHeight(),
//              Text
//                      .translatable(LocalizationKey.INDICATOR_COLOR_TEXTFIELD)
//                      .withColor(newIndicatorColor),
//              textRenderer);
//      this.addDrawableChild(indicatorColorLabelWidget);

      ModConfig.indicatorColor = newColorText;
    }
  }

  @Override
  public void close() {
    modConfig.save();
    super.close();
  }
}
