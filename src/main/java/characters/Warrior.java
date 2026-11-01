package characters;

import characters.actions.BattleAction;
import characters.actions.ShieldAction;

import java.util.Map;

public class Warrior extends Character {
    private static final float DAMAGE_REDUCTION_WHEN_SHIELDED = 0.75f;

    private boolean isShielded = false;

    public Warrior(int health, int minDamage, int maxDamage) {
        super(health, minDamage, maxDamage);
    }

    @Override
    public void performSpecialAttack() {
        isShielded = true;
    }

    @Override
    public BattleAction createSpecialAction() {
        return new ShieldAction(this);
    }

    @Override
    public void damage(int amount) {
        super.damage(isShielded ? Math.round(amount * DAMAGE_REDUCTION_WHEN_SHIELDED) : amount);
    }

    @Override
    public Map<AttackType, String> getAttacks() {
        return Map.of(AttackType.Regular,
                String.format("Perform a regular attack with a damage between %d-%d", minDamage, maxDamage),
                AttackType.Special,
                String.format("Put up your shield, blocking %d%% of the next attacks damage inflicted on you", (int) (DAMAGE_REDUCTION_WHEN_SHIELDED * 100f)));

    }
}
