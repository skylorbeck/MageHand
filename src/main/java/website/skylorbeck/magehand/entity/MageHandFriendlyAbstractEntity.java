package website.skylorbeck.magehand.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import website.skylorbeck.magehand.Declarar;
import website.skylorbeck.magehand.entity.goals.ReturnToStartGoal;

import java.util.Random;

public abstract class MageHandFriendlyAbstractEntity extends MageHandAbstractEntity{
    public MageHandFriendlyAbstractEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
    @Override
    public Identifier getTexture() {
        return Declarar.getIdentifier("textures/copper.png");
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(7, new ReturnToStartGoal(this,1,40));
        super.initGoals();
    }

    @Override
    protected boolean shouldDropXp() {
        return false;
    }
}

