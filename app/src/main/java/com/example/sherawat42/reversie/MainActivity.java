package com.example.sherawat42.reversie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    LinearLayout mainLayout;
    private int dimensionX=8;
    private int dimensionY=8;
    private int p1Score=0;
    private int p2Score=0;
    TextView p1ScoreTV;
    TextView p2ScoreTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout)findViewById(R.id.activity_main);
        setBoard();
    }

    private void setBoard() {
        mainLayout.removeAllViews();

        LinearLayout.LayoutParams params;

        LinearLayout scoreBoard = new LinearLayout(this);
        scoreBoard.setOrientation(LinearLayout.HORIZONTAL);
        TextView textP1 = new TextView(this);
        textP1.setText("PLAYER1: ");
        TextView textP2 = new TextView(this);
        TextView space = new TextView(this);
        space.setText("      ");
        textP2.setText("PLAYER2: ");
        p1ScoreTV = new TextView(this);
        p1ScoreTV.setText("0");
        p2ScoreTV = new TextView(this);
        p2ScoreTV.setText("0");
        scoreBoard.addView(textP1);
        scoreBoard.addView(p1ScoreTV);
        scoreBoard.addView(space);
        scoreBoard.addView(textP2);
        scoreBoard.addView(p2ScoreTV);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        scoreBoard.setLayoutParams(params);
        mainLayout.addView(scoreBoard);

        ReversieBoard gameBoard = new ReversieBoard(this, dimensionX, dimensionY);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3);
        gameBoard.setLayoutParams(params);
        mainLayout.addView(gameBoard);
    }
}
