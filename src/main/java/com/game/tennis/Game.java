package com.game.tennis;

import com.game.tennis.enums.WinningPlayer;

import java.util.Optional;

public interface Game {

    boolean incrementFirstPlayer();

    boolean incrementSecondPlayer();

    boolean isFinished();

    Player getFirstPlayer();

    Player getSecondPlayer();

    int firstPlayerScore();

    int secondPlayerScore();

    WinningPlayer getWinningPlayer();

    default Optional<Player> getWinner(){
        if (getWinningPlayer().equals(WinningPlayer.NONE)) {
            return Optional.empty();
        }
        return getWinningPlayer().equals(WinningPlayer.PLAYER_ONE) ?
                Optional.of(getFirstPlayer()) :
                Optional.of(getSecondPlayer());
    }
}
