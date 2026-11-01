package characters.actions;

import characters.Character;
import characters.Warrior;

public record ShieldAction(Warrior warrior) implements BattleAction {
    @Override
    public Character getOwner() {
        return warrior;
    }

    @Override
    public ActionPhase getActionPhase() {
        return ActionPhase.DEFENSE;
    }

    @Override
    public void execute() {
        if (warrior.isAlive()) {
            warrior.performSpecialAttack();
        }
    }
}
