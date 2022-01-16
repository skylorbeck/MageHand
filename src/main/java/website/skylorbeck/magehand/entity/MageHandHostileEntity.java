package website.skylorbeck.magehand.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

public class MageHandHostileEntity extends MageHandAbstractEntity{
    public MageHandHostileEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
}
