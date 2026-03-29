package minecraftlover.moonvent.HPHUD.gui.screen;

import java.util.function.Supplier;
import minecraftlover.moonvent.HPHUD.config.ModConfig;
import minecraftlover.moonvent.HPHUD.util.Constant;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.renderer.RenderPipelines;

import static minecraftlover.moonvent.HPHUD.util.Constant.*;
import static minecraftlover.moonvent.HPHUD.util.Constant.ConfigurationMenu.*;
import static minecraftlover.moonvent.HPHUD.util.Constant.IndicatorPosition.*;
import static minecraftlover.moonvent.HPHUD.util.Constant.LocalizationKey;
import static minecraftlover.moonvent.HPHUD.util.Constant.LocalizationKey.BUTTON_KEY;
import static minecraftlover.moonvent.HPHUD.util.Constant.LocalizationKey.WARNING_AFTER_PRESS_MENU_KEY;

public class ConfigurationScreen extends Screen {
  private ModConfig modConfig = ModConfig.getInstance();

  private static Identifier MOD_CURRENT_HP_TEXTURE, MOD_CURRENT_WITH_MAX_HP_TEXTURE,
      MOD_CURRENT_PERCENTAGE_HP_TEXTURE,
      LEFT_NEAR_CROSSHAIR_TEXTURE, RIGHT_NEAR_CROSSHAIR_TEXTURE, TOP_NEAR_CROSSHAIR_TEXTURE,
      BOTTOM_NEAR_CROSSHAIR_TEXTURE, LEFT_UPPER_NEAR_CROSSHAIR_TEXTURE, LEFT_BOTTOM_NEAR_CROSSHAIR_TEXTURE,
      RIGHT_UPPER_NEAR_CROSSHAIR_TEXTURE, RIGHT_BOTTOM_NEAR_CROSSHAIR_TEXTURE;

  private int scrollOffset = 0;
  private int totalContentHeight = 0;

  private static Identifier currentModeImage;
  private static Identifier currentPositionImage;
  private static int currentIndicatorColor;

  private static String newSelectedPosition, newSelectedHPMode;
  private static Integer newIndicatorColor;

  private static StringWidget indicatorColorLabelWidget;
  private static EditBox indicatorColorFieldWidget;

  private int currentRowX, currentRowY, currentX, currentY, indicatorModeX, indicatorModeY,
      indicatorPositionX, indicatorPositionY, cyclingButtonWidgetWidth, sliderWidth;

  public ConfigurationScreen() {
    super(Component.translatable(LocalizationKey.CONFIGURATION_MENU_NAME));
  }

  @Override
  protected void init() {
    super.init();
    currentX = currentRowX = this.width / 2 - WIDGET_WIDTH - SPACING;
    currentY = currentRowY = 10 - scrollOffset;
    currentIndicatorColor = 0xFF000000 | Integer.parseInt(ModConfig.indicatorColor, 16);
    addColorField();
    loadTextures();
    setupDefaultTextures();
    addModeSwitcher();
    addPositionSwitcher();
    addSearchSlider();
    addWarningChecker();
    addGreetingsChecker();
    totalContentHeight = currentY + scrollOffset;
  }

  @Override
  public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
    int maxScroll = Math.max(0, totalContentHeight - this.height + 10);
    scrollOffset = (int) Math.max(0, Math.min(maxScroll, scrollOffset - verticalAmount * 10));
    clearWidgets();
    init();
    return true;
  }

  private void changeIndicatorMode(CycleButton button, String value) {
    newSelectedHPMode = value.split("\\.")[2];
    switch (newSelectedHPMode) {
      case IndicatorType.CURRENT_PERCENTAGE_HP -> currentModeImage = MOD_CURRENT_PERCENTAGE_HP_TEXTURE;
      case IndicatorType.CURRENT_WITH_MAX_HP -> currentModeImage = MOD_CURRENT_WITH_MAX_HP_TEXTURE;
      default -> currentModeImage = MOD_CURRENT_HP_TEXTURE;
    }
    ModConfig.outputIndicatorMode = newSelectedHPMode;
  }

  private void changeIndicatorPosition(CycleButton button, String value) {
    newSelectedPosition = value.split("\\.")[2];
    switch (newSelectedPosition) {
      case LEFT_NEAR_CROSSHAIR -> currentPositionImage = LEFT_NEAR_CROSSHAIR_TEXTURE;
      case LEFT_UPPER_NEAR_CROSSHAIR -> currentPositionImage = LEFT_UPPER_NEAR_CROSSHAIR_TEXTURE;
      case LEFT_BOTTOM_NEAR_CROSSHAIR -> currentPositionImage = LEFT_BOTTOM_NEAR_CROSSHAIR_TEXTURE;
      case RIGHT_NEAR_CROSSHAIR -> currentPositionImage = RIGHT_NEAR_CROSSHAIR_TEXTURE;
      case RIGHT_BOTTOM_NEAR_CROSSHAIR -> currentPositionImage = RIGHT_BOTTOM_NEAR_CROSSHAIR_TEXTURE;
      case RIGHT_UPPER_NEAR_CROSSHAIR -> currentPositionImage = RIGHT_UPPER_NEAR_CROSSHAIR_TEXTURE;
      case TOP_NEAR_CROSSHAIR -> currentPositionImage = TOP_NEAR_CROSSHAIR_TEXTURE;
      case BOTTOM_NEAR_CROSSHAIR -> currentPositionImage = BOTTOM_NEAR_CROSSHAIR_TEXTURE;
    }
    ModConfig.indicatorPosition = newSelectedPosition;
  }

  @Override
  public void extractRenderState(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
    super.extractRenderState(context, mouseX, mouseY, delta);
    context.blit(RenderPipelines.GUI_TEXTURED, currentModeImage, indicatorModeX, indicatorModeY, 0, 0,
        ResourcesPath.MOD_TEXTURE_SIZE, ResourcesPath.MOD_TEXTURE_SIZE,
        ResourcesPath.MOD_TEXTURE_SIZE, ResourcesPath.MOD_TEXTURE_SIZE);
    context.blit(RenderPipelines.GUI_TEXTURED, currentPositionImage, indicatorPositionX, indicatorPositionY, 0, 0,
        ResourcesPath.MOD_TEXTURE_SIZE, ResourcesPath.MOD_TEXTURE_SIZE,
        ResourcesPath.MOD_TEXTURE_SIZE, ResourcesPath.MOD_TEXTURE_SIZE);
  }

  private void setupNewColor(String newColorText) {
    if (newColorText.length() == COLOR_LENGTH) {
      try {
        newIndicatorColor = 0xFF000000 | Integer.parseInt(newColorText, 16);
      } catch (NumberFormatException e) {
        return;
      }
      this.removeWidget(indicatorColorLabelWidget);
      indicatorColorLabelWidget = new StringWidget(currentX,
          indicatorColorLabelWidget.getY(),
          indicatorColorLabelWidget.getWidth(),
          indicatorColorLabelWidget.getHeight(),
          Component.translatable(LocalizationKey.INDICATOR_COLOR_TEXTFIELD).withColor(newIndicatorColor),
          this.font);
      this.addRenderableWidget(indicatorColorLabelWidget);
      indicatorColorFieldWidget.setTextColor(newIndicatorColor);
      ModConfig.indicatorColor = newColorText;
    }
  }

  @Override
  public void onClose() {
    modConfig.save();
    super.onClose();
  }

  private void addColorField() {
    indicatorColorLabelWidget = new StringWidget(currentRowX, currentY, WIDGET_WIDTH, ROW_HEIGHT,
        Component.translatable(LocalizationKey.INDICATOR_COLOR_TEXTFIELD).withColor(currentIndicatorColor),
        this.font);
    currentRowX += WIDGET_WIDTH + SPACING;
    this.addRenderableWidget(indicatorColorLabelWidget);

    indicatorColorFieldWidget = new EditBox(this.font, currentRowX, currentY, WIDGET_WIDTH, ROW_HEIGHT,
        Component.literal(ModConfig.indicatorColor));
    indicatorColorFieldWidget.setMaxLength(COLOR_LENGTH);
    indicatorColorFieldWidget
        .setHint(Component.translatable(ModConfig.indicatorColor).withColor(currentIndicatorColor));
    indicatorColorFieldWidget.setValue(ModConfig.indicatorColor);
    indicatorColorFieldWidget.setTextColor(currentIndicatorColor);
    indicatorColorFieldWidget.setResponder(this::setupNewColor);
    this.addRenderableWidget(indicatorColorFieldWidget);

    currentY += ROW_HEIGHT + SPACING;
    currentRowY = currentY;
    currentRowX = currentX;
  }

  private void loadTextures() {
    MOD_CURRENT_HP_TEXTURE = Identifier.fromNamespaceAndPath(Constant.MOD_ID,
        Constant.ResourcesPath.MOD_CURRENT_HP);
    MOD_CURRENT_WITH_MAX_HP_TEXTURE = Identifier.fromNamespaceAndPath(Constant.MOD_ID,
        Constant.ResourcesPath.MOD_CURRENT_WITH_MAX_HP);
    MOD_CURRENT_PERCENTAGE_HP_TEXTURE = Identifier.fromNamespaceAndPath(Constant.MOD_ID,
        Constant.ResourcesPath.MOD_CURRENT_PERCANTAGE_HP);
    LEFT_NEAR_CROSSHAIR_TEXTURE = Identifier.fromNamespaceAndPath(Constant.MOD_ID,
        Constant.ResourcesPath.LEFT_NEAR_CROSSHAIR);
    RIGHT_NEAR_CROSSHAIR_TEXTURE = Identifier.fromNamespaceAndPath(Constant.MOD_ID,
        Constant.ResourcesPath.RIGHT_NEAR_CROSSHAIR);
    TOP_NEAR_CROSSHAIR_TEXTURE = Identifier.fromNamespaceAndPath(Constant.MOD_ID,
        Constant.ResourcesPath.TOP_NEAR_CROSSHAIR);
    BOTTOM_NEAR_CROSSHAIR_TEXTURE = Identifier.fromNamespaceAndPath(Constant.MOD_ID,
        Constant.ResourcesPath.BOTTOM_NEAR_CROSSHAIR);
    LEFT_UPPER_NEAR_CROSSHAIR_TEXTURE = Identifier.fromNamespaceAndPath(Constant.MOD_ID,
        Constant.ResourcesPath.LEFT_UPPER_NEAR_CROSSHAIR);
    LEFT_BOTTOM_NEAR_CROSSHAIR_TEXTURE = Identifier.fromNamespaceAndPath(Constant.MOD_ID,
        Constant.ResourcesPath.LEFT_BOTTOM_NEAR_CROSSHAIR);
    RIGHT_UPPER_NEAR_CROSSHAIR_TEXTURE = Identifier.fromNamespaceAndPath(Constant.MOD_ID,
        Constant.ResourcesPath.RIGHT_UPPER_NEAR_CROSSHAIR);
    RIGHT_BOTTOM_NEAR_CROSSHAIR_TEXTURE = Identifier.fromNamespaceAndPath(Constant.MOD_ID,
        Constant.ResourcesPath.RIGHT_BOTTOM_NEAR_CROSSHAIR);
  }

  private void setupDefaultTextures() {
    switch (ModConfig.outputIndicatorMode) {
      case IndicatorType.CURRENT_HP -> currentModeImage = MOD_CURRENT_HP_TEXTURE;
      case IndicatorType.CURRENT_WITH_MAX_HP -> currentModeImage = MOD_CURRENT_WITH_MAX_HP_TEXTURE;
      case IndicatorType.CURRENT_PERCENTAGE_HP -> currentModeImage = MOD_CURRENT_PERCENTAGE_HP_TEXTURE;
    }
    switch (ModConfig.indicatorPosition) {
      case LEFT_UPPER_NEAR_CROSSHAIR -> currentPositionImage = LEFT_UPPER_NEAR_CROSSHAIR_TEXTURE;
      case LEFT_NEAR_CROSSHAIR -> currentPositionImage = LEFT_NEAR_CROSSHAIR_TEXTURE;
      case LEFT_BOTTOM_NEAR_CROSSHAIR -> currentPositionImage = LEFT_BOTTOM_NEAR_CROSSHAIR_TEXTURE;
      case IndicatorPosition.BOTTOM_NEAR_CROSSHAIR -> currentPositionImage = BOTTOM_NEAR_CROSSHAIR_TEXTURE;
      case IndicatorPosition.RIGHT_BOTTOM_NEAR_CROSSHAIR -> currentPositionImage = RIGHT_BOTTOM_NEAR_CROSSHAIR_TEXTURE;
      case IndicatorPosition.RIGHT_NEAR_CROSSHAIR -> currentPositionImage = RIGHT_NEAR_CROSSHAIR_TEXTURE;
      case IndicatorPosition.RIGHT_UPPER_NEAR_CROSSHAIR -> currentPositionImage = RIGHT_UPPER_NEAR_CROSSHAIR_TEXTURE;
      case IndicatorPosition.TOP_NEAR_CROSSHAIR -> currentPositionImage = TOP_NEAR_CROSSHAIR_TEXTURE;
    }
  }

  private void addModeSwitcher() {
    cyclingButtonWidgetWidth = WIDGET_WIDTH * 2;
    CycleButton<String> outputModeButton = CycleButton.<String>builder(Component::translatable,
        BUTTON_KEY + MOD_NAME_FOR_LOCALIZATION + "." + ModConfig.outputIndicatorMode)
        .withValues(LocalizationKey.MODE_BUTTON_CURRENT_HP, LocalizationKey.MODE_BUTTON_CURRENT_WITH_MAX_HP,
            LocalizationKey.MODE_BUTTON_CURRENT_PERCENTAGE_HP)
        .create(this.width / 2 - cyclingButtonWidgetWidth / 2, currentY, cyclingButtonWidgetWidth, ROW_HEIGHT,
            Component.translatable(LocalizationKey.MODE_BUTTON_DESCRIPTION),
            (button, value) -> changeIndicatorMode(button, value));
    this.addRenderableWidget(outputModeButton);
    currentY += ROW_HEIGHT + SPACING;
    indicatorModeY = currentY;
    indicatorModeX = this.width / 2 - ResourcesPath.MOD_TEXTURE_SIZE / 2;
    currentY += ResourcesPath.MOD_TEXTURE_SIZE + SPACING;
    indicatorPositionY = currentY;
    indicatorPositionX = indicatorModeX;
  }

  private void addPositionSwitcher() {
    CycleButton<String> outputPositionButton = CycleButton.<String>builder(Component::translatable,
        BUTTON_KEY + MOD_NAME_FOR_LOCALIZATION + "." + ModConfig.indicatorPosition)
        .withValues(LocalizationKey.LEFT_UPPER_NEAR_CROSSHAIR, LocalizationKey.LEFT_NEAR_CROSSHAIR,
            LocalizationKey.LEFT_BOTTOM_NEAR_CROSSHAIR, LocalizationKey.BOTTOM_NEAR_CROSSHAIR,
            LocalizationKey.RIGHT_BOTTOM_NEAR_CROSSHAIR, LocalizationKey.RIGHT_NEAR_CROSSHAIR,
            LocalizationKey.RIGHT_UPPER_NEAR_CROSSHAIR, LocalizationKey.TOP_NEAR_CROSSHAIR)
        .create(this.width / 2 - cyclingButtonWidgetWidth / 2, currentY, cyclingButtonWidgetWidth, ROW_HEIGHT,
            Component.translatable(LocalizationKey.MODE_BUTTON_POSITION),
            (button, value) -> changeIndicatorPosition(button, value));
    this.addRenderableWidget(outputPositionButton);
    currentY += ROW_HEIGHT + SPACING;
    indicatorPositionY = currentY;
    indicatorPositionX = indicatorModeX;
    currentY += ResourcesPath.MOD_TEXTURE_SIZE + SPACING;
  }

  private void addSearchSlider() {
    sliderWidth = WIDGET_WIDTH * 2;
    AbstractSliderButton sliderWidget = new AbstractSliderButton(
        this.width / 2 - sliderWidth / 2, currentY, sliderWidth, ROW_HEIGHT,
        Component.translatable(LocalizationKey.VISIBILITY_RANGE_VALUE, ModConfig.searchDistance),
        ModConfig.searchDistance / (float) MAX_SEARCH_DISTANCE) {
      @Override
      protected void updateMessage() {
        setMessage(Component.translatable(LocalizationKey.VISIBILITY_RANGE_VALUE,
            (int) (value * MAX_SEARCH_DISTANCE)));
      }

      @Override
      protected void applyValue() {
        ModConfig.searchDistance = (int) (value * MAX_SEARCH_DISTANCE);
        if (ModConfig.searchDistance == 0)
          ModConfig.searchDistance = MIN_SEARCH_DISTANCE;
      }
    };
    this.addRenderableWidget(sliderWidget);
    currentY += ROW_HEIGHT + SPACING;
  }

  private void addWarningChecker() {
    Checkbox checkbox = Checkbox.builder(Component.translatable(WARNING_AFTER_PRESS_MENU_KEY), this.font)
        .pos(this.width / 2 - 100, currentY)
        .selected(ModConfig.warningAfterPressMenuKey)
        .onValueChange((cb, checked) -> ModConfig.warningAfterPressMenuKey = checked)
        .build();
    this.addRenderableWidget(checkbox);
    currentY += ROW_HEIGHT + SPACING;
  }

  private void addGreetingsChecker() {
    Checkbox checkbox = Checkbox
        .builder(Component.translatable(LocalizationKey.GREETINGS_AFTER_ENTER_IN_WORLD), this.font)
        .pos(this.width / 2 - 100, currentY)
        .selected(ModConfig.greetingsAfterEnterInWorld)
        .onValueChange((cb, checked) -> ModConfig.greetingsAfterEnterInWorld = checked)
        .build();
    this.addRenderableWidget(checkbox);
    currentY += ROW_HEIGHT + SPACING;
  }
}
