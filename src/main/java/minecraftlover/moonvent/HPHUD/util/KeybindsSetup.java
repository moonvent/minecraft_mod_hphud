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
    KeyBinding.Category category = KeyBinding.Category.create(
        net.minecraft.util.Identifier.of(Constant.MOD_ID, "general"));

    openConfigurationMenuKey = new KeyBinding(
        Constant.LocalizationKey.SETTINGS_DESCRIPTION_CONFIGURE_KEY,
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_H,
        category);
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
    net.minecraft.client.util.Window window = MinecraftClient.getInstance().getWindow();
    return ((InputUtil.isKeyPressed(window, GLFW.GLFW_KEY_LEFT_SHIFT)
        || InputUtil.isKeyPressed(window, GLFW.GLFW_KEY_RIGHT_SHIFT))
        && (InputUtil.isKeyPressed(window, GLFW.GLFW_KEY_LEFT_CONTROL)
            || InputUtil.isKeyPressed(window, GLFW.GLFW_KEY_RIGHT_CONTROL)));
  }

}
