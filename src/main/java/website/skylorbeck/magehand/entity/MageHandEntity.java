package website.skylorbeck.magehand.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MageHandEntity extends LivingEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "locomotion_controller", 5, this::locomotion_predicate));
    }

    private <E extends IAnimatable> PlayState locomotion_predicate(AnimationEvent<E> event) {
        MageHandEntity mageHand = (MageHandEntity) event.getAnimatable();
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.magehand.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
