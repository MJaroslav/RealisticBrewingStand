package io.github.mjaroslav.realisticbrewingstand.util;

import lombok.experimental.UtilityClass;
import net.minecraft.item.ItemStack;

@UtilityClass
public class Utils {
    public boolean isStackChanged(ItemStack prevStack, ItemStack stack) {
        if ((prevStack == null && stack != null) || (prevStack != null && stack == null)) return true;
        if (prevStack == null) return false;
        return !ItemStack.areItemStacksEqual(prevStack, stack);
    }
}
