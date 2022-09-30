package com.github.mjaroslav.realisticbrewingstand.client.render.block.entity;

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
    private static final float[] degresses = new float[] { -45, 45, 0 };
    private static final double[] offsets = new double[] { -0.321, -0.325, 0.265 };

    private final ItemRenderer itemRenderer;

    public BrewingStandBlockEntityRenderer(ItemRenderer itemRenderer) {
        this.itemRenderer = itemRenderer;
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
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(degresses[slot]));
        matrices.translate(offsets[slot], 0, 0);
        itemRenderer.renderItem(stack, Mode.GROUND, light, overlay, matrices, consumer, 0);
        matrices.pop();
    }
}
