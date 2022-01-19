package website.skylorbeck.magehand.entity.goals;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import website.skylorbeck.magehand.entity.MageHandHostileEntity;

public class MageHandAttackGoal extends MeleeAttackGoal {
    private final MageHandHostileEntity mageHandHostile;
    private int ticks;

    public MageHandAttackGoal(MageHandHostileEntity mageHandHostile, double speed, boolean pauseWhenMobIdle) {
        super(mageHandHostile, speed, pauseWhenMobIdle);
        this.mageHandHostile = mageHandHostile;
    }

    @Override
    public void start() {
        super.start();
        this.ticks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.mageHandHostile.setAttacking(false);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.ticks;
        if (this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
            this.mageHandHostile.setAttacking(true);
        } else {
            this.mageHandHostile.setAttacking(false);
        }
    }
}
