package characters;

import characters.actions.BattleAction;
import characters.actions.HealAction;

import java.util.Map;

public class Mage extends Character {
    public static final int HEAL_AMOUNT = 10;

    public Mage(int health, int minDamage, int maxDamage) {
        super(health, minDamage, maxDamage);
    }

    @Override
    public void performSpecialAttack() {
        healthComponent.increaseHealth(HEAL_AMOUNT);
    }

    @Override
    public BattleAction createSpecialAction() {
        return new HealAction(this);
    }

    @Override
    public Map<AttackType, String> getAttacks() {
        return Map.of(AttackType.Regular,
                String.format("Perform a regular attack with a damage between %d-%d", minDamage, maxDamage),
                AttackType.Special,
                String.format("Cast a spell, healing yourself with %d health", HEAL_AMOUNT));
    }
}
