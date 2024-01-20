package com.example;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import net.minecraft.client.MinecraftClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateModClient implements ClientModInitializer {
 public static final Logger LOGGER = LoggerFactory.getLogger("sex");

 private boolean hasJoinedWorld = false;

 @Override
 public void onInitializeClient() {

  LOGGER.info("Hello from client Fabric world!");

  ClientTickEvents.START_CLIENT_TICK.register(client -> {
   if (client.world != null && !hasJoinedWorld) {
    client.player.sendMessage(Text.literal("Добро пожаловать епТА!"), false);
    hasJoinedWorld = true;
   }

   if (client.world == null) {
    hasJoinedWorld = false;
   }
  });

//  TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
//  Screen screen = MinecraftClient.getInstance().currentScreen;

  int a = 5;
  // Выводим текст на экран
//  textRenderer.draw(
//      "Hello, world!",
//      (screen.width / 2) - (textRenderer.getWidth("Hello, world!") / 2),
//      (screen.height / 2),
//      0xFFFFFF
//  );

 }

 // @Override
 // public void onInitialize() {
 //// LOGGER.info("sex");
 ////
 //// Registry.ITEM.forEach(item -> {
 //// if (item instanceof SwordItem) {s
 //// // Создаем новый модификатор атрибута с нулевой скоростью атаки
 //// EntityAttributeModifier modifier = new EntityAttributeModifier("Attack
 // Speed Modifier", -4.0,
 //// EntityAttributeModifier.Operation.ADDITION);
 ////
 //// // Добавляем или заменяем модификатор в атрибуте меча
 //// item.getDefaultStack().getAttributeModifiers(EquipmentSlot.MAINHAND)
 //// .add(EntityAttributes.GENERIC_ATTACK_SPEED, modifier);
 //// }
 //// });
 //
 // }

 // @Override
 // public void onInitializeClient() {
 // LOGGER.info("sex");
 // // Registry.ITEM.getIds().forEach(id -> {
 // // Item item = Registry.ITEM.get(id);
 // // LOGGER.info(item);
 // // // Теперь у вас есть доступ к объекту Item, и вы можете делать с ним что
 // // угодно
 // // // Например, проверить, является ли он мечом и изменить его атрибуты
 // // });
 // // for (Item item : Registry.ITEM) {
 // // if (item instanceof SwordItem) {
 // // ((SwordItem) item).getAttributeModifiers(EquipmentSlot.MAINHAND)
 // // .put(EntityAttributes.GENERIC_ATTACK_SPEED, new
 // // EntityAttributeModifier("Weapon modifier", 0.0,
 // // EntityAttributeModifier.Operation.ADDITION));
 // // }
 // // }
 // }

}
