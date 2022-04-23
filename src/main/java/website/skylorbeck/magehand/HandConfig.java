package website.skylorbeck.magehand;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.ArrayList;

import static website.skylorbeck.magehand.Declarar.MODID;

@Config(name = "Magehand")
public class HandConfig implements ConfigData {

    @ConfigEntry.BoundedDiscrete(min = 1, max = 4)
    public int minGroupSize = 1;
    @ConfigEntry.BoundedDiscrete(min = 4, max = 10)
    public int maxGroupSize = 4;

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public spawnStuff spawnStuff = new spawnStuff();

    static class spawnStuff {
        public boolean nether_wastes = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 20)
        public int spawnWeightA = 10;

        public boolean warped_forest = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 20)
        public int spawnWeightB = 2;

        public boolean crimson_forest = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 20)
        public int spawnWeightC = 4;

        public boolean soul_valley = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 20)
        public int spawnWeightD = 10;

        public boolean basalt_delta = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 20)
        public int spawnWeightE = 4;
    }
}