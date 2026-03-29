package minecraftlover.moonvent.HPHUD.util;

import minecraftlover.moonvent.HPHUD.config.ModConfig;
import minecraftlover.moonvent.HPHUD.gui.screen.ConfigurationScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.resources.Identifier;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

public class KeybindsSetup {
  public static KeyMapping openConfigurationMenuKey;

  public KeybindsSetup() {
    addNewKeyBindings();
  }

  public void addNewKeyBindings() {
    setupOpenConfigurationMenuKey();
  }

  private void setupOpenConfigurationMenuKey() {
    KeyMapping.Category category = KeyMapping.Category.register(
        Identifier.fromNamespaceAndPath(Constant.MOD_ID, "general"));
    openConfigurationMenuKey = new KeyMapping(
        Constant.LocalizationKey.SETTINGS_DESCRIPTION_CONFIGURE_KEY,
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_H,
        category);

    KeyMappingHelper.registerKeyMapping(openConfigurationMenuKey);

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if (openConfigurationMenuKey.consumeClick() && client.player != null) {
        if (isCtrlShiftPressed())
          client.setScreen(new ConfigurationScreen());
        else if (ModConfig.warningAfterPressMenuKey)
          Minecraft.getInstance().player.sendSystemMessage(
              Component.translatable(Constant.LocalizationKey.WARNING_IN_CHAT_ABOUT_CONFLICTS,
                  KeybindsSetup.openConfigurationMenuKey.getTranslatedKeyMessage(),
                  KeybindsSetup.openConfigurationMenuKey.getTranslatedKeyMessage()));
      }
    });
  }

  private boolean isCtrlShiftPressed() {
    com.mojang.blaze3d.platform.Window window = Minecraft.getInstance().getWindow();
    return ((InputConstants.isKeyDown(window, GLFW.GLFW_KEY_LEFT_SHIFT)
        || InputConstants.isKeyDown(window, GLFW.GLFW_KEY_RIGHT_SHIFT))
        && (InputConstants.isKeyDown(window, GLFW.GLFW_KEY_LEFT_CONTROL)
            || InputConstants.isKeyDown(window, GLFW.GLFW_KEY_RIGHT_CONTROL)));
  }
}
