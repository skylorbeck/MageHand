package website.skylorbeck.magehand.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import website.skylorbeck.magehand.Declarar;
import website.skylorbeck.magehand.entity.goals.MageHandCallForHelpGoal;
import website.skylorbeck.magehand.entity.goals.MageHandFilteredPickUpItemGoal;
import website.skylorbeck.magehand.entity.goals.MageHandPickUpItemGoal;
import website.skylorbeck.magehand.entity.goals.MageHandPutItemInChestGoal;

public class MageHandAmethystEntity extends MageHandFriendlyAbstractEntity {
    boolean whitelist = true;

    public MageHandAmethystEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MageHandPutItemInChestGoal(this, 2, 20));
        this.goalSelector.add(2, new MageHandFilteredPickUpItemGoal(this, 2, 16));
        this.targetSelector.add(1, new MageHandCallForHelpGoal(this).setGroupRevenge(MageHandDiamondEntity.class, MageHandAmethystEntity.class, MageHandGoldEntity.class, MageHandHostileEntity.class));
        super.initGoals();
    }

    public boolean isWhitelist() {
        return whitelist;
    }

    public void flipMode() {
        this.whitelist = !this.whitelist;
    }

    @Override
    public Identifier getTexture() {
        return Declarar.getIdentifier("textures/amethyst.png");
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (this.getOffHandStack().isEmpty()) {
            this.equipStack(EquipmentSlot.OFFHAND, itemStack);
            this.setEquipmentDropChance(EquipmentSlot.OFFHAND, 0);
        } else if (itemStack.isOf(this.getOffHandStack().getItem())) {
            this.flipMode();
        } else if (this.getMainHandStack().isEmpty()) {
            this.equipStack(EquipmentSlot.MAINHAND, itemStack);
            this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 100f);
            player.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
        if (itemStack.isEmpty() && player.isSneaking()) {
            if (!this.getMainHandStack().isEmpty()) {
                this.dropStack(this.getMainHandStack());
                this.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            } else if (!this.getOffHandStack().isEmpty()) {
                this.equipStack(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
            }
        }
        return super.interactMob(player, hand);
    }
}