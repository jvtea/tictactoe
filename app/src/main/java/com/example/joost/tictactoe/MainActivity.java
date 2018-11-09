package com.example.joost.tictactoe;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Game game;


    // BOARD_SIZE!!!
    public String[][] buttons = new String[game.BOARD_SIZE][game.BOARD_SIZE];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // don't perform setting of button texts if no savedInstance state yet
        if (savedInstanceState == null) {
            game = new Game();

            // make array list containing all buttons
            for (int i = 0; i < game.BOARD_SIZE; i++) {
                for (int j = 0; j < game.BOARD_SIZE; j++) {
                    buttons[i][j] = "button" + i + "_" + j;
                }
            }
        }
        else {
            game = (Game) savedInstanceState.getSerializable("game");



            for (int i = 0; i < game.BOARD_SIZE; i++) {
                for (int j = 0; j < game.BOARD_SIZE; j++) {

                    System.out.println("Hier nog wel!");
                    System.out.println(game.board[i][j]);

                    buttons[i][j] = "button" + i + "_" + j;

                    String buttonId = buttons[i][j];
                    int resID = getResources().getIdentifier(buttonId, "id", getPackageName());

                    Button button = findViewById(resID);
                    switch (game.board[i][j]) {
                        case BLANK:
                            button.setText(" ");
                            break;
                        case CIRCLE:
                            button.setText("O");
                            break;
                        case CROSS:
                            button.setText("X");
                            break;
                    }
                }
            }

        }

    }

    public void tileClicked (View view) {
        int id = view.getId();

        boolean flag = false;


        int row = 0;
        int column = 0;

        for (int i=0; i<game.BOARD_SIZE; i++){
            if (flag) {
                break;
            }
            for (int j=0; j<game.BOARD_SIZE; j++) {
                String buttonId = buttons[i][j];

                // get id with corresponding id name from array
                int resID = getResources().getIdentifier(buttonId, "id", getPackageName());

                    if (resID == id) {
                    row = i;
                    column = j;
                    flag = true;
                    break;
                }
            }
        }
        TileState state = game.choose(row, column);

        //System.out.println(state);

        Button button = findViewById(id);

        switch(state) {
            case CROSS:
                button.setText("X");
                break;
            case CIRCLE:
                button.setText("O");
                break;
            case INVALID:
                break;
        }

        // disable buttons if game ends
        if(game.won() != GameState.IN_PROGRESS) {
            for (int i = 0; i < game.BOARD_SIZE; i++) {
                for (int j = 0; j < game.BOARD_SIZE; j++) {
                    String buttonId = buttons[i][j];

                    // remove text from each button
                    int resID = getResources().getIdentifier(buttonId, "id", getPackageName());
                    Button button_2 = findViewById(resID);
                    button_2.setEnabled(false);
                }
            }
        }



        if (game.won() == GameState.PLAYER_ONE) {
            int resID = getResources().getIdentifier("end_text", "id", getPackageName());
            TextView text = findViewById(resID);
            text.setText("Player ONE is the winner!");
            game = new Game();
        }
        else if (game.won() == GameState.PLAYER_TWO) {
            int resID = getResources().getIdentifier("end_text", "id", getPackageName());
            TextView text = findViewById(resID);
            text.setText("Player TWO is the winner!");
            System.out.println("PLAYER TWO IS WINNER");
            game = new Game();
        }
        else if (game.won() == GameState.DRAW) {
            int resID = getResources().getIdentifier("end_text", "id", getPackageName());
            TextView text = findViewById(resID);
            text.setText("Game is a draw!");
            System.out.println("GAME IS A DRAW");
            game = new Game();
        }
    }



    public void resetClicked (View view) {

        int resID = getResources().getIdentifier("end_text", "id", getPackageName());
        TextView text = findViewById(resID);
        text.setText("");


        for (int i = 0; i < game.BOARD_SIZE; i++) {
            for (int j = 0; j < game.BOARD_SIZE; j++) {
                String buttonId = buttons[i][j];

                // remove text from each button
                resID = getResources().getIdentifier(buttonId, "id", getPackageName());
                Button button = findViewById(resID);
                button.setText(" ");
                button.setEnabled(true);
            }
        }

        game = new Game();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("game", game);

    }
}
