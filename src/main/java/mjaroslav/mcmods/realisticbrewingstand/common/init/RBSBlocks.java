package mjaroslav.mcmods.realisticbrewingstand.common.init;

import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import mjaroslav.mcmods.mjutils.common.objects.IModModule;
import mjaroslav.mcmods.mjutils.common.objects.ModInitModule;
import mjaroslav.mcmods.realisticbrewingstand.RBSInfo;
import mjaroslav.mcmods.realisticbrewingstand.common.block.BlockFixedStand;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileFixedStand;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemReed;

@ModInitModule(modid = RBSInfo.MODID)
public class RBSBlocks implements IModModule {
	public static Block fixedBrewingStand = new BlockFixedStand().setHardness(0.5F).setLightLevel(0.125F)
	    .setBlockName("brewingStand").setBlockTextureName("brewing_stand");

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
		GameRegistry.registerBlock(fixedBrewingStand, "fixed_brewing_stand");
		GameRegistry.registerTileEntity(TileFixedStand.class, "tile_fixed_brewing_stand");
		((ItemReed) Items.brewing_stand).field_150935_a = fixedBrewingStand;
	}

	@Override
	public void init(FMLInitializationEvent event) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
	}
}
