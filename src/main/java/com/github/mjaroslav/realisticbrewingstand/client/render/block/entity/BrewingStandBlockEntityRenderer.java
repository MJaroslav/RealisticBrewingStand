package com.github.mjaroslav.realisticbrewingstand.client.render.block.entity;

import com.github.mjaroslav.realisticbrewingstand.RealisticBrewingStandMod;
import com.github.mjaroslav.realisticbrewingstand.config.ModConfig.BottlePosition;
import com.github.mjaroslav.realisticbrewingstand.util.Utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation.Mode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;

@Environment(value = EnvType.CLIENT)
public class BrewingStandBlockEntityRenderer implements BlockEntityRenderer<BrewingStandBlockEntity> {
    private final ItemRenderer itemRenderer;

    public BrewingStandBlockEntityRenderer(ItemRenderer itemRenderer) {
        this.itemRenderer = itemRenderer;
    }

    @Override
    public int getRenderDistance() {
        return RealisticBrewingStandMod.getConfig().renderDistance;
    }

    @Override
    public void render(BrewingStandBlockEntity tile, float partialTicks, MatrixStack matrices,
            VertexConsumerProvider consumer,
            int light, int overlay) {
        matrices.push();
        matrices.translate(0.5, 0.22, 0.5);
        for (var i = 0; i < 3; i++)
            renderSlot(i, tile.getStack(i), matrices, consumer, light, overlay);
        matrices.pop();
    }

    private void renderSlot(int slot, ItemStack stack, MatrixStack matrices, VertexConsumerProvider consumer,
            int light, int overlay) {
        matrices.push();
        BottlePosition pos = Utils.getPositionBySlot(slot);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(pos.rotation));
        matrices.translate(pos.x, pos.y, pos.z);
        itemRenderer.renderItem(stack, Mode.GROUND, light, overlay, matrices, consumer, 0);
        matrices.pop();
    }
}
