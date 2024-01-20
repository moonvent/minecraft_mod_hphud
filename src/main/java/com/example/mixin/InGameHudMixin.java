package com.example.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    private static final Logger LOGGER = LoggerFactory.getLogger("sex3");
    private Screen screen = null;

    @Inject(method = "render", at = @At("RETURN"))
    private void render(DrawContext context, float tickDelta, CallbackInfo ci) {

        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;
        Screen currentScreen = minecraftClient.currentScreen;

        PlayerEntity player = minecraftClient.player;
        if (currentScreen != null) {
//            context.drawText(textRenderer, "sex", currentScreen.width / 2, currentScreen.height / 2, 0xFFAFFF, false);
//            context.drawText(textRenderer, "sex", 100, 100, 0xFFAFFF, false);
            screen = currentScreen;
        }
        else {

            Entity viewer = minecraftClient.getCameraEntity();
            Vec3d position = viewer.getCameraPosVec(tickDelta);
            Vec3d look = viewer.getRotationVec(1.0F);
            int reachDistance = 5;
            Vec3d max = position.add(look.x * reachDistance, look.y * reachDistance, look.z * reachDistance);
            Box searchBox = viewer.getBoundingBox().stretch(look.multiply(reachDistance)).expand(1.0D, 1.0D, 1.0D);
            Predicate<Entity> isPositive = entity -> true;
            EntityHitResult result = ProjectileUtil.raycast(viewer, position, max, searchBox, isPositive, reachDistance * reachDistance);

//            if (result == null || result.getEntity() == null) {
//                return null;
//            }

            if (result != null && result.getEntity() instanceof LivingEntity) {
                LivingEntity target = (LivingEntity) result.getEntity();

                context.drawText(textRenderer,String.format("%0" + Integer.toString((int) target.getMaxHealth()).length() + "d", (int)target.getHealth()) , screen.width / 2, screen.height / 2, 0xFFAFFF, false);
//                HitResult blockHit = raycast(setupRayTraceContext(client.player, reachDistance, RaycastContext.FluidHandling.NONE));
//
//                if (!blockHit.getType().equals(HitResult.Type.MISS)) {
//                    double blockDistance = blockHit.getPos().distanceTo(position);
//                    if (blockDistance > target.distanceTo(client.player)) {
//                        return target;
//                    }
//                } else {
//                    return target;
//                }
            }
//            Vec3d cameraPos = player.getCameraPosVec(1.0F);
//            Vec3d rotationVec = player.getRotationVec(1.0F);
//            int len = 5;
//            Vec3d combinedVec = cameraPos.add(rotationVec.x * len, rotationVec.y * len, rotationVec.z * len);
//
//            HitResult hitResult = minecraftClient.player.getWorld().raycast(new RaycastContext(cameraPos, combinedVec, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));
//            int a = 5;
//            if (hitResult.getType() == HitResult.Type.ENTITY) {
//                Entity entity = ((EntityHitResult) hitResult).getEntity();
//                player.sendMessage(Text.of("Вы смотрите на: " + entity.getName().getString()), false);
//                // Тут ваш код для обработки сущности
//            }
////            LOGGER.info(String.valueOf(raycast.getType()));


        }

//        ToroHealth.HUD.draw(matrixStack, ToroHealth.CONFIG);

//        Vec3d cameraPos = player.getCameraPosVec(1.0F);
//        Vec3d rotationVec = player.getRotationVec(1.0F);
//        Vec3d combinedVec = cameraPos.add(rotationVec.x * 20, rotationVec.y * 20, rotationVec.z * 20);
//
//        HitResult hitResult = player.raycast(new RaycastContext(cameraPos, combinedVec, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));
//
//        if (hitResult.getType() == HitResult.Type.ENTITY) {
//            Entity entity = ((EntityHitResult) hitResult).getEntity();
//            // Тут ваш код для обработки сущности
//        }
    }


}
