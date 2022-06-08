package website.skylorbeck.minecraft.magehand.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Quaternion;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import website.skylorbeck.minecraft.magehand.Declarar;

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
        MinecraftClient.getInstance().getItemRenderer().renderItem((LivingEntity) livingEntity,itemStack, ModelTransformation.Mode.GROUND,false,matrixStack,vertexConsumerProvider,livingEntity.world,packedLightIn, OverlayTexture.DEFAULT_UV,0);
//        MinecraftClient.getInstance().getItemRenderer().renderItem((LivingEntity) livingEntity, itemStack, ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, packedLightIn);
        if (livingEntity instanceof MageHandAmethystEntity amethystEntity && !amethystEntity.isWhitelist()) {
            itemStack = Items.BARRIER.getDefaultStack();
            matrixStack.translate(0f, 0.05, -0.15);
            MinecraftClient.getInstance().getItemRenderer().renderItem((LivingEntity) livingEntity,itemStack, ModelTransformation.Mode.GROUND,false,matrixStack,vertexConsumerProvider,livingEntity.world,packedLightIn,OverlayTexture.DEFAULT_UV,0);
        } else if (livingEntity instanceof MageHandGoldEntity goldEntity && goldEntity.getOffHandStack().getCount()>0){
            renderLabelIfPresent(goldEntity,matrixStack,vertexConsumerProvider,packedLightIn);
        }
        matrixStack.pop();
    }

    public static void renderLabelIfPresent(LivingEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        EntityRenderDispatcher dispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        double d = dispatcher.getSquaredDistanceToCamera(entity);
        if (d > 4096.0) {
            return;
        }
        String title = "x"+entity.getOffHandStack().getCount();

        matrices.translate(-0.5, 0.2f, 0.0);
        matrices.multiply(Quaternion.fromEulerXyz(entity.getMovementDirection().asRotation(),0,0));
        matrices.scale(-0.025f, -0.025f, 0.025f);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        float g = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25f);
        int j = (int) (g * 255.0f) << 24;
        float width = -textRenderer.getWidth(title) * 0.5f;
        textRenderer.draw(Text.of(title), width, 0, 0x20FFFFFF, false, matrix4f, vertexConsumers, true, j, light);
        textRenderer.draw(Text.of(title), width, 0, -1, false, matrix4f, vertexConsumers, false, 0, light);
    }
}