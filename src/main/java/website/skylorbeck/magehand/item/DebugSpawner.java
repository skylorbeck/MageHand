package website.skylorbeck.magehand.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import website.skylorbeck.magehand.Declarar;
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;

public class DebugSpawner extends Item {
    public DebugSpawner(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!(world instanceof ServerWorld)) {
            return ActionResult.SUCCESS;
        }
            EntityType<?>[] entityTypes = new EntityType<?>[] {
                    Declarar.MAGE_HAND_COPPER_ENTITY_TYPE,
                    Declarar.MAGE_HAND_IRON_ENTITY_TYPE,
                    Declarar.MAGE_HAND_GOLD_ENTITY_TYPE,
                    Declarar.MAGE_HAND_DIAMOND_ENTITY_TYPE,
                    Declarar.MAGE_HAND_AMETHYST_ENTITY_TYPE,
            };
        for (int x = 0; x < entityTypes.length ; x++) {
            for (int y = 0; y < 5; y++) {
                for (int z = 0; z < 10; z++) {
                    MageHandAbstractEntity entity = (MageHandAbstractEntity) entityTypes[x].create(world);
                    if (entity != null) {
                        entity.setPosition(context.getBlockPos().getX()+x, context.getBlockPos().getY() + 1 + y, context.getBlockPos().getZ() + z);
                        entity.setAiDisabled(true);
                        world.spawnEntity(entity);
                    }
                }
            }
        }
        return ActionResult.CONSUME;
    }
}
