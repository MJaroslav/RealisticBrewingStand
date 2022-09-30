package com.github.mjaroslav.realisticbrewingstand.client;

import com.github.mjaroslav.realisticbrewingstand.client.render.block.entity.BrewingStandBlockEntityRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.block.entity.BlockEntityType;

@Environment(value = EnvType.CLIENT)
public class RealisticBrewingStandClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(BlockEntityType.BREWING_STAND,
                context -> new BrewingStandBlockEntityRenderer(context.getItemRenderer()));
    }
}
