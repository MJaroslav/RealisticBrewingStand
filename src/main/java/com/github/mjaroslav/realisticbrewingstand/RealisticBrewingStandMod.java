package com.github.mjaroslav.realisticbrewingstand;

import com.github.mjaroslav.realisticbrewingstand.config.ModConfig;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class RealisticBrewingStandMod implements ModInitializer {
    private static ConfigHolder<ModConfig> configHolder;

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        configHolder = AutoConfig.getConfigHolder(ModConfig.class);
    }

    public static ConfigHolder<ModConfig> getConfigHolder() {
        return configHolder;
    }

    public static ModConfig getConfig() {
        return getConfigHolder().getConfig();
    }
}
