package website.skylorbeck.magehand.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import website.skylorbeck.magehand.Declarar;
import website.skylorbeck.magehand.entity.goals.MageHandCallForHelpGoal;
import website.skylorbeck.magehand.entity.goals.MageHandFilteredPickUpItemGoal;
import website.skylorbeck.magehand.entity.goals.MageHandPutItemInChestGoal;

public class MageHandAmethystEntity extends MageHandFriendlyAbstractEntity {
    private static final TrackedData<Boolean> filterMode = DataTracker.registerData(MageHandAbstractEntity.class, TrackedDataHandlerRegistry.BOOLEAN);


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
        return this.dataTracker.get(filterMode);
    }

    public void flipMode() {
        this.dataTracker.set(filterMode, !this.dataTracker.get(filterMode));
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(filterMode,true);
        super.initDataTracker();
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putBoolean("whitelist",this.dataTracker.get(filterMode));
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.dataTracker.set(filterMode,nbt.getBoolean("whitelist"));
        super.readCustomDataFromNbt(nbt);
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
            this.equipStack(EquipmentSlot.OFFHAND, itemStack.copy());
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