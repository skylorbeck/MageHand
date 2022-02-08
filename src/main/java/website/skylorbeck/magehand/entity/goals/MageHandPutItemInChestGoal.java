/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package website.skylorbeck.magehand.entity.goals;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;

import static website.skylorbeck.minecraft.skylorlib.storage.StorageUtils.transfer;

public class MageHandPutItemInChestGoal
extends MoveToTargetPosGoal {
    MageHandAbstractEntity magehand;
    public MageHandPutItemInChestGoal(MageHandAbstractEntity mageHand, double speed, int range) {
        super(mageHand, speed, range,4);
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
//        return true;
        return !magehand.getMainHandStack().isEmpty() && super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return !magehand.getMainHandStack().isEmpty() && super.shouldContinue();
    }

    @Override
    public boolean shouldResetPath() {
        return  this.tryingTime % 20 == 0;
    }

    @Override
    protected BlockPos getTargetPos() {
        return magehand.getStartingPos();
    }

    @Override
    public void tick() {
            BlockPos.iterateOutwards(this.magehand.getBlockPos(), 1, 1, 1).forEach(blockPos -> {
                if (isTargetPos(this.magehand.world, blockPos)) {
                    ItemStack itemStack = magehand.getMainHandStack();
                    BlockState blockState = this.magehand.world.getBlockState(blockPos);
                    if (blockState.hasBlockEntity()) {
                        BlockEntity blockEntity = this.magehand.world.getBlockEntity(blockPos);
                        if (blockEntity instanceof SidedInventory sidedInventory) {
                            int[] is = sidedInventory.getAvailableSlots(Direction.UP);
                            for (int i = 0; i < is.length && !itemStack.isEmpty(); ++i) {
                                itemStack = transfer(sidedInventory, itemStack, is[i], Direction.UP);
                                magehand.equipStack(EquipmentSlot.MAINHAND, itemStack);
                                magehand.world.playSoundFromEntity(null, magehand, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.NEUTRAL, 0.2f, ((magehand.world.random.nextFloat() - magehand.world.random.nextFloat()) * 0.7f + 1.0f) * 2.0f);
                            }
                        } else if (blockEntity instanceof Inventory inventory) {
                            int sidedInventory = ((Inventory) blockEntity).size();
                            for (int is = 0; is < sidedInventory && !itemStack.isEmpty(); ++is) {
                                itemStack = transfer(inventory, itemStack, is, Direction.DOWN);
                                magehand.equipStack(EquipmentSlot.MAINHAND, itemStack);
                                magehand.world.playSoundFromEntity(null, magehand, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.NEUTRAL, 0.2f, ((magehand.world.random.nextFloat() - magehand.world.random.nextFloat()) * 0.7f + 1.0f) * 2.0f);
                            }
                        }
                    }
                }
            });
        super.tick();

    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        if (!world.isAir(pos.up()) && pos.isWithinDistance(this.magehand.getStartingPos(),2)) {
            return false;
        }
        BlockState blockState = world.getBlockState(pos);
        return blockState.hasBlockEntity() && (world.getBlockEntity(pos) instanceof Inventory);
    }
}

