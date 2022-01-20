package website.skylorbeck.magehand;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import software.bernie.geckolib3.GeckoLib;

public class Magehand implements ModInitializer {
    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE,
                MobEntity.createMobAttributes()
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,5f)
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 10f)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED,0.4)
        );
        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_COPPER_ENTITY_TYPE,
                MobEntity.createMobAttributes()
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,5f)
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 10f)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED,0.4)

        );
        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_IRON_ENTITY_TYPE,
        MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0)
                .add(EntityAttributes.GENERIC_ARMOR,5f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_FLYING_SPEED,0.4)
        );
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), SpawnGroup.MONSTER,Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE,50,2,4);
    }
}
