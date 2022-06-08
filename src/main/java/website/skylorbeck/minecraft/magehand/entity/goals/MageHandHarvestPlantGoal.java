/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package website.skylorbeck.minecraft.magehand.entity.goals;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import website.skylorbeck.minecraft.magehand.entity.MageHandAbstractEntity;

public class MageHandHarvestPlantGoal
extends MoveToTargetPosGoal {
    MageHandAbstractEntity magehand;
    int range = 16;
    boolean reset = false;

    public MageHandHarvestPlantGoal(MageHandAbstractEntity mageHand, double speed, int range) {
        super(mageHand, speed, range, 4);
        this.range = range;
        this.magehand = mageHand;
    }

    @Override
    public boolean shouldContinue() {
        return !this.targetPos.equals(BlockPos.ORIGIN) && !reset && super.shouldContinue();
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
    public void tick() {
//        if (this.hasReached()) {
            BlockPos.iterateOutwards(this.magehand.getBlockPos(), 1, 1, 1).forEach(blockPos -> {
                if (isTargetPos(this.magehand.world,blockPos)) {
                    this.magehand.world.breakBlock(blockPos.up(),true);
                    this.reset = true;
                }
            });
//        }
        super.tick();
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState cropState = this.magehand.world.getBlockState(pos.up());
        return pos.isWithinDistance(this.magehand.getStartingPos(),this.range) && world.getBlockState(pos).getBlock().equals(Blocks.FARMLAND) && cropState.getBlock() instanceof CropBlock cropBlock && cropBlock.isMature(cropState);
    }
}