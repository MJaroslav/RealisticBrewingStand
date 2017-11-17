package mjaroslav.mcmods.realisticbrewingstand.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import mjaroslav.mcmods.realisticbrewingstand.common.inventory.ContainerFixedStandEtFuturum;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileFixedStandEtFuturum;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFixedStandEtFuturum extends GuiContainer {
	public static final ResourceLocation etFuturumStandGui = new ResourceLocation(
	    "etfuturum:textures/gui/container/brewing_stand.png");
	private TileFixedStandEtFuturum stand;

	public GuiFixedStandEtFuturum(InventoryPlayer player, TileFixedStandEtFuturum tile) {
		super(new ContainerFixedStandEtFuturum(player, tile));
		this.stand = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		String text = this.stand.hasCustomInventoryName() ? this.stand.getInventoryName()
		    : I18n.format(this.stand.getInventoryName(), new Object[0]);
		fontRendererObj.drawString(text, xSize / 2 - fontRendererObj.getStringWidth(text) / 2, 6, 0x404040);
		fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 94, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float size, int x, int y) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(etFuturumStandGui);
		int xZero = (width - xSize) / 2, yZero = (height - ySize) / 2;
		drawTexturedModalRect(xZero, yZero, 0, 0, xSize, ySize);
		int scale = 0, brewTime = stand.getBrewTime(), fuel = stand.getFuel();
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
				drawTexturedModalRect(xZero + 63, yZero + 43 - scale, 185, 29 - scale, 12, scale);
		}
		if (fuel > 0) {
			scale = (int) (18F * fuel / stand.getCurrentFuel());
			drawTexturedModalRect(xZero + 60, yZero + 44, 176, 29, scale, 4);
		}
	}
}
