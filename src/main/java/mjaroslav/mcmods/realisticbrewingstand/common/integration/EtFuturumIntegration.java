package mjaroslav.mcmods.realisticbrewingstand.common.integration;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.EtFuturum;
import mjaroslav.mcmods.realisticbrewingstand.common.block.BlockFixedBrewingStandEF;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileEntityFixedBrewingStandEF;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemReed;

public class EtFuturumIntegration {
	public static Block fixedBrewingStand;
	private static boolean exist = false;

	public static void checkExist() {
		exist = Loader.isModLoaded("etfuturum");
		if (exist)
			exist = EtFuturum.enableBrewingStands;
	}

	public static boolean isExist() {
		return exist;
	}

	public static void registerBlock() {
		if (!isExist())
			return;
		fixedBrewingStand = new BlockFixedBrewingStandEF().setHardness(0.5F).setLightLevel(0.125F)
		    .setBlockName("brewingStand").setBlockTextureName("brewing_stand");
		GameRegistry.registerBlock(fixedBrewingStand, "fixed_brewing_stand_ef");
		GameRegistry.registerTileEntity(TileEntityFixedBrewingStandEF.class, "tile_fixed_entity_brewing_stand_ef");
		((ItemReed) Items.brewing_stand).field_150935_a = fixedBrewingStand;
	}
}
