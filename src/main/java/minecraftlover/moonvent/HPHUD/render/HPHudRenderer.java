package minecraftlover.moonvent.HPHUD.render;

import minecraftlover.moonvent.HPHUD.config.ModConfig;
import minecraftlover.moonvent.HPHUD.util.Constant;
import minecraftlover.moonvent.HPHUD.util.IndicatorCoordinate;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.function.Predicate;

public class HPHudRenderer implements HudElement {

    private String indicatorText;
    private int maxEntityHpAmountLength;
    private int currentEntityHealthLength;
    private int textWidth;
    private int textHeight;

    @Override
    public void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.currentScreen != null) return;
        if (client.getCameraEntity() == null) return;

        IndicatorCoordinate indicatorCoordinate = IndicatorCoordinate.getInstance();
        if (indicatorCoordinate == null) return;

        if (client.getWindow() != null) {
            indicatorCoordinate.updateFromWindow(
                client.getWindow().getScaledWidth(),
                client.getWindow().getScaledHeight()
            );
        }
        int x = context.getScaledWindowWidth() / 2;
        int y = context.getScaledWindowHeight() / 2;

        ModConfig config = ModConfig.getInstance();
        TextRenderer textRenderer = client.textRenderer;
        Entity viewer = client.getCameraEntity();

        Vec3d position = viewer.getCameraPosVec(tickCounter.getDynamicDeltaTicks());
        Vec3d look = viewer.getRotationVec(1.0F);
        Vec3d max = position.add(
            look.x * config.searchDistance,
            look.y * config.searchDistance,
            look.z * config.searchDistance
        );
        Box searchBox = viewer.getBoundingBox()
            .stretch(look.multiply(config.searchDistance))
            .expand(1.0D, 1.0D, 1.0D);

        Predicate<Entity> isPositive = entity -> true;
        EntityHitResult result = ProjectileUtil.raycast(
            viewer, position, max, searchBox, isPositive,
            config.searchDistance * config.searchDistance
        );
        // System.out.println("result: " + result);

        if (result != null && result.getEntity() instanceof LivingEntity target) {
            getTextForIndicator(target, config);
            if (indicatorText == null) return;

            textWidth = textRenderer.getWidth(indicatorText);
            textHeight = textRenderer.getWrappedLinesHeight(indicatorText, textWidth);

            context.drawTextWithShadow(textRenderer,
                indicatorText,
                indicatorCoordinate.getX() - textWidth / 2,
                indicatorCoordinate.getY() - textHeight / 2,
               0xFF000000 |  Integer.parseInt(config.indicatorColor, 16)
                );
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
