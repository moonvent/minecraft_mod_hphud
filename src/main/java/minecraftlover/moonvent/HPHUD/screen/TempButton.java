package minecraftlover.moonvent.HPHUD.screen;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.function.Supplier;


public class TempButton extends ButtonWidget {
    protected TempButton(int x, int y, int width, int height, Text message, PressAction onPress, NarrationSupplier narrationSupplier) {
        super(x, y, width, height, message, onPress, narrationSupplier);
    }
}
