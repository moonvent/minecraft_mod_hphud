package minecraftlover.moonvent.HPHUD.mixin;

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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.function.Predicate;

@Mixin(InGameHud.class)
public class PlayHUDMixin {
  private static final Logger LOGGER = LoggerFactory.getLogger(Constant.LOGGER_HUD_NAME);
  private Screen lastScreen = null;
  private int maxEntityHealth;
  private String indicatorText;
  private int currentEntityHealth;
  private int maxEntityHpAmountLength;

  private int currentEntityHealthLenght;

  private int centerX;
  private int centerY;

  private int offsetX;
  private int offsetY;

  private int textX;
  private int textY;

  private int horizontalGuiScale;
  private int verticalGuiScale;

  private ModConfig config;

  private void getTextForIndicator(LivingEntity entity) {

    maxEntityHealth = (int) entity.getMaxHealth();
    currentEntityHealth = (int) Math.ceil(entity.getHealth());
    maxEntityHpAmountLength = Integer.toString(maxEntityHealth).length();
    currentEntityHealthLenght = Integer.toString(currentEntityHealth).length();

    if (currentEntityHealth == 0) {
      indicatorText = null;
      return;
    }

    switch (config.outputIndicatorMode) {
      case Constant.INDICATOR_TYPE.CURRENT_WITH_MAX_HP:
        indicatorText = String.format("%s / %s",
            currentEntityHealth, maxEntityHealth);
        break;
      case Constant.INDICATOR_TYPE.CURRENT_PERCENTAGE_HP:
        indicatorText = String.format("%s %%",
            (int) Math.ceil((float) currentEntityHealth / (float) maxEntityHealth * 100.0));
        break;
      default:
        indicatorText = String.format("%s",
            currentEntityHealth);
        break;
    }

    // if some text decrease, stay away in old position it, cause render run from left to right,
    // and if hp was 100 and now 99, text len will decrease, and that will be annoying
    if (currentEntityHealthLenght < maxEntityHpAmountLength)
      indicatorText = String.format("%" + (maxEntityHpAmountLength - currentEntityHealthLenght) + "s%s", "", indicatorText);
  }

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

        getTextForIndicator(target);

        if (indicatorText == null)
          return;

        // guiScale = minecraftClient.options.getGuiScale().getValue();

        horizontalGuiScale = !Objects.equals(config.outputIndicatorMode, Constant.INDICATOR_TYPE.CURRENT_HP) ? 14 : 7;
        verticalGuiScale = 7;

        centerX = lastScreen.width / 2;
        centerY = lastScreen.height / 2;

        offsetX = 3 * horizontalGuiScale;
        offsetY = 2 * verticalGuiScale;

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
