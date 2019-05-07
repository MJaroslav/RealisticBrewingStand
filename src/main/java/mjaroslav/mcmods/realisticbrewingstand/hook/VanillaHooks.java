package mjaroslav.mcmods.realisticbrewingstand.hook;

import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.asm.Hook;
import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.asm.Hook.ReturnValue;
import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.asm.ReturnCondition;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;

public class VanillaHooks {
    public static final String DISABLE_ID = "hooks_vanilla";

    @Hook(returnCondition = ReturnCondition.ALWAYS, returnType = "void", createMethod = true)
    public static void onDataPacket(TileEntityBrewingStand instance, NetworkManager manager,
                                    S35PacketUpdateTileEntity packet) {
        instance.readFromNBT(packet.getNbtCompound());
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS, returnType = "net.minecraft.network.Packet", createMethod = true)
    public static Packet getDescriptionPacket(TileEntityBrewingStand instance) {
        NBTTagCompound syncData = new NBTTagCompound();
        instance.writeToNBT(syncData);
        return new S35PacketUpdateTileEntity(instance.getPos(), instance.getBlockMetadata(), syncData);
    }

    private static final boolean[] REPLACE_VALUE = new boolean[]{false, false, false};

    @Hook(injectOnExit = true, returnCondition = ReturnCondition.ALWAYS)
    public static boolean[] func_174902_m(TileEntityBrewingStand instance, @ReturnValue boolean[] returnValue) {
        if (!instance.getWorld().isRemote)
            if (returnValue[0] || returnValue[1] || returnValue[2]) {
                instance.markDirty();
                instance.getWorld().markBlockForUpdate(instance.getPos());
            }
        return REPLACE_VALUE;
    }
}
