package website.skylorbeck.magehand.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import website.skylorbeck.magehand.Declarar;

public class MageHandModel extends AnimatedGeoModel<MageHandAbstractEntity> {
    @Override
    public Identifier getModelLocation(MageHandAbstractEntity object) {
        return Declarar.getIdentifier("geo/hand.geo.json");
    }

    @Override
    public Identifier getTextureLocation(MageHandAbstractEntity object) {
        return object.getTexture();
    }

    @Override
    public Identifier getAnimationFileLocation(MageHandAbstractEntity animatable) {
        return Declarar.getIdentifier("animations/hand.animation.json");
    }

    @Override
    public void setLivingAnimations(MageHandAbstractEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
//        IBone sword = this.getAnimationProcessor().getBone("sword");
//        ItemStack itemstack = entity.getMainHandStack();
//        sword.setHidden(!(itemstack.getItem() instanceof SwordItem));
    }

}
