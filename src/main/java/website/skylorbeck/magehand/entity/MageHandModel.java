package website.skylorbeck.magehand.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MageHandModel extends AnimatedGeoModel<MageHandEntity> {
    @Override
    public Identifier getModelLocation(MageHandEntity object) {
        return null;
    }

    @Override
    public Identifier getTextureLocation(MageHandEntity object) {
        return null;
    }

    @Override
    public Identifier getAnimationFileLocation(MageHandEntity animatable) {
        return null;
    }

    @Override
    public void setLivingAnimations(MageHandEntity entity, Integer uniqueID) {
        super.setLivingAnimations(entity, uniqueID);
    }

    @Override
    public IBone getBone(String boneName) {
        return super.getBone(boneName);
    }
}
