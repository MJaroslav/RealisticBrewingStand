package mjaroslav.mcmods.realisticbrewingstand;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import mjaroslav.mcmods.realisticbrewingstand.client.gui.GuiHandler;
import mjaroslav.mcmods.realisticbrewingstand.common.CommonProxy;
import mjaroslav.mcmods.realisticbrewingstand.common.block.BlockFixedBrewingStand;
import mjaroslav.mcmods.realisticbrewingstand.common.event.PlayerEvents;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileEntityFixedBrewingStand;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemReed;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = RealisticBrewingStandMod.MODID, name = RealisticBrewingStandMod.NAME, version = RealisticBrewingStandMod.VERSION)
public class RealisticBrewingStandMod {
	public static final String MODID = "realisticbrewingstand";
	public static final String NAME = "Realistic Brewing Stand";
	public static final String VERSION = "1.7.10-1";
	public static final String CLIENTPROXY = "mjaroslav.mcmods.realisticbrewingstand.client.ClientProxy";
	public static final String COMMONPROXY = "mjaroslav.mcmods.realisticbrewingstand.common.CommonProxy";
	public static int fixedBrewingStandRI = RenderingRegistry.getNextAvailableRenderId();
	
	@SidedProxy(serverSide = COMMONPROXY, clientSide = CLIENTPROXY)
	public static CommonProxy proxy;

	@Instance(MODID)
	public static RealisticBrewingStandMod instance;

	public static Block fixedBrewingStand = new BlockFixedBrewingStand().setHardness(0.5F).setLightLevel(0.125F)
			.setBlockName("brewingStand").setBlockTextureName("brewing_stand");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		instance = this;
		GameRegistry.registerBlock(fixedBrewingStand, "fixed_brewing_stand");
		GameRegistry.registerTileEntity(TileEntityFixedBrewingStand.class, "tile_fixed_entity_brewing_stand");
		((ItemReed) Items.brewing_stand).field_150935_a = fixedBrewingStand;
	}

	@EventHandler
	public void init(FMLInitializationEvent vent) {
		proxy.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new PlayerEvents());
	}
}
