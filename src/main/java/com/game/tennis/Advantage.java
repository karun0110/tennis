package com.game.tennis;

import com.game.tennis.enums.TennisRuleResultType;

public class Advantage extends AbstractTennisRule{
    public Advantage(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
    }

    @Override
    public boolean canBeApplied() {
        boolean canBeApplied = getPlayerOne().getScore() == AbstractTennisRule.MAX_SCORE || getPlayerTwo().getScore() == AbstractTennisRule.MAX_SCORE;
        this.ruleResult = getPlayerOne().getScore() == AbstractTennisRule.MAX_SCORE
                ? new TennisRuleResultImpl(TennisRuleResultType.ADVANTAGE, getPlayerOne())
                : new TennisRuleResultImpl(TennisRuleResultType.ADVANTAGE, getPlayerTwo());
        return canBeApplied;
    }
}
