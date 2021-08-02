package com.game.tennis;

import com.game.tennis.enums.TennisRuleResultType;

public interface TennisRuleResult {
    TennisRuleResultType getType();

    Player getScorer();
}
