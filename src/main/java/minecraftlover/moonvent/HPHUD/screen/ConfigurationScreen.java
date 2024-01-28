package minecraftlover.moonvent.HPHUD.screen;

import minecraftlover.moonvent.HPHUD.config.ModConfig;
import minecraftlover.moonvent.HPHUD.util.Constant;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

public class ConfigurationScreen extends Screen {
  private TextFieldWidget colorTextField;
  private ButtonWidget positionButton;
  // private CycleButtonWidget<?> outputModeButton;
  private TextFieldWidget entityDistanceTextField;

  public ConfigurationScreen() {
    super(Text.translatable(Constant.LocalizationKey.CONFIGURATION_MENU_NAME));
  }

  @Override
  protected void init() {
    super.init();
    // Создание кнопок
    NarrationSupplierForButton narrationSupplierForButton = new NarrationSupplierForButton();

    int labelWidth = 100;
    int widgetWidth = 150;
    int rowHeight = 20;
    int spacing = 4;

    // this.addDrawableChild(
    // new TempButton(this.width / 2 - 100, this.height / 2 - 24,
    // ButtonWidget.DEFAULT_WIDTH,
    // ButtonWidget.DEFAULT_HEIGHT, Text.of("Кнопка 1"), button -> {
    // // Действие при нажатии на кнопку 1
    // System.out.println("button 1");
    // }, narrationSupplierForButton));

    TextWidget textLabelWidget = new TextWidget(this.width / 2 - (widgetWidth) / 2,
        this.height / 2 - 60,
        labelWidth, rowHeight,
        Text.translatable(Constant.LocalizationKey.INDICATOR_COLOR_TEXTFIELD),
        textRenderer);

    this.addDrawableChild(textLabelWidget);
    colorTextField = new TextFieldWidget(this.textRenderer, this.width / 2 - widgetWidth / 2 + labelWidth + spacing,
        this.height / 2 - 60,
        50, rowHeight, Text.of("123123"));

    this.addDrawableChild(colorTextField);

    // this.addDrawableChild(
    // new ButtonWidget(this.width / 2 - 100, this.height / 2,
    // ButtonWidget.DEFAULT_WIDTH, ButtonWidget.DEFAULT_HEIGHT, Text.of("Кнопка 2"),
    // button -> {
    // // Действие при нажатии на кнопку 2
    // }));
  }

  @Override
  public void render(DrawContext context, int mouseX, int mouseY, float delta) {
    super.render(context, mouseX, mouseY, delta);
  }

  // public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
  // {
  // this.renderBackground(matrices); // Фон
  // drawCenteredText(matrices, this.textRenderer, "Мое меню", this.width / 2, 15,
  // 16777215);
  // super.render(matrices, mouseX, mouseY, delta);
  // }

}
