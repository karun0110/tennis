package com.game.tennis;

import com.game.tennis.enums.TennisRuleResultType;

public class Deuce extends AbstractTennisRule {
    public Deuce(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
    }

    @Override
    public boolean canBeApplied() {
        if (getPlayerOne().hasAdvantage() || getPlayerTwo().hasAdvantage()) {
            return false;
        }
        this.ruleResult = new TennisRuleResultImpl(TennisRuleResultType.DEUCE, null);
        return (getPlayerOne().getScore() == MAX_SCORE && getPlayerTwo().getScore() == MAX_SCORE);
    }
}
