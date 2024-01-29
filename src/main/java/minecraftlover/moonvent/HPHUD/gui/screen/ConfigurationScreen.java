package minecraftlover.moonvent.HPHUD.gui.screen;

import minecraftlover.moonvent.HPHUD.HPHUDClient;
import minecraftlover.moonvent.HPHUD.HPHUDDataGenerator;
import minecraftlover.moonvent.HPHUD.gui.widget.NarrationSupplierForButton;
import minecraftlover.moonvent.HPHUD.util.Constant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Objects;

import static minecraftlover.moonvent.HPHUD.util.Constant.*;

public class ConfigurationScreen extends Screen {
  private TextFieldWidget colorTextField;
  private ButtonWidget positionButton;
  // private CycleButtonWidget<?> outputModeButton;
  private TextFieldWidget entityDistanceTextField;


  public static Identifier MOD_CURRENT_HP_TEXTURE;
  public static Identifier MOD_CURRENT_WITH_MAX_HP_TEXTURE;
  public static Identifier MOD_CURRENT_PERCENTAGE_HP_TEXTURE;
  private Identifier currentModImage;
  public ConfigurationScreen() {
    super(Text.translatable(LocalizationKey.CONFIGURATION_MENU_NAME));
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
        Text.translatable(LocalizationKey.INDICATOR_COLOR_TEXTFIELD),
        textRenderer);

    this.addDrawableChild(textLabelWidget);
    colorTextField = new TextFieldWidget(this.textRenderer, this.width / 2 - widgetWidth / 2 + labelWidth + spacing,
        this.height / 2 - 60,
        50, rowHeight, Text.of("test"));

    this.addDrawableChild(colorTextField);

//    this.addDrawableChild(new TempButton(this.width / 2 - (labelWidth + widgetWidth) / 2, this.height / 2 - 8, labelWidth, rowHeight, Text.of("Режим вывода"), button -> {}, narrationSupplierForButton));

    TextWidget textLabelWidget2 = new TextWidget(this.width / 2 - (widgetWidth) / 2,
            this.height / 2 - 60,
            labelWidth, rowHeight,
            Text.of("Моды"),
            textRenderer);


    MOD_CURRENT_HP_TEXTURE = new Identifier(Constant.MOD_ID, Constant.ResourcesPath.MOD_CURRENT_HP);
    MOD_CURRENT_WITH_MAX_HP_TEXTURE = new Identifier(Constant.MOD_ID, Constant.ResourcesPath.MOD_CURRENT_WITH_MAX_HP);
    MOD_CURRENT_PERCENTAGE_HP_TEXTURE = new Identifier(Constant.MOD_ID, Constant.ResourcesPath.MOD_CURRENT_PERCANTAGE_HP);

    currentModImage = MOD_CURRENT_HP_TEXTURE;

    MinecraftClient.getInstance().getTextureManager().bindTexture(MOD_CURRENT_HP_TEXTURE);
    MinecraftClient.getInstance().getTextureManager().bindTexture(MOD_CURRENT_WITH_MAX_HP_TEXTURE);
    MinecraftClient.getInstance().getTextureManager().bindTexture(MOD_CURRENT_PERCENTAGE_HP_TEXTURE);

    this.addDrawableChild(textLabelWidget);
    CyclingButtonWidget<String> outputModeButton = CyclingButtonWidget.<String>builder(Text::translatable)
            .values(LocalizationKey.MODE_BUTTON_CURRENT_HP, LocalizationKey.MODE_BUTTON_CURRENT_WITH_MAX_HP, LocalizationKey.MODE_BUTTON_CURRENT_PERCENTAGE_HP)
            .build(this.width / 2 - widgetWidth / 2, this.height / 2 - 8, widgetWidth, rowHeight, Text.translatable(LocalizationKey.MODE_BUTTON_DESCRIPTION), ((button, value) -> {
              System.out.println(value);
//              switch (value) {
//                case LocalizationKey.MODE_BUTTON_CURRENT_HP:
//                  int a = 1;
//                  break;
//              }
              if (Objects.equals(value, LocalizationKey.MODE_BUTTON_CURRENT_PERCENTAGE_HP)) {
                currentModImage = MOD_CURRENT_PERCENTAGE_HP_TEXTURE;
              } else if (Objects.equals(value, LocalizationKey.MODE_BUTTON_CURRENT_WITH_MAX_HP)) {
                currentModImage = MOD_CURRENT_WITH_MAX_HP_TEXTURE;
              } else {
                currentModImage = MOD_CURRENT_HP_TEXTURE;
              }
            }));
    this.addDrawableChild(outputModeButton);

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
//    context.drawGuiTexture(IMAGE_RESOURCE, 1, 1, 32, 32);
    context.drawTexture(currentModImage, 1, 1, 0, 0 , ResourcesPath.MOD_TEXTURE_SIZE, ResourcesPath.MOD_TEXTURE_SIZE, ResourcesPath.MOD_TEXTURE_SIZE, ResourcesPath.MOD_TEXTURE_SIZE);

//    RenderSystem.setShaderColor(255, 255, 100, 1);
//    RenderSystem.setShaderTexture(0, IMAGE_RESOURCE);
//    RenderSystem.enableBlend();
//    Tessellator tessellator = Tessellator.getInstance();
//    tessellator.draw();
//    MinecraftClient.getInstance().getTextureManager().bindTexture(IMAGE_RESOURCE);
//    drawTexture(matrices, x, y, 0, 0, imageWidth, imageHeight); // Укажите x, y, ширину и высоту
//    addDrawableChild();
//    TexturedButtonWidget
//    Texture
  }

  // public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
  // {
  // this.renderBackground(matrices); // Фон
  // drawCenteredText(matrices, this.textRenderer, "Мое меню", this.width / 2, 15,
  // 16777215);
  // super.render(matrices, mouseX, mouseY, delta);
  // }

}
