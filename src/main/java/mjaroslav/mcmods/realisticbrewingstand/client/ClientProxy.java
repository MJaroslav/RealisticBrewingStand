package mjaroslav.mcmods.realisticbrewingstand.client;

import mjaroslav.mcmods.realisticbrewingstand.client.render.tileentity.TileFixedStandRenderer;
import mjaroslav.mcmods.realisticbrewingstand.common.CommonProxy;
import mjaroslav.mcmods.realisticbrewingstand.hook.HookConfig;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void init(FMLInitializationEvent event) {
        if (HookConfig.vanilla())
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBrewingStand.class, new TileFixedStandRenderer());
    }
}
