package website.skylorbeck.magehand.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MageHandAbstractEntity extends HostileEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final TrackedData<Integer> trackedTarget = DataTracker.registerData(MageHandAbstractEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected MageHandAbstractEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);

    }

    public abstract Identifier getTexture();

    @Override
    protected void initGoals() {
        this.goalSelector.add(5, new GoToWalkTargetGoal(this, 1.0));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0, 0.0f));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this).setGroupRevenge());
        super.initGoals();
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(trackedTarget,0);
        super.initDataTracker();
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        this.dataTracker.set(trackedTarget, target == null ? 0 : target.getId());
        super.setTarget(target);
    }


    public boolean hasTrackedTarget(){
        return !this.dataTracker.get(trackedTarget).equals(0);
    }
    public int getTrackedTarget(){
        return this.dataTracker.get(trackedTarget);
    }


    //gecko
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "locomotion_controller", 5, this::locomotion_predicate));
        animationData.addAnimationController(new AnimationController<>(this, "hover_controller", 5, this::hover_predicate));
    }

    private <E extends IAnimatable> PlayState locomotion_predicate(AnimationEvent<E> event) {
        MageHandAbstractEntity mageHand = (MageHandAbstractEntity) event.getAnimatable();
        if (mageHand.hasTrackedTarget()){
            Entity target = mageHand.world.getEntityById(mageHand.getTrackedTarget());
            float distance = mageHand.distanceTo(target);
            if (distance>8){
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.point", true));
            } else if (distance<3){
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.punch", true));
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.fist", true));
            }
        } else {
            if (mageHand.world.random.nextFloat() <= 0.05f) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.stretch", true));
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.idle", true));
            }
        }
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState hover_predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.float", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
