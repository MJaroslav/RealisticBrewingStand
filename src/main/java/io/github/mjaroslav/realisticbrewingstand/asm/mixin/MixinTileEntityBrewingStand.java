package io.github.mjaroslav.realisticbrewingstand.asm.mixin;

import io.github.mjaroslav.realisticbrewingstand.util.Utils;
import lombok.val;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityBrewingStand.class)
public abstract class MixinTileEntityBrewingStand extends TileEntity {
    @Override
    public void onDataPacket(NetworkManager manager, @NotNull S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public Packet getDescriptionPacket() {
        val syncData = new NBTTagCompound();
        writeToNBT(syncData);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
    }

    @Shadow
    private ItemStack[] brewingItemStacks;
    private final ItemStack[] prevBrewingItemStacks = new ItemStack[3];

    @Inject(method = "getFilledSlots", at = @At("HEAD"), cancellable = true)
    public void getFilledSlots(@NotNull CallbackInfoReturnable<Integer> ci) {
        var flag = false;
        for (var i = 0; i < 3; i++)
            flag = flag || Utils.isStackChanged(prevBrewingItemStacks[i], brewingItemStacks[i]);
        if (flag) {
            for (var i = 0; i < 3; i++) prevBrewingItemStacks[i] = brewingItemStacks[i].copy(); // I'm not sure in copy.
            markDirty();
            getWorldObj().markBlockForUpdate(xCoord, yCoord, zCoord);
        }
        ci.setReturnValue(0);
    }
}
