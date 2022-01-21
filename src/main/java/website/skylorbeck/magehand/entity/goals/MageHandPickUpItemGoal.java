/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package website.skylorbeck.magehand.entity.goals;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;

import java.util.List;

public class MageHandPickUpItemGoal
extends MoveToTargetPosGoal {
    MageHandAbstractEntity magehand;
    int range = 16;
    public MageHandPickUpItemGoal(MageHandAbstractEntity mageHand, double speed, int range) {
        super(mageHand, speed, range,6);
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
    public boolean canStart() {
        return magehand.getMainHandStack().isEmpty() && super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return magehand.getMainHandStack().isEmpty() && super.shouldContinue();
    }

    @Override
    protected BlockPos getTargetPos() {
        return new Move
    }

    @Override
    public void tick() {
            ItemStack itemStack = magehand.getMainHandStack();
        List<ItemEntity> list = this.magehand.world.getEntitiesByClass(ItemEntity.class, new Box(magehand.getBlockPos()), itemEntity -> true);
        if (this.hasReached()) {
            for (ItemEntity itemEntity : list) {
                itemStack = itemEntity.getStack();
                magehand.equipStack(EquipmentSlot.MAINHAND, itemStack);
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
                break;
            }
        }
        super.tick();
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return this.magehand.world.getEntitiesByClass(ItemEntity.class,new Box(pos), itemEntity -> true).stream().findFirst().isPresent();
    }
}

