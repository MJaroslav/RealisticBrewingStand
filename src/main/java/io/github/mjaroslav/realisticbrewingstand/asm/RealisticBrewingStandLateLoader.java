package io.github.mjaroslav.realisticbrewingstand.asm;

import cpw.mods.fml.common.Loader;
import io.github.tox1cozz.mixinbooterlegacy.ILateMixinLoader;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class RealisticBrewingStandLateLoader implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return Arrays.asList("mixin.realisticbrewingstand.etfuturum.json",
                "mixin.realisticbrewingstand.HardcoreEnderExpansion.json");
    }

    @Override
    public boolean shouldMixinConfigQueue(@NotNull String mixinConfig) {
        if (mixinConfig.equals("mixin.realisticbrewingstand.etfuturum.json"))
            return Loader.isModLoaded("etfuturum");
        if (mixinConfig.equals("mixin.realisticbrewingstand.HardcoreEnderExpansion.json"))
            return Loader.isModLoaded("HardcoreEnderExpansion");
        return false;
    }
}
