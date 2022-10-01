package com.github.mjaroslav.realisticbrewingstand.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "realisticbrewingstand")
public class ModConfig implements ConfigData {
    @Comment("Potion render distance in blocks")
    public int renderDistance = 64;

    @CollapsibleObject
    public BottlePosition bottle1 = new BottlePosition(-45, -0.321, 0, 0);
    @CollapsibleObject
    public BottlePosition bottle2 = new BottlePosition(45, -0.325, 0, 0);
    @CollapsibleObject
    public BottlePosition bottle3 = new BottlePosition(0, 0.265, 0, 0);

    public static class BottlePosition {
        @Comment("Rotation in degrees for Y. Rotatio1n calls before position!")
        public float rotation;
        public double x;
        public double y;
        public double z;

        public BottlePosition(float rotation, double x, double y, double z) {
            this.rotation = rotation;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
