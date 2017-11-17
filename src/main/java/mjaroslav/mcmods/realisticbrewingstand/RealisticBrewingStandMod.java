package mjaroslav.mcmods.realisticbrewingstand;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import mjaroslav.mcmods.mjutils.common.objects.ModInitHandler;
import mjaroslav.mcmods.realisticbrewingstand.common.RBSCommonProxy;

@Mod(modid = RBSInfo.MODID, name = RBSInfo.NAME, version = RBSInfo.VERSION, dependencies = RBSInfo.DEPENDENCIES)
public class RealisticBrewingStandMod {
	@SidedProxy(serverSide = RBSInfo.COMMONPROXY, clientSide = RBSInfo.CLIENTPROXY)
	public static RBSCommonProxy proxy;

	@Instance(RBSInfo.MODID)
	public static RealisticBrewingStandMod instance;

	public static ModInitHandler initHandler = new ModInitHandler(RBSInfo.MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		initHandler.preInit(event);
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		initHandler.init(event);
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		initHandler.postInit(event);
		proxy.postInit(event);
	}

	@EventHandler
	public void constr(FMLConstructionEvent event) {
		initHandler.findModules(event);
	}
}
