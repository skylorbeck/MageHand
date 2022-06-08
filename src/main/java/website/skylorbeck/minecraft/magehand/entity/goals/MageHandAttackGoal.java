package website.skylorbeck.minecraft.magehand.entity.goals;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import website.skylorbeck.minecraft.magehand.entity.MageHandAbstractEntity;

public class MageHandAttackGoal extends MeleeAttackGoal {
    private final MageHandAbstractEntity mageHand;
    private int ticks;

    public MageHandAttackGoal(MageHandAbstractEntity mageHand, double speed, boolean pauseWhenMobIdle) {
        super(mageHand, speed, pauseWhenMobIdle);
        this.mageHand = mageHand;
    }

    @Override
    public void start() {
        super.start();
        this.ticks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.mageHand.setAttacking(false);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.ticks;
        if (this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
            this.mageHand.setAttacking(true);
        } else {
            this.mageHand.setAttacking(false);
        }
    }
}
