package website.skylorbeck.magehand.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MageHandRenderer extends GeoEntityRenderer<MageHandEntity> {
    protected MageHandRenderer(EntityRendererFactory.Context ctx, AnimatedGeoModel<MageHandEntity> modelProvider) {
        super(ctx, modelProvider);
    }
}
