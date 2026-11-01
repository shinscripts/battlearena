package characters.actions;

import characters.Character;

public record AttackAction(characters.Character attacker, Character other) implements BattleAction {
    @Override
    public Character getOwner() {
        return attacker;
    }

    @Override
    public ActionPhase getActionPhase() {
        return ActionPhase.ATTACK;
    }

    @Override
    public void execute() {
        if (attacker.isAlive() && other.isAlive()) {
            attacker.performRegularAttack(other);
        }
    }
}
