package website.skylorbeck.magehand;

import com.google.common.collect.Lists;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import software.bernie.geckolib3.GeckoLib;
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;
import website.skylorbeck.magehand.entity.MageHandIronEntity;
import website.skylorbeck.minecraft.skylorlib.DynamicRecipeLoader;
import website.skylorbeck.minecraft.skylorlib.Registrar;

public class Magehand implements ModInitializer {
    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.NETHER), SpawnGroup.MONSTER, Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE, 50, 2, 4);
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

        Registrar.regItem("bone_hand_", Declarar.MAGE_HAND_BONE_ITEM, Declarar.MODID);
        Registrar.regItem("flesh_hand_", Declarar.MAGE_HAND_FLESH_ITEM, Declarar.MODID);
        Registrar.regItem("hand_essence_", Declarar.MAGE_HAND_ESSENCE,Declarar.MODID);
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
    }
}

//todo
// hand skins
// crafting recipes