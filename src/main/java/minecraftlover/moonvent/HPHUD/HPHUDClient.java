package minecraftlover.moonvent.HPHUD;

import minecraftlover.moonvent.HPHUD.render.HPHudRenderer;
import minecraftlover.moonvent.HPHUD.util.Constant;
import minecraftlover.moonvent.HPHUD.util.KeybindsSetup;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.resources.Identifier;

public class HPHUDClient implements ClientModInitializer {
    public static final String MOD_ID = Constant.MOD_ID;
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        new KeybindsSetup();
        HudElementRegistry.addLast(
                Identifier.fromNamespaceAndPath(MOD_ID, "hud"),
                new HPHudRenderer());
    }
}
