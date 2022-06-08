package website.skylorbeck.minecraft.magehand.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import website.skylorbeck.minecraft.magehand.Declarar;

public class MageHandModel extends AnimatedGeoModel<MageHandAbstractEntity> {
    @Override
    public Identifier getModelResource(MageHandAbstractEntity object) {
        return Declarar.getIdentifier("geo/hand.geo.json");
    }

    @Override
    public Identifier getTextureResource(MageHandAbstractEntity object) {
        String name = object.getName().getString();
        if (name.equalsIgnoreCase("Chone")){
            return Declarar.getIdentifier("textures/glitch.png");
        } else
        if (name.equalsIgnoreCase("Striker")){
            return Declarar.getIdentifier("textures/wallmaster_blue.png");
        } else
        if (name.equalsIgnoreCase("CommissarGrey")){
            return Declarar.getIdentifier("textures/commissargrey.png");
        } else
        if (name.equalsIgnoreCase("R2zoo")){
            return Declarar.getIdentifier("textures/r2zoo.png");
        } else
        if (name.equalsIgnoreCase("Wally")){
            return Declarar.getIdentifier("textures/wallmaster.png");
        } else
        if (name.equalsIgnoreCase("cattamale")){
            return Declarar.getIdentifier("textures/floormaster.png");
        } else
        if (name.equalsIgnoreCase("SkylorBeck")){
            return Declarar.getIdentifier("textures/mouse.png");
        } else
        if (name.equalsIgnoreCase("Thanos")){
            return Declarar.getIdentifier("textures/thanos.png");
        } else
        if (name.equalsIgnoreCase("ironman")){
            return Declarar.getIdentifier("textures/ironman.png");
        } else
        if (name.equalsIgnoreCase("noobgamer")){
            return Declarar.getIdentifier("textures/noobgamer.png");
        } else
        return object.getTexture();
    }

    @Override
    public Identifier getAnimationResource(MageHandAbstractEntity animatable) {
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
