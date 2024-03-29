package minecraftlover.moonvent.HPHUD.mixin;

import minecraftlover.moonvent.HPHUD.config.ModConfig;
import minecraftlover.moonvent.HPHUD.util.Constant;
import minecraftlover.moonvent.HPHUD.util.IndicatorCoordinate;
import minecraftlover.moonvent.HPHUD.util.KeybindsSetup;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class EnterInWorldMessageMixin {
	private boolean firstAppearInNewWorld = true;

	@Inject(at = @At("RETURN"), method = "onPlayerMove")
	private void init(CallbackInfo info) {
		if (firstAppearInNewWorld) {
			firstAppearInNewWorld = false;
			new IndicatorCoordinate();
			new ModConfig();
			if (ModConfig.greetingsAfterEnterInWorld)
				MinecraftClient.getInstance().player.sendMessage(
						Text.translatable(Constant.LocalizationKey.GREETINGS_IN_WORLD,
								KeybindsSetup.openConfigurationMenuKey.getBoundKeyLocalizedText()),
						false);
		}
	}
}
