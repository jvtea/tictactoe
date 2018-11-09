package com.example.joost.tictactoe;

import java.io.Serializable;

public class Game implements Serializable {
    public static final int BOARD_SIZE = 3;
    public TileState[][] board;

    // true if player 1's turn, false if player 2's turn
    private Boolean playerOneTurn;

    // initialize new game and set all TileStates to blank
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
    }

    public TileState choose(int row, int column) {

        TileState clickedTile = board[row][column];

        // return input if player clicked empty tile, depending on which player clicked
        switch (clickedTile) {
            case CIRCLE:
                return TileState.INVALID;
            case CROSS:
                return TileState.INVALID;
            case BLANK:
                if (playerOneTurn) {
                    playerOneTurn = false;
                    board[row][column] = TileState.CROSS;
                    return TileState.CROSS;
                } else {
                    playerOneTurn = true;
                    board[row][column] = TileState.CIRCLE;
                    return TileState.CIRCLE;
                }
        }
        return TileState.INVALID;
    }

    public GameState won() {
        // check if horizontal, vertical or diagonals are either all X's or al O's

        int blank = BOARD_SIZE * BOARD_SIZE;

        TileState winner = TileState.BLANK;

        // checks for horizontal winner
        for (int i = 0; i < BOARD_SIZE; i++) {

            // variables to count
            int circle = 0;
            int cross = 0;
            for (int j = 0; j < BOARD_SIZE; j++) {

                // count crosses and circles and count down blanks
                switch (board[i][j]) {
                    case CROSS:
                        cross += 1;
                        blank -= 1;
                        break;
                    case CIRCLE:
                        circle += 1;
                        blank -= 1;
                        break;
                }
            }

            // if 3 crosses or cirkels in row, return winner
            if (cross == BOARD_SIZE) {
                return GameState.PLAYER_ONE;
            } else if (circle == BOARD_SIZE) {
                return GameState.PLAYER_TWO;
            }

        }
        // check if draw
        if (blank == 0) {
            return GameState.DRAW;
        }

        // checks for vertical winner
        for (int i = 0; i < BOARD_SIZE; i++) {

            int circle = 0;
            int cross = 0;
            for (int j = 0; j < BOARD_SIZE; j++) {

                switch (board[j][i]) {
                    case CROSS:
                        cross += 1;
                        blank -= 1;
                        break;
                    case CIRCLE:
                        circle += 1;
                        blank -= 1;
                        break;
                }
            }

            // check for diagonal winner
            if (board[0][0] == board[1][1] &&
                board[0][0] == board[2][2]) {
                winner = board[0][0];
            }
            else if (board[0][2] == board [1][1] &&
                    board[0][2] == board [2][0]){
                winner = board[0][2];
            }

            // return winner
            if (cross == BOARD_SIZE) {
                return GameState.PLAYER_ONE;
            }
            else if (circle == BOARD_SIZE) {
                return GameState.PLAYER_TWO;
            }
            else if (winner == TileState.CROSS) {
                return GameState.PLAYER_ONE;
            }
            else if (winner == TileState.CIRCLE) {
                return GameState.PLAYER_TWO;
            }






        }
        //System.out.println("IN PROGRESS!");
        return GameState.IN_PROGRESS;
    }
}