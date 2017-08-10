package mjaroslav.mcmods.realisticbrewingstand.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mjaroslav.mcmods.realisticbrewingstand.RealisticBrewingStandMod;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class PlayerEvents {
	@SubscribeEvent
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if (event.action == Action.RIGHT_CLICK_BLOCK) {
			if (event.world != null)
				if (event.world.getBlock(event.x, event.y, event.z) == Blocks.brewing_stand)
					event.world.setBlock(event.x, event.y, event.z, RealisticBrewingStandMod.fixedBrewingStand);
		}
	}
}
