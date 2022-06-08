package website.skylorbeck.minecraft.magehand;

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
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.BiomeKeys;
import software.bernie.geckolib3.GeckoLib;
import website.skylorbeck.minecraft.magehand.entity.MageHandAbstractEntity;
import website.skylorbeck.minecraft.magehand.entity.MageHandGoldEntity;
import website.skylorbeck.minecraft.magehand.entity.MageHandIronEntity;
import website.skylorbeck.minecraft.skylorlib.ConfigFileHandler;
import website.skylorbeck.minecraft.skylorlib.DynamicRecipeLoader;
import website.skylorbeck.minecraft.skylorlib.Registrar;

public class Magehand implements ModInitializer {
    @Override
    public void onInitialize() {
        GeckoLib.initialize();

        ConfigHolder<HandConfig> configHolder = AutoConfig.register(HandConfig.class, GsonConfigSerializer::new);
        Declarar.config = configHolder.getConfig();
        configHolder.registerSaveListener((manager, data) -> {
            Declarar.config = data;
            return ActionResult.SUCCESS;
        });

        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());

        if (Declarar.config.spawnStuff.nether_wastes){
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.NETHER_WASTES), SpawnGroup.MONSTER, Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE, Declarar.config.spawnStuff.spawnWeightA, Declarar.config.minGroupSize, Declarar.config.maxGroupSize);
        }
        if (Declarar.config.spawnStuff.basalt_delta){
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BASALT_DELTAS), SpawnGroup.MONSTER, Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE, Declarar.config.spawnStuff.spawnWeightB, Declarar.config.minGroupSize, Declarar.config.maxGroupSize);
        }
        if (Declarar.config.spawnStuff.crimson_forest){
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.CRIMSON_FOREST), SpawnGroup.MONSTER, Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE, Declarar.config.spawnStuff.spawnWeightC, Declarar.config.minGroupSize, Declarar.config.maxGroupSize);
        }
        if (Declarar.config.spawnStuff.warped_forest){
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.WARPED_FOREST), SpawnGroup.MONSTER, Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE, Declarar.config.spawnStuff.spawnWeightD, Declarar.config.minGroupSize, Declarar.config.maxGroupSize);
        }
        if (Declarar.config.spawnStuff.soul_valley){
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SOUL_SAND_VALLEY), SpawnGroup.MONSTER, Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE, Declarar.config.spawnStuff.spawnWeightE, Declarar.config.minGroupSize, Declarar.config.maxGroupSize);
        }

        MageHandGoldEntity.seedables = ConfigFileHandler.initConfigFile("Magehand/Seeds.json",MageHandGoldEntity.seedables);

        Registrar.regItem("hostile_spawner_", Declarar.MAGE_HAND_HOSTILE_SPAWNER, Declarar.MODID);

        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_COPPER_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        Registrar.regItem("copper_spawner_", Declarar.MAGE_HAND_COPPER_SPAWNER, Declarar.MODID);

        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_IRON_ENTITY_TYPE,
                MageHandIronEntity.createMobAttributes());
        Registrar.regItem("iron_spawner_", Declarar.MAGE_HAND_IRON_SPAWNER, Declarar.MODID);

        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_GOLD_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        Registrar.regItem("gold_spawner_", Declarar.MAGE_HAND_GOLD_SPAWNER, Declarar.MODID);

        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_DIAMOND_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        Registrar.regItem("diamond_spawner_", Declarar.MAGE_HAND_DIAMOND_SPAWNER, Declarar.MODID);

        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_AMETHYST_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        Registrar.regItem("amethyst_spawner_", Declarar.MAGE_HAND_AMETHYST_SPAWNER, Declarar.MODID);
        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_WOOD_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        Registrar.regItem("wood_spawner_", Declarar.MAGE_HAND_WOOD_SPAWNER, Declarar.MODID);

        Registrar.regItem("bone_hand_", Declarar.MAGE_HAND_BONE_ITEM, Declarar.MODID);
        Registrar.regItem("flesh_hand_", Declarar.MAGE_HAND_FLESH_ITEM, Declarar.MODID);
        Registrar.regItem("hand_essence_", Declarar.MAGE_HAND_ESSENCE, Declarar.MODID);
//        Registrar.regItem("debugger", Declarar.DEBUGSPAWNER,Declarar.MODID);
        Declarar.MAGE_HAND_BONE = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(Items.BONE),Registry.ITEM.getId(Declarar.MAGE_HAND_ESSENCE)),//items
                Lists.newArrayList(false,false),//type
                Lists.newArrayList("0  ", "000", "010"),//pattern
                Registry.ITEM.getId(Declarar.MAGE_HAND_BONE_ITEM),
                1
        );
        Declarar.MAGE_HAND_FLESH = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(Declarar.MAGE_HAND_BONE_ITEM), Registry.ITEM.getId(Items.ROTTEN_FLESH)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(Declarar.MAGE_HAND_FLESH_ITEM),
                1
        );
        Declarar.MAGE_HAND_COPPER = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(Declarar.MAGE_HAND_FLESH_ITEM), Registry.ITEM.getId(Items.COPPER_INGOT)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(Declarar.MAGE_HAND_COPPER_SPAWNER),
                1
        );
        Declarar.MAGE_HAND_IRON = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(Declarar.MAGE_HAND_FLESH_ITEM), Registry.ITEM.getId(Items.IRON_INGOT)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(Declarar.MAGE_HAND_IRON_SPAWNER),
                1
        );
        Declarar.MAGE_HAND_GOLD = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(Declarar.MAGE_HAND_FLESH_ITEM), Registry.ITEM.getId(Items.GOLD_INGOT)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(Declarar.MAGE_HAND_GOLD_SPAWNER),
                1
        );
        Declarar.MAGE_HAND_DIAMOND = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(Declarar.MAGE_HAND_FLESH_ITEM), Registry.ITEM.getId(Items.DIAMOND)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(Declarar.MAGE_HAND_DIAMOND_SPAWNER),
                1
        );
        Declarar.MAGE_HAND_AMETHYST = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(Declarar.MAGE_HAND_FLESH_ITEM), Registry.ITEM.getId(Items.AMETHYST_SHARD)),//items
                Lists.newArrayList(false, false),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(Declarar.MAGE_HAND_AMETHYST_SPAWNER),
                1
        );
        Declarar.MAGE_HAND_WOOD = DynamicRecipeLoader.createShapedRecipeJson(
                Lists.newArrayList(Registry.ITEM.getId(Declarar.MAGE_HAND_FLESH_ITEM), new Identifier("minecraft","planks")),
                Lists.newArrayList(false, true),//type
                Lists.newArrayList("111", "101", "111"),//pattern
                Registry.ITEM.getId(Declarar.MAGE_HAND_WOOD_SPAWNER),
                1
        );
    }
}