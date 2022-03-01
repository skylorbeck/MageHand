/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package website.skylorbeck.magehand.entity.goals;

import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;

import java.util.EnumSet;

public class ReturnToStartGoal extends MoveToTargetPosGoal {
    private final MageHandAbstractEntity hand;

    public ReturnToStartGoal(MageHandAbstractEntity hand, double speed, int range) {
        super(hand, speed, range, 10);
        this.lowestY = -2;
        this.hand = hand;
        this.targetPos= hand.getStartingPos();
        this.setControls(EnumSet.of(Control.JUMP, Control.MOVE));
    }

    @Override
    protected BlockPos getTargetPos() {
        return hand.getStartingPos();
    }

    @Override
    protected int getInterval(PathAwareEntity mob) {
        return 40;
    }

    @Override
    public double getDesiredDistanceToTarget() {
        return 1f;
    }


    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
//        return pos.isWithinDistance(hand.getStartingPos(),1);
        return true;
    }
}

