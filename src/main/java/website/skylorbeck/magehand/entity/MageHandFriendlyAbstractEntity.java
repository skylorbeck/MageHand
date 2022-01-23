package website.skylorbeck.magehand.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import website.skylorbeck.magehand.Declarar;
import website.skylorbeck.magehand.entity.goals.ReturnToStartGoal;

import java.util.Optional;

public abstract class MageHandFriendlyAbstractEntity extends MageHandAbstractEntity{
    public MageHandFriendlyAbstractEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
    @Override
    public Identifier getTexture() {
        return Declarar.getIdentifier("textures/copper.png");
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(7, new ReturnToStartGoal(this,1,40));
        super.initGoals();
    }
    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        this.setEquipmentDropChance(EquipmentSlot.MAINHAND,100f);
        super.equipStack(slot,stack);
    }

    @Override
    protected boolean shouldDropXp() {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        Optional<Entity> damSource = Optional.ofNullable(source.getSource());
        if (damSource.isPresent() && damSource.get().isPlayer()){
            this.kill();
            return true;
        }
        return super.damage(source, amount);
    }
}

