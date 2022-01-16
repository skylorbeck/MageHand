package website.skylorbeck.magehand.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import website.skylorbeck.magehand.Declarar;
import website.skylorbeck.magehand.Magehand;

public class MageHandModel extends AnimatedGeoModel<MageHandAbstractEntity> {
    @Override
    public Identifier getModelLocation(MageHandAbstractEntity object) {
        return Declarar.getIdentifier("geo/hand.geo.json");
    }

    @Override
    public Identifier getTextureLocation(MageHandAbstractEntity object) {
        return Declarar.getIdentifier("textures/hand.png");
    }

    @Override
    public Identifier getAnimationFileLocation(MageHandAbstractEntity animatable) {
        return Declarar.getIdentifier("animations/hand.animation.json");
    }

    @Override
    public void setLivingAnimations(MageHandAbstractEntity entity, Integer uniqueID) {
        super.setLivingAnimations(entity, uniqueID);
    }

    @Override
    public IBone getBone(String boneName) {
        return super.getBone(boneName);
    }
}
