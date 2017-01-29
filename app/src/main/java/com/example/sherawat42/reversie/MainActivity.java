package com.example.sherawat42.reversie;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout mainLayout;

    int NO_PLAYER=0;
    int PLAYER_1=0;
    int PLAYER_2=0;

    private int dimensionX=8;
    private int dimensionY=8;
    private int p1Score=0;
    private int p2Score=0;
    TextView p1ScoreTV;
    TextView p2ScoreTV;
    private static int[][] DIRECTIONS = new int[8][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout)findViewById(R.id.activity_main);
        mainLayout.setBackgroundColor(Color.CYAN);
        setDirections();
        setBoard();
    }

    private void setDirections() {

        DIRECTIONS[0][0] = -1;
        DIRECTIONS[1][0] = -1;
        DIRECTIONS[2][0] = -1;
        DIRECTIONS[3][0] = 0;
        DIRECTIONS[4][0] = 1;
        DIRECTIONS[5][0] = 1;
        DIRECTIONS[6][0] = 1;
        DIRECTIONS[7][0] = 0;

        DIRECTIONS[0][1] = -1;
        DIRECTIONS[1][1] = 0;
        DIRECTIONS[2][1] = 1;
        DIRECTIONS[3][1] = 1;
        DIRECTIONS[4][1] = 1;
        DIRECTIONS[5][1] = 0;
        DIRECTIONS[6][1] = -1;
        DIRECTIONS[7][1] = -1;


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

        for(int i=0;i<dimensionX;i++){
            for(int j=0;j<dimensionY;j++){
                gameBoard.getButton(i,j).setOnClickListener(this);
            }
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(false){

        }else if(false){

        }else{
            ReversieButton clickedButton = (ReversieButton)findViewById(id);
            if(moveAllowed(clickedButton)){

            }
        }
    }

    private boolean moveAllowed(ReversieButton clickedButton) {
        //First check the clicked button



        return false;
    }
}
