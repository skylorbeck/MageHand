package website.skylorbeck.minecraft.magehand.entity;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import website.skylorbeck.minecraft.magehand.Declarar;

@SuppressWarnings("rawtypes")
public class MageHandSwordLayer extends GeoLayerRenderer {
    private static final Identifier MODEL = Declarar.getIdentifier("geo/hand.geo.json");

    @SuppressWarnings("unchecked")
    public MageHandSwordLayer(IGeoRenderer<?> entityRendererIn) {
        super(entityRendererIn);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, Entity MageHandEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        MageHandAbstractEntity mageHand = (MageHandAbstractEntity) MageHandEntity;
        ItemStack heldItem = mageHand.getMainHandStack();
        if (heldItem.isEmpty() || !(heldItem.getItem() instanceof SwordItem)) {
            return;
        }
        Identifier identifier = Declarar.getIdentifier("textures/" + (
                heldItem.isOf(Items.WOODEN_SWORD) ? "woodsword" :
                        heldItem.isOf(Items.IRON_SWORD) ? "ironsword" :
                                heldItem.isOf(Items.GOLDEN_SWORD) ? "goldsword" :
                                        heldItem.isOf(Items.NETHERITE_SWORD) ? "netheritesword" :
                                                heldItem.isOf(Items.DIAMOND_SWORD) ? "diamondsword" : "stonesword"
                )+".png"
        );
        RenderLayer cameo = RenderLayer.getEntityAlpha(identifier);
        matrixStackIn.push();
        //Move or scale the model as you see fit
        matrixStackIn.scale(1.0f, 1.0f, 1.0f);
        matrixStackIn.translate(0.0d, 0.0d, 0.0d);
        this.getRenderer().render(this.getEntityModel().getModel(MODEL), MageHandEntity, partialTicks, cameo, matrixStackIn, bufferIn,
                bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
        matrixStackIn.pop();
    }
}