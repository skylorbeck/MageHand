package website.skylorbeck.magehand.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import website.skylorbeck.magehand.Declarar;
import website.skylorbeck.magehand.entity.goals.MageHandAttackGoal;

public class MageHandHostileEntity extends MageHandAbstractEntity{
    public MageHandHostileEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new FleeEntityGoal<WolfEntity>(this, WolfEntity.class, 6f, 1, 1));
        this.goalSelector.add(2,new MageHandAttackGoal(this,1f,false));
        this.goalSelector.add(5, new GoToWalkTargetGoal(this, 1.0f));
        this.goalSelector.add(7, new FlyGoal(this, 1.0f));
        this.targetSelector.add(2, new RevengeGoal(this).setGroupRevenge());
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PlayerEntity.class, true,true));
        super.initGoals();
    }
    @Override
    protected boolean isDisallowedInPeaceful() {
        return true;
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    @Override
    protected boolean shouldDropXp() {
        return true;
    }

    @Override
    public Identifier getTexture() {
        return Declarar.getIdentifier("textures/hand.png");
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PARROT_FLY;
    }
}

