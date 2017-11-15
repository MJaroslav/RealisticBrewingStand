package mjaroslav.mcmods.realisticbrewingstand.common.init;

import cpw.mods.fml.common.event.*;
import mjaroslav.mcmods.mjutils.common.objects.IModModule;
import mjaroslav.mcmods.mjutils.common.objects.ModInitModule;
import mjaroslav.mcmods.realisticbrewingstand.RealisticBrewingStandMod;
import mjaroslav.mcmods.realisticbrewingstand.common.integration.EtFuturumIntegration;

@ModInitModule(modid = RealisticBrewingStandMod.MODID)
public class RBSIntegration implements IModModule {
	@Override
	public String getModuleName() {
		return "Integration";
	}

	@Override
	public int getPriority() {
		return 1;
	}

	@Override
	public void preInit(FMLPreInitializationEvent arg0) {
		EtFuturumIntegration.checkExist();
		EtFuturumIntegration.registerBlock();
	}

	@Override
	public void init(FMLInitializationEvent arg0) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent arg0) {
	}
}
