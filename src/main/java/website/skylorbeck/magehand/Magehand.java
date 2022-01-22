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
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;
import website.skylorbeck.magehand.entity.MageHandIronEntity;
import website.skylorbeck.minecraft.skylorlib.Registrar;

public class Magehand implements ModInitializer {
    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.NETHER), SpawnGroup.MONSTER, Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE, 50, 2, 4);
        Registrar.regItem("hostile_spawner",Declarar.MAGE_HAND_HOSTILE_SPAWNER,Declarar.MODID);

        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_COPPER_ENTITY_TYPE,
                MageHandAbstractEntity.createMobAttributes());
        Registrar.regItem("copper_spawner",Declarar.MAGE_HAND_COPPER_SPAWNER,Declarar.MODID);

        FabricDefaultAttributeRegistry.register(Declarar.MAGE_HAND_IRON_ENTITY_TYPE,
                MageHandIronEntity.createMobAttributes());
        Registrar.regItem("iron_spawner",Declarar.MAGE_HAND_IRON_SPAWNER,Declarar.MODID);
    }
}