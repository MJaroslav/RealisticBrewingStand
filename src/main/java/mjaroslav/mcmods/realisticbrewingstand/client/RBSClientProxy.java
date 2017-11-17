package mjaroslav.mcmods.realisticbrewingstand.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import mjaroslav.mcmods.realisticbrewingstand.client.render.tileentity.TileFixedStandRenderer;
import mjaroslav.mcmods.realisticbrewingstand.common.RBSCommonProxy;
import mjaroslav.mcmods.realisticbrewingstand.common.integration.EtFuturumIntegration;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileFixedStand;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileFixedStandEtFuturum;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class RBSClientProxy extends RBSCommonProxy {
	@Override
	public Minecraft getMinecraft() {
		return Minecraft.getMinecraft();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		ClientRegistry.bindTileEntitySpecialRenderer(TileFixedStand.class, new TileFixedStandRenderer());
		if (EtFuturumIntegration.isExist())
			ClientRegistry.bindTileEntitySpecialRenderer(TileFixedStandEtFuturum.class, new TileFixedStandRenderer());
	}

	@Override
	public EntityPlayer getEntityPlayer(MessageContext arg0) {
		return arg0.side.equals(Side.CLIENT) ? getMinecraft().thePlayer : super.getEntityPlayer(arg0);
	}
}
