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
        instance.readFromNBT(packet.func_148857_g());
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS, returnType = "net.minecraft.network.Packet", createMethod = true)
    public static Packet getDescriptionPacket(TileEntityBrewingStand instance) {
        NBTTagCompound syncData = new NBTTagCompound();
        instance.writeToNBT(syncData);
        return new S35PacketUpdateTileEntity(instance.xCoord, instance.yCoord, instance.zCoord, 1, syncData);
    }

    @Hook(injectOnExit = true, returnCondition = ReturnCondition.ALWAYS)
    public static int getFilledSlots(TileEntityBrewingStand instance, @ReturnValue int returnValue) {
        if (!instance.getWorldObj().isRemote)
            if (returnValue > 1) {
                instance.markDirty();
                instance.getWorldObj().markBlockForUpdate(instance.xCoord, instance.yCoord, instance.zCoord);
            }
        return 0;
    }
}
