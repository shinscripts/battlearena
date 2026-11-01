package characters;

import characters.actions.BattleAction;

import java.util.Map;

public abstract class Character implements Damageable {
    protected final int minDamage;
    protected final int maxDamage;
    protected final HealthComponent healthComponent;

    public Character(int health, int minDamage, int maxDamage) {
        healthComponent = new HealthComponent(health);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    @Override
    public void damage(int amount) {
        healthComponent.reduceHealth(amount);
    }

    public final void performRegularAttack(Damageable other) {
        int damageAmount = minDamage + (int) Math.round((Math.random() * (maxDamage - minDamage)));
        other.damage(damageAmount);
    }

    public boolean isAlive() {
        return healthComponent.getCurrentHealth() > 0f;
    }

    public final String getHealthStats() {
        return String.format("%s / %s", healthComponent.getCurrentHealth(), healthComponent.getMaxHealth());
    }

    public abstract Map<AttackType, String> getAttacks();

    public abstract void performSpecialAttack();

    public abstract BattleAction createSpecialAction();

}


