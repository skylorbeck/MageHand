package website.skylorbeck.minecraft.magehand.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Objects;

public class MageHandSpawner extends SpawnEggItem {
    public MageHandSpawner(EntityType<? extends MobEntity> type, Settings settings) {
        super(type,16777215,16777215, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Object blockEntity;
        World world = context.getWorld();
        if (!(world instanceof ServerWorld)) {
            return ActionResult.SUCCESS;
        }
        ItemStack itemStack = context.getStack();
        BlockPos blockPos = context.getBlockPos();
        Direction direction = Direction.UP;
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.SPAWNER) && (blockEntity = world.getBlockEntity(blockPos)) instanceof MobSpawnerBlockEntity) {
            MobSpawnerLogic mobSpawnerLogic = ((MobSpawnerBlockEntity)blockEntity).getLogic();
            EntityType<?> entityType = this.getEntityType(itemStack.getNbt());
            mobSpawnerLogic.setEntityId(entityType);
            ((BlockEntity)blockEntity).markDirty();
            world.updateListeners(blockPos, blockState, blockState, Block.NOTIFY_ALL);
            itemStack.decrement(1);
            return ActionResult.CONSUME;
        }
        blockEntity = blockState.getCollisionShape(world, blockPos).isEmpty() ? blockPos : blockPos.offset(direction);
        EntityType<?> mobSpawnerLogic = this.getEntityType(itemStack.getNbt());
        Entity entity = mobSpawnerLogic.spawnFromItemStack((ServerWorld)world, itemStack, context.getPlayer(), (BlockPos)blockEntity, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockPos, blockEntity));
        if (entity != null) {
            itemStack.decrement(1);
            world.emitGameEvent((Entity)context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos);
        }
        return ActionResult.CONSUME;
    }
}
