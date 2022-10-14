package io.github.mjaroslav.realisticbrewingstand.asm.mixin;

import ganymedes01.etfuturum.tileentities.TileEntityNewBrewingStand;
import io.github.mjaroslav.realisticbrewingstand.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBrewingStand;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityNewBrewingStand.class)
public abstract class MixinTileEntityNewBrewingStand extends TileEntityBrewingStand {
    @Shadow(remap = false)
    private ItemStack[] inventory;
    private final ItemStack[] prevBrewingItemStacks = new ItemStack[3];

    @Inject(method = "getFilledSlots", at = @At("HEAD"), cancellable = true)
    public void getFilledSlots(@NotNull CallbackInfoReturnable<Integer> ci) {
        var flag = false;
        for (var i = 0; i < 3; i++)
            flag = flag || Utils.isStackChanged(prevBrewingItemStacks[i], inventory[i]);
        if (flag) {
            for (var i = 0; i < 3; i++) prevBrewingItemStacks[i] = inventory[i].copy(); // I'm not sure in copy.
            markDirty();
            getWorldObj().markBlockForUpdate(xCoord, yCoord, zCoord);
        }
        ci.setReturnValue(0);
    }
}
