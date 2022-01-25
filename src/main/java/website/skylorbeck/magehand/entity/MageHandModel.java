package website.skylorbeck.magehand.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import website.skylorbeck.magehand.Declarar;

public class MageHandModel extends AnimatedGeoModel<MageHandAbstractEntity> {
    @Override
    public Identifier getModelLocation(MageHandAbstractEntity object) {
        return Declarar.getIdentifier("geo/hand.geo.json");
    }

    @Override
    public Identifier getTextureLocation(MageHandAbstractEntity object) {
        if (object.getName().asString().equalsIgnoreCase("Chone")){
            return Declarar.getIdentifier("textures/glitch.png");
        } else
        if (object.getName().asString().equalsIgnoreCase("Striker")){
            return Declarar.getIdentifier("textures/wallmaster_blue.png");
        } else
        if (object.getName().asString().equalsIgnoreCase("CommissarGrey")){
            return Declarar.getIdentifier("textures/commissargrey.png");
        } else
        if (object.getName().asString().equalsIgnoreCase("R2zoo")){
            return Declarar.getIdentifier("textures/r2zoo.png");
        } else
        if (object.getName().asString().equalsIgnoreCase("Wally")){
            return Declarar.getIdentifier("textures/wallmaster.png");
        } else
        if (object.getName().asString().equalsIgnoreCase("cattamale")){
            return Declarar.getIdentifier("textures/floormaster.png");
        } else
        if (object.getName().asString().equalsIgnoreCase("SkylorBeck")){
            return Declarar.getIdentifier("textures/mouse.png");
        } else
        if (object.getName().asString().equalsIgnoreCase("Thanos")){
            return Declarar.getIdentifier("textures/thanos.png");
        } else
        if (object.getName().asString().equalsIgnoreCase("ironman")){
            return Declarar.getIdentifier("textures/ironman.png");
        } else
        return object.getTexture();
    }

    @Override
    public Identifier getAnimationFileLocation(MageHandAbstractEntity animatable) {
        return Declarar.getIdentifier("animations/hand.animation.json");
    }

    @Override
    public void setLivingAnimations(MageHandAbstractEntity entity, Integer uniqueID, AnimationEvent customPredicate) {

        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("shell");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }

}
