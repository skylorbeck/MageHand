package website.skylorbeck.magehand.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
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


public abstract class MageHandAbstractEntity extends PathAwareEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final TrackedData<Integer> trackedTarget = DataTracker.registerData(MageHandAbstractEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private BlockPos startingPos;


    protected MageHandAbstractEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this,45,true);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.startingPos = this.getBlockPos();
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putDouble("startX",this.startingPos.getX());
        nbt.putDouble("startY",this.startingPos.getY());
        nbt.putDouble("startZ",this.startingPos.getZ());
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.startingPos = new BlockPos(nbt.getDouble("startX"),nbt.getDouble("startY"),nbt.getDouble("startZ"));
        super.readCustomDataFromNbt(nbt);
    }

    public abstract Identifier getTexture();

    @Override
    protected void initGoals() {
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
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

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
    }

    @Override
    public void tick() {
        this.goalSelector.getRunningGoals().forEach((goal)-> {
            //Logger.getGlobal().log(Level.SEVERE,goal.getGoal().toString())
        });
        super.tick();
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

        if (mageHand != null) {
            if (mageHand.hasTrackedTarget()) {
                Entity target = mageHand.world.getEntityById(mageHand.getTrackedTarget());
                float distance = mageHand.distanceTo(target);
                if (mageHand.getMainHandStack().isEmpty()) {
                    if (distance > 8) {
                        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.point", true));
                    } else if (distance < 3) {
                        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.punch", true));
                    } else {
                        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.fist", true));
                    }
                } else {
                    if (distance < 3) {
                        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.slash", true));
                    } else {
                        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.fist", true));
                    }
                }
            } else if (!mageHand.getMainHandStack().isEmpty()) {
                if (mageHand.getMainHandStack().getItem() instanceof SwordItem){
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.fist", true));
                } else {
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.holdingitem", true));
                }
            } else {
                if (mageHand.random.nextFloat()<= 0.05f) {
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.stretch", true));
                } else {
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.idle", true));
                }
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

    public BlockPos getStartingPos() {
        return this.startingPos;
    }
}
