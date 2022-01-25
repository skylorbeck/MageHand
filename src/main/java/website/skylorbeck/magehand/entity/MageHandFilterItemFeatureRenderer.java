package website.skylorbeck.magehand.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import website.skylorbeck.magehand.Declarar;

@SuppressWarnings("rawtypes")
@Environment(value= EnvType.CLIENT)
public class MageHandFilterItemFeatureRenderer extends GeoLayerRenderer {
    private static final Identifier MODEL = Declarar.getIdentifier("geo/hand.geo.json");

    @SuppressWarnings("unchecked")
    public MageHandFilterItemFeatureRenderer(IGeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }


    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int packedLightIn, Entity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemStack = ((LivingEntity) livingEntity).getOffHandStack();
        matrixStack.push();
        matrixStack.translate(0.05, 0.75, 0);
        matrixStack.scale(0.25f, 0.25f, 0.25f);
        MinecraftClient.getInstance().getHeldItemRenderer().renderItem((LivingEntity) livingEntity, itemStack, ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, packedLightIn);
        if (livingEntity instanceof MageHandAmethystEntity amethystEntity && !amethystEntity.isWhitelist()) {
            itemStack = Items.BARRIER.getDefaultStack();
            matrixStack.translate(0f, 0.05, -0.15);
            MinecraftClient.getInstance().getHeldItemRenderer().renderItem((LivingEntity) livingEntity, itemStack, ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, packedLightIn);
        }
        matrixStack.pop();
    }
}