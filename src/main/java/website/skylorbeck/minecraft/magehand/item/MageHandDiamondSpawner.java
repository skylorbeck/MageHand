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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import website.skylorbeck.minecraft.magehand.entity.MageHandDiamondEntity;

import java.util.Objects;

public class MageHandDiamondSpawner extends SpawnEggItem {
    public possibleTargets currentTarget = possibleTargets.Diamond;

    public MageHandDiamondSpawner(EntityType<? extends MobEntity> type, Settings settings) {
        super(type,16777215,16777215, settings);
    }

    enum possibleTargets {
        Coal(new Block[]{Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE}),
        Iron(new Block[]{Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE}),
        Copper(new Block[]{Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE}),
        Gold(new Block[]{Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE}),
        Redstone(new Block[]{Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE}),
        Emerald(new Block[]{Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE}),
        Lapis(new Block[]{Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE}),
        Diamond(new Block[]{Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE});
        final Block[] blockTypes;

        possibleTargets(Block[] blocks) {
            blockTypes = blocks;
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && user.isSneaking()) {
            this.currentTarget = this.currentTarget.ordinal() == possibleTargets.values().length-1 ? possibleTargets.Coal : possibleTargets.values()[this.currentTarget.ordinal() + 1];
            user.sendMessage(Text.of("Target: " + currentTarget.name()), true);
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
//        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer() != null && context.getPlayer().isSneaking()) {
            return ActionResult.PASS;
        }
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
            MobSpawnerLogic mobSpawnerLogic = ((MobSpawnerBlockEntity) blockEntity).getLogic();
            EntityType<?> entityType = this.getEntityType(itemStack.getNbt());
            mobSpawnerLogic.setEntityId(entityType);
            ((BlockEntity) blockEntity).markDirty();
            world.updateListeners(blockPos, blockState, blockState, Block.NOTIFY_ALL);
            itemStack.decrement(1);
            return ActionResult.CONSUME;
        }
        blockEntity = blockState.getCollisionShape(world, blockPos).isEmpty() ? blockPos : blockPos.offset(direction);
        EntityType<?> mobSpawnerLogic = this.getEntityType(itemStack.getNbt());
        Entity entity = mobSpawnerLogic.spawnFromItemStack((ServerWorld) world, itemStack, context.getPlayer(), (BlockPos) blockEntity, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockPos, blockEntity));
        if (entity != null) {
            ((MageHandDiamondEntity) entity).setTargetBlock(currentTarget.blockTypes);
            itemStack.decrement(1);
            world.emitGameEvent((Entity) context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos);
        }
        return ActionResult.CONSUME;
    }
}