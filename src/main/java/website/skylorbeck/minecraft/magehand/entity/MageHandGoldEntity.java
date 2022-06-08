package website.skylorbeck.minecraft.magehand.entity;

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
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import website.skylorbeck.minecraft.magehand.Declarar;
import website.skylorbeck.minecraft.magehand.entity.goals.*;

public class MageHandGoldEntity extends MageHandFriendlyAbstractEntity{
    public static Identifier[] seedables = {
            Registry.ITEM.getId(Items.POTATO),
            Registry.ITEM.getId(Items.CARROT),
            Registry.ITEM.getId(Items.BEETROOT_SEEDS),
            Registry.ITEM.getId(Items.WHEAT_SEEDS),
    };
    public MageHandGoldEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1,new MageHandHarvestPlantGoal(this,1,16));
        this.goalSelector.add(2,new MageHandResupplySeedsGoal(this,1,16));
        this.goalSelector.add(3,new MageHandPlantSeedGoal(this,1,16));
        this.goalSelector.add(4,new MageHandUseBoneMealGoal(this,1,16));
        this.targetSelector.add(1, new MageHandCallForHelpGoal(this).setGroupRevenge(MageHandDiamondEntity.class,MageHandCopperEntity.class,MageHandGoldEntity.class,MageHandHostileEntity.class));
        super.initGoals();
    }

    @Override
    public Identifier getTexture() {
        return Declarar.getIdentifier("textures/gold.png");
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        for (Identifier seedable : seedables) {
            Item seed = Registry.ITEM.get(seedable);
            if (this.getMainHandStack().isEmpty()) {
                if (itemStack.isOf(seed)) {
                    this.equipStack(EquipmentSlot.MAINHAND, itemStack);
                    this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 100f);
                    player.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                }
            } else {
                if (itemStack.isOf(seed)) {
                    this.dropStack(this.getMainHandStack());
                    this.equipStack(EquipmentSlot.MAINHAND, itemStack);
                    this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 100f);
                    player.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                }
            }
        }
        if (itemStack.isOf(Items.BONE_MEAL)) {
            if (this.getOffHandStack().isEmpty()) {
                this.equipStack(EquipmentSlot.OFFHAND, itemStack);
                this.setEquipmentDropChance(EquipmentSlot.OFFHAND, 100f);
                player.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            }
        } else if (itemStack.isEmpty() && player.isSneaking()) {
            this.dropStack(this.getMainHandStack());
            this.dropStack(this.getOffHandStack());
            this.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            this.equipStack(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
        }
        return super.interactMob(player, hand);
    }
}

