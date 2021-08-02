package com.game.tennis;

public class TennisApp {
    public static void main(String[] args) {
        Match match = Match.tennisMatchPrompt();
        match.run();
    }

}
