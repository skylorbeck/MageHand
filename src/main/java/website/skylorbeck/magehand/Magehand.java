package website.skylorbeck.magehand;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.tag.Tag;
import net.minecraft.world.biome.Biome;
import software.bernie.geckolib3.GeckoLib;
import website.skylorbeck.minecraft.skylorlib.Registrar;

public class Magehand implements ModInitializer {
    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE,
                MobEntity.createMobAttributes()
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5f)
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 10f)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4)
        );
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.NETHER), SpawnGroup.MONSTER, Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE, 50, 2, 4);

        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_COPPER_ENTITY_TYPE,
                MobEntity.createMobAttributes()
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5f)
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 10f)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4)

        );
        Registrar.regItem("copper_spawner",Declarar.MAGE_HAND_COPPER_SPAWNER,Declarar.MODID);

        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_IRON_ENTITY_TYPE,
                MobEntity.createMobAttributes()
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0)
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0)
                        .add(EntityAttributes.GENERIC_ARMOR, 5f)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                        .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4)
        );
        Registrar.regItem("iron_spawner",Declarar.MAGE_HAND_IRON_SPAWNER,Declarar.MODID);
    }
}