/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package website.skylorbeck.magehand.entity.goals;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;

import java.util.EnumSet;

public class MageHandLookAtBlockGoal
extends Goal {
    protected final MobEntity mob;
    @Nullable
    protected BlockPos target;
    protected final int range;
    private int lookTime;
    protected final Block targetType;

    public MageHandLookAtBlockGoal(MobEntity mob, Block targetType, int range) {
        this.mob = mob;
        this.targetType = targetType;
        this.range = range;
        this.setControls(EnumSet.of(Control.LOOK));
    }

    @Override
    public boolean canStart() {
        for (BlockPos blockPos : BlockPos.iterateOutwards(this.mob.getBlockPos(), range, range, range)) {
            if (this.mob.world.getBlockState(blockPos).getBlock().equals(targetType)) {
                this.target = blockPos;
                ((MageHandAbstractEntity)this.mob).overrideTrackedTarget(1);
                break;
            }
        }
        return this.target != null;
    }

    @Override
    public boolean shouldContinue() {
        return !(this.mob.getBlockPos().getSquaredDistance(this.target) > (double) (this.range * this.range));
    }

    @Override
    public void start() {
        this.lookTime = 40;
    }

    @Override
    public void stop() {
        this.target = null;
    }

    @Override
    public void tick() {
        if (this.target !=null) {
            this.mob.getLookControl().lookAt(this.target.getX()+0.5f, this.target.getY()+0.5f, this.target.getZ()+0.5f);
            if (--this.lookTime<=0){
                this.mob.kill();
            }
        }
    }
}

