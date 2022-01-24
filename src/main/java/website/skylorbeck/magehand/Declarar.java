package website.skylorbeck.magehand;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import website.skylorbeck.magehand.entity.*;
import website.skylorbeck.magehand.item.MageHandDiamondSpawner;
import website.skylorbeck.magehand.item.MageHandSpawner;
import website.skylorbeck.minecraft.skylorlib.DynamicRecipeLoader;

public class Declarar {
    public static String MODID = "magehand";

    public static Identifier getIdentifier(String path) {
        return new Identifier(MODID, path);
    }

    public static final ItemGroup mageHand = FabricItemGroupBuilder.build(
            getIdentifier("category"),
            () -> new ItemStack(Declarar.MAGE_HAND_GOLD_SPAWNER));

    public static Identifier MAGE_HAND_HOSTILE_ID = getIdentifier("hostile");
    public static final EntityType<MageHandHostileEntity> MAGE_HAND_HOSTILE_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, MAGE_HAND_HOSTILE_ID,
            EntityType.Builder.create(MageHandHostileEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(0.5f, 0.75f)
                    .maxTrackingRange(10)
                    .build(MAGE_HAND_HOSTILE_ID.toString()));
    public static final Item MAGE_HAND_HOSTILE_SPAWNER = new MageHandSpawner(MAGE_HAND_HOSTILE_ENTITY_TYPE, 16733525, new FabricItemSettings().group(mageHand).maxCount(16));

    public static Identifier MAGE_HAND_COPPER_ID = getIdentifier("copper");
    public static final EntityType<MageHandCopperEntity> MAGE_HAND_COPPER_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, MAGE_HAND_COPPER_ID,
            EntityType.Builder.create(MageHandCopperEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5f, 0.75f)
                    .maxTrackingRange(10)
                    .build(MAGE_HAND_COPPER_ID.toString()));
    public static final Item MAGE_HAND_COPPER_SPAWNER = new MageHandSpawner(MAGE_HAND_COPPER_ENTITY_TYPE, 16350732, new FabricItemSettings().group(mageHand).maxCount(16).rarity(Rarity.UNCOMMON));

    public static Identifier MAGE_HAND_IRON_ID = getIdentifier("iron");
    public static final EntityType<MageHandIronEntity> MAGE_HAND_IRON_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, MAGE_HAND_IRON_ID,
            EntityType.Builder.create(MageHandIronEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5f, 0.75f)
                    .maxTrackingRange(10)
                    .build(MAGE_HAND_IRON_ID.toString()));
    public static final Item MAGE_HAND_IRON_SPAWNER = new MageHandSpawner(MAGE_HAND_IRON_ENTITY_TYPE, 11184810, new FabricItemSettings().group(mageHand).maxCount(16).rarity(Rarity.RARE));

    public static Identifier MAGE_HAND_GOLD_ID = getIdentifier("gold");
    public static final EntityType<MageHandGoldEntity> MAGE_HAND_GOLD_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, MAGE_HAND_GOLD_ID,
            EntityType.Builder.create(MageHandGoldEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5f, 0.75f)
                    .maxTrackingRange(10)
                    .build(MAGE_HAND_GOLD_ID.toString()));
    public static final Item MAGE_HAND_GOLD_SPAWNER = new MageHandSpawner(MAGE_HAND_GOLD_ENTITY_TYPE, 15514118, new FabricItemSettings().group(mageHand).maxCount(16).rarity(Rarity.UNCOMMON));

    public static Identifier MAGE_HAND_DIAMOND_ID = getIdentifier("diamond");
    public static final EntityType<MageHandDiamondEntity> MAGE_HAND_DIAMOND_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, MAGE_HAND_DIAMOND_ID,
            EntityType.Builder.create(MageHandDiamondEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5f, 0.75f)
                    .maxTrackingRange(10)
                    .build(MAGE_HAND_DIAMOND_ID.toString()));
    public static final Item MAGE_HAND_DIAMOND_SPAWNER = new MageHandDiamondSpawner(MAGE_HAND_DIAMOND_ENTITY_TYPE, 9295582, new FabricItemSettings().group(mageHand).maxCount(16).rarity(Rarity.RARE));

    public static final Item MAGE_HAND_BONE_ITEM = new Item(new FabricItemSettings().group(mageHand));
    public static JsonObject MAGE_HAND_BONE = null;

    public static final Item MAGE_HAND_FLESH_ITEM = new Item(new FabricItemSettings().group(mageHand));
    public static JsonObject MAGE_HAND_FLESH = null;

    public static JsonObject MAGE_HAND_COPPER = null;
    public static JsonObject MAGE_HAND_IRON = null;
    public static JsonObject MAGE_HAND_GOLD = null;
    public static JsonObject MAGE_HAND_DIAMOND = null;

    public static final Item MAGE_HAND_ESSENCE = new Item(new FabricItemSettings().group(mageHand));

}