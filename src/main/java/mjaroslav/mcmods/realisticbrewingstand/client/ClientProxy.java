package mjaroslav.mcmods.realisticbrewingstand.client;

import chylex.hee.tileentity.TileEntityEnhancedBrewingStand;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import ganymedes01.etfuturum.tileentities.TileEntityNewBrewingStand;
import mjaroslav.mcmods.realisticbrewingstand.client.render.tileentity.TileFixedStandRenderer;
import mjaroslav.mcmods.realisticbrewingstand.common.CommonProxy;
import mjaroslav.mcmods.realisticbrewingstand.hook.HookConfig;
import net.minecraft.tileentity.TileEntityBrewingStand;

public class ClientProxy extends CommonProxy {
    @Override
    public void init(FMLInitializationEvent event) {
        if (HookConfig.vanilla())
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBrewingStand.class, new TileFixedStandRenderer());
        else {
            if (HookConfig.etFuturum() && Loader.isModLoaded("etfuturum"))
                ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNewBrewingStand.class,
                        new TileFixedStandRenderer());
            if (HookConfig.hee() && Loader.isModLoaded("HardcoreEnderExpansion"))
                ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnhancedBrewingStand.class,
                        new TileFixedStandRenderer());
        }
    }
}
