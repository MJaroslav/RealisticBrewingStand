package mjaroslav.mcmods.realisticbrewingstand.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mjaroslav.mcmods.realisticbrewingstand.common.tileentity.TileEntityFixedBrewingStand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class TileFixedBrewingStandRenderer extends TileEntitySpecialRenderer {
	TileEntityFixedBrewingStand tile;
	ItemStack slot0;
	ItemStack slot1;
	ItemStack slot2;
	private boolean cache;

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float size) {

		if (tileentity instanceof TileEntityFixedBrewingStand) {
			this.tile = (TileEntityFixedBrewingStand) tileentity;
			this.slot0 = this.tile.getStackInSlot(0);
			this.slot1 = this.tile.getStackInSlot(1);
			this.slot2 = this.tile.getStackInSlot(2);
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.0F, (float) z + 0.3F);
			renderPotion(0, this.slot0);
			renderPotion(1, this.slot1);
			renderPotion(2, this.slot2);
			GL11.glPopMatrix();
		}
	}

	private void renderPotion(int slot, ItemStack slotStack) {
		if (slotStack == null)
			return;
		EntityItem slotEntity = new EntityItem(null, 0.0D, 0.0D, 0.0D, slotStack);
		slotEntity.hoverStart = 0.0F;
		RenderItem.renderInFrame = true;
		this.cache = Minecraft.getMinecraft().gameSettings.fancyGraphics;
		Minecraft.getMinecraft().gameSettings.fancyGraphics = true;
		GL11.glPushMatrix();
		GL11.glTranslated(0.0, 0, 0.205);
		switch (slot) {
		case 0: {
			GL11.glRotated(-60, 0, 1, 0);
			GL11.glTranslated(-0.3, -0.85, 0);
		}
			break;
		case 1: {
			GL11.glRotated(60, 0, 1, 0);
			GL11.glTranslated(-0.3, -0.85, 0);
		}
			break;
		case 2: {
			GL11.glTranslated(0.265, -0.85, 0);
		}
		default:
			break;
		}
		RenderManager.instance.renderEntityWithPosYaw(slotEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		RenderItem.renderInFrame = false;
		GL11.glPopMatrix();
		Minecraft.getMinecraft().gameSettings.fancyGraphics = false;
	}
}
