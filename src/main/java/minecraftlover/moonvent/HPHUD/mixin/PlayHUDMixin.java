package minecraftlover.moonvent.HPHUD.mixin;

import minecraftlover.moonvent.HPHUD.HPHUDClient;
import minecraftlover.moonvent.HPHUD.config.ModConfig;
import minecraftlover.moonvent.HPHUD.util.Constant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(InGameHud.class)
public class PlayHUDMixin {
  private static final Logger LOGGER = LoggerFactory.getLogger(Constant.LOGGER_HUD_NAME);
  private Screen lastScreen = null;
  private int maxEntityHealth;
  private String indicatorText;
  private int currentEntityHealth;
  private int maxEntityHpAmountLength;

  private int centerX;
  private int centerY;

  private int offsetX;
  private int offsetY;

  private int textX;
  private int textY;

  private int firstZerosIndex;
  private int guiScale;

  private ModConfig config;


  @Inject(method = "render", at = @At("RETURN"))
  private void render(DrawContext context, float tickDelta, CallbackInfo ci) {

    MinecraftClient minecraftClient = MinecraftClient.getInstance();
    TextRenderer textRenderer = minecraftClient.textRenderer;
    Screen currentScreen = minecraftClient.currentScreen;
    config = ModConfig.getInstance();

    if (currentScreen != null) {
      lastScreen = currentScreen;

    } else {

      Entity viewer = minecraftClient.getCameraEntity();
      Vec3d position = viewer.getCameraPosVec(tickDelta);
      Vec3d look = viewer.getRotationVec(1.0F);

      Vec3d max = position.add(look.x * config.searchDistance, look.y * config.searchDistance,
          look.z * config.searchDistance);
      Box searchBox = viewer.getBoundingBox().stretch(look.multiply(config.searchDistance)).expand(1.0D, 1.0D, 1.0D);
      Predicate<Entity> isPositive = entity -> true;
      EntityHitResult result = ProjectileUtil.raycast(viewer, position, max, searchBox, isPositive,
              config.searchDistance * config.searchDistance);

      if (result != null && result.getEntity() instanceof LivingEntity) {
        LivingEntity target = (LivingEntity) result.getEntity();

        maxEntityHealth = (int) target.getMaxHealth();
        currentEntityHealth = (int) Math.ceil(target.getHealth());
        maxEntityHpAmountLength = Integer.toString(maxEntityHealth).length();

        if (currentEntityHealth == 0)
          return;

        if (config.outputGeneralAmountEnemyHp) {
          indicatorText = String.format("%0" + maxEntityHpAmountLength + "d / %s",
              currentEntityHealth, maxEntityHealth);
        } else {
          indicatorText = String.format("%0" + maxEntityHpAmountLength + "d",
              currentEntityHealth);
        }
        firstZerosIndex = indicatorText.indexOf(String.valueOf(currentEntityHealth));

        if (firstZerosIndex > -1) {
          indicatorText = indicatorText.replaceFirst("0".repeat(firstZerosIndex), " ".repeat(firstZerosIndex));
        }

//        guiScale = minecraftClient.options.getGuiScale().getValue();

        guiScale = 7;

        centerX = lastScreen.width / 2;
        centerY = lastScreen.height / 2;

        offsetX = 3 * guiScale;
        offsetY = 2 * guiScale;

        textX = centerX - offsetX;
        textY = centerY - offsetY;

        context.drawText(textRenderer,
            indicatorText,
            textX,
            textY,
            config.indicatorColor,
            true);
      }

    }

  }

}
