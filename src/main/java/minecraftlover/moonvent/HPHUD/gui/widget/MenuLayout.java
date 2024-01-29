package minecraftlover.moonvent.HPHUD.gui.widget;

import net.minecraft.client.gui.widget.Widget;

import java.util.ArrayList;
import java.util.List;

public class MenuLayout {
    private List components = new ArrayList<>();
    public final void addComponent(Widget widget) {
        components.add(widget);
    }
}
