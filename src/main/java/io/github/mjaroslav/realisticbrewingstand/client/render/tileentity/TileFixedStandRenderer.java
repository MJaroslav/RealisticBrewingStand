package io.github.mjaroslav.realisticbrewingstand.client.render.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.val;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import static org.lwjgl.opengl.GL11.*;

@SideOnly(Side.CLIENT)
public class TileFixedStandRenderer extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float size) {
        if (tileEntity instanceof IInventory inventory) {
            glPushMatrix();
            val cache = Minecraft.getMinecraft().gameSettings.fancyGraphics;
            Minecraft.getMinecraft().gameSettings.fancyGraphics = true;
            RenderItem.renderInFrame = true;
            glTranslatef((float) x + 0.5F, (float) y + 1F, (float) z + 0.3F);
            for (var i = 0; i < 3; i++) renderPotion(i, inventory.getStackInSlot(i));
            glPopMatrix();
            RenderItem.renderInFrame = false;
            Minecraft.getMinecraft().gameSettings.fancyGraphics = cache;
        }
    }

    private void renderPotion(int slot, ItemStack slotStack) {
        if (slotStack == null) return;
        val slotEntity = new EntityItem(null, 0, 0, 0, slotStack);
        slotEntity.hoverStart = 0F;
        glPushMatrix();
        glTranslated(0.0, 0, 0.205);
        switch (slot) {
            case 0 -> {
                glRotated(-60, 0, 1, 0);
                glTranslated(-0.3, -0.85, 0);
            }
            case 1 -> {
                glRotated(60, 0, 1, 0);
                glTranslated(-0.3, -0.85, 0);
            }
            case 2 -> glTranslated(0.265, -0.85, 0);
        }
        RenderManager.instance.renderEntityWithPosYaw(slotEntity, 0, 0, 0, 0, 0);
        glPopMatrix();
    }
}
