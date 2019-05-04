package mjaroslav.mcmods.realisticbrewingstand.lib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModInfo {
    public static final String MOD_ID = "realisticbrewingstand";
    public static final String NAME = "Realistic Brewing Stand";
    public static final String VERSION = "1.3.0";
    public static final String CLIENT_PROXY = "mjaroslav.mcmods.realisticbrewingstand.client.ClientProxy";
    public static final String COMMON_PROXY = "mjaroslav.mcmods.realisticbrewingstand.common.CommonProxy";
    public static final String DEPENDENCIES = "after:etfuturum;after:HardcoreEnderExpansion;";

    public static final Logger LOG = LogManager.getLogger(NAME);
}
