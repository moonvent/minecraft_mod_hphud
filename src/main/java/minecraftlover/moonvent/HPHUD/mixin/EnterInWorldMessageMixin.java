package minecraftlover.moonvent.HPHUD.mixin;

import minecraftlover.moonvent.HPHUD.config.ModConfig;
import minecraftlover.moonvent.HPHUD.util.Constant;
import minecraftlover.moonvent.HPHUD.util.IndicatorCoordinate;
import minecraftlover.moonvent.HPHUD.util.KeybindsSetup;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class EnterInWorldMessageMixin {
	private boolean firstAppearInNewWorld = true;

	@Inject(at = @At("RETURN"), method = "handleMovePlayer")
	private void init(CallbackInfo info) {
		if (firstAppearInNewWorld) {
			firstAppearInNewWorld = false;
			new IndicatorCoordinate();
			new ModConfig();
			if (ModConfig.greetingsAfterEnterInWorld)
				Minecraft.getInstance().player.sendSystemMessage(
						Component.translatable(Constant.LocalizationKey.GREETINGS_IN_WORLD,
								KeybindsSetup.openConfigurationMenuKey.getTranslatedKeyMessage()));
		}
	}
}
