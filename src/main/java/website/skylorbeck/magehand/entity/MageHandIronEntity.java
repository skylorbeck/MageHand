package website.skylorbeck.magehand.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
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
        this.goalSelector.add(1,new MageHandAttackGoal(this,1.25f,false));
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
        return true;
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0)
                .add(EntityAttributes.GENERIC_ARMOR, 5f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4);
    }

    @Override
    public Identifier getTexture() {
        return Declarar.getIdentifier("textures/iron.png");
    }
}

