package mjaroslav.mcmods.realisticbrewingstand.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import mjaroslav.mcmods.realisticbrewingstand.client.render.tileentity.TileFixedStandRenderer;
import mjaroslav.mcmods.realisticbrewingstand.common.RBSCommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityBrewingStand;

public class RBSClientProxy extends RBSCommonProxy {
    @Override
    public void init(FMLInitializationEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBrewingStand.class, new TileFixedStandRenderer());
        // if (Loader.isModLoaded("etfuturum"))
        // ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNewBrewingStand.class,
        // new TileFixedStandRenderer());
    }

    @Override
    public Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }

    @Override
    public EntityPlayer getEntityPlayer(MessageContext ctx) {
        return ctx.side == Side.CLIENT ? getMinecraft().thePlayer : super.getEntityPlayer(ctx);
    }
}
