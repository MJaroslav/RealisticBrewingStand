package mjaroslav.mcmods.realisticbrewingstand;

import mjaroslav.mcmods.realisticbrewingstand.common.CommonProxy;
import mjaroslav.mcmods.realisticbrewingstand.lib.ModInfo;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class RealisticBrewingStandMod {
    @SidedProxy(serverSide = ModInfo.COMMON_PROXY, clientSide = ModInfo.CLIENT_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}
