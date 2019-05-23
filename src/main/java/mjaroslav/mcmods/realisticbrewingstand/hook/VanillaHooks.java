package mjaroslav.mcmods.realisticbrewingstand.hook;

import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.asm.Hook;
import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.asm.Hook.ReturnValue;
import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.asm.ReturnCondition;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;

public class VanillaHooks {
    public static final String DISABLE_ID = "hooks_vanilla";

    @Hook(returnCondition = ReturnCondition.ALWAYS, createMethod = true)
    public static void onDataPacket(TileEntityBrewingStand instance, NetworkManager manager,
                                    SPacketUpdateTileEntity packet) {
        instance.readFromNBT(packet.getNbtCompound());
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS, createMethod = true)
    public static SPacketUpdateTileEntity getUpdatePacket(TileEntityBrewingStand instance) {
        NBTTagCompound syncData = new NBTTagCompound();
        instance.writeToNBT(syncData);
        return new SPacketUpdateTileEntity(instance.getPos(), -999, syncData);
    }

    private static final boolean[] REPLACER = {false, false, false};

    @Hook(injectOnExit = true, returnCondition = ReturnCondition.ALWAYS)
    public static boolean[] createFilledSlotsArray(TileEntityBrewingStand instance, @ReturnValue boolean[] returnValue) {
        if (!instance.getWorld().isRemote)
            if (returnValue[0] || returnValue[1] || returnValue[2]) {
                instance.markDirty();
                instance.getWorld().notifyBlockUpdate(instance.getPos(),
                        instance.getWorld().getBlockState(instance.getPos()),
                        instance.getWorld().getBlockState(instance.getPos()), 2);
            }
        return REPLACER;
    }
}
