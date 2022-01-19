package website.skylorbeck.magehand.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import website.skylorbeck.magehand.Declarar;

public class MageHandHostileEntity extends MageHandAbstractEntity{
    public MageHandHostileEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1,new MageHandAttackGoal(this,1f,false));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true,true));
        super.initGoals();
    }


    @Override
    public Identifier getTexture() {
        return Declarar.getIdentifier("textures/hand.png");
    }
}

