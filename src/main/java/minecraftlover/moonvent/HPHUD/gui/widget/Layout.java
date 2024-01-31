package minecraftlover.moonvent.HPHUD.gui.widget;

import minecraftlover.moonvent.HPHUD.gui.screen.ConfigurationScreen;
import minecraftlover.moonvent.HPHUD.util.Constant;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Layout {
    public List components = new ArrayList<>();
    private Constant.LayoutType layoutType;

    private int x, y, width, height;

    private int currentX = 0, currentY = 0;

    private int labelWidth = ButtonWidget.DEFAULT_WIDTH;
    private int widgetWidth = ButtonWidget.DEFAULT_WIDTH;

    private int rowHeight = ButtonWidget.DEFAULT_HEIGHT;

    private int spacing = 5;

//    public Layout(int x, int y, int width, int height, Constant.LayoutType layoutType) {
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
//        this.layoutType = layoutType;
//    }
    public Layout (Constant.LayoutType layoutType) {
        this.layoutType = layoutType;
    }

    private final void increaseCoordinates() {
        if (layoutType == Constant.LayoutType.VERTICAL)
            currentX += rowHeight + spacing;
        else
            currentY += widgetWidth + spacing;
    }

    public final TextWidget addTextLabel(String localizationKey, TextRenderer textRenderer) {
        TextWidget textWidget = new TextWidget(currentX,
                currentY,
                labelWidth,
                rowHeight,
                Text.translatable(localizationKey),
                textRenderer);

        components.add(textWidget);
        increaseCoordinates();

        return textWidget;
    }

    public final TextFieldWidget addTextField(TextRenderer textRenderer) {
        TextFieldWidget textFieldWidget = new TextFieldWidget(textRenderer,
                currentX,
                currentY,
                labelWidth,
                rowHeight,
                Text.of("test"));

        components.add(textFieldWidget);
        increaseCoordinates();

        return textFieldWidget;
    }

    public final Layout addLayout(Constant.LayoutType layoutType) {
        Layout layout = new Layout(layoutType);

//        components.add(layoutType);
        increaseCoordinates();

        return layout;
    }

//    public final void drawLayout(ConfigurationScreen screen) {
//        int screenCenterX = screen.width / 2;
//        int screenCenterY = screen.height / 2;
//
//        for (Element component : components)
//            screen.addDrawableChild(component);
//    }
}
