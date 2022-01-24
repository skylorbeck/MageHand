package website.skylorbeck.magehand.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import website.skylorbeck.magehand.Declarar;
import website.skylorbeck.magehand.entity.goals.*;

public class MageHandDiamondEntity extends MageHandFriendlyAbstractEntity {
    public MageHandDiamondEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MageHandLookAtBlockGoal(this, Blocks.DIAMOND_ORE, 16));
        this.goalSelector.add(1, new MageHandLookAtBlockGoal(this, Blocks.DEEPSLATE_DIAMOND_ORE, 16));
        this.targetSelector.add(1, new MageHandCallForHelpGoal(this).setGroupRevenge(MageHandCopperEntity.class, MageHandDiamondEntity.class, MageHandGoldEntity.class, MageHandHostileEntity.class));
        super.initGoals();
    }

    @Override
    public Identifier getTexture() {
        return Declarar.getIdentifier("textures/diamond.png");
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        return super.interactMob(player, hand);
    }

    @Override
    public <E extends IAnimatable> PlayState locomotion_predicate(AnimationEvent<E> event) {
        MageHandAbstractEntity mageHand = (MageHandAbstractEntity) event.getAnimatable();

        if (mageHand != null) {
            if (mageHand.hasTrackedTarget()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.point", true));
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hand.idle", true));
            }
        }
        return PlayState.CONTINUE;
    }
}