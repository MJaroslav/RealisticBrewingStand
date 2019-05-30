package mjaroslav.mcmods.realisticbrewingstand.lib;

import mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.asm.HookLogger;

public class Log4jHookLogger implements HookLogger {
    @Override
    public void debug(String message) {
        ModInfo.LOG.debug(message);
    }

    @Override
    public void warning(String message) {
        ModInfo.LOG.warn(message);
    }

    @Override
    public void severe(String message) {
        ModInfo.LOG.fatal(message);
    }

    @Override
    public void severe(String message, Throwable cause) {
        ModInfo.LOG.error(message, cause);
    }
}
