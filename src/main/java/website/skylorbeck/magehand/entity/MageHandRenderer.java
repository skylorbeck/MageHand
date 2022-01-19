package website.skylorbeck.magehand.entity;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.awt.*;

public class MageHandRenderer extends GeoEntityRenderer<MageHandAbstractEntity> {
    public MageHandRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new MageHandModel());
    }

    @Override
    public RenderLayer getRenderType(MageHandAbstractEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(textureLocation);
    }

    @Override
    public Color getRenderColor(MageHandAbstractEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn) {
        return new Color(255, 255, 255, 155);
    }
}
