package minecraftlover.moonvent.HPHUD.util;

import minecraftlover.moonvent.HPHUD.config.ModConfig;
import minecraftlover.moonvent.HPHUD.gui.screen.ConfigurationScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.util.InputUtil;

import net.minecraft.client.MinecraftClient;

public class KeybindsSetup {

  public static KeyBinding openConfigurationMenuKey;

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
      if (openConfigurationMenuKey.wasPressed() && client.player != null) {
        if (isCtrlShiftPressed())
          client.setScreen(new ConfigurationScreen());
        else if (ModConfig.warningAfterPressMenuKey)
          MinecraftClient.getInstance().player.sendMessage(
              Text.translatable(Constant.LocalizationKey.WARNING_IN_CHAT_ABOUT_CONFLICTS,
                  KeybindsSetup.openConfigurationMenuKey.getBoundKeyLocalizedText(),
                  KeybindsSetup.openConfigurationMenuKey.getBoundKeyLocalizedText()),
              false);

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
