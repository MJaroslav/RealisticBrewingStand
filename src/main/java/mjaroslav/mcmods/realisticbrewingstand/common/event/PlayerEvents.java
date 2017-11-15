package mjaroslav.mcmods.realisticbrewingstand.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ganymedes01.etfuturum.ModBlocks;
import mjaroslav.mcmods.realisticbrewingstand.common.init.RBSBlocks;
import mjaroslav.mcmods.realisticbrewingstand.common.integration.EtFuturumIntegration;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class PlayerEvents {
	@SubscribeEvent
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if (event.action == Action.RIGHT_CLICK_BLOCK) {
			if (event.world != null)
				if (event.world.getBlock(event.x, event.y, event.z) == Blocks.brewing_stand) {
					NBTTagCompound nbt = new NBTTagCompound();
					event.world.getTileEntity(event.x, event.y, event.z).writeToNBT(nbt);
					event.world.removeTileEntity(event.x, event.y, event.z);
					event.world.setBlock(event.x, event.y, event.z,
					    EtFuturumIntegration.isExist() ? EtFuturumIntegration.fixedBrewingStand : RBSBlocks.fixedBrewingStand);
					event.world.getTileEntity(event.x, event.y, event.z).readFromNBT(nbt);
				}
			if (EtFuturumIntegration.isExist()
			    && event.world.getBlock(event.x, event.y, event.z) == ModBlocks.brewing_stand) {
				NBTTagCompound nbt = new NBTTagCompound();
				event.world.getTileEntity(event.x, event.y, event.z).writeToNBT(nbt);
				event.world.removeTileEntity(event.x, event.y, event.z);
				event.world.setBlock(event.x, event.y, event.z, EtFuturumIntegration.fixedBrewingStand);
				event.world.getTileEntity(event.x, event.y, event.z).readFromNBT(nbt);
			}
		}
	}
}
