package mjaroslav.mcmods.realisticbrewingstand.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class TileFixedStandRenderer extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float size) {
        if (tileEntity instanceof IInventory) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1F, (float) z + 0.3F);
            for (int i = 0; i < 3; i++)
                renderPotion(i, ((IInventory) tileEntity).getStackInSlot(i));
            GL11.glPopMatrix();
        }
    }

    private void renderPotion(int slot, ItemStack slotStack) {
        if (slotStack == null)
            return;
        EntityItem slotEntity = new EntityItem(null, 0, 0, 0, slotStack);
        slotEntity.hoverStart = 0F;
        boolean cache = Minecraft.getMinecraft().gameSettings.fancyGraphics;
        Minecraft.getMinecraft().gameSettings.fancyGraphics = true;
        RenderItem.renderInFrame = true;
        GL11.glPushMatrix();
        GL11.glTranslated(0.0, 0, 0.205);
        switch (slot) {
        case 0:
            GL11.glRotated(-60, 0, 1, 0);
            GL11.glTranslated(-0.3, -0.85, 0);
            break;
        case 1:
            GL11.glRotated(60, 0, 1, 0);
            GL11.glTranslated(-0.3, -0.85, 0);
            break;
        case 2:
            GL11.glTranslated(0.265, -0.85, 0);
        default:
            break;
        }
        RenderManager.instance.renderEntityWithPosYaw(slotEntity, 0, 0, 0, 0, 0);
        GL11.glPopMatrix();
        RenderItem.renderInFrame = false;
        Minecraft.getMinecraft().gameSettings.fancyGraphics = cache;
    }
}
