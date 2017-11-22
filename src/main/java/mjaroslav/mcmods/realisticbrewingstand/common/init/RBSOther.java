package mjaroslav.mcmods.realisticbrewingstand.common.init;

import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import mjaroslav.mcmods.mjutils.common.objects.IModModule;
import mjaroslav.mcmods.mjutils.common.objects.ModInitModule;
import mjaroslav.mcmods.realisticbrewingstand.RBSInfo;
import mjaroslav.mcmods.realisticbrewingstand.RealisticBrewingStandMod;
import mjaroslav.mcmods.realisticbrewingstand.client.gui.GuiHandler;
import mjaroslav.mcmods.realisticbrewingstand.common.event.PlayerEvents;
import net.minecraftforge.common.MinecraftForge;

@ModInitModule(modid = RBSInfo.MODID)
public class RBSOther implements IModModule {
	@Override
	public String getModuleName() {
		return "Other";
	}

	@Override
	public int getPriority() {
		return 2;
	}

	@Override
	public void preInit(FMLPreInitializationEvent arg0) {
	}

	@Override
	public void init(FMLInitializationEvent arg0) {
		NetworkRegistry.INSTANCE.registerGuiHandler(RealisticBrewingStandMod.instance, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerEvents());
	}

	@Override
	public void postInit(FMLPostInitializationEvent arg0) {
	}
}
