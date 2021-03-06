package website.skylorbeck.minecraft.magehand.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import website.skylorbeck.minecraft.magehand.Declarar;
import website.skylorbeck.minecraft.magehand.entity.goals.MageHandCallForHelpGoal;
import website.skylorbeck.minecraft.magehand.entity.goals.MageHandPutItemInChestGoal;

public class MageHandWoodEntity extends MageHandFriendlyAbstractEntity{
    public MageHandWoodEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1,new MageHandPutItemInChestGoal(this,2,20));
        this.targetSelector.add(1, new MageHandCallForHelpGoal(this).setGroupRevenge(MageHandCopperEntity.class,MageHandDiamondEntity.class, MageHandWoodEntity.class,MageHandGoldEntity.class,MageHandHostileEntity.class));
        super.initGoals();
    }

    @Override
    public Identifier getTexture() {
        return Declarar.getIdentifier("textures/wood.png");
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (this.getMainHandStack().isEmpty()) {
            this.equipStack(EquipmentSlot.MAINHAND, itemStack);
            this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 100f);
            player.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        } else {
            if (itemStack.isEmpty() && player.isSneaking()) {
                this.dropStack(this.getMainHandStack());
                this.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            }
        }
        return super.interactMob(player, hand);
    }
}