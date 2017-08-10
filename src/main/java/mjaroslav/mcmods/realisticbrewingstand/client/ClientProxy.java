package mjaroslav.mcmods.realisticbrewingstand.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import mjaroslav.mcmods.realisticbrewingstand.client.render.tileentity.TileFixedBrewingStandRenderer;
import mjaroslav.mcmods.realisticbrewingstand.common.CommonProxy;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileEntityFixedBrewingStand;

public class ClientProxy extends CommonProxy {
	@Override
	public void init() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFixedBrewingStand.class,
				new TileFixedBrewingStandRenderer());
	}
}
