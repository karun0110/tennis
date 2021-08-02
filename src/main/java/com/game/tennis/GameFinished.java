package com.game.tennis;

import com.game.tennis.enums.TennisRuleResultType;

public class GameFinished extends AbstractTennisRule {
    public GameFinished(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
    }

    @Override
    public boolean canBeApplied() {
        boolean canBeApplied = getPlayerOne().getScore() > AbstractTennisRule.MAX_SCORE || getPlayerTwo().getScore() > MAX_SCORE;
        this.ruleResult = getPlayerOne().getScore() > getPlayerTwo().getScore()
                ? new TennisRuleResultImpl(TennisRuleResultType.FINISHED, getPlayerOne())
                : new TennisRuleResultImpl(TennisRuleResultType.FINISHED, getPlayerTwo());
        return canBeApplied;
    }
}
