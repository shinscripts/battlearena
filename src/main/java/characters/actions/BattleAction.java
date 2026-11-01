package characters.actions;

import characters.Character;

public interface BattleAction {
    Character getOwner();

    ActionPhase getActionPhase();

    void execute();
}
