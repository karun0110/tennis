package com.game.tennis;

public abstract class AbstractTennisRule {
    static final int MAX_SCORE = 40;
    private final Player playerOne;
    private final Player playerTwo;
    protected TennisRuleResult ruleResult;

    AbstractTennisRule(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    abstract boolean  canBeApplied();

    public TennisRuleResult getResult() {
        return ruleResult;
    }

    protected Player getPlayerOne() {
        return this.playerOne;
    }

    protected Player getPlayerTwo() {
        return playerTwo;
    }
}
