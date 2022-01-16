package website.skylorbeck.magehand.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MageHandRenderer extends GeoEntityRenderer<MageHandAbstractEntity> {
    public MageHandRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new MageHandModel());
    }
}
