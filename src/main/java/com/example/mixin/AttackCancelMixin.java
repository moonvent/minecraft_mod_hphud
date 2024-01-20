package com.example.mixin;

import com.google.common.collect.Multimap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.item.SwordItem;
//import net.minecraft.world.item.TieredItem;
//import net.minecraft.world.entity.ai.attributes.AttributeModifier;
//import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

//@Mixin(PlayerEntity.class)
@Mixin(PlayerEntity.class)
// public abstract class AttackCancelMixin extends TieredItem extends
// LivingEntity{
public abstract class AttackCancelMixin {
	private static final Logger LOGGER = LoggerFactory.getLogger("sex2");

	// @Inject(method = "resetLastAttackedTicks", at = @At("HEAD"), cancellable =
	// true)
	// private void onResetLastAttackedTicks(CallbackInfo info) {
	// info.cancel(); // Отменяет обычное поведение сброса таймера атаки
	// LOGGER.info("enter");
	// }

	@Inject(method = "applyDamage", at = @At("HEAD"))
	private void onApplyDamage(CallbackInfo info) {
		// Ваш код здесь
		LOGGER.info("apply damage");
	}

//	@Inject(method = "attack", at = @At("RETURN"))
//	private void render(MatrixStack matrixStack, float partial, CallbackInfo info) {
////		ToroHealth.HUD.draw(matrixStack, ToroHealth.CONFIG);
//	}

	@Inject(method = "attack", at = @At("HEAD"))
	private void onAttackHead(Entity target, CallbackInfo ci) {

//		int a = 5;
		if (target.getWorld() instanceof ServerWorld)
			if (target instanceof LivingEntity) {
				((LivingEntity) target).getAttributes();
				((LivingEntity) target).getHealth();
				LOGGER.info(String.valueOf(((LivingEntity) target).getHealth()));
				// int a = 5;
				// LOGGER.info(String.valueOf(((LivingEntity) target)));
//				float applied_damage = ((LivingEntity) target).getDamageTracker().recentDamage.stream()
//						.findFirst().value.damage;
				// float applied_damage = 0;
				// LOGGER.info("Damage " + applied_damage);
			}
		// this.jump();
		// LOGGER.info(String.valueOf(this.getAttackCooldownProgress(1)));
		// try {
		// info.cancel();
		// } catch (Exception e) {
		// LOGGER.info(e.toString());
		// }
	}



	@Inject(method = "damage", at = @At("TAIL"))
	private void onAttack(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
//		int a = 5;

		TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
		Screen screen = MinecraftClient.getInstance().currentScreen;

		int a = 5;
//		if (target.getWorld() instanceof ServerWorld)
//			if (target instanceof LivingEntity) {
//				((LivingEntity) target).getAttributes();
//				((LivingEntity) target).getHealth();
//				LOGGER.info(String.valueOf(((LivingEntity) target).getHealth()));
//				int a = 5;
//				// LOGGER.info(String.valueOf(((LivingEntity) target)));
////				float applied_damage = ((LivingEntity) target).getDamageTracker().recentDamage.stream()
////						.findFirst().value.damage;
//				// float applied_damage = 0;
//				// LOGGER.info("Damage " + applied_damage);
//			}
		// this.jump();
		// LOGGER.info(String.valueOf(this.getAttackCooldownProgress(1)));
		// try {
		// info.cancel();
		// } catch (Exception e) {
		// LOGGER.info(e.toString());
		// }
	}

	// public AttackCancelMixin(Settings settings) {
	// super(settings);
	// }
	//
	// @Inject(method = "getAttributeModifiers", at = @At("TAIL"))
	// private void keksik(EquipmentSlot slot,
	// CallbackInfoReturnable<Multimap<EntityAttribute, EntityAttributeModifier>>
	// cir) {
	//// LOGGER.info(String.format("%s %s", attackSpeed, attackDamage));
	//// LOGGER.info(toolMaterial.toString());
	// int a = 1;
	//
	// }

	// private static final UUID ATTACK_SPEED_MODIFIER_ID =
	// UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
	//
	// @Inject(method =
	// "<init>(Lnet/minecraft/item/ToolMaterial;IFLnet/minecraft/item/Item$Settings;)V",
	// at = @At("TAIL"))
	// private void init(ToolMaterial material, int attackDamage, float attackSpeed,
	// Settings settings, CallbackInfo ci) {
	// // Изменяем атрибуты здесь
	// try {
	// this.attributeModifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED,
	// new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID,
	// "Weapon modifier",
	// -4.0, // Значение, уменьшающее базовую скорость атаки
	// EntityAttributeModifier.Operation.ADDITION));
	// }
	// catch (Exception e) {
	// LOGGER.info(e.toString());
	// }
	//
	//
	//
	// }

	// @Inject(method = "<init>", at = @At("TAIL"))
	// private void init(CallbackInfo info) {
	// this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED,
	// new EntityAttributeModifier("Attack Speed Modifier",
	// 0.0 -
	// this.getAttributeModifiers(EquipmentSlot.MAINHAND).get(EntityAttributes.GENERIC_ATTACK_SPEED).getValue(),
	// EntityAttributeModifier.Operation.ADDITION));
	// }

	// @Inject(method =
	// "<init>(Lnet/minecraft/item/ToolMaterial;IFLnet/minecraft/item/Item$Settings;)V",
	// at = @At("TAIL"))
	// private void modifyAttackSpeed(ToolMaterial toolMaterial, int attackDamage,
	// float attackSpeed, Settings settings,
	// CallbackInfo ci) {
	// // Здесь вы можете изменить значение attackSpeed
	// // Например, установить его в 0 или любое другое значение
	// LOGGER.info(String.format("%s %s", attackSpeed, attackDamage));
	// LOGGER.info(toolMaterial.toString());
	//
	//// DefaultAttributeContainer.Builder builder = new
	// DefaultAttributeContainer.Builder();
	//// builder.add(EntityAttributes.GENERIC_ATTACK_SPEED,
	//// new EntityAttributeModifier("Weapon modifier", -4.0,
	// EntityAttributeModifier.Operation.ADDITION).getValue());
	// this.attributeModifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED,
	// new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID,
	// "Weapon modifier",
	// -4.0, // Значение, уменьшающее базовую скорость атаки
	// EntityAttributeModifier.Operation.ADDITION));
	//
	//// ((SwordItem)(Object)this).attributeModifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED,
	//// new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID,
	//// "Weapon modifier",
	//// -4.0, // Значение, уменьшающее базовую скорость атаки
	//// EntityAttributeModifier.Operation.ADDITION));
	//
	// // Применение модификатора к мечу
	//// toolMaterial.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED,
	//// new EntityAttributeModifier("Weapon modifier", -4.0,
	//// EntityAttributeModifier.Operation.ADDITION),
	//// EquipmentSlot.MAINHAND);
	//
	// }

	// @Inject(method = "createAttributes", at = @At("HEAD"), cancellable = true)
	// private void modifyAttributes(CallbackInfoReturnable<Void> cir) {
	// // Your code to set the attack speed to 0
	// // You will need to modify the attributes map here
	// LOGGER.info("enter1");
	// }

	// private static final UUID ATTACK_SPEED_MODIFIER_ID =
	// UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
	//
	// // protected AttackCancelMixin(Properties settings, Tier tier) {
	// // super(tier, settings);
	// // }
	//
	// @Inject(method = "getDefaultAttributeModifiers", at = @At("RETURN"),
	// cancellable = true)
	// private void injectDefaultAttributeModifiers(EquipmentSlot slot,
	// CallbackInfoReturnable<MultiAttributeModifierMap> cir) {
	// if (slot == EquipmentSlot.MAINHAND) {
	// MultiAttributeModifierMap modifiers = cir.getReturnValue();
	// modifiers.removeAll(Attributes.ATTACK_SPEED);
	// modifiers.add(Attributes.ATTACK_SPEED, new
	// AttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", 0.0,
	// AttributeModifier.Operation.ADDITION));
	// cir.setReturnValue(modifiers);
	// LOGGER.info(modifiers);
	// }
	// }

}
