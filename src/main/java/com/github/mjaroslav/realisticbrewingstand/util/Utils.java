package com.github.mjaroslav.realisticbrewingstand.util;

import com.github.mjaroslav.realisticbrewingstand.RealisticBrewingStandMod;
import com.github.mjaroslav.realisticbrewingstand.config.ModConfig.BottlePosition;

import net.minecraft.item.ItemStack;

public final class Utils {
    public static boolean isStackChanged(ItemStack prevStack, ItemStack stack) {
        System.out.println(prevStack + " " + stack);
        if ((prevStack == null && stack != null) || (prevStack != null && stack == null))
            return true;
        if (prevStack == null && stack == null)
            return false;
        return !ItemStack.areEqual(prevStack, stack);
    }

    public static BottlePosition getPositionBySlot(int slot) {
        return switch (slot) {
            case 0 -> RealisticBrewingStandMod.getConfig().bottle1;
            case 1 -> RealisticBrewingStandMod.getConfig().bottle2;
            case 2 -> RealisticBrewingStandMod.getConfig().bottle3;
            default -> null;
        };
    }
}
