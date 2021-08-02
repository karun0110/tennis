package com.game.tennis;

public class Score {
    private int score;
    private final static int MAX_SCORE = 7;

    public Score(){
        this.score = 0;
    }

    public Score(int score) {
        if( score != 0 && score != 15 && score != 30 && score != 40){
            throw new IllegalArgumentException(String.format("%d is not a tennis score", score));
        }
        this.score = score;

    }


    public boolean updateSet() {
        if( score >= MAX_SCORE){
            return false;
        }
        score ++;
        return true;
    }
    public boolean increment() {
        if( score >= 40 ){
            return false;
        }
        if (score == 0 || score == 15) {
            score += 15;
        } else if (score == 30) {
            score = 40;
        }
        return true;
    }
    public boolean forceIncrement(){
        score ++;
        return true;
    }


    public int currentScore() {
        return score;
    }


    public void reset() {
        throw new IllegalStateException("This method should net be used for this object.");
    }

    public void resetScore(){
        score = 0;
    }
}


