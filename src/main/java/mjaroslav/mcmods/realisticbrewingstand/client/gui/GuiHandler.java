package mjaroslav.mcmods.realisticbrewingstand.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import mjaroslav.mcmods.realisticbrewingstand.client.gui.inventory.GuiFixedBrewingStand;
import mjaroslav.mcmods.realisticbrewingstand.client.gui.inventory.GuiFixedBrewingStandEF;
import mjaroslav.mcmods.realisticbrewingstand.common.inventory.ContainerFixedBrewingStand;
import mjaroslav.mcmods.realisticbrewingstand.common.inventory.ContainerFixedBrewingStandEF;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileEntityFixedBrewingStand;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileEntityFixedBrewingStandEF;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (ID == 0 && tile instanceof TileEntityFixedBrewingStand)
			return new ContainerFixedBrewingStand(player.inventory, (TileEntityFixedBrewingStand) tile);
		if (ID == 1 && tile instanceof TileEntityFixedBrewingStandEF)
			return new ContainerFixedBrewingStandEF(player.inventory, (TileEntityFixedBrewingStandEF) tile);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (ID == 0 && tile instanceof TileEntityFixedBrewingStand)
			return new GuiFixedBrewingStand(player.inventory, (TileEntityFixedBrewingStand) tile);
		if (ID == 1 && tile instanceof TileEntityFixedBrewingStandEF)
			return new GuiFixedBrewingStandEF(player.inventory, (TileEntityFixedBrewingStandEF) tile);
		return null;
	}
}
