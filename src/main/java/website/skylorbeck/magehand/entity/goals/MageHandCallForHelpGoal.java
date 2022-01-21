package website.skylorbeck.magehand.entity.goals;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import website.skylorbeck.magehand.entity.MageHandIronEntity;

import java.util.List;

public class MageHandCallForHelpGoal extends RevengeGoal {

    public MageHandCallForHelpGoal(PathAwareEntity mob, Class<?>... noRevengeTypes) {
        super(mob, noRevengeTypes);
    }

    @Override
    protected void callSameTypeForRevenge() {
        double d = this.getFollowRange();
        Box box = Box.from(this.mob.getPos()).expand(d, 10.0, d);
        List<MageHandIronEntity> list = this.mob.world.getEntitiesByClass(MageHandIronEntity.class, box, EntityPredicates.EXCEPT_SPECTATOR);
        for (MobEntity mobEntity : list) {
            if (this.mob == mobEntity || mobEntity.getTarget() != null || this.mob instanceof TameableEntity && ((TameableEntity)this.mob).getOwner() != ((TameableEntity)mobEntity).getOwner() || mobEntity.isTeammate(this.mob.getAttacker())) continue;
            this.setMobEntityTarget(mobEntity, this.mob.getAttacker());
        }
        super.callSameTypeForRevenge();
    }
}
