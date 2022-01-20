package website.skylorbeck.magehand.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import website.skylorbeck.magehand.Declarar;
import website.skylorbeck.magehand.entity.goals.MageHandAttackGoal;

public class MageHandIronEntity extends MageHandFriendlyAbstractEntity{
    public MageHandIronEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1,new MageHandAttackGoal(this,1f,false));
        this.targetSelector.add(1, new RevengeGoal(this).setGroupRevenge());
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, HostileEntity.class, true,true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, MageHandHostileEntity.class, true,true));
        super.initGoals();
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (this.getMainHandStack().isEmpty()) {
            if (itemStack.getItem() instanceof SwordItem) {
                this.equipStack(EquipmentSlot.MAINHAND, itemStack);
                this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 100f);
                player.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            }
        } else {
            if (itemStack.getItem() instanceof SwordItem) {
                this.dropStack(this.getMainHandStack());
                this.equipStack(EquipmentSlot.MAINHAND, itemStack);
                this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 100f);
                player.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            } else if (itemStack.isEmpty() && player.isSneaking()) {
                this.dropStack(this.getMainHandStack());
                this.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            }
        }
        return super.interactMob(player, hand);
    }

    @Override
    protected boolean shouldDropLoot() {
        return  true;
    }

    @Override
    public Identifier getTexture() {
        return Declarar.getIdentifier("textures/iron.png");
    }
}

