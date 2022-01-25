package website.skylorbeck.magehand.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import website.skylorbeck.magehand.Declarar;

import java.util.Map;
import java.util.function.BiFunction;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @Inject(method = "apply", at = @At("HEAD"))
    public void interceptApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        BiFunction<JsonObject, String, ?> createMap = (json, id) -> {
            if (json != null) {
                map.put(new Identifier(Declarar.MODID, id), json);
                return true;
            }
            return false;
        };
        createMap.apply(Declarar.MAGE_HAND_BONE,"mage_hand_bone");
        createMap.apply(Declarar.MAGE_HAND_FLESH,"mage_hand_flesh");
        createMap.apply(Declarar.MAGE_HAND_COPPER,"mage_hand_copper");
        createMap.apply(Declarar.MAGE_HAND_IRON,"mage_hand_iron");
        createMap.apply(Declarar.MAGE_HAND_GOLD,"mage_hand_gold");
        createMap.apply(Declarar.MAGE_HAND_DIAMOND,"mage_hand_diamond");
        createMap.apply(Declarar.MAGE_HAND_AMETHYST,"mage_hand_amethyst");

    }
}
