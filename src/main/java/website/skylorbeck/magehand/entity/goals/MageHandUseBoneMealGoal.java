/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package website.skylorbeck.magehand.entity.goals;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.AutomaticItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;

public class MageHandUseBoneMealGoal
extends MoveToTargetPosGoal {
    MageHandAbstractEntity magehand;
    int range = 16;

    public MageHandUseBoneMealGoal(MageHandAbstractEntity mageHand, double speed, int range) {
        super(mageHand, speed, range, 4);
        this.range = range;
        this.magehand = mageHand;
    }

    @Override
    public double getDesiredDistanceToTarget() {
        return 1;
    }

    @Override
    protected int getInterval(PathAwareEntity mob) {
        return 20;
    }

    @Override
    public boolean shouldResetPath() {
        return this.tryingTime % 20 == 0;
    }

    @Override
    public boolean canStart() {
        return !magehand.getOffHandStack().isEmpty() && super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return !this.targetPos.equals(BlockPos.ORIGIN) && !magehand.getOffHandStack().isEmpty() && this.tryingTime<=100 && super.shouldContinue();
    }

    @Override
    public void tick() {
//        if (this.hasReached()) {
            ItemStack itemStack = magehand.getOffHandStack();
            BlockPos.iterateOutwards(this.magehand.getBlockPos(), 1, 1, 1).forEach(blockPos -> {
                BlockState blockState = this.magehand.world.getBlockState(blockPos);
                if (blockState.getBlock() instanceof CropBlock block && !(block).isMature(blockState)) {
                    itemStack.useOnBlock(new AutomaticItemPlacementContext(this.magehand.world, blockPos, Direction.DOWN, itemStack, Direction.UP));
                }
            });
//        }
        super.tick();
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return pos.isWithinDistance(this.magehand.getStartingPos(),this.range) && world.getBlockState(pos).getBlock().equals(Blocks.FARMLAND);
    }
}