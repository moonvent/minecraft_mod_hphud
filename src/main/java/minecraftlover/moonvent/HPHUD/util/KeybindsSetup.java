package minecraftlover.moonvent.HPHUD.util;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.util.InputUtil;

import net.minecraft.client.MinecraftClient;

public class KeybindsSetup {

  private KeyBinding openConfigurationMenuKey;

  public KeybindsSetup() {
    addNewKeyBindings();
  }

  public void addNewKeyBindings() {
    setupOpenConfigurationMenuKey();
  }

  private void setupOpenConfigurationMenuKey() {
    openConfigurationMenuKey = new KeyBinding(
        Constant.LocalizationKey.SETTINGS_DESCRIPTION_CONFIGURE_KEY,
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_H,
        Constant.LocalizationKey.SETTINGS_CATEGORY_NAME);

    KeyBindingHelper.registerKeyBinding(openConfigurationMenuKey);

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if (openConfigurationMenuKey.wasPressed() && isCtrlShiftPressed() && client.player != null) {
          System.out.println("keks");
        // client.setScreen(new CustomMenuScreen(Text.of("Мое меню")));
      }
    });
  }

  private boolean isCtrlShiftPressed() {
    long handle = MinecraftClient.getInstance().getWindow().getHandle();

    return ((InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_LEFT_SHIFT)
        || InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_RIGHT_SHIFT))
        && (InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_LEFT_CONTROL)
            || InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_RIGHT_CONTROL)));
  }

}
