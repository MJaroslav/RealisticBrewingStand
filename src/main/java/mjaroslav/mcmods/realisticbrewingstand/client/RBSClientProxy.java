package mjaroslav.mcmods.realisticbrewingstand.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import mjaroslav.mcmods.realisticbrewingstand.client.render.tileentity.TileFixedBrewingStandRenderer;
import mjaroslav.mcmods.realisticbrewingstand.common.RBSCommonProxy;
import mjaroslav.mcmods.realisticbrewingstand.common.integration.EtFuturumIntegration;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileEntityFixedBrewingStand;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileEntityFixedBrewingStandEF;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class RBSClientProxy extends RBSCommonProxy {
	@Override
	public void init(FMLInitializationEvent arg0) {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFixedBrewingStand.class,
		    new TileFixedBrewingStandRenderer());
		if (EtFuturumIntegration.isExist())
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFixedBrewingStandEF.class,
			    new TileFixedBrewingStandRenderer());
	}

	@Override
	public Minecraft getMinecraft() {
		return Minecraft.getMinecraft();
	}

	@Override
	public EntityPlayer getEntityPlayer(MessageContext arg0) {
		return arg0.side.equals(Side.CLIENT) ? getMinecraft().thePlayer : super.getEntityPlayer(arg0);
	}
}
