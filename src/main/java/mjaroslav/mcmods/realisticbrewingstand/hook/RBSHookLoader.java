package mjaroslav.mcmods.realisticbrewingstand.hook;

import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.minecraft.HookLoader;
import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.minecraft.PrimaryClassTransformer;

public class RBSHookLoader extends HookLoader {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{PrimaryClassTransformer.class.getName()};
    }

    @Override
    protected void registerHooks() {
        if (HookConfig.vanilla())
            registerHookContainer(VanillaHooks.class.getName());
        if (HookConfig.etFuturum())
            registerHookContainer(EtFuturumHooks.class.getName());
        if (HookConfig.hee())
            registerHookContainer(HEEHooks.class.getName());
    }
}
