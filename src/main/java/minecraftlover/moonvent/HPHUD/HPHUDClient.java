package minecraftlover.moonvent.HPHUD;

import minecraftlover.moonvent.HPHUD.config.ModConfig;
import minecraftlover.moonvent.HPHUD.util.Constant;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HPHUDClient implements ClientModInitializer {
    public static final String MOD_ID = Constant.MOD_ID;
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        new ModConfig();
    }
}
