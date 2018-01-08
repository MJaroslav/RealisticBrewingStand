package mjaroslav.mcmods.realisticbrewingstand;

import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.minecraft.HookLoader;
import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.minecraft.PrimaryClassTransformer;

public class RBSHookLoader extends HookLoader {
    @Override
    public String[] getASMTransformerClass() {
        return new String[] { PrimaryClassTransformer.class.getName() };
    }

    @Override
    protected void registerHooks() {
        registerHookContainer("mjaroslav.mcmods.realisticbrewingstand.RBSHooks");
        registerHookContainer("mjaroslav.mcmods.realisticbrewingstand.RBSHooksEtFuturum");
        registerHookContainer("mjaroslav.mcmods.realisticbrewingstand.RBSHooksHEE");
    }
}
