package minecraftlover.moonvent.HPHUD.gui.widget;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class NarrationSupplierForButton implements ButtonWidget.NarrationSupplier {
    @Override
    public MutableText createNarrationMessage(Supplier<MutableText> textSupplier) {
        return Text.literal("test");
    }
}
