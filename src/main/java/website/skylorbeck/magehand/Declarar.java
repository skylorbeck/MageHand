package website.skylorbeck.magehand;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;
import website.skylorbeck.magehand.entity.MageHandCopperEntity;
import website.skylorbeck.magehand.entity.MageHandHostileEntity;

public class Declarar {
    public static String MODID = "magehand";
    public static Identifier getIdentifier(String path){return new Identifier(MODID,path);}
    public static Identifier MAGE_HAND_HOSTILE_ID = getIdentifier("hostile");
    public static final EntityType<MageHandHostileEntity> MAGE_HAND_HOSTILE_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, MAGE_HAND_HOSTILE_ID,
            EntityType.Builder.create(MageHandHostileEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(0.5f, 0.5f)
                    .maxTrackingRange(10)
                    .build(MAGE_HAND_HOSTILE_ID.toString()));
    public static Identifier MAGE_HAND_COPPER_ID = getIdentifier("copper");
    public static final EntityType<MageHandCopperEntity> MAGE_HAND_COPPER_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, MAGE_HAND_COPPER_ID,
            EntityType.Builder.create(MageHandCopperEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5f, 0.5f)
                    .maxTrackingRange(10)
                    .build(MAGE_HAND_COPPER_ID.toString()));
}
