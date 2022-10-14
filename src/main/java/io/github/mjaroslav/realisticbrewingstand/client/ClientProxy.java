package io.github.mjaroslav.realisticbrewingstand.client;

import chylex.hee.tileentity.TileEntityEnhancedBrewingStand;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import ganymedes01.etfuturum.tileentities.TileEntityNewBrewingStand;
import io.github.mjaroslav.realisticbrewingstand.client.render.tileentity.TileFixedStandRenderer;
import io.github.mjaroslav.realisticbrewingstand.common.CommonProxy;
import net.minecraft.tileentity.TileEntityBrewingStand;
import org.jetbrains.annotations.NotNull;

public class ClientProxy extends CommonProxy {
    @Override
    public void init(@NotNull FMLInitializationEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBrewingStand.class, new TileFixedStandRenderer());
        if (Loader.isModLoaded("etfuturum"))
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNewBrewingStand.class,
                    new TileFixedStandRenderer());
        if (Loader.isModLoaded("HardcoreEnderExpansion"))
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnhancedBrewingStand.class,
                    new TileFixedStandRenderer());
    }
}
