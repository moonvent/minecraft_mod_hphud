package com.example;

import com.example.item.CustomSword;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class TemplateMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	// public static final Item FABRIC_ITEM = new Item(new FabricItemSettings());
	public static final String MOD_ID = "templatemod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

//	public static RayTrace RAYTRACE = new RayTrace();

	public static final Item FABRIC_ITEM = new CustomSword();

	// public static final Item CUSTOM_SWORD = new CustomSword(new
	// CustomToolMaterial(), 3, -2.4F,
	// new Item.Settings().group(ItemGroup.COMBAT));

	// public static void registerItems() {
	// Registry.register(Registry.ITEM, new Identifier("modid", "custom_sword"),
	// CUSTOM_SWORD);
	// }
	public static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
		entries.add(FABRIC_ITEM);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		// ClientTickEvents.END_CLIENT_TICK.register(client -> {
		// if (client.world != null && !client.isInSingleplayer()) {
		// client.player.sendMessage(Text.of("Добро пожаловать в мир Minecraft!"));
		// }
		// });
		LOGGER.info("Hello Fabric world!");
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "custom_sword"), FABRIC_ITEM);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(TemplateMod::addItemsToIngredientItemGroup);

//		TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
//		Screen screen = MinecraftClient.getInstance().currentScreen;
//
//		int a = 5;


//		AttackEntityCallback.EVENT.register((player, world, hand, pos, entityHitResult) -> {
////			EntityState state = world.getBlockState(pos);
////            /* Manual spectator check is necessary because AttackBlockCallbacks
////               fire before the spectator check */
////			if (state.isToolRequired() && !player.isSpectator() &&
////					player.getMainHandStack().isEmpty()) {
////				player.damage(DamageSource.field_5869, 1.0F);
////			}
//
//			return ActionResult.PASS;
//		});
//
//		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
//			BlockState state = world.getBlockState(pos);
//            /* Manual spectator check is necessary because AttackBlockCallbacks
//               fire before the spectator check */
//			if (state.isToolRequired() && !player.isSpectator() &&
//					player.getMainHandStack().isEmpty()) {
////				DamageSource
////				player.damage(DamageSource, 1.0F);
//			}
//			return ActionResult.FAIL;
//		});

	}
}
