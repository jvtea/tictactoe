package com.example.joost.tictactoe;

import java.io.Serializable;

public class Game implements Serializable {
    public static final int BOARD_SIZE = 3;
    public TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {

        TileState clickedTile = board[row][column];

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

        // checks for horizontal winner
        for (int i = 0; i < BOARD_SIZE; i++) {

            int h_circle = 0;
            int h_cross = 0;
            for (int j = 0; j < BOARD_SIZE; j++) {


                switch (board[i][j]) {
                    case CROSS:
                        h_cross += 1;
                        blank -= 1;
                        //System.out.println("cross: " + h_cross);
                        break;
                    case CIRCLE:
                        h_circle += 1;
                        blank -= 1;
                        //System.out.println("circle: " + h_circle);
                        break;
                }
            }

            if (h_cross == BOARD_SIZE) {
                //System.out.println("PLAYER ONE IS WINNER!");
                return GameState.PLAYER_ONE;
            } else if (h_circle == BOARD_SIZE) {
                //System.out.println("PLAYER TWO IS WINNER!");
                return GameState.PLAYER_TWO;
            }

        }
        // check if draw
        if (blank == 0) {
            //System.out.println("IN PROGRESS!");
            return GameState.DRAW;
        }

        // checks for vertical winner
        for (int i = 0; i < BOARD_SIZE; i++) {

            int h_circle = 0;
            int h_cross = 0;
            for (int j = 0; j < BOARD_SIZE; j++) {

                switch (board[j][i]) {
                    case CROSS:
                        h_cross += 1;
                        blank -= 1;
                        //System.out.println("cross: " + h_cross);
                        break;
                    case CIRCLE:
                        h_circle += 1;
                        blank -= 1;
                        //System.out.println("circle: " + h_circle);
                        break;
                }
            }

            if (h_cross == BOARD_SIZE) {
                //System.out.println("PLAYER ONE IS WINNER!");
                return GameState.PLAYER_ONE;
            } else if (h_circle == BOARD_SIZE) {
                //System.out.println("PLAYER TWO IS WINNER!");
                return GameState.PLAYER_TWO;
            }


        }
        //System.out.println("IN PROGRESS!");
        return GameState.IN_PROGRESS;
    }
}