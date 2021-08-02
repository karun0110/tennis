package com.game.tennis;

import java.util.Objects;

public class Player {
    private final String name;
    private final Score score;
    private boolean hasAdvantage;

    public Player(String name) {
        this.name = Objects.requireNonNull(name, "Player name should not be null");
        this.score = new Score();
    }


    public boolean incrementScore() {
        return score.increment();
    }


    public String getName() {
        return name;
    }


    public int getScore() {
        return score.currentScore();
    }

    public boolean hasAdvantage() {
        return hasAdvantage;
    }

    void setAdvantage(boolean advantage) {
        hasAdvantage = advantage;
    }

    void resetScore() {
        score.resetScore();
    }
}
