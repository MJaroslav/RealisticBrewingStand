package mjaroslav.mcmods.realisticbrewingstand.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import mjaroslav.mcmods.realisticbrewingstand.client.gui.inventory.GuiFixedStand;
import mjaroslav.mcmods.realisticbrewingstand.client.gui.inventory.GuiFixedStandEtFuturum;
import mjaroslav.mcmods.realisticbrewingstand.common.inventory.ContainerFixedBrewingStand;
import mjaroslav.mcmods.realisticbrewingstand.common.inventory.ContainerFixedStandEtFuturum;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileFixedStand;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileFixedStandEtFuturum;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile == null)
			return null;
		if (ID == 0 && tile instanceof TileFixedStand)
			return new ContainerFixedBrewingStand(player.inventory, (TileFixedStand) tile);
		if (ID == 1 && tile instanceof TileFixedStandEtFuturum)
			return new ContainerFixedStandEtFuturum(player.inventory, (TileFixedStandEtFuturum) tile);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile == null)
			return null;
		if (ID == 0 && tile instanceof TileFixedStand)
			return new GuiFixedStand(player.inventory, (TileFixedStand) tile);
		if (ID == 1 && tile instanceof TileFixedStandEtFuturum)
			return new GuiFixedStandEtFuturum(player.inventory, (TileFixedStandEtFuturum) tile);
		return null;
	}
}
