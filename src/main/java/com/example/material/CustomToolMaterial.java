package com.example.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CustomToolMaterial implements ToolMaterial {
  @Override
  public int getDurability() {
    return 250; // Пример долговечности
  }

  @Override
  public float getMiningSpeedMultiplier() {
    return 12.0f; // Скорость добычи
  }

  @Override
  public float getAttackDamage() {
    return 2.0f; // Урон
  }

  @Override
  public int getMiningLevel() {
    return 2; // Уровень добычи
  }

  @Override
  public int getEnchantability() {
    return 22; // Возможность зачаровывания
  }

  @Override
  public Ingredient getRepairIngredient() {
    return null;
  }

}
