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
	public void init(FMLInitializationEvent arg0) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent arg0) {
	}

	@Override
	public void preInit(FMLPreInitializationEvent arg0) {
	}

	@Override
	public EntityPlayer getEntityPlayer(MessageContext arg0) {
		return arg0.getServerHandler().playerEntity;
	}

	@Override
	public Minecraft getMinecraft() {
		return null;
	}

	@Override
	public void spawnParticle(String arg0, double arg1, double arg2, double arg3, Object... arg4) {
	}
}
