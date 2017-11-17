package mjaroslav.mcmods.realisticbrewingstand.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mjaroslav.mcmods.realisticbrewingstand.common.init.RBSBlocks;
import mjaroslav.mcmods.realisticbrewingstand.common.integration.EtFuturumIntegration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class PlayerEvents {
	@SubscribeEvent
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if (event.action != Action.RIGHT_CLICK_BLOCK || event.world == null || event.world.isRemote)
			return;
		Block block = EtFuturumIntegration.isExist() ? EtFuturumIntegration.fixedBrewingStand : RBSBlocks.fixedBrewingStand;
		if (event.world.getBlock(event.x, event.y, event.z) instanceof BlockBrewingStand
		    && event.world.getBlock(event.x, event.y, event.z) != block) {
			NBTTagCompound nbt = new NBTTagCompound();
			event.world.getTileEntity(event.x, event.y, event.z).writeToNBT(nbt);
			event.world.removeTileEntity(event.x, event.y, event.z);
			event.world.setBlock(event.x, event.y, event.z, block);
			event.world.getTileEntity(event.x, event.y, event.z).readFromNBT(nbt);
		}
	}
}
