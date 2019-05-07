package mjaroslav.mcmods.realisticbrewingstand.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileFixedStandRenderer extends TileEntitySpecialRenderer<TileEntityBrewingStand> {
    private void renderPotion(int slot, ItemStack slotStack) {
        if (slotStack == null)
            return;
        EntityItem slotEntity = new EntityItem(null, 0, 0, 0, slotStack);
        slotEntity.hoverStart = 0F;
        GL11.glPushMatrix();
        GL11.glTranslated(0.0, -0.85, 0.205);
        switch (slot) {
            case 0:
                GL11.glRotated(-45, 0, 1, 0);
                GL11.glTranslated(-0.415, 0, 0);
                break;
            case 1:
                GL11.glRotated(45, 0, 1, 0);
                GL11.glTranslated(-0.415, 0, 0);
                break;
            case 2:
                GL11.glTranslated(0.265, 0, 0);
            default:
                break;
        }
        Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(slotEntity, 0, 0, 0, 0, 0);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntityBrewingStand tileEntity, double x, double y, double z, float partialTicks, int destroyStage) {
        GL11.glPushMatrix();
        boolean cache = Minecraft.getMinecraft().gameSettings.fancyGraphics;
        Minecraft.getMinecraft().gameSettings.fancyGraphics = true;
//            RenderItem.renderInFrame = true;
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1F, (float) z + 0.3F);
        for (int i = 0; i < 3; i++)
            renderPotion(i, tileEntity.getStackInSlot(i));
        GL11.glPopMatrix();
//            RenderItem.renderInFrame = false;
        Minecraft.getMinecraft().gameSettings.fancyGraphics = cache;
    }
}
