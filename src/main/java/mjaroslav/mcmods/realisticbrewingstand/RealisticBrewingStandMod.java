package mjaroslav.mcmods.realisticbrewingstand;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import mjaroslav.mcmods.mjutils.common.objects.ModInitHandler;
import mjaroslav.mcmods.realisticbrewingstand.client.gui.GuiHandler;
import mjaroslav.mcmods.realisticbrewingstand.common.RBSCommonProxy;
import mjaroslav.mcmods.realisticbrewingstand.common.event.PlayerEvents;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = RealisticBrewingStandMod.MODID, name = RealisticBrewingStandMod.NAME, version = RealisticBrewingStandMod.VERSION, dependencies = RealisticBrewingStandMod.DEPENDENCIES)
public class RealisticBrewingStandMod {
	public static final String MODID = "realisticbrewingstand";
	public static final String NAME = "Realistic Brewing Stand";
	public static final String VERSION = "1.1.0";
	public static final String CLIENTPROXY = "mjaroslav.mcmods.realisticbrewingstand.client.RBSClientProxy";
	public static final String COMMONPROXY = "mjaroslav.mcmods.realisticbrewingstand.common.RBSCommonProxy";
	public static final String DEPENDENCIES = "after:etfuturum;";

	@SidedProxy(serverSide = COMMONPROXY, clientSide = CLIENTPROXY)
	public static RBSCommonProxy proxy;

	@Instance(MODID)
	public static RealisticBrewingStandMod instance;

	public static ModInitHandler initHandler = new ModInitHandler(MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		initHandler.preInit(event);
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		initHandler.init(event);
		proxy.init(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		initHandler.postInit(event);
		proxy.postInit(event);
		MinecraftForge.EVENT_BUS.register(new PlayerEvents());
	}

	@EventHandler
	public void constr(FMLConstructionEvent event) {
		initHandler.findModules(event);
	}
}
