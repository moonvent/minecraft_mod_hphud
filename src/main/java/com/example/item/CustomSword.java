package com.example.item;

import com.example.material.CustomToolMaterial;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.injection.Inject;

// public class CustomSword extends Item {
//   // public CustomSword(ToolMaterial material, int attackDamage, float
//   // attackSpeed, Settings settings) {
//   // super(material, attackDamage, attackSpeed, settings);
//   // }
//
//   public CustomSword(Settings settings) {
//     super(settings);
//   }
//
//   @Override
//   public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
//     playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
//     return TypedActionResult.success(playerEntity.getStackInHand(hand));
//   }
// }
//

public class CustomSword extends SwordItem {
  public CustomSword() {
    super(new CustomToolMaterial(), 1, 5, new FabricItemSettings());
  }

  public static final Logger LOGGER = LoggerFactory.getLogger("sword");

//  @Override
//  public float getAttackDamage() {
//    float damage = super.getAttackDamage();
//    LOGGER.info("Damage" + damage);
//    return damage;
//  }

  @Override
  public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    if (target instanceof ZombieEntity)
//      ((ZombieEntity) target).onDamaged();
      LOGGER.info("zombie");
    boolean result = super.postHit(stack, target, attacker);
    return result;
  }





  // public CustomSword(Settings settings) {
  // super(settings);
  // }

//  @Override
//  public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
//    playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
//    return TypedActionResult.success(playerEntity.getStackInHand(hand));
//  }
}
