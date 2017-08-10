package mjaroslav.mcmods.realisticbrewingstand.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import mjaroslav.mcmods.realisticbrewingstand.common.inventory.ContainerFixedBrewingStand;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileEntityFixedBrewingStand;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFixedBrewingStand extends GuiContainer {
	private static final ResourceLocation brewingStandGuiTextures = new ResourceLocation(
			"textures/gui/container/brewing_stand.png");
	private TileEntityFixedBrewingStand tileFixedBrewingStand;

	public GuiFixedBrewingStand(InventoryPlayer player, TileEntityFixedBrewingStand tile) {
		super(new ContainerFixedBrewingStand(player, tile));
		this.tileFixedBrewingStand = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		String s = this.tileFixedBrewingStand.hasCustomInventoryName() ? this.tileFixedBrewingStand.getInventoryName()
				: I18n.format(this.tileFixedBrewingStand.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2,
				4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float size, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(brewingStandGuiTextures);
		int xZero = (this.width - this.xSize) / 2;
		int yZero = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(xZero, yZero, 0, 0, this.xSize, this.ySize);
		int brewTime = this.tileFixedBrewingStand.getBrewTime();
		if (brewTime > 0) {
			int scale = (int) (28.0F * (1.0F - (float) brewTime / 400.0F));

			if (scale > 0) {
				this.drawTexturedModalRect(xZero + 97, yZero + 16, 176, 0, 9, scale);
			}
			int scale1 = brewTime / 2 % 7;
			switch (scale1) {
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
			if (scale > 0) {
				this.drawTexturedModalRect(xZero + 65, yZero + 14 + 29 - scale, 185, 29 - scale, 12, scale);
			}
		}
	}
}
