package website.skylorbeck.magehand.client;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.impl.client.rendering.EntityRendererRegistryImpl;
import website.skylorbeck.magehand.Declarar;
import website.skylorbeck.magehand.HandConfig;
import website.skylorbeck.magehand.entity.MageHandRenderer;

@Environment(EnvType.CLIENT)
public class MagehandClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AutoConfig.getGuiRegistry(HandConfig.class);

        EntityRendererRegistryImpl.register(Declarar.MAGE_HAND_HOSTILE_ENTITY_TYPE, MageHandRenderer::new);
        EntityRendererRegistryImpl.register(Declarar.MAGE_HAND_COPPER_ENTITY_TYPE, MageHandRenderer::new);
        EntityRendererRegistryImpl.register(Declarar.MAGE_HAND_IRON_ENTITY_TYPE, MageHandRenderer::new);
        EntityRendererRegistryImpl.register(Declarar.MAGE_HAND_GOLD_ENTITY_TYPE, MageHandRenderer::new);
        EntityRendererRegistryImpl.register(Declarar.MAGE_HAND_DIAMOND_ENTITY_TYPE, MageHandRenderer::new);
        EntityRendererRegistryImpl.register(Declarar.MAGE_HAND_AMETHYST_ENTITY_TYPE, MageHandRenderer::new);
    }
}
