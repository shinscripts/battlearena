package characters.actions;

import characters.Character;
import characters.Mage;

public record HealAction(Mage mage) implements BattleAction {
    @Override
    public Character getOwner() {
        return mage;
    }

    @Override
    public ActionPhase getActionPhase() {
        return ActionPhase.HEAL;
    }

    @Override
    public void execute() {
        if (mage.isAlive()) {
            mage.performSpecialAttack();
        }
    }
}
