package mjaroslav.mcmods.realisticbrewingstand.common.init;

import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import mjaroslav.mcmods.mjutils.common.objects.IModModule;
import mjaroslav.mcmods.mjutils.common.objects.ModInitModule;
import mjaroslav.mcmods.realisticbrewingstand.RealisticBrewingStandMod;
import mjaroslav.mcmods.realisticbrewingstand.common.block.BlockFixedBrewingStand;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileEntityFixedBrewingStand;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemReed;

@ModInitModule(modid = RealisticBrewingStandMod.MODID)
public class RBSBlocks implements IModModule {
	public static Block fixedBrewingStand;

	@Override
	public String getModuleName() {
		return "Blocks";
	}

	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		fixedBrewingStand = new BlockFixedBrewingStand().setHardness(0.5F).setLightLevel(0.125F)
		    .setBlockName("brewingStand").setBlockTextureName("brewing_stand");
		GameRegistry.registerBlock(fixedBrewingStand, "fixed_brewing_stand");
		GameRegistry.registerTileEntity(TileEntityFixedBrewingStand.class, "tile_fixed_entity_brewing_stand");
		((ItemReed) Items.brewing_stand).field_150935_a = fixedBrewingStand;
	}

	@Override
	public void init(FMLInitializationEvent event) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
	}
}
