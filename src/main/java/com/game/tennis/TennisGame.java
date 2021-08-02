package com.game.tennis;

import com.game.tennis.enums.WinningPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TennisGame implements Game{
    private static final int MAX_SCORE = 40;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private WinningPlayer winningPlayer;
    private List<AbstractTennisRule> rules = new ArrayList<>();

    private TennisGame(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = Objects.requireNonNull(firstPlayer);
        this.secondPlayer = Objects.requireNonNull(secondPlayer);
        this.winningPlayer = WinningPlayer.NONE;
        this.rules.add(new Advantage(firstPlayer, secondPlayer));
        this.rules.add(new Deuce(firstPlayer, secondPlayer));
        this.rules.add(new GameFinished(firstPlayer, secondPlayer));
    }

    /**
     * Creates a newly initialized {@link TennisGame} object between two players.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @return a new game;
     */
    static public TennisGame between(String firstPlayer, String secondPlayer) {
        Player one = new Player(firstPlayer);
        Player two = new Player(secondPlayer);
        return new TennisGame(one, two);
    }

    static public TennisGame between(Player firstPlayer, Player secondPlayer) {
        return new TennisGame(firstPlayer, secondPlayer);
    }

    /**
     * Increments the first player score. If the game is finished this method returns false.
     *
     * @return true if the score has been incremented, false otherwise
     */
    @Override
    public boolean incrementFirstPlayer() {
        return this.incrementPlayerScore(firstPlayer, secondPlayer);
    }

    @Override
    public boolean incrementSecondPlayer() {
        return this.incrementPlayerScore(secondPlayer, firstPlayer);
    }

    private boolean incrementPlayerScore(Player toIncrement, Player opponent) {
        if (isFinished()) {
            return false;
        }
        if (opponent.hasAdvantage()) {
            opponent.setAdvantage(false);
            return true;
        }
        if (isDeuce()) {
            toIncrement.setAdvantage(true);
            opponent.setAdvantage(false);
            return true;
        }
        if (toIncrement.getScore() == MAX_SCORE || toIncrement.hasAdvantage()) {
            winningPlayer = WinningPlayer.PLAYER_TWO;
            return false;
        }
        return toIncrement.incrementScore();
    }

    /**
     * @return true if the game is finished, false otherwise
     */
    @Override
    public boolean isFinished() {
        return !winningPlayer.equals(WinningPlayer.NONE);
    }

    public boolean  isDeuce() {
        if (firstPlayer.hasAdvantage() || secondPlayer.hasAdvantage()) {
            return false;
        }
        return (firstPlayer.getScore() == MAX_SCORE && secondPlayer.getScore() == MAX_SCORE);
    }

    @Override
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public Player getSecondPlayer() {
        return secondPlayer;
    }

    @Override
    public int firstPlayerScore() {
        return firstPlayer.getScore();
    }

    @Override
    public int secondPlayerScore() {
        return secondPlayer.getScore();
    }

    @Override
    public WinningPlayer getWinningPlayer() {
        return winningPlayer;
    }

    void reset() {
        firstPlayer.resetScore();
        secondPlayer.resetScore();
        winningPlayer = WinningPlayer.NONE;
    }
}
