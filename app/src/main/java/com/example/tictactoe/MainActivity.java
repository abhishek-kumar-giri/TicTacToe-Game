package com.example.tictactoe;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    TextView header_text;
    int player_o = 0;
    int player_x = 1;
    int activePlayer = player_o;

    int[] filledPos = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    boolean isGameActive = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header_text = findViewById(R.id.header_text);
        header_text.setText("O Turn");


        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //logic for button press


        if (!isGameActive)
            return;


        Button clickedBtn = findViewById(v.getId());
        int clickedTag = Integer.parseInt(v.getTag().toString());
        if (filledPos[clickedTag] != -1) {
            return;

        }

        filledPos[clickedTag] = activePlayer;

        if (activePlayer == player_o) {
            clickedBtn.setText("O");
            clickedBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
            activePlayer = player_x;
            header_text.setText(R.string.X_turn);
        } else {
            clickedBtn.setText("X");
            clickedBtn.setBackgroundColor(getResources().getColor(R.color.teal_700));
            activePlayer = player_o;
            header_text.setText(R.string.O_Turn);

        }
        checkForWin();


    }

    private void checkForWin() {
        //we will check who is winner and show

        int[][] winningPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        for (int i = 0; i < 8; i++) {
            int val0 = winningPos[i][0];
            int val1 = winningPos[i][1];
            int val2 = winningPos[i][2];

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {
                if (filledPos[val0] != -1) {
                    //winner declared

                    isGameActive = false;


                    if (filledPos[val0] == player_o)
                        showDialog("O is Winner");
                    else
                        showDialog("X is Winner");
                }
            }
        }


                int count = 0;
                for (int i = 0; i < 9; i++) {
                    if (filledPos[i] != -1) {
                        count++;
                    }
                }
                if (count == 9) {
                    showDialog("Match is Draw");
                    restartGame();

                }

    }


    private void showDialog(String winnerText) {
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();

                    }
                })
                .show();
    }

    private void restartGame() {
        activePlayer = player_o;
        header_text.setText(R.string.o_turn);

        filledPos = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");


        btn0.setBackgroundColor(getResources().getColor(R.color.design_default_color_on_secondary));
        btn1.setBackgroundColor(getResources().getColor(R.color.design_default_color_on_secondary));
        btn2.setBackgroundColor(getResources().getColor(R.color.design_default_color_on_secondary));
        btn3.setBackgroundColor(getResources().getColor(R.color.design_default_color_on_secondary));
        btn4.setBackgroundColor(getResources().getColor(R.color.design_default_color_on_secondary));
        btn5.setBackgroundColor(getResources().getColor(R.color.design_default_color_on_secondary));
        btn6.setBackgroundColor(getResources().getColor(R.color.design_default_color_on_secondary));
        btn7.setBackgroundColor(getResources().getColor(R.color.design_default_color_on_secondary));
        btn8.setBackgroundColor(getResources().getColor(R.color.design_default_color_on_secondary));


        isGameActive = true;


    }


}

