/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package website.skylorbeck.magehand.entity.goals;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.WorldView;
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;

import java.util.List;

public class MageHandPickUpItemGoal
extends MoveToTargetPosGoal {
    MageHandAbstractEntity magehand;
    int range = 16;

    public MageHandPickUpItemGoal(MageHandAbstractEntity mageHand, double speed, int range) {
        super(mageHand, speed, range, 4);
        this.range = range;
        this.magehand = mageHand;
    }

    @Override
    public double getDesiredDistanceToTarget() {
        return 2;
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
        return magehand.getMainHandStack().isEmpty() && super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return magehand.getMainHandStack().isEmpty() && super.shouldContinue();
    }

    @Override
    public void tick() {
        ItemStack itemStack = magehand.getMainHandStack();
        List<ItemEntity> list = this.magehand.world.getEntitiesByClass(ItemEntity.class, new Box(magehand.getBlockPos()).expand(1), itemEntity -> true);
        if (this.hasReached() && !list.isEmpty()) {
            if (itemStack.isEmpty()) {
                ItemEntity itemEntity = list.get(0);
                itemStack = itemEntity.getStack().copy();
                magehand.equipStack(EquipmentSlot.MAINHAND, itemStack);
                itemEntity.getStack().setCount(0);
            }
        }
        super.tick();
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return this.magehand.world.getEntitiesByClass(ItemEntity.class, new Box(pos), itemEntity -> true).stream().findAny().isPresent();
    }
}