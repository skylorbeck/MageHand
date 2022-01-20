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
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import website.skylorbeck.magehand.Declarar;

@SuppressWarnings("rawtypes")
@Environment(value= EnvType.CLIENT)
public class MageHandHeldItemFeatureRenderer extends GeoLayerRenderer {
    private static final Identifier MODEL =  Declarar.getIdentifier("geo/hand.geo.json");

    @SuppressWarnings("unchecked")
    public MageHandHeldItemFeatureRenderer(IGeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }


    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int packedLightIn, Entity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemStack = ((LivingEntity)livingEntity).getEquippedStack(EquipmentSlot.MAINHAND);
        if (itemStack.getItem() instanceof SwordItem){
            return;
        }
        matrixStack.push();
        matrixStack.translate(0.05, 0.15,0);
        matrixStack.scale(0.75f,0.75f,0.75f);
        MinecraftClient.getInstance().getHeldItemRenderer().renderItem((LivingEntity)livingEntity, itemStack, ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, packedLightIn);
        matrixStack.pop();
    }
}

