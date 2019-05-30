package mjaroslav.mcmods.realisticbrewingstand.hook;

import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.minecraft.HookLoader;
import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.minecraft.PrimaryClassTransformer;
import mjaroslav.mcmods.realisticbrewingstand.lib.ModInfo;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name(ModInfo.NAME)
@IFMLLoadingPlugin.MCVersion(ModInfo.MC_VERSION)
public class RBSHookLoader extends HookLoader {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{PrimaryClassTransformer.class.getName()};
    }

    @Override
    protected void registerHooks() {
        if (HookConfig.vanilla())
            registerHookContainer(VanillaHooks.class.getName());
    }
}
