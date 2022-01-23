package website.skylorbeck.magehand;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import website.skylorbeck.magehand.entity.MageHandCopperEntity;
import website.skylorbeck.magehand.entity.MageHandGoldEntity;
import website.skylorbeck.magehand.entity.MageHandHostileEntity;
import website.skylorbeck.magehand.entity.MageHandIronEntity;
import website.skylorbeck.magehand.item.MageHandSpawner;

public class Declarar {
    public static String MODID = "magehand";

    public static Identifier getIdentifier(String path) {
        return new Identifier(MODID, path);
    }

    public static final ItemGroup mageHand = FabricItemGroupBuilder.build(
            getIdentifier("category"),
            () -> new ItemStack(Items.GOLD_INGOT));

    public static Identifier MAGE_HAND_HOSTILE_ID = getIdentifier("hostile");
    public static final EntityType<MageHandHostileEntity> MAGE_HAND_HOSTILE_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, MAGE_HAND_HOSTILE_ID,
            EntityType.Builder.create(MageHandHostileEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(0.5f, 0.75f)
                    .maxTrackingRange(10)
                    .build(MAGE_HAND_HOSTILE_ID.toString()));
    public static final Item MAGE_HAND_HOSTILE_SPAWNER = new MageHandSpawner(MAGE_HAND_HOSTILE_ENTITY_TYPE, 6029312, 13181728, new FabricItemSettings().group(mageHand).maxCount(16));

    public static Identifier MAGE_HAND_COPPER_ID = getIdentifier("copper");
    public static final EntityType<MageHandCopperEntity> MAGE_HAND_COPPER_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, MAGE_HAND_COPPER_ID,
            EntityType.Builder.create(MageHandCopperEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5f, 0.75f)
                    .maxTrackingRange(10)
                    .build(MAGE_HAND_COPPER_ID.toString()));
    public static final Item MAGE_HAND_COPPER_SPAWNER = new MageHandSpawner(MAGE_HAND_COPPER_ENTITY_TYPE, 10506797, 12088115, new FabricItemSettings().group(mageHand).maxCount(16).rarity(Rarity.UNCOMMON));

    public static Identifier MAGE_HAND_IRON_ID = getIdentifier("iron");
    public static final EntityType<MageHandIronEntity> MAGE_HAND_IRON_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, MAGE_HAND_IRON_ID,
            EntityType.Builder.create(MageHandIronEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5f, 0.75f)
                    .maxTrackingRange(10)
                    .build(MAGE_HAND_IRON_ID.toString()));
    public static final Item MAGE_HAND_IRON_SPAWNER = new MageHandSpawner(MAGE_HAND_IRON_ENTITY_TYPE, 8488329, 12632256, new FabricItemSettings().group(mageHand).maxCount(16).rarity(Rarity.RARE));

    public static Identifier MAGE_HAND_GOLD_ID = getIdentifier("gold");
    public static final EntityType<MageHandGoldEntity> MAGE_HAND_GOLD_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, MAGE_HAND_GOLD_ID,
            EntityType.Builder.create(MageHandGoldEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5f, 0.75f)
                    .maxTrackingRange(10)
                    .build(MAGE_HAND_GOLD_ID.toString()));
    public static final Item MAGE_HAND_GOLD_SPAWNER = new MageHandSpawner(MAGE_HAND_GOLD_ENTITY_TYPE, 8214022, 13209888, new FabricItemSettings().group(mageHand).maxCount(16).rarity(Rarity.UNCOMMON));

}