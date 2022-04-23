package website.skylorbeck.magehand;

import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.BiomeKeys;
import software.bernie.geckolib3.GeckoLib;
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;
import website.skylorbeck.magehand.entity.MageHandIronEntity;
import website.skylorbeck.minecraft.skylorlib.DynamicRecipeLoader;
import website.skylorbeck.minecraft.skylorlib.Registrar;

import static website.skylorbeck.magehand.Declarar.*;

public class Magehand implements ModInitializer {
    @Override
    public void onInitialize() {
        GeckoLib.initialize();

        ConfigHolder<HandConfig> configHolder = AutoConfig.register(HandConfig.class, GsonConfigSerializer::new);
        config = configHolder.getConfig();
        configHolder.registerSaveListener((manager, data) -> {
            config = data;
            return ActionResult.SUCCESS;
        });

        FabricDefaultAttributeRegistry.register(MAGE_HAND_HOSTILE_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());

        if (config.spawnStuff.nether_wastes){
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.NETHER_WASTES), SpawnGroup.MONSTER, MAGE_HAND_HOSTILE_ENTITY_TYPE, config.spawnStuff.spawnWeightA, config.minGroupSize, config.maxGroupSize);
        }
        if (config.spawnStuff.basalt_delta){
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BASALT_DELTAS), SpawnGroup.MONSTER, MAGE_HAND_HOSTILE_ENTITY_TYPE, config.spawnStuff.spawnWeightB, config.minGroupSize, config.maxGroupSize);
        }
        if (config.spawnStuff.crimson_forest){
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.CRIMSON_FOREST), SpawnGroup.MONSTER, MAGE_HAND_HOSTILE_ENTITY_TYPE, config.spawnStuff.spawnWeightC, config.minGroupSize, config.maxGroupSize);
        }
        if (config.spawnStuff.warped_forest){
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.WARPED_FOREST), SpawnGroup.MONSTER, MAGE_HAND_HOSTILE_ENTITY_TYPE, config.spawnStuff.spawnWeightD, config.minGroupSize, config.maxGroupSize);
        }
        if (config.spawnStuff.soul_valley){
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SOUL_SAND_VALLEY), SpawnGroup.MONSTER, MAGE_HAND_HOSTILE_ENTITY_TYPE, config.spawnStuff.spawnWeightE, config.minGroupSize, config.maxGroupSize);
        }

        Registrar.regItem("hostile_spawner_", MAGE_HAND_HOSTILE_SPAWNER, MODID);

        FabricDefaultAttributeRegistry.register(MAGE_HAND_COPPER_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        Registrar.regItem("copper_spawner_", MAGE_HAND_COPPER_SPAWNER, MODID);

        FabricDefaultAttributeRegistry.register(MAGE_HAND_IRON_ENTITY_TYPE,
                MageHandIronEntity.createMobAttributes());
        Registrar.regItem("iron_spawner_", MAGE_HAND_IRON_SPAWNER, MODID);

        FabricDefaultAttributeRegistry.register(MAGE_HAND_GOLD_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        Registrar.regItem("gold_spawner_", MAGE_HAND_GOLD_SPAWNER, MODID);

        FabricDefaultAttributeRegistry.register(MAGE_HAND_DIAMOND_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        Registrar.regItem("diamond_spawner_", MAGE_HAND_DIAMOND_SPAWNER, MODID);

        FabricDefaultAttributeRegistry.register(MAGE_HAND_AMETHYST_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        Registrar.regItem("amethyst_spawner_", MAGE_HAND_AMETHYST_SPAWNER, MODID);

        Registrar.regItem("bone_hand_", MAGE_HAND_BONE_ITEM, MODID);
        Registrar.regItem("flesh_hand_", MAGE_HAND_FLESH_ITEM, MODID);
        Registrar.regItem("hand_essence_", MAGE_HAND_ESSENCE, MODID);
//        Registrar.regItem("debugger", Declarar.DEBUGSPAWNER,Declarar.MODID);
        MAGE_HAND_BONE = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(Items.BONE),Registry.ITEM.getId(MAGE_HAND_ESSENCE)),//items
                Lists.newArrayList(false,false),//type
                Lists.newArrayList("0  ", "000", "010"),//pattern
                Registry.ITEM.getId(MAGE_HAND_BONE_ITEM),
                1
        );
        MAGE_HAND_FLESH = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(MAGE_HAND_BONE_ITEM), Registry.ITEM.getId(Items.ROTTEN_FLESH)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(MAGE_HAND_FLESH_ITEM),
                1
        );
        MAGE_HAND_COPPER = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(MAGE_HAND_FLESH_ITEM), Registry.ITEM.getId(Items.COPPER_INGOT)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(MAGE_HAND_COPPER_SPAWNER),
                1
        );
        MAGE_HAND_IRON = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(MAGE_HAND_FLESH_ITEM), Registry.ITEM.getId(Items.IRON_INGOT)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(MAGE_HAND_IRON_SPAWNER),
                1
        );
        MAGE_HAND_GOLD = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(MAGE_HAND_FLESH_ITEM), Registry.ITEM.getId(Items.GOLD_INGOT)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(MAGE_HAND_GOLD_SPAWNER),
                1
        );
        MAGE_HAND_DIAMOND = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(MAGE_HAND_FLESH_ITEM), Registry.ITEM.getId(Items.DIAMOND)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(MAGE_HAND_DIAMOND_SPAWNER),
                1
        );
        MAGE_HAND_AMETHYST = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(MAGE_HAND_FLESH_ITEM), Registry.ITEM.getId(Items.AMETHYST_SHARD)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(MAGE_HAND_AMETHYST_SPAWNER),
                1
        );
    }
}