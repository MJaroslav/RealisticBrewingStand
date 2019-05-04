package mjaroslav.mcmods.realisticbrewingstand;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import mjaroslav.mcmods.realisticbrewingstand.common.CommonProxy;
import mjaroslav.mcmods.realisticbrewingstand.lib.ModInfo;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCIES)
public class RealisticBrewingStandMod {
    @SidedProxy(serverSide = ModInfo.COMMON_PROXY, clientSide = ModInfo.CLIENT_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}
