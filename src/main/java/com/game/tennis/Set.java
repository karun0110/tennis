package com.game.tennis;

import com.game.tennis.enums.WinningPlayer;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Objects;

public class Set implements Game {
    private final Pair<Player, Score> firstPlayer;
    private final Pair<Player, Score> secondPlayer;
    private WinningPlayer winningPlayer;
    private boolean tieBreak;

    private Set(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = Pair.of(Objects.requireNonNull(firstPlayer), new Score());
        this.secondPlayer = Pair.of(Objects.requireNonNull(secondPlayer), new Score());
        this.winningPlayer = WinningPlayer.NONE;
    }

    /**
     * Creates a newly initialized {@link TennisGame} object between two players.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @return a new game;
     */
    static Set between(String firstPlayer, String secondPlayer) {
        Player one = new Player(firstPlayer);
        Player two = new Player(secondPlayer);
        return new Set(one, two);
    }

    static Set between(Player firstPlayer, Player secondPlayer) {
        return new Set(firstPlayer, secondPlayer);
    }

    private static boolean isWinning(Pair<Player, Score> player, Pair<Player, Score> opponent) {
        return opponent.getValue().currentScore() <= 4 && player.getValue().currentScore() == 5;
    }

    private static boolean tieBreakRule(Pair<Player, Score> scoringPlayer,
                                        Pair<Player, Score> opponent,
                                        Runnable winAction) {
        if (scoringPlayer.getValue().currentScore() == (opponent.getValue().currentScore() + 1)) {
            winAction.run();
            return false;
        }
        return scoringPlayer.getValue().forceIncrement();
    }

    @Override
    public boolean incrementFirstPlayer() {
        if (isFinished()) {
            return false;
        }
        if (tieBreak) {
            return tieBreakRule(firstPlayer, secondPlayer, this::firstPlayerWins);
        }
        if (firstPlayerScore() == 5 && secondPlayerScore() == 6) {
            tieBreak = true;
            return firstPlayer.getValue().updateSet();
        }
        if (isWinning(firstPlayer, secondPlayer)) {
            firstPlayerWins();
            return false;
        }
        return firstPlayer.getValue().updateSet();
    }

    private void firstPlayerWins() {
        firstPlayer.getValue().updateSet();
        winningPlayer = WinningPlayer.PLAYER_ONE;
    }

    @Override
    public boolean incrementSecondPlayer() {
        if (isFinished()) {
            return false;
        }
        if (tieBreak) {
            return tieBreakRule(secondPlayer, firstPlayer, this::secondPlayerWins);
        }
        if (firstPlayerScore() == 6 && secondPlayerScore() == 5) {
            tieBreak = true;
            return secondPlayer.getValue().updateSet();
        }
        if (isWinning(secondPlayer, firstPlayer)) {
            secondPlayerWins();
            return false;
        }
        return secondPlayer.getValue().updateSet();
    }

    private void secondPlayerWins() {
        secondPlayer.getValue().updateSet();
        winningPlayer = WinningPlayer.PLAYER_TWO;
    }

    @Override
    public boolean isFinished() {
        return !winningPlayer.equals(WinningPlayer.NONE);
    }

    @Override
    public Player getFirstPlayer() {
        return firstPlayer.getKey();
    }

    @Override
    public Player getSecondPlayer() {
        return secondPlayer.getKey();
    }

    @Override
    public int firstPlayerScore() {
        return firstPlayer.getValue().currentScore();
    }

    @Override
    public int secondPlayerScore() {
        return secondPlayer.getValue().currentScore();
    }

    @Override
    public WinningPlayer getWinningPlayer() {
        return winningPlayer;
    }
}
