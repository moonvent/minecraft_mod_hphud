package minecraftlover.moonvent.HPHUD.render;

import minecraftlover.moonvent.HPHUD.config.ModConfig;
import minecraftlover.moonvent.HPHUD.util.Constant;
import minecraftlover.moonvent.HPHUD.util.IndicatorCoordinate;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;

public class HPHudRenderer implements HudElement {

    private String indicatorText;
    private int maxEntityHpAmountLength;
    private int currentEntityHealthLength;
    private int textWidth;
    private int textHeight;

    @Override
    public void extractRenderState(GuiGraphicsExtractor context, DeltaTracker tickCounter) {
        Minecraft client = Minecraft.getInstance();

        if (client.screen != null)
            return;
        if (client.getCameraEntity() == null)
            return;

        IndicatorCoordinate indicatorCoordinate = IndicatorCoordinate.getInstance();
        if (indicatorCoordinate == null)
            return;

        indicatorCoordinate.updateFromWindow(
                context.guiWidth(),
                context.guiHeight());

        ModConfig config = ModConfig.getInstance();
        var font = client.font;
        Entity viewer = client.getCameraEntity();

        Vec3 position = viewer.getEyePosition(tickCounter.getGameTimeDeltaPartialTick(true));
        Vec3 look = viewer.getViewVector(1.0F);
        Vec3 max = position.add(
                look.x * config.searchDistance,
                look.y * config.searchDistance,
                look.z * config.searchDistance);
        AABB searchBox = viewer.getBoundingBox()
                .expandTowards(look.scale(config.searchDistance))
                .inflate(1.0D, 1.0D, 1.0D);

        Predicate<Entity> isPositive = entity -> true;
        EntityHitResult result = ProjectileUtil.getEntityHitResult(
                viewer, position, max, searchBox, isPositive,
                config.searchDistance * config.searchDistance);

        if (result != null && result.getEntity() instanceof LivingEntity target) {
            getTextForIndicator(target, config);
            if (indicatorText == null)
                return;

            textWidth = font.width(indicatorText);
            // textHeight = font.wordWrapHeight(indicatorText, textWidth);
            textHeight = font.lineHeight;

            context.text(font,
                    indicatorText,
                    indicatorCoordinate.getX() - textWidth / 2,
                    indicatorCoordinate.getY() - textHeight / 2,
                    0xFF000000 | Integer.parseInt(config.indicatorColor, 16),
                    true);
        }
    }

    private void getTextForIndicator(LivingEntity entity, ModConfig config) {
        int maxHealth = (int) entity.getMaxHealth();
        int currentHealth = (int) Math.ceil(entity.getHealth());
        maxEntityHpAmountLength = Integer.toString(maxHealth).length();
        currentEntityHealthLength = Integer.toString(currentHealth).length();

        if (currentHealth == 0) {
            indicatorText = null;
            return;
        }

        switch (config.outputIndicatorMode) {
            case Constant.IndicatorType.CURRENT_WITH_MAX_HP:
                indicatorText = String.format("%s / %s", currentHealth, maxHealth);
                break;
            case Constant.IndicatorType.CURRENT_PERCENTAGE_HP:
                indicatorText = String.format("%s %%",
                        (int) Math.ceil((float) currentHealth / (float) maxHealth * 100.0));
                break;
            default:
                indicatorText = String.format("%s", currentHealth);
                break;
        }

        if (currentEntityHealthLength < maxEntityHpAmountLength)
            indicatorText = String.format(
                    "%" + (maxEntityHpAmountLength - currentEntityHealthLength) + "s%s",
                    "", indicatorText);
    }
}
