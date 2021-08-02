package com.game.tennis;

import com.game.tennis.enums.TennisRuleResultType;

public class TennisRuleResultImpl implements TennisRuleResult {
    private TennisRuleResultType type;
    private Player scorer;

    public TennisRuleResultImpl(TennisRuleResultType type, Player scorer) {
        this.type = type;
        this.scorer = scorer;
    }

    @Override
    public TennisRuleResultType getType() {
        return this.type;
    }

    @Override
    public Player getScorer() {
        return this.scorer;
    }
}
