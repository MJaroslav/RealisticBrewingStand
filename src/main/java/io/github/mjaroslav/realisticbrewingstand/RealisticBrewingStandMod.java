package io.github.mjaroslav.realisticbrewingstand;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import io.github.mjaroslav.realisticbrewingstand.common.CommonProxy;
import io.github.mjaroslav.realisticbrewingstand.lib.ModInfo;
import org.jetbrains.annotations.NotNull;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCIES)
public class RealisticBrewingStandMod {
    @SidedProxy(serverSide = ModInfo.COMMON_PROXY, clientSide = ModInfo.CLIENT_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void init(@NotNull FMLInitializationEvent event) {
        proxy.init(event);
    }
}
