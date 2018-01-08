package mjaroslav.mcmods.realisticbrewingstand.common;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import mjaroslav.mcmods.mjutils.common.objects.ProxyBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class RBSCommonProxy extends ProxyBase {
    public static int fixedBrewingStandRI = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void init(FMLInitializationEvent event) {
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
    }

    @Override
    public EntityPlayer getEntityPlayer(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity;
    }

    @Override
    public Minecraft getMinecraft() {
        return null;
    }

    @Override
    public void spawnParticle(String name, double x, double y, double z, Object... args) {
    }
}
