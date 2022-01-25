/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package website.skylorbeck.magehand.entity.goals;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.WorldView;
import website.skylorbeck.magehand.entity.MageHandAbstractEntity;
import website.skylorbeck.magehand.entity.MageHandAmethystEntity;

import java.util.List;

public class MageHandFilteredPickUpItemGoal
extends MageHandPickUpItemGoal {
    int range = 16;

    public MageHandFilteredPickUpItemGoal(MageHandAbstractEntity mageHand, double speed, int range) {
        super(mageHand, speed, range);
    }

    @Override
    public void tick() {
        ItemStack itemStack = magehand.getMainHandStack();
        MageHandAmethystEntity mageHand = (MageHandAmethystEntity) this.magehand;
        List<ItemEntity> list = this.magehand.world.getEntitiesByClass(ItemEntity.class, new Box(magehand.getBlockPos()).expand(1), itemEntity -> mageHand.isWhitelist() == mageHand.getOffHandStack().isOf(itemEntity.getStack().getItem()));
        if (this.hasReached() && !list.isEmpty()) {
            if (itemStack.isEmpty()) {
                ItemEntity itemEntity = list.get(0);
                itemStack = itemEntity.getStack().copy();
                magehand.equipStack(EquipmentSlot.MAINHAND, itemStack);
                itemEntity.getStack().setCount(0);
            }
        }
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        MageHandAmethystEntity mageHand = (MageHandAmethystEntity) this.magehand;
        return this.magehand.world.getEntitiesByClass(ItemEntity.class, new Box(pos), itemEntity -> !mageHand.getOffHandStack().isEmpty() && mageHand.isWhitelist() == mageHand.getOffHandStack().isOf(itemEntity.getStack().getItem())).stream().findAny().isPresent();
    }

}

