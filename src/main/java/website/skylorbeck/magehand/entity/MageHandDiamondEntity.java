package website.skylorbeck.magehand.entity;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import website.skylorbeck.magehand.entity.goals.MageHandLookAtBlockGoal;

public class MageHandDiamondEntity extends MageHandFriendlyAbstractEntity {
    private Block[] targetBlock = new Block[]{Blocks.DIAMOND_ORE,Blocks.DEEPSLATE_DIAMOND_ORE};
    public MageHandDiamondEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        if (targetBlock!=null)
        for (Block block : targetBlock) {
            this.goalSelector.add(1, new MageHandLookAtBlockGoal(this, block, 16));
        }
        super.initGoals();
    }

    public void setTargetBlock(Block[] blocks){
        this.targetBlock = blocks;
        this.goalSelector.clear();
        this.targetSelector.clear();
        this.initGoals();
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