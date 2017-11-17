package mjaroslav.mcmods.realisticbrewingstand.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import mjaroslav.mcmods.realisticbrewingstand.common.inventory.ContainerFixedBrewingStand;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileFixedStand;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiFixedStand extends GuiContainer {
	public static final ResourceLocation brewingStandGuiTextures = new ResourceLocation(
	    "textures/gui/container/brewing_stand.png");
	private TileFixedStand stand;

	public GuiFixedStand(InventoryPlayer player, TileFixedStand tile) {
		super(new ContainerFixedBrewingStand(player, tile));
		stand = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		String text = stand.hasCustomInventoryName() ? stand.getInventoryName()
		    : StatCollector.translateToLocal(stand.getInventoryName());
		fontRendererObj.drawString(text, xSize / 2 - fontRendererObj.getStringWidth(text) / 2, 6, 0x404040);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float size, int x, int y) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(brewingStandGuiTextures);
		int xZero = (width - xSize) / 2, yZero = (height - ySize) / 2;
		drawTexturedModalRect(xZero, yZero, 0, 0, xSize, ySize);
		int scale = 0, brewTime = stand.getBrewTime();
		if (brewTime > 0) {
			scale = (int) (28F * (1F - brewTime / 400F));
			if (scale > 0)
				drawTexturedModalRect(xZero + 97, yZero + 16, 176, 0, 9, scale);
			switch (brewTime / 2 % 7) {
			case 0:
				scale = 29;
				break;
			case 1:
				scale = 24;
				break;
			case 2:
				scale = 20;
				break;
			case 3:
				scale = 16;
				break;
			case 4:
				scale = 11;
				break;
			case 5:
				scale = 6;
				break;
			case 6:
				scale = 0;
			}
			if (scale > 0)
				drawTexturedModalRect(xZero + 65, yZero + 43 - scale, 185, 29 - scale, 12, scale);
		}
	}
}
