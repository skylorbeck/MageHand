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
    private static ItemStack transfer(Inventory to, ItemStack stack, int slot, Direction dir) {
        ItemStack itemStack = to.getStack(slot);
        if (canInsert(to, stack, slot, dir)) {
            int j;
            boolean bl = false;
            if (itemStack.isEmpty()) {
                to.setStack(slot, stack);
                stack = ItemStack.EMPTY;
                bl = true;
            } else if (canMergeItems(itemStack, stack)) {
                int i = stack.getMaxCount() - itemStack.getCount();
                j = Math.min(stack.getCount(), i);
                stack.decrement(j);
                itemStack.increment(j);
            }
            if (bl) {
                to.markDirty();
            }
        }
        return stack;
    }
    private static boolean canInsert(Inventory inventory, ItemStack stack, int slot, Direction dir) {
        if (!inventory.isValid(slot, stack)) {
            return false;
        }
        return !(inventory instanceof SidedInventory) || ((SidedInventory)inventory).canInsert(slot, stack, dir);
    }
    private static boolean canMergeItems(ItemStack first, ItemStack second) {
        if (!first.isOf(second.getItem())) {
            return false;
        }
        if (first.getDamage() != second.getDamage()) {
            return false;
        }
        if (first.getCount() > first.getMaxCount()) {
            return false;
        }
        return ItemStack.areNbtEqual(first, second);
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        if (!world.isAir(pos.up())) {
            return false;
        }
        BlockState blockState = world.getBlockState(pos);
        return blockState.hasBlockEntity() && (world.getBlockEntity(pos) instanceof Inventory);
    }
}

