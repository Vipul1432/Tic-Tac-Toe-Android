package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    TextView headerText;

    int player_o = 0;
    int player_x = 1;
    int activePlayer = player_o;
    int[] filledPos = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    boolean isGameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerText = findViewById(R.id.headerText);
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

        if (!isGameActive)
            return;
        // Logic
        Button clickedBtn = findViewById(v.getId());
        int clickedTag = Integer.parseInt(v.getTag().toString());

        if (filledPos[clickedTag] != -1) {
            return;
        }
        filledPos[clickedTag] = activePlayer;

        if (activePlayer == player_o) {
            clickedBtn.setText("O");
            clickedBtn.setBackground(getDrawable(android.R.color.holo_red_dark));
            activePlayer = player_x;
            headerText.setText("X-Turn");
        } else {
            clickedBtn.setText("X");
            clickedBtn.setBackground(getDrawable(android.R.color.holo_green_dark));
            activePlayer = player_o;
            headerText.setText("O-Turn");
        }
        checkForWin();
    }

    private void checkForWin() {
        // Check for winner
        int[][] winPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int i = 0; i < 8; i++) {
            int val0 = winPos[i][0];
            int val1 = winPos[i][1];
            int val2 = winPos[i][2];

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {
                if (filledPos[val0] != -1) {
                    //Winner

                    isGameActive = false;
                    if (filledPos[val0] == player_o) {
                        showDialog("O is winner");
                    } else {
                        showDialog("X is winner");
                    }

                }
            }
        }
    }


    private void showDialog(String winnerText) {
        new AlertDialog.Builder(this).setTitle(winnerText).setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                restartGame();
            }
        })
                .show();
    }

    private void restartGame() {
        activePlayer = player_o;
        headerText.setText("O Turn");
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

        btn0.setBackground(getDrawable(android.R.color.holo_blue_light));
        btn1.setBackground(getDrawable(android.R.color.holo_blue_light));
        btn1.setBackground(getDrawable(android.R.color.holo_blue_light));
        btn3.setBackground(getDrawable(android.R.color.holo_blue_light));
        btn4.setBackground(getDrawable(android.R.color.holo_blue_light));
        btn5.setBackground(getDrawable(android.R.color.holo_blue_light));
        btn6.setBackground(getDrawable(android.R.color.holo_blue_light));
        btn7.setBackground(getDrawable(android.R.color.holo_blue_light));
        btn8.setBackground(getDrawable(android.R.color.holo_blue_light));
        isGameActive = true;
    }
}