package minecraftlover.moonvent.HPHUD.mixin;

import minecraftlover.moonvent.util.Constant;
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

import java.util.function.Predicate;

@Mixin(InGameHud.class)
public class PlayHUDMixin {
    private static final Logger LOGGER = LoggerFactory.getLogger(Constant.LOGGER_HUD_NAME);
    private Screen lastScreen = null;

    @Inject(method = "render", at = @At("RETURN"))
    private void render(DrawContext context, float tickDelta, CallbackInfo ci) {

        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;
        Screen currentScreen = minecraftClient.currentScreen;

        if (currentScreen != null) {
            lastScreen = currentScreen;

        } else {

            Entity viewer = minecraftClient.getCameraEntity();
            Vec3d position = viewer.getCameraPosVec(tickDelta);
            Vec3d look = viewer.getRotationVec(1.0F);
            int reachDistance = 5;
            Vec3d max = position.add(look.x * reachDistance, look.y * reachDistance, look.z * reachDistance);
            Box searchBox = viewer.getBoundingBox().stretch(look.multiply(reachDistance)).expand(1.0D, 1.0D, 1.0D);
            Predicate<Entity> isPositive = entity -> true;
            EntityHitResult result = ProjectileUtil.raycast(viewer, position, max, searchBox, isPositive,
                    reachDistance * reachDistance);

            if (result != null && result.getEntity() instanceof LivingEntity) {
                LivingEntity target = (LivingEntity) result.getEntity();

                int maxHealth = (int) target.getMaxHealth();

                context.drawText(textRenderer,
                        String.format("%0" + Integer.toString(maxHealth).length() + "d",
                                (int) target.getHealth()),
                        lastScreen.width / 2,
                        lastScreen.height / 2,
                        0xFFAFFF,
                        true);
            }

        }

    }

}
