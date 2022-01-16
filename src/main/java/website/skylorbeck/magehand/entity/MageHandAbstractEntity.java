package website.skylorbeck.magehand.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class MageHandAbstractEntity extends MobEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    protected MageHandAbstractEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }



    //gecko
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "locomotion_controller", 5, this::locomotion_predicate));
        animationData.addAnimationController(new AnimationController(this, "hover_controller", 5, this::hover_predicate));
    }

    private <E extends IAnimatable> PlayState locomotion_predicate(AnimationEvent<E> event) {
        MageHandAbstractEntity mageHand = (MageHandAbstractEntity) event.getAnimatable();
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.idle", true));
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
